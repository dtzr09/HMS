# HMS

## Folder System

- controller: Contains all the logics and is the middle layer between display and model
- database: Responsible for loading and saving the values to a file
- model: Contains all the core entities in the system
- display: Contains all the interface level code
- resources: Contains our initial data load from a csv file
- utils: Contains all the utility functions for the entire system
- tests: Contains all the test files

## To Start:

- In terminal: `./run.sh`

## To generate javadocs

- In terminal: `javadoc -d docs $(find controller database display model utils  -name "*.java")`

## All functionalities

### Administrator

- [x] View all hospital staffs
- [x] Add new hospital staffs
- [x] Delete hospital staffs
- [x] View the appointment details of the doctors
- [x] View all medications
- [x] Delete medication
- [x] Add new medication
- [x] Increase medication stock
- [x] View pending medication replenishment request
- [x] Approve medication replenishment request
- [x] View profile
- [x] Update profile
- [x] Change password

### Patient

- [x] View medical record
- [x] View the available appointment slots for his/her doctor
- [x] Schedule appointment
- [x] Reschedule appointment
- [x] Cancel appointment
- [x] View scheduled appointments
- [x] View pending appointments
- [x] View past appointment outcome records
- [x] View profile
- [x] Update profile
- [x] Change password

### Doctor

- [x] View his/her patients
- [x] View his/her patients medical records
- [x] Update his/her patient medical record
- [x] Set availability for appointment slots
- [x] Manage appointment requests
- [x] View scheduled appointments
- [x] Record appointment outcome
- [x] View profile
- [x] Update profile
- [x] Change password

### Pharmacist

- [x] View all the appointment outcome records
- [x] Update prescription status for the appointments
- [x] View medication inventory
- [x] View low stock medication inventory
- [x] Submit request to replenish low stock medication 
- [x] View profile
- [x] Update profile
- [x] Change password
