## Example CRUD app

```
User Model:
{
    "key": {
        "id": 41823,
        "version": 4
    },
    "firstName": "first",
    "middleName": "mid",
    "lastName": "last",
    "email": "name@email.com",
    "age": 12,
    "numOfVersions": 5
}
```

POST localhost:8080/api/users/ w/ User in JSON creates

PUT localhost:8080/api/users/ w/ User in JSON updates

DELETE localhost:8080/api/users/{idOfUser} deletes
all versions of that user

GET localhost:8080/api/users/{idOfUser} gets
all versions of that user

GET localhost:8080/api/users/{idOfUser}/{version} gets
specific version of that user

 