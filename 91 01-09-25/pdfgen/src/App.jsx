import { useState } from 'react'
import axios from 'axios'
import jsPDF from 'jspdf'
import './App.css'

function App() {
  const [pnr, setPnr] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)

  const generatePDF = (ticketData) => {
  const doc = new jsPDF('p', 'mm', [210, 297]); // A4 size

  // --- Header Section ---
  // doc.setFillColor(255,255,255); // deep blue
  doc.setFillColor(30,64,124);
  doc.rect(0, 0, 210, 32, 'F');
  
  doc.setFillColor(232, 76, 61); // red accent
  doc.rect(0, 32, 210, 5, 'F');
  
  // Put a placeholder for logo (replace with actual image if available)
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(255,255,255);
  // doc.setTextColor(30, 64, 124);
  doc.setFontSize(22);
  doc.text('INDIAN RAILWAYS', 15, 20, {align: 'left'});
  doc.setFontSize(18);
  doc.text('e-Ticket', 195, 20, {align: 'right'});
  
  // Ticket meta
  doc.setFontSize(12);
  doc.setTextColor(40,40,40);
  doc.text(`PNR: ${ticketData.pnr}`, 15, 45);
  doc.text(`Train: ${ticketData.trainDetails.trainNumber} - ${ticketData.trainDetails.trainName}`, 15, 55);

  // --- Journey Details Card ---
  doc.setFillColor(245, 245, 245); // light background
  doc.roundedRect(13, 63, 184, 50, 5, 5, 'F');
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(30, 64, 124);
  doc.setFontSize(14);
  doc.text('Journey Details', 18, 73);

  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0,0,0);
  doc.setFontSize(11);
  doc.text(`From: ${ticketData.trainDetails.fromStation}`, 18, 80);
  doc.text(`To: ${ticketData.trainDetails.toStation}`, 100, 80);
  doc.text(`Date: ${new Date(ticketData.trainDetails.journeyDate).toLocaleDateString()}`, 18, 88);
  doc.text(`Departure: ${ticketData.trainDetails.departureTime}`, 100, 88);
  doc.text(`Arrival: ${ticketData.trainDetails.arrivalTime}`, 18, 96);
  doc.text(`Class: ${ticketData.bookingDetails.class}`, 100, 96);

  // --- Passenger Details Table ---
  const passengerStartY = 120;
  doc.setFont('helvetica', 'bold');
  doc.setFontSize(13);
  doc.setTextColor(30, 64, 124);
  doc.text('Passenger Details', 18, passengerStartY);

  // Table header with gray background
  doc.setFillColor(220, 230, 245);
  doc.rect(13, passengerStartY + 2, 184, 8, 'F');
  const headers = ['Name', 'Age', 'Gender', 'Coach', 'Seat'];
  headers.forEach((header, i) => {
    doc.setTextColor(30,64,124);
    doc.setFontSize(11);
    doc.setFont('helvetica', 'bold');
    doc.text(header, 18 + i*35, passengerStartY + 8);
  });

  // Table rows
  doc.setFont('helvetica', 'normal');
  ticketData.passengers.forEach((passenger, idx) => {
    const y = passengerStartY + 14 + idx * 8;
    doc.setTextColor(0,0,0);
    doc.setFontSize(10);
    doc.text(passenger.name, 18, y);
    doc.text(passenger.age.toString(), 53, y);
    doc.text(passenger.gender, 88, y);
    doc.text(passenger.coachNumber, 123, y);
    doc.text(passenger.seatNumber, 158, y);
    doc.setDrawColor(220,220,220);
    doc.line(13, y + 2, 197, y + 2); // horizontal line
  });

  // --- Fare Details Card ---
  const fareY = passengerStartY + 20 + ticketData.passengers.length * 8;
  doc.setFillColor(245,245,245);
  doc.roundedRect(13, fareY, 184, 32, 5, 5, 'F');
  doc.setFontSize(13);
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(232,76,61); // red accent
  doc.text('Fare Details', 18, fareY + 10);
  doc.setFont('helvetica', 'normal');
  doc.setFontSize(11);
  doc.setTextColor(0,0,0);
  doc.text(`Base Fare: Rs. ${ticketData.fareDetails.baseFare}`, 18, fareY + 18);
  doc.text(`GST: Rs. ${ticketData.fareDetails.gst}`, 90, fareY + 18);
  doc.text(`Total Fare: Rs. ${ticketData.fareDetails.totalFare}`, 18, fareY + 26);

  // --- Footer ---
  doc.setTextColor(128,128,128);
  doc.setFont('helvetica', 'italic');
  doc.setFontSize(9);
  doc.text('This is a computer generated ticket and does not require signature.', 105, 292, { align: 'center' });

  // Both save and open PDF in new tab
  const pdfOutput = doc.output('blob');
  
  // Create URLs for both opening and downloading
  const pdfUrl = URL.createObjectURL(pdfOutput);
  
  // Create a hidden download link
  // const downloadLink = document.createElement('a');
  // downloadLink.href = pdfUrl;
  // downloadLink.download = `IR_Ticket_${ticketData.pnr}.pdf`; // Set filename
  // document.body.appendChild(downloadLink);
  
  // Trigger both download and open in new tab
  // downloadLink.click(); // This triggers the download
  window.open(pdfUrl, `_${ticketData.pnr}`); // This opens in new tab
  
  // Clean up
  // document.body.removeChild(downloadLink);
  setTimeout(() => URL.revokeObjectURL(pdfUrl), 1000); // Increased timeout to ensure download completes
};


  const handleSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)

    try {
      const response = await axios.get(`http://localhost:5000/api/ticket/${pnr}`)
      generatePDF(response.data)
    } catch (err) {
      setError(err.response?.data?.message || 'Error fetching ticket details')
      console.log(err)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <h1>Railway e-Ticket Generator</h1>
      <form onSubmit={handleSubmit}>
        <div className="input-group">
          <label htmlFor="pnr">Enter PNR Number:</label>
          <input
            type="text"
            id="pnr"
            value={pnr}
            onChange={(e) => setPnr(e.target.value)}
            placeholder="Enter 5-digit PNR number"
            pattern="[0-9]{5}"
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? 'Generating...' : 'Generate Ticket'}
        </button>
      </form>
      {error && <div className="error">{error}</div>}
    </div>
  )
}

export default App
