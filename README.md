APPLICATION FOR HOTEL MANAGER
***
This application gives different
possibilities from user's role:
* Admin:
1. CRUD operations with hotel
2. Manager registration
* Manger:
1. Change room configuration
2. Show free rooms
3. Show booking rooms from date
4. Canceled booking room with sending the letter
* Client:
1. Add hotel to favorites
2. Booking rooms
3. Estimate hotel
* Authorization/Registration/Reset Password

[![](D:\Project\JE_140_01\.img\SHEMA_BD.png "DATABASE SHEMA IN MY APPLICATION")]

* You need to start project:

  First, run:
    './mvnw clean install'

  Next:

    'docker-compose -f docker-compose.yml up -d --build'
* Open link : 
> http://localhost:8002/hotelWeb-app/login
* Use Swagger :
> http://localhost:8003/hotel-app/swagger-iu
