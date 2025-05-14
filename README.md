
# N3N3Ta7weesh – GUI Version

An enhanced version of the N3N3Ta7weesh budgeting application featuring a user-friendly Java Swing graphical interface.

## Features

- **Graphical User Interface**: Intuitive design using Java Swing.
- **User Authentication**: Register and log in with OTP verification.
- **Income & Expense Management**: Add, view, and manage financial records through the dashboard.
- **Data Persistence**: Maintains user data using Java serialization.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- An IDE like IntelliJ IDEA or Eclipse (optional but recommended).

### Installation

1. **Clone the Repository**:

   ```bash
   git clone -b gui https://github.com/MohammedHany123/N3N3Ta7weesh.git
   cd N3N3Ta7weesh
   ```

2. **Compile the Application**:
    ```bash
    javac -d bin src/**/*.java
    ```

3. **Run the Application**:
   ```bash
   java -cp bin Main
   ```

## Usage
Upon running the application
- **Register:** Create a new account by providing your email, username, password, and phone number.
- **Login:** Access your account using your credentials.
- **Dashboard:** Navigate through options to add income, add expenses, view records, or logout.

## Project Structure

```css
N3N3Ta7weesh/
├── src/
│   ├── model/
│   ├── repository/
│   ├── service/
│   ├── ui/
│   └── Main.java
├── bin/
├── doc/
├── .vscode/
└── README.md
```

## Authors
- [Mohammed Hany](https://github.com/MohammedHany123)
- [Ahmed Samir](https://github.com/Ahmed-Samir11)
- [Loay Medhat](https://github.com/LoayMedhat)

## License
This project is licensed under the MIT License.
