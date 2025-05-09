/*
Write a MongoDB aggregation query to List each restaurant along with the price 
of its most expensive food item.
- Display the restaurant name and the highest-priced food item offered by the restaurant.
- Sort by the restaurant ID.

Sample output:
--------------
[                                                                               
  {                                                                             
    _id: 'R101',                                                                
    maxPrice: 166,                                                              
    restaurant_name: 'Paradise Biryani'                                         
  },
  {                                                                             
    _id: 'R102',                                                                
    maxPrice: 492,                                                              
    restaurant_name: 'Chutneys'                                                 
  }, 
  {                                                                             
    _id: 'R103',                                                                
    maxPrice: 404,                                                              
    restaurant_name: 'Minerva Coffee Shop'                                      
  },                                                                            
  {                                                                             
    _id: 'R104',                                                                
    maxPrice: 431,                                                              
    restaurant_name: 'Shah Ghouse'                                              
  },
  {                                                                             
    _id: 'R106',                                                                
    maxPrice: 495, 
    restaurant_name: 'MTR'                                                      
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
    db.Restaurants.aggregate([
        {
            $lookup : {
                from : 'FoodItems',
                localField : 'restaurant_id',
                foreignField : 'restaurant_id',
                as : 'temp'
            }
        },
        {
            $unwind : '$temp'
        },
        {
            $group : {
                _id : '$restaurant_id',
                maxPrice : {$max : '$temp.price'},
                restaurant_name : {$first : '$name'}
            }
        },
        {
            $sort : {_id : 1}
        }
    ])
)
