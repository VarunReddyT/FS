/*
Write a MongoDB aggregation query to list all doctors along with their 
specialization(s) and count of total appointments each doctor has handled.

Sample output:
--------------
[
  {
    staffId: 'D001',
    name: 'Dr. Alice Smith',
    specialization: [ 'Cardiology' ],
    totalAppointments: 3
  },
  {
    staffId: 'D002',
    name: 'Dr. Bob Jones',
    specialization: [ 'Neurology' ],
    totalAppointments: 3
  },
  {
    staffId: 'D003',
    name: 'Dr. Carol White',
    specialization: [ 'Orthopedics' ],
    totalAppointments: 3
  }
]

Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.staff.aggregate([
        {
            $match : {role : "Doctor"}
        },
        {
            
            $lookup : {
                from : "appointments",
                localField : "staffId",
                foreignField : "doctorId",
                as : "appointments"
            }
        },
        {
            $addFields : {
                specialization : "$specializations",
                totalAppointments : {$size : "$appointments"}
            }
        },
        {   $project : {
                _id : 0, staffId : 1, name :1,specialization : 1, totalAppointments : 1 
            }
        }
    ]
)
)

