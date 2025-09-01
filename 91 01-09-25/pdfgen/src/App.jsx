import { useState, useEffect } from 'react'
import axios from 'axios'
import jsPDF from 'jspdf'
import QRCode from 'qrcode'
import './App.css'

function App() {
  const [pnr, setPnr] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const [generatedTickets, setGeneratedTickets] = useState([])

  // Function to generate QR code
  const generateQRCode = async (data) => {
    try {
      return await QRCode.toDataURL(JSON.stringify({
        pnr: data.pnr,
        train: `${data.trainDetails.trainNumber}`,
        date: data.trainDetails.journeyDate,
        passengers: data.passengers.length
      }))
    } catch (err) {
      console.error("QR Code generation error:", err)
      return null
    }
  }

  const generatePDF = async (ticketData) => {
    const doc = new jsPDF('p', 'mm', [210, 297]); // A4 size
    
    // Generate QR Code
    const qrCodeDataUrl = await generateQRCode(ticketData);

    // --- Header Section ---
    // Gradient-like header effect
    for (let i = 0; i < 32; i++) {
      doc.setFillColor(30 - i/2, 64 - i/2, 124 - i/2);
      doc.rect(0, i, 210, 1, 'F');
    }
    
    // Accent line
    doc.setFillColor(232, 76, 61);
    doc.rect(0, 32, 210, 3, 'F');
    
    // Header Content
    doc.setFont('helvetica', 'bold');
    doc.setTextColor(255, 255, 255);
    doc.setFontSize(24);
    doc.text('INDIAN RAILWAYS', 15, 20, {align: 'left'});
    
    // Right side header info
    doc.setFontSize(16);
    doc.text('e-Ticket', 195, 15, {align: 'right'});
    doc.setFontSize(10);
    doc.text('(Electronic Reservation Slip)', 195, 20, {align: 'right'});
    doc.text(`Generated on: ${new Date().toLocaleString()}`, 195, 25, {align: 'right'});
  
  // Ticket meta with modern box design
  doc.setDrawColor(220, 220, 220);
  doc.setFillColor(250, 250, 250);
  doc.roundedRect(13, 40, 184, 20, 3, 3, 'FD');
  
  doc.setFont('helvetica', 'bold');
  doc.setFontSize(14);
  doc.setTextColor(30, 64, 124);
  doc.text(`PNR: ${ticketData.pnr}`, 18, 52);
  
  doc.setFont('helvetica', 'bold');
  doc.text(`Train: ${ticketData.trainDetails.trainNumber}`, 90, 52);
  doc.setFont('helvetica', 'normal');
  doc.text(`${ticketData.trainDetails.trainName}`, 140, 52);

  // --- Journey Details Card with QR Code ---
  // Main card background
  doc.setFillColor(250, 250, 250);
  doc.setDrawColor(220, 220, 220);
  doc.roundedRect(13, 65, 184, 65, 3, 3, 'FD');
  
  // Section header
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(30, 64, 124);
  doc.setFontSize(14);
  doc.text('Journey Details', 18, 75);

  // Add QR Code
  if (qrCodeDataUrl) {
    doc.addImage(qrCodeDataUrl, 'PNG', 150, 70, 40, 40);
  }

  // Journey details in two columns with icons (using unicode)
  doc.setDrawColor(240, 240, 240);
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(80, 80, 80);
  doc.setFontSize(10);
  
  // Left column
  let yPos = 83;
  doc.text('From', 18, yPos);
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0, 0, 0);
  doc.text(ticketData.trainDetails.fromStation, 18, yPos + 5);
  
  yPos += 15;
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(80, 80, 80);
  doc.text('Journey Date', 18, yPos);
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0, 0, 0);
  doc.text(new Date(ticketData.trainDetails.journeyDate).toLocaleDateString('en-IN'), 18, yPos + 5);
  
  yPos += 15;
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(80, 80, 80);
  doc.text('Departure', 18, yPos);
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0, 0, 0);
  doc.text(ticketData.trainDetails.departureTime, 18, yPos + 5);

  // Right column
  yPos = 83;
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(80, 80, 80);
  doc.text('To', 80, yPos);
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0, 0, 0);
  doc.text(ticketData.trainDetails.toStation, 80, yPos + 5);
  
  yPos += 15;
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(80, 80, 80);
  doc.text('Class', 80, yPos);
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0, 0, 0);
  doc.text(ticketData.bookingDetails.class, 80, yPos + 5);
  
  yPos += 15;
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(80, 80, 80);
  doc.text('Arrival', 80, yPos);
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(0, 0, 0);
  doc.text(ticketData.trainDetails.arrivalTime, 80, yPos + 5);

  // --- Passenger Details Section ---
  const passengerStartY = 140;
  
  // Section Title with accent
  doc.setFillColor(30, 64, 124);
  doc.roundedRect(13, passengerStartY - 5, 184, 10, 2, 2, 'F');
  doc.setFont('helvetica', 'bold');
  doc.setFontSize(13);
  doc.setTextColor(255, 255, 255);
  doc.text('Passenger Details', 18, passengerStartY + 2);

  // Modern table design
  const headers = ['Name', 'Age', 'Gender', 'Coach', 'Seat'];
  const colWidths = [70, 25, 30, 30, 29];
  let currentX = 13;
  
  // Header row with gradient-like effect
  doc.setFillColor(240, 240, 240);
  doc.rect(13, passengerStartY + 7, 184, 10, 'F');
  
  headers.forEach((header, i) => {
    doc.setTextColor(30, 64, 124);
    doc.setFontSize(11);
    doc.setFont('helvetica', 'bold');
    doc.text(header, currentX + 5, passengerStartY + 14);
    currentX += colWidths[i];
  });

  // Calculate total height needed for passengers
  const rowHeight = 15; // Increased row height
  const headerHeight = 10;
  const totalTableHeight = headerHeight + (ticketData.passengers.length * rowHeight);
  
  // Table rows with alternating background
  doc.setFont('helvetica', 'normal');
  ticketData.passengers.forEach((passenger, idx) => {
    const y = passengerStartY + 25 + (idx * rowHeight); // Use consistent row height
    
    // Alternate row background
    if (idx % 2 === 0) {
      doc.setFillColor(250, 250, 250);
      doc.rect(13, y - 3, 184, rowHeight, 'F');
    }

    currentX = 13;
    doc.setTextColor(0, 0, 0);
    doc.setFontSize(10);
    
    // Name with status indicator
    doc.text(passenger.name, currentX + 5, y + 5);
    currentX += colWidths[0];
    
    // Age
    doc.text(passenger.age.toString(), currentX + 5, y + 5);
    currentX += colWidths[1];
    
    // Gender without icon
    doc.text(passenger.gender, currentX + 5, y + 5);
    currentX += colWidths[2];
    
    // Coach
    // doc.setFont('helvetica', 'bold');
    doc.text(passenger.coachNumber, currentX + 5, y + 5);
    currentX += colWidths[3];
    
    // Seat
    doc.text(passenger.seatNumber, currentX + 5, y + 5);
  });

  // Table border with correct height
  doc.setDrawColor(220, 220, 220);
  doc.rect(13, passengerStartY + 7, 184, totalTableHeight, 'D');

  // --- Fare Details Card ---
  const fareY = passengerStartY + 15 + totalTableHeight; // Adjust based on table height
  
  // Modern fare card with gradient background
  doc.setFillColor(250, 250, 250);
  doc.roundedRect(13, fareY, 184, 45, 3, 3, 'F');
  
  // Card header
  doc.setFillColor(30, 64, 124);
  doc.roundedRect(13, fareY, 184, 10, 3, 3, 'F');
  doc.setFont('helvetica', 'bold');
  doc.setFontSize(13);
  doc.setTextColor(255, 255, 255);
  doc.text('Fare Details', 18, fareY + 7);

  // Fare breakdown
  const startX = 25;
  doc.setFont('helvetica', 'normal');
  doc.setFontSize(11);
  doc.setTextColor(80, 80, 80);
  
  // Base fare with currency symbol
  doc.text('Base Fare:', startX, fareY + 20);
  doc.setTextColor(0, 0, 0);
  doc.setFont('helvetica', 'bold');
  doc.text(`Rs. ${ticketData.fareDetails.baseFare.toFixed(2)}`, startX + 50, fareY + 20);
  
  // GST
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(80, 80, 80);
  doc.text('GST (5%):', startX, fareY + 28);
  doc.setTextColor(0, 0, 0);
  doc.setFont('helvetica', 'bold');
  doc.text(`Rs. ${ticketData.fareDetails.gst.toFixed(2)}`, startX + 50, fareY + 28);
  
  // Total with highlight
  doc.setDrawColor(220, 220, 220);
  doc.line(startX - 5, fareY + 32, 185, fareY + 32);
  doc.setFont('helvetica', 'bold');
  doc.setTextColor(30, 64, 124);
  doc.text('Total Fare:', startX, fareY + 40);
  doc.setTextColor(232, 76, 61);
  doc.text(`Rs. ${ticketData.fareDetails.totalFare.toFixed(2)}`, startX + 50, fareY + 40);

  // --- Footer Section ---
  // Calculate footer position based on fare details
  const footerY = fareY + 60; // Add enough space after fare details
  
  // Important Notice
  doc.setFillColor(255, 245, 230);
  doc.roundedRect(13, footerY - 15, 184, 20, 2, 2, 'F');
  doc.setFont('helvetica', 'normal');
  doc.setTextColor(180, 95, 4);
  doc.setFontSize(9);
  doc.text('Important:', 18, footerY - 10);
  doc.text('1. This is a computer generated ticket and does not require signature.', 18, footerY - 5);
  doc.text('2. Please carry a valid photo ID proof during the journey.', 18, footerY);
  
  // Bottom footer
  doc.setTextColor(128, 128, 128);
  doc.setFontSize(8);
  doc.text('Generated by Indian Railways e-Ticketing System', 105, 290, { align: 'center' });
  doc.text(`Booking Time: ${new Date().toLocaleString()}`, 105, 294, { align: 'center' });

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
      <header className="header">
        <h1>Railway e-Ticket Generator</h1>
      </header>

      <div className="card">
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label htmlFor="pnr">
              PNR Number
            </label>
            <input
              type="text"
              id="pnr"
              value={pnr}
              onChange={(e) => setPnr(e.target.value)}
              placeholder="Enter 5-digit PNR number"
              pattern="[0-9]{5}"
              required
              className="pnr-input"
              maxLength="5"
            />
            <small className="hint">Enter your 5-digit PNR number to generate e-ticket</small>
          </div>
          
          <button 
            type="submit" 
            disabled={loading}
            className={`submit-button ${loading ? 'loading' : ''}`}
          >
            {loading ? (
              <>
                <span className="spinner">*</span>
                Generating Ticket...
              </>
            ) : (
              <>
                Generate Ticket
              </>
            )}
          </button>
        </form>

        {error && (
          <div className="error">
            <span className="icon">⚠️</span>
            {error}
          </div>
        )}

        {generatedTickets.length > 0 && (
          <div className="history">
            <h3>Recent Tickets</h3>
            <ul>
              {generatedTickets.map((ticket, index) => (
                <li key={index}>
                  PNR: {ticket.pnr} - {new Date(ticket.timestamp).toLocaleString()}
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>

      <footer className="footer">
        <p>© {new Date().getFullYear()} Indian Railways e-Ticketing System</p>
      </footer>
    </div>
  )
}

export default App
