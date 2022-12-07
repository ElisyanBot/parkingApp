


## API docs
---

| METHOD | PATH | COMMENT |
|:-----:|-----|------|
||||
|| **USERS** ||
||||
| **GET** | /users | get all current users objects |
| **GET** | /users/{id} | get one user objects |
| **GET** | /users/{id}/cars | get all cars from a user |
| **GET** | /users/{id}/cars/{id} | get one car from a user |
| **POST** | /users | add one user to database: { firstName: string, lastName: String } |
| **POST** | /users/{id}/cars | add one car to a selected user: { licensePlateNr: int } |
||||
||  **CARS** | |
||||
| **GET** | /cars | get all current cars in database |
| **GET** | /cars/{id} | get one car from database |
| **POST**|/cars | add one car to database: { licensePlateNr: string, userId: int } |
||||
||  **PARKING SLOTS** | |
||||
| **GET** | /parkingslots | get all current parking slots in database |
| **GET** | /parkingslots/{id} | get one parking slot from database |
| **POST** | /parkingslots | add one parking slot: { address: string, areaX: float, areaY: float zoneId: int } |
| **PATCH** | /parkingslots/{id} | updates parking slot, params: slotAvailable: boolean|
||||
|| **PARKING ZONES** ||
||||
| **GET** | /parkingzones | get all current parking lots in database |
| **GET** | /parkingzones/{id} | get one parking lot from database |
| **GET** | /parkingzones/{id}/parkingslots | get all parking slots from a Zone |
| **POST** | /parkingzones | add one parking lot: { pricePerMin: int } |
| **POST** | /parkingzones/{id}/parkingslots | add one Slot to a selected Zone: { address: int } |
| **PATCH** | /parkingzones/{id}/parkingslots/{id} | updates parking slot, params: slotAvailable: boolean|
||||
|| **PARKING EVENTS** ||
||||
| **GET** | /parkingevents | get all parking events } |
| **POST** | /parkingevents | add a parking event: { userId: int, carId: int, parkingSlotId: int } |
| **PATCH** | /parkingevents/{id} | updates ongoing parking on current event, params: ongoing: boolean |
