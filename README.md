# restaurant
Tables:

    Restaurants
        id (pk)
        name
    Meals
        id(pk)
        restaurant_id (fk)
        meal
        dateTime
        price
    Users
        id (pk)
        name
        email
        password
        enables
        registred
    Vote
        id(pk)
        user_id (fk)
        restaurant_id(fk)
        value
        dateTime
    Roles
        id(pk)
        user_id (fk)
        role

