# 📚 Library Management System

A console-based library management system built in Java for a first-year Computer Engineering project — written after only one month of learning Java, during a temporary displacement from home due to the conflict in the Middle East.

---

## ⚙️ Technical Details
- Built with **Java** using OOP principles
- `Library` class follows the **Singleton pattern** — only one instance exists throughout the entire program
- Data is separated across multiple classes: `Book`, `Member`, `Library`, `Date`, `Status`
- No data persistence — library resets when the program closes

---

## 👤 Roles
The system has two roles — **Admin** and **Member**.

---

## 🔧 Admin Features

### Books Management
- Add a new book with a **name and author** — if you leave the author unknown the system stores it as "Unknown"
- If you add a book with the **same name**, the system detects it and asks you if it's the same book (adds a copy) or a different book (adds a new entry)
- Edit a book's **name or author**
- Delete a book — if it has multiple copies you can choose to remove **one copy or all copies**
- View all books or **search** for a specific book

### Members Management
- Add a new member with a **name and birthday**
- Members must be **between 18 and 100 years old**
- Edit a member's **name or birthday**
- Delete a member — but only if they have **no borrowed books**
- View all members or **search** for a specific member

---

## 📖 Member Features
- **Login** using your member ID or browse as a **guest**
- As a guest you can only **view books** — borrowing and returning requires login
- Once logged in you stay logged in for your **entire session** until you press back
- **Borrow** a book by browsing all available books or searching for a specific one
- **Return** a book from your borrowed list
- View your **profile** showing your info and all books you currently have borrowed

---

## 🔍 Search System
- Search for books or members by **name or ID**
- Search is **case insensitive** — searching "harry" finds "Harry Potter" ✅
- Search works with **partial names** — searching "har" also finds "Harry Potter" ✅
- If your search returns multiple results you can **pick the one you want** from the list

---

## 📋 Book Details
- Every book has a unique **auto-incremented ID** starting from 1
- A book has a **name, author, ID, status and copies count**
- Book status is automatically managed:
    - **AVAILABLE** → has at least one copy available
    - **BORROWED** → all copies are currently borrowed
    - **UNAVAILABLE** → all copies of this book have been deleted by the admin
- A book can have **multiple copies** and multiple members can borrow it simultaneously

---

## 👥 Member Details
- Every member has a unique **auto-incremented ID** starting from **1001**
- A member has a **name, ID, age, birthday and borrowed books list**
- Age is **always accurate** — if you run the program just before midnight and a member's birthday starts at midnight, their age will correctly update the moment it is calculated

---

## ✅ Input Validation
- Letters instead of numbers → **rejected** ✅
- Numbers instead of a name → **rejected** ✅
- Out of range numbers → **rejected with a helpful message** ✅
- Blank/empty input → **rejected** ✅
- Invalid day for a given month → **impossible to enter** — the system uses the actual calendar to determine the max days allowed (e.g. February correctly allows 28 or 29 days depending on leap year) ✅

---

## 🚀 How to Run
1. Clone the repository
2. Open in IntelliJ (or any Java IDE)
3. Run `Main.java`

---

## 🌱 Learning Journey
This project was built after only one month of learning Java. Several concepts used in this project were self-taught and went beyond the course curriculum, including:

- **ArrayList** and its methods — for dynamically storing and managing books and members
- **Reusable validation methods** — instead of writing try-catch blocks repeatedly, I learned to wrap them into reusable methods like `getValidInput()` and `getValidName()` that are called throughout the program
- **LocalDate** class — for accurately calculating a member's age based on their birthday and the current date
- **YearMonth** class — for determining the correct number of days in a given month, making sure a date like February 30th is impossible to enter
