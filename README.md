# Implement FilmTheater Website Using MicroService
This application is used to manage objects appear in the filmtheater such as: Ticket, Film, Room, Seat, Customer, Revenue, Order, ...  

Client-Server and MicroService are the main architectures of this application include of:  
- Client
- Server with 3 services:  
    - Person Service: Manage activities concerning to people such as: Customer, Employee, User, ...
    - Business Service (also BillService): Manage items and events related to business like: Bill, Film, Item, Order, ...
    - RoomSeatService: Manage room and seat in the film theater. 

Main Technology used: Spring Boot. 

## Design
### Entity model
![](images/image.png)

### ER model
![alt text](images/image-2.png)

### Module design
Sample of the feature: Statis the revenue by customers.
![alt text](images/image-1.png)