/*
Write a MongoDB query to identify the top-performing delivery persons based on 
the number of completed deliveries.
- Display the delivery person's name and total number of deliveries.
- Sort by the number of deliveries in descending order.


Sample output:
--------------
[                                                                               
  {                                                                             
    _id: 'D102',                                                                
    deliveries: 21,                                                             
    delivery_person_name: 'Vasana Mutti'                                        
  },                                                                            
  {                                                                             
    _id: 'D101',                                                                
    deliveries: 15,                                                             
    delivery_person_name: 'Saumya Kumar'                                        
  },                                                                            
  {                                                                             
    _id: 'D105',                                                                
    deliveries: 10,                                                              
    delivery_person_name: 'Upkaar Goyal'                                        
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
    db.DeliveryPersons.aggregate([
        {
            $lookup : {
                from : 'Orders',
                localField : 'delivery_person_id',
                foreignField : 'delivery_person_id',
                as : 'temp'
            }
        },
        {
            $unwind : '$temp'
        },
        {
            $group : {
                _id : '$delivery_person_id',
                deliveries : {$sum : 1},
                delivery_person_name : {$first : '$name'}
            }
        },
        {
            $sort : {deliveries : -1}
        }
    ])
)
