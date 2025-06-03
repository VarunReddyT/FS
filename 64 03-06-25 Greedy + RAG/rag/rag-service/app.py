from flask import Flask, request, jsonify
from flask_cors import CORS
from pymongo import MongoClient
from langchain_community.document_loaders import PyPDFLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain_community.vectorstores import FAISS
from langchain.chains import RetrievalQA
from langchain_google_genai import ChatGoogleGenerativeAI, GoogleGenerativeAIEmbeddings
import os
import tempfile
import google.generativeai as genai

app = Flask(__name__)
CORS(app)

# Configuration
MONGO_URI = 'mongodb://localhost:27017/'
GEMINI_API_KEY = os.getenv('GEMINI_API_KEY')
DB_NAME = 'pdf-chatbot'
PDF_COLLECTION = 'pdfs'
VECTOR_STORE_DIR = 'vector_store'

# Initialize MongoDB client
mongo_client = MongoClient(MONGO_URI)
db = mongo_client[DB_NAME]
pdf_collection = db[PDF_COLLECTION]

# Initialize Gemini with explicit API key configuration
genai.configure(api_key=GEMINI_API_KEY)
embeddings = GoogleGenerativeAIEmbeddings(
    model="models/embedding-001",
    google_api_key=GEMINI_API_KEY  # Explicitly pass the API key
)

# Global variable for vector store
vector_store = None

def initialize_vector_store():
    global vector_store
    if os.path.exists(VECTOR_STORE_DIR):
        vector_store = FAISS.load_local(VECTOR_STORE_DIR, embeddings, allow_dangerous_deserialization=True)
    else:
        vector_store = None

def process_pdf(pdf_path):
    # Load PDF
    loader = PyPDFLoader(pdf_path)
    pages = loader.load_and_split()
    
    # Split text
    text_splitter = RecursiveCharacterTextSplitter(
        chunk_size=1000,
        chunk_overlap=200
    )
    texts = text_splitter.split_documents(pages)
    
    # Create or update vector store
    global vector_store
    if vector_store is None:
        vector_store = FAISS.from_documents(texts, embeddings)
    else:
        vector_store.add_documents(texts)
    
    # Save vector store
    vector_store.save_local(VECTOR_STORE_DIR)

@app.route('/process_pdf', methods=['POST'])
def handle_process_pdf():
    if 'pdf' not in request.files:
        return jsonify({'error': 'No PDF file provided'}), 400
    
    pdf_file = request.files['pdf']
    if pdf_file.filename == '':
        return jsonify({'error': 'No selected file'}), 400
    
    # Save to temp file
    with tempfile.NamedTemporaryFile(delete=False, suffix='.pdf') as tmp:
        pdf_file.save(tmp.name)
        tmp_path = tmp.name
    
    try:
        process_pdf(tmp_path)
        # Save to MongoDB
        pdf_collection.insert_one({
            'filename': pdf_file.filename,
            'processed': True
        })
        return jsonify({'message': 'PDF processed successfully'}), 200
    except Exception as e:
        return jsonify({'error': str(e)}), 500
    finally:
        os.unlink(tmp_path)

@app.route('/ask', methods=['POST'])
def handle_ask():
    data = request.get_json()
    question = data.get('question')
    query = "Only use this context to give answer. Remove all the astersisks. Make it clean. Dont give your own answers"
    
    if not question:
        return jsonify({'error': 'No question provided'}), 400
    
    if vector_store is None:
        return jsonify({'error': 'No documents processed yet'}), 400
    
    # Create QA chain with Gemini
    llm = ChatGoogleGenerativeAI(
        model="gemini-1.5-flash",
        temperature=0.7,
        google_api_key=GEMINI_API_KEY  # Explicitly pass the API key
    )
    
    qa = RetrievalQA.from_chain_type(
        llm=llm,
        chain_type="stuff",
        retriever=vector_store.as_retriever(),
    )
    
    result = qa.run(query + " " + question)
    
    return jsonify({
        'answer': result
    })

if __name__ == '__main__':
    initialize_vector_store()
    app.run(port=5001, debug=True)