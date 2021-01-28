# xy-inc project

## Table of contents
* [General info](#general-info)
* [Building](#building)
* [Running](#running)
* [Testing](#testing)

## General info
This project consists of three services, each of them
with a specific purpose:
- [create](https://github.com/caiomelo/xy-inc/tree/master/create)
  -> creates points of interest
- [list](https://github.com/caiomelo/xy-inc/tree/master/list)
  -> lists all points of interest
- [search](https://github.com/caiomelo/xy-inc/tree/master/search)
  -> searches points of interest near a given coordinate

## Building 
All three services need to go through the same building process
in order to work correctly. To do so, access the root folder of each service and run:
```
./gradlew clean build
```

## Running

To start a containerized version of a specific service, run:
```
docker-compose run -d <service name>
```

To start all services at once, run:
```
docker-compose run -d 
```

## Testing

### Creating a point of interest
Request:
```
POST localhost:8080/poi
``` 
Request body (all attributes are required):
```
{
  "name": <poi name>,
  "position": [<x value>, <y value>]
}
```

### Listing all points of interest
Request:
```
GET localhost:8081/poi
``` 

### Searching for all points of interest within the given distance from the given coordinate
Request:
```
GET localhost:8082/poi?x=<value>&y=<value>&maxDistance=<value>
``` 
