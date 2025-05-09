/*
Write a MongoDB aggregation query to Identify users who have ordered food from 
at least 3 different restaurants.
- Display user names, the number of distinct restaurants they ordered from, and 
  total number of orders placed.
- Sort by the highest number of restaurants first, followed by the total number 
  of orders


Sample output:
--------------
[
  {
    _id: 'U108',
    totalOrders: 10,
    restaurantCount: 5,
    user_name: 'Tejas Sura'
  },
  {
    _id: 'U106',
    totalOrders: 8,
    restaurantCount: 4,
    user_name: 'Kritika Keer'
  },
  {
    _id: 'U104',
    totalOrders: 4,
    restaurantCount: 4,
    user_name: 'Libni Sekhon'
  },
  {
    _id: 'U109',
    totalOrders: 5,
    restaurantCount: 3,
    user_name: 'Darsh Anne'
  },
  {
    _id: 'U110',
    totalOrders: 3,
    restaurantCount: 3,
    user_name: 'Isaiah Ramakrishnan'
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
    db.Users.aggregate([
        {
            $lookup : {
                from : 'Orders',
                localField : 'user_id',
                foreignField : 'user_id',
                as : 'temp'
            }
        },
        {
            $unwind : '$temp'
        },
        {
            $group : {
                _id : '$user_id',
                totalOrders : {$sum : 1},
                sett : {$addToSet : '$temp.restaurant_id'},
                user_name : {$first : '$name'},
            }
        },
        {
            $addFields : {
                restaurantCount : {$size : '$sett'}
            }
        },
        {
            $match : {restaurantCount : {$gte : 3}}
        },
        {
            $project : {_id : 1, totalOrders : 1, restaurantCount : 1, user_name : '$user_name'}
        },
        {
            $sort : {restaurantCount : -1, totalOrders : -1}
        }
    ])
)
