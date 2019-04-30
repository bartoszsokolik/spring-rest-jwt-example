## About
Simple REST application with Json Web Token (JWT) authentication and H2 Datbase.
Available endpoints:

| Endpoint       | Request Method |                Purpose                |
|----------------|:--------------:|---------------------------------------|
|/users/register |       POST     | Register new user                     |
|/users/login    |       POST     | Login into app                        |
|/resource/all   |       GET      | Get content for all user type         |
|/resource/admin |       GET      | Get content for user with admin role  |
|/resource/user  |       GET      | Get content for user with user role   |
|/resource/tester|       GET      | Get content for user with tester role |

## Usage
To start application with H2 database run in terminal `./gradlew bootrun`

To run application with postgres db and docker build an application with `./gradlew clean build` command and then with `docker-compose up`

To register user curl a POST request:

```
curl -v -X POST -H 'Content-Type: application/json' http://localhost:8080/users/register -d '{"username" : "user", "password" : "password", "email" : "test@test.com", "role" : "ROLE_USER"}'
```

Antoher available roles : `ROLE_ADMIN` and `ROLE_TESTER`

To login and get JWT token use curl POST request:
```
curl -v -X POST -H 'Content-Type:application/json' http://localhost:8080/users/login -d '{"username" : "user", "password" : "password"}'`
```

The response should look like this:
```
{
   "token" : "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTUzODU1MDE1fQ.MxA0d7Lqe1lGoMDmP3gx5JRXl8INtq7KMyFgiQF47oY"
}
```

Then with returned JWT token you can use cur GET request for appropriate endpoint, i.e.
```
curl -v -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNTUzODU1MDE1fQ.MxA0d7Lqe1lGoMDmP3gx5JRXl8INtq7KMyFgiQF47oY' http://localhost:8080/resource/user`
```