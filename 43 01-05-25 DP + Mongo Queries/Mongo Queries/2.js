/*
Write a MongoDB aggregation query to display each patient's total billed 
amount across all their visits.

Sample output:
--------------
[
  { totalBilledAmount: 200, patientId: 'PT005', name: 'Patient 5' },
  { totalBilledAmount: 200, patientId: 'PT006', name: 'Patient 6' },
  { totalBilledAmount: 200, patientId: 'PT001', name: 'Patient 1' }
]

Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
	
printjson(
    db.patients.aggregate([
        {
            
            $lookup : {
                from : "billing",
                localField : "patientId",
                foreignField : "patientId",
                as : "bills"
            }
        },
        {
          $unwind : "$bills"  
        },
        {
            $group : {
                _id : "$patientId", totalBilledAmount : {$sum : "$bills.totalAmount"}, name : {$first : "$name"}
            }
        },
        {
            $sort : {name : 1}
        },
        {   $project : {
                _id : 0, totalBilledAmount : 1, patientId :"$_id",name : "$name" 
            }
        }
    ]
)
)

