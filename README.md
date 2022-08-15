# Employees
![HighLevelDesign-screenshots](https://user-images.githubusercontent.com/86013600/184606095-dd15fe66-6f66-4426-83a7-47b2534b51b3.png)


This is a  demo project to get employee contact details using an API call.

EmployeeListActivity is the first and the only activity in this Application.

It lists all the employees and their designations.
It also provides a search bar to search employees by their names.

On pressing the call button, the application opens default calling app with the respective employee's contact number entered in the dialler.
On pressing the message button, the application opens default messenger with the respective employee's contact number entered in the "To" field.

This project uses MVVM architecture.
When a requet is made to fetch data, first it is checked if the network is available.
If available then only try fetching data using network calls and save the data in SQLite database.

When network is not available, it tries to fetch data from the database.

Below is the high level **Block Diagram**:

![HighLevelDesign-Block diagram](https://user-images.githubusercontent.com/86013600/184604219-17f59c9e-7cb6-4058-8aa1-73b212c83ea2.png)

**Sequence diagram:**

![HighLevelDesign-Sequence Diagram](https://user-images.githubusercontent.com/86013600/184604382-fc88bd9b-40fd-4b05-8ea2-49e1e75328c0.png)
