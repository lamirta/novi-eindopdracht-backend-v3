# Graduation Project
During my education at NOVI I developed a RESTful web service. I made a functional and technical design, wrote the front- and backend code including necessary documentation with the APIs / endpoints. I just graduated and therefore completed the program.

## Introduction
This repository contains the backend code for the application 'Flash Word', which was created by me from scratch as a graduation project. As this application is part of a full stack software development education, it requires running the frontend code to get the platform functional. The frontend project can be found here: https://github.com/lamirta/novi-eindopdracht-frontend-v1. This application uses a database, running with PostgreSQL. 

The backend is created in Spring Boot, using Java. 

## Instructions
Run the backend code on http://localhost:8080. Make sure to have PostgreSQL open serving as a database. Feel free to use another program for the database, but make sure to adjust the settings in the file `application.properties`

Run the frontend code on localhost:3000.

## REST-endpoint documentation
These are the endpoints that are available in the backend code. The backend code is running on http://localhost:8080 , so make sure to check this. Copy+Paste the reuqest path to access the endpoint. 

### Authentication
#### POST `/authenticate`
Endpoint for user login. UserDetail authentication is used. The body is a JSON object with String username and String password. After correct verification, a JWT token is returned (token is valid for 24 hours).

```javascript
{
  username: "TestDocent",
  password: "password"
}
```

#### GET `/authenticated`
Endpoint to check if user is logged in. Returns a JSON object containing information about user, such as username, password (encrypted) and authorities/roles.


### User
#### GET `/users/{username}`
Endpoint for requesting a specific 'user' from the database, based on the unique id, in this case the username. This is passed as a parameter in the uri.

#### GET `/users`
Endpoint for requesting all 'users' from the database.

#### POST `/users`
Endpoint for creating a new user. This endpoint expects a body (in JSON format) with the necessary information to create a new user object - that is, all required fields are filled in correctly.
*When creating a new user, a user profile is automatically created and linked.

```javascript
{
  username: "johndoe",
  password: "password",
  email: "johndoe@test.nl",
  enabled: true,
  authorities:
          [{
            username: "johndoe",
            authority: "STUDENT"
          }]
}
```

#### PUT `/users/{username}`
Endpoint for modifying a specific 'user' from the database, based on the unique id, in this case the username. This should be included as a parameter in the uri. Furthermore, a body is provided (in JSON format) with the necessary information to update the user object - that is, all required fields are correctly filled in / modified.
*The username itself cannot be changed, because this concerns the unique id. The enabled and authority fields are modified via other request.

```javascript
{
  password: "password",
  email: "johndoe@test.nl"
}
```

#### PUT `/users/{username}/enabled`
Endpoint for modifying the 'enabled' field of a specific 'user' from the database, based on the unique id, in this case the username. This endpoint can only edit said field and this can only be done with the Authority 'TEACHER'.
*This is still being implemented: A user with the field 'enabled' set to false will not be able to log in and use the application.

```javascript
{
  enabled: false
}
```

#### GET `/users/{username}/authorities`
Endpoint for requesting the Authorities of a specific 'user' from the database, based on the unique id, in this case the username. This is passed as a parameter in the uri request.

#### POST `/users/{username}/authorities`
Endpoint for adding an 'authority' to a specific 'user' from the database, based on the unique id, in this case the username. This is passed as a parameter in the uri request. An 'authority' object must be included in the body (in JSON format).

```javascript
{
  username: "johndoe",
  authority: "TEACHER"
}
```

#### DELETE `/users/{username}/authorities/{authority}`
Endpoint for removing an 'authority' of a specific 'user' from the database, based on the unique id, in this case the username. The latter is passed as a parameter in the uri request, also as a parameter of the 'authority' that needs to be removed.
*In the current frontend, the old authority is removed and a new one added.


### UserProfile
#### GET `/userprofiles`
Endpoint for retrieving all user profiles from the database.

#### GET `/userprofiles/{id}`
Endpoint for requesting a specific 'user profiles' from the database, based on the unique id. This is passed as a parameter in the uri request.

#### POST `/userprofiles`
Endpoint for creating a new user profile. This endpoint expects a body (in JSON format) with the necessary information to create a new user profile object - that is, all required fields are filled in correctly.
*In the current frontend, this endpoint is not used, because a profile is automatically created with a new 'user'.

