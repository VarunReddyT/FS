from flask import Flask, jsonify, request

app = Flask(__name__)

# Static medical bills data
bills = [
    {"id": 1, "patient_name": "John Doe", "amount": 500, "status": "Paid"},
    {"id": 2, "patient_name": "Jane Smith", "amount": 300, "status": "Unpaid"},
]

# Get all bills
@app.route('/bills', methods=['GET'])
def get_bills():
    return jsonify(bills)

# Get bill by ID
@app.route('/bills/<int:bill_id>', methods=['GET'])
def get_bill(bill_id):
    bill = next((bill for bill in bills if bill["id"] == bill_id), None)
    return jsonify(bill) if bill else ("Bill not found", 404)

# Add a new bill
@app.route('/bills', methods=['POST'])
def add_bill():
    new_bill = request.json
    bills.append(new_bill)
    return jsonify(new_bill), 201

# Update an existing bill
@app.route('/bills/<int:bill_id>', methods=['PUT'])
def update_bill(bill_id):
    bill = next((bill for bill in bills if bill["id"] == bill_id), None)
    if bill:
        bill.update(request.json)
        return jsonify(bill)
    return ("Bill not found", 404)

# Delete a bill
@app.route('/bills/<int:bill_id>', methods=['DELETE'])
def delete_bill(bill_id):
    global bills
    bills = [bill for bill in bills if bill["id"] != bill_id]
    return ('', 204)

# New "Hello App" route
@app.route('/hello', methods=['GET'])
def hello_app():
    return "Hello App"

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=80)
