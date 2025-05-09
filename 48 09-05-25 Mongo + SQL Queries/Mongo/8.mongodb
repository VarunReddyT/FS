/*
Write a MongoDB aggregation query to count and categorize all food items as 
Vegetarian or Non-Vegetarian.
- Display category labels (Vegetarian or Non-Vegetarian) with the count of food 
  items in each.

Sample output:
--------------
[
  { count: 5, type: 'Non-Vegetarian' },
  { count: 10, type: 'Vegetarian' }
]

Query Reference:
-------------------
printjson() : JS library function to display the JSON Object data.
    In db.<collection>.find()/aggregate():
    	db -> Refers to the database.
    	<collection> -> Your collection name
	
*/
	
printjson(
    db.FoodItems.aggregate([
        {
            $addFields : {
                type : {$cond : {if:'$is_veg' , then : 'Vegetarian', else : 'Non-Vegetarian'}}
            }
        },
        {
            $group : {
                _id : '$type',
                count : {$sum : 1}
            }
        },
        {
            $project : {_id : 0, count : 1, type : '$_id'}
        }
    ])
)
