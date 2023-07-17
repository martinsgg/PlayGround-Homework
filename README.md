# PlayGround-Homework

## Usage examples
### http://localhost:8080/playground (POST) to create PlayGround body:
```
{
   "name":"Ķīpsalas parks",
   "id":0,
   "attractions":[
      {
         "slots":2,
         "attractionName":"DoubleSwing"
      },
      {
         "slots":2,
         "attractionName":"DoubleSwing"
      },
      {
         "slots":50,
         "attractionName":"Carousel"
      },
      {
         "slots":15,
         "attractionName":"Slide"
      },
      {
         "slots":120,
         "attractionName":"BallPit"
      }
   ]
}
```

### To receive just created playground. (GET)
```
http://localhost:8080/playground/1 
```

### To add new guest to playground. (POST)
```
http://localhost:8080/playground/{playground_id}
{"name":"Jānis","age":5}
```
Provide id(ticket number) or the id will be assigned automatically.
If the playground is full the guest will be added to the queue and moved to the playground once there is a free space.

### Remove guest from playground. (DELETE) 
```
http://localhost:8080/playground/{playground_id}/guest/{guest_id}
```

### To receive all guest count who are using playground equipment.
```
http://localhost:8080/playground/all-visitor-count
```

