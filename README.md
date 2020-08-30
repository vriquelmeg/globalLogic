# Steps to run the application

# Install 

Open a console and run the following command

```
./gradlew build
```

After finishing the compilation the generated .jar file will be executed

```
java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
```

# Execute Test

The application in which the Postman functionalities were tested in which the following URL must be entered

```
http://localhost:8080/login/auth
```

Enter the following json test

```
{
"email":"juanperez@gmail.com",
"password":"Juan123"
} 
```

If the user is registered, it will show a json with the data entered

Otherwise, if the user is not registered, the following URL must be entered

```
http://localhost:8080/user/save
```

Enter the following json test

```
{
"name":"Juan Perez",
"email":"juanperez@gmail.com",
"password":"Juan123",
"phonesList":[
				{	
					"phoneNumber":"29186645",
					"countryCode":"56",
					"cityCode":"2"
				},
				{
					"phoneNumber":"28873632",
					"countryCode":"56",
					"cityCode":"2"
				}
			 ]
}
```