```javascript
{
  firstName: "Willem",
  lastName: "Willemsen",
  age: 1,
  school: "School"
}
```

#### PUT `/userprofiles/{id}`
Endpoint for adjusting a specific 'user profile' from the database, based on the unique id. This should be included as a parameter in the uri. Furthermore, a body is provided (in JSON format) with the necessary information to update the user profile object - that is, all required fields are correctly filled in / modified.

#### DELETE `/userprofiles/{id}`
Endpoint for removing a specific user profile from the database, based on the unique identifier.
*This endpoint also immediately removes the associated 'user'.

#### PUT `/userprofiles/{id}/image`
Endpoint for uploading and linking an 'image' to a 'user profile' based on the unique id. The 'user profile' id is given as a parameter and the 'image' id (fileName) is given in the body.
*It is the intention that an image is given here. Maximum size is 1MB.


### Image
#### POST `/upload`
Endpoint for uploading an 'image'. This endpoint expects a file that is given under the key 'file' (fileName). The intention is to provide an image. Maximum size is 1MB. *Upload also returns the URL for getting the 'image'. This can be used with the GET request below.

#### GET `/download/{fileName}`
Endpoint for requesting a specific 'image' from the database, based on the unique id, fileName in this case.

#### GET `/images`
Endpoint for querying all 'images' in the database.

#### DELETE `/images/{fileName}`
Endpoint for removing a specific 'image' from the database, based on the unique id, fileName in this case.


### WordList
#### GET `/wordlists`
Endpoint for retrieving all word lists from the database.

#### GET `/wordlists/{title}`
Endpoint for requesting a specific 'word list' from the database, based on the unique id, in this case the title.

#### POST `/wordlists/`
Endpoint for creating a new word list. This endpoint expects a body (in JSON format) with the necessary information to create a new word list object - that is, all required fields are filled in correctly.

```javascript
{
  title: "vormen",
  words: [
            "vierkant",
            "driehoek",
            "circle"
          ]
}
```

#### DELETE `/wordlists/{title}`
Endpoint for removing a specific word list from the database, based on its unique identifier, in this case the title.
*wordlists cannot be deleted if 'exams' have been created with them.

#### PUT `/wordlists/{title}`
Endpoint for modifying a specific 'word list' from the database, based on the unique id, in this case the title. This should be included as a parameter in the uri. Furthermore, a body is provided (in JSON format) with the necessary information to update the object - that is, all required fields are correctly filled in / modified.

```javascript
{
  title: "vormen",
  words: [
            "vierkant",
            "driehoek",
            "rechthoek", 
            "ovaal",
            "circle"
          ]
}
```


### Exam
#### GET `/exams`
Endpoint for retrieving all 'exams' from the database.

#### GET `/exams/{id}`
Endpoint for requesting a specific 'exam' from the database, based on the unique id.

#### POST `/exams/`
Endpoint for starting and thus creating a new 'exam'. This endpoint expects a body (in JSON format) with the necessary information to create a new exam object - that is, all required fields are filled in correctly.
*It is important that full wordList and userProfile objects are included. This is also arranged in the frontend. If both are left out, they can still be linked with the PUT requests below.

```javascript
{
  wrongEntries: 6,
  passed: true,
  wordList: {
              title: "vormen"
             }
  userProfile: {
              id: 1002
               }             
}
```

#### PUT `/exams/{id}/profileId`
Endpoint for linking a 'user profile' to an 'exam' based on the unique ID of both. The 'exam' id is passed as a parameter in the uri and the 'user profile' id is passed in the body via a JSON object, see example on the left

```javascript
{
  id: 1002  
}
```

#### PUT `/exams/{id}/title`
Endpoint for linking a 'wordlist' to an 'exam' based on the unique id of both. The 'exam' id is passed as a parameter in the uri and the 'wordlist' title is passed in the body via the JSON object.

```javascript
{
  title: "vormen"  
}
```

#### DELETE `/exams/{id}`
Endpoint for removing a specific 'exam' from the database, based on the unique identifier.

#### DELETE `/exams/userprofile/{id}`
Endpoint for removing all 'exams' from a 'user profile', based on the unique identifier of the 'user profile'.




