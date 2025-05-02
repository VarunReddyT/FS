/*
Write a MongoDB aggregation query to determine how frequently each medicine 
is prescribed. List medicines in descending order of their usage frequency.

Sample output:
--------------
[
  { prescriptionCount: 3, medicineName: 'Atorvastatin' },
  { prescriptionCount: 3, medicineName: 'Hydrocortisone' },
  { prescriptionCount: 3, medicineName: 'Iohexol' }
]

Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(db.medicines.aggregate([
  {
      $lookup : {
          from : "prescriptions",
          localField : "medicineId",
          foreignField : "medicines.medicineId",
          as : "prescriptions"
      }
      
  },
  {
      $addFields : {
          prescriptionCount : {$size : "$prescriptions"}
      }
  },
  {
      $match : {
          prescriptionCount : {$gt : 0}
      }
  },
  {
      $sort : {
          name : 1
      }
  },
  {
      $project : {
          _id : 0,prescriptionCount : 1,medicineName :"$name"
      }
  }
  
  ])
  
)
