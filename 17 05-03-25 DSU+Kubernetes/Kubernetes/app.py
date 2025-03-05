from flask import Flask, jsonify, request
import logging

app = Flask(__name__)

LOG_FILE = '/logs/app.log'

# Configure logging to write logs to a file
logging.basicConfig(filename=LOG_FILE, level=logging.INFO, format='%(asctime)s - %(message)s')

# Static medical bills data
bills = [
    {"id": 1, "patient_name": "John Doe", "amount": 500, "status": "Paid"},
    {"id": 2, "patient_name": "Jane Smith", "amount": 300, "status": "Unpaid"},
]

# Get all bills
@app.route('/bills', methods=['GET'])
def get_bills():
    logging.info("GET /bills request received")
    return jsonify(bills)

# Get bill by ID
@app.route('/bills/<int:bill_id>', methods=['GET'])
def get_bill(bill_id):
    logging.info(f"GET /bills/{bill_id} request received")
    bill = next((bill for bill in bills if bill["id"] == bill_id), None)
    return jsonify(bill) if bill else ("Bill not found", 404)

# Add a new bill
@app.route('/bills', methods=['POST'])
def add_bill():
    new_bill = request.json
    bills.append(new_bill)
    logging.info(f"POST /bills request received: {new_bill}")
    return jsonify(new_bill), 201

# Update an existing bill
@app.route('/bills/<int:bill_id>', methods=['PUT'])
def update_bill(bill_id):
    bill = next((bill for bill in bills if bill["id"] == bill_id), None)
    if bill:
        bill.update(request.json)
        logging.info(f"PUT /bills/{bill_id} request received: {request.json}")
        return jsonify(bill)
    return ("Bill not found", 404)

# Delete a bill
@app.route('/bills/<int:bill_id>', methods=['DELETE'])
def delete_bill(bill_id):
    global bills
    bills = [bill for bill in bills if bill["id"] != bill_id]
    logging.info(f"DELETE /bills/{bill_id} request received")
    return ('', 204)

# New "Hello App" route
@app.route('/hello', methods=['GET'])
def hello_app():
    logging.info("GET /hello request received")
    return "Hello App"

# New route to get logs
@app.route('/logs', methods=['GET'])
def get_logs():
    try:
        with open(LOG_FILE, 'r') as f:
            logs = f.readlines()
        return jsonify({"logs": logs})
    except FileNotFoundError:
        return jsonify({"error": "Log file not found"}), 404

# New route to delete logs
@app.route('/logs', methods=['DELETE'])
def delete_logs():
    try:
        open(LOG_FILE, 'w').close()  # Clears the log file
        return jsonify({"message": "Logs cleared"}), 200
    except Exception as e:
        return jsonify({"error": f"Failed to clear logs: {str(e)}"}), 500
    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80)
