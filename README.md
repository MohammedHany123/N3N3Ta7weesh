# N3N3Ta7weesh – Terminal Version

A personal budgeting application developed by the Ne3Na3 team. This terminal-based Java application allows users to manage their finances efficiently through a command-line interface.

## Features

- **User Authentication**: Register and log in securely.
- **Income & Expense Tracking**: Add, view, and manage your financial records.
- **Data Persistence**: Utilizes Java serialization for storing user data.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.
- A terminal or command-line interface.

### Installation

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/MohammedHany123/N3N3Ta7weesh.git
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
Upon running the application:
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
