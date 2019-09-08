## Distributed Computing
##### CSE3122Y Semester 1 Distributed Computing TCP Assignment
This is a **basic** java implementation of a TCP client/server application for CSE3122Y at UoM. The client is built using javaFX and the server uses TCP sockets to communitcate with the client. All data is written to the `GymData.dat` file and the core logic is handled by the `Controller.java` class.

#### Team Members
* Ashray Bhagbut 
* Kaushik Nihal Shyam 
* Veegish Ramdani 

#### Structure
```markdown
├── src
│   ├── GymData.dat
│   └── IntegriFitGym
│       ├── Controller.java [Core Logic]
│       ├── GymClient.java [JavaFX Client]
│       ├── GymData.java [Data Model]
│       ├── GymServer.java [TCP Server]
│       ├── Main.java
│       └── fxmlUserInterfaces [JavaFX UI]
│           ├── Create.fxml
│           ├── DeleteAccount.fxml
│           ├── FetchData.fxml
│           ├── Gym.jpg
│           ├── MainMenu.fxml
│           ├── UpdateAccount.fxml
│           └── style.css
```

![IntegriFitScreenshot](IntegriFitScreenshot.png)