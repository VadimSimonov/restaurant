# restaurant
Создайте и внедрите REST API, используя Hibernate / Spring / SpringMVC (или Spring-Boot).

Задача:

Создайте систему голосования, чтобы решить, где взять обед.

     2 типа пользователей: администратор и постоянные пользователи
     Администратор может ввести ресторан и это обеденное меню дня (обычно 2-5 предметов, только название и цена)
     Меню меняется каждый день (администраторы делают обновления)
     Пользователи могут проголосовать, в каком ресторане они хотят пообедать в
     Только один голос подсчитывается для каждого пользователя
     Если пользователь снова проголосовал в тот же день:
         Если до 11:00 мы предполагаем, что он передумал.
         Если это после 11:00, то уже слишком поздно, голосование не может быть изменено

Каждый ресторан предлагает новое меню каждый день.

Tables:

    Restaurants
        id (pk)
        name
        
    Meals
        id(pk)
        restaurant_id (fk)
        meal
        price
        
    Menu
        id (pk)
        date
        
    Menu_day
        menu_id (fk)
        restauran_id (fk)
        
    Users
        id (pk)
        role_id(fk)
        name
        email
        password
        enabled
        registred
        
    Vote_restaurant
        vote_id
        restaurant_id
        
    Vote
        id(pk)
        user_id (fk)
        restaurant_id(fk)
        dateTime
        vote
        
    User_roles
        id(pk)
        role




### User access
#### get All Menu
curl -v --noproxy localhost, -s http://localhost:8080/restuser/admin/menu --user user@yandex.ru:password
#### get All Meals by Restaurant 100060
curl -v --noproxy localhost, -s http://localhost:8080/restuser/admin/restaurants/100060/meals --user user@yandex.ru:password
#### plus Vote
curl -v --noproxy localhost, -s -X POST -d '{"vote":"1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restuser/admin/menu/100060/user/100040 --user user@yandex.ru:password
#### minus Vote
curl -v --noproxy localhost, -s -X POST -d '{"vote":"-1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restuser/admin/menu/100060 --user user@yandex.ru:password


### Admin access
#### get All Users
curl -v --noproxy localhost, -s http://localhost:8080/rest/admin/users --user admin@gmail.com:admin
#### get User 100040
curl -v --noproxy localhost, -s http://localhost:8080/rest/admin/users/100040 --user admin@gmail.com:admin
#### create User
curl -v --noproxy localhost, -s -X POST -d '{"name": "test","email": "2@yandex.ru","password":"12345","role": {"id": null,"role": "ROLE_USER"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users --user admin@gmail.com:admin
#### update User 100040
curl -v --noproxy localhost, -s -X PUT -d '{"name": "test","email": "update@yandex.ru","password":"12345","role": {"id": null,"role": "ROLE_USER"}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/users/100040 --user admin@gmail.com:admin
#### delete User 100040
curl -v --noproxy localhost, -s -X DELETE http://localhost:8080/rest/admin/users/100040 --user admin@gmail.com:admin


#### get All Restaurants
curl -v --noproxy localhost, -s http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin
#### get Restaurant 100060
curl -v --noproxy localhost, -s http://localhost:8080/rest/admin/restaurants/100060 --user admin@gmail.com:admin
#### create Restaurant
curl -v --noproxy localhost, -s -X POST -d '{"name": "NewRestaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user admin@gmail.com:admin
#### update Restaurant 100060
curl -v --noproxy localhost, -s -X PUT -d '{"name": "UpdateRestaurant"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100060 --user admin@gmail.com:admin
#### delete Restaurant 100060
curl -v --noproxy localhost, -s -X DELETE http://localhost:8080/rest/admin/restaurants/100060 --user admin@gmail.com:admin


#### get All Meals by Restaurant 100060
curl -v --noproxy localhost, -s http://localhost:8080/rest/admin/restaurants/100060/meals --user admin@gmail.com:admin
#### create Meals
curl -v --noproxy localhost, -s -X POST -d '{"meal": "NewMeal","price":"123"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100060/meals --user admin@gmail.com:admin
#### update Meals 100000 by Restaurant 100060
curl -v --noproxy localhost, -s -X PUT -d '{"meal": "UpdateMeal","price":"123"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100060/meals/100000 --user admin@gmail.com:admin
#### delete Meals 100060
curl -v --noproxy localhost, -s -X DELETE http://localhost:8080/rest/admin/restaurants/meals/100000 --user admin@gmail.com:admin

#### get All Menu
curl -v --noproxy localhost, -s http://localhost:8080/rest/admin/menu --user admin@gmail.com:admin
#### new Menu
curl -v --noproxy localhost, -s -X POST -d '["100060","100061"]' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/menu --user admin@gmail.com:admin
#### plus Vote
curl -v --noproxy localhost, -s -X POST -d '{"vote":"1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/menu/100060/user/100040 --user admin@gmail.com:admin
#### minus Vote
curl -v --noproxy localhost, -s -X POST -d '{"vote":"-1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/menu/100060 --user admin@gmail.com:admin
