# ✈️ Airline Reservation System

A desktop-based Airline Reservation System developed using **Java Swing**, **JDBC**, and **MySQL**. It supports user registration, login, flight search, ticket booking, PDF ticket generation, and email confirmation.

## 🚀 Features

- User registration and login
- Flight search
- Ticket booking
- MySQL database connectivity
- PDF ticket generation using iText
- Email confirmation using JavaMail API
- DAO design pattern

## 🛠️ Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- iText PDF
- JavaMail API

## 📂 Project Structure

```text
AirLineReservation/
├── src/
│   └── com/
│       └── airline/
│           ├── Main/
│           ├── connection/
│           ├── dao/
│           ├── gui/
│           ├── model/
│           └── utils/
├── .classpath
├── .project
└── README.md
```

## ⚙️ How to Run

1. Clone the repository:

```bash
git clone https://github.com/Guna-sekhar7/Airline-Reservation-System.git
```

2. Open the project in VS Code or Eclipse.

3. Create the required MySQL database and tables.

4. Update the database details in:

```text
src/com/airline/connection/DBConnection.java
```

5. Add the required libraries:

- MySQL Connector/J
- iText PDF
- JavaMail API

6. Run:

```text
src/com/airline/Main/Main.java
```

## 📸 Project Preview

Add your application screenshots inside a folder named `screenshots`.

```markdown
![Login Page](screenshots/login.png)

![Registration Page](screenshots/register.png)

![Booking Page](screenshots/booking.png)

![Generated Ticket](screenshots/ticket.png)
```

## 🔮 Future Enhancements

- Seat selection
- Admin dashboard
- Ticket cancellation
- Online payment integration
- Real-time flight status

## 👨‍💻 Author

**Guna Sekhar**

⭐ Star the repository if you found this project useful.
