APPLICATION FOR HOTEL MANAGER
***
This application gives different
possibilities from user's role. Tasks that must be done :
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

![SHEMA_BD](https://user-images.githubusercontent.com/79446450/141263813-063ce993-c30f-48de-a8b5-fcaef91a9e83.png)

* You need to start project:

  First, run:
    './mvnw clean install'

  Next:

    'docker-compose -f docker-compose.yml up -d --build'
* Open link : 
> http://localhost:8002/hotelWeb-app/login
* Use Swagger :
> http://localhost:8003/hotel-app/swagger-ui
