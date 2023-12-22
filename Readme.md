# Library Management System

This is a Java-based Library Management System application developed using Object-Oriented Programming (OOP) principles. It allows library staff (librarians) and library members (students) to interact with the library system. The application is designed to handle various operations related to managing library members, books, and transactions.

# Table of Contents

1. [Software Used](#software-used)
2. [Assumptions](#assumptions)
3. [Main Class (`Main.java`)](#main-class-mainjava)
4. [Book Class (`Book.java`)](#book-class-bookjava)
5. [Librarian Class (`Librarian.java`)](#librarian-class-librarianjava)
6. [Member Class (`Member.java`)](#member-class-memberjava)

## Softwares used
- I have made the project on IntelliJ idea version 2023.2.1
- The project build is of type Apache Maven




## Assumptions
- Phone number of every member will be unique.
- A member will get a fine only after returning some book whereas the librarian can see fines of everybody
- For name,age,phone number suitable data type should be inputed and not exceptions
- I haven't made any changes in the pom file as instructed by the TAs.
- 1 day = 1 second in the program 
- Members can borrow atmost 2 books at a time
- The code calculates fine for completed seconds (to nano-seconds).So sometimes on subtracting there can be a difference of 3 ruppees on fine. 


### Main Class (`Main.java`):

The `Main` class serves as the entry point. It contains the main method and handles user interactions through the command line. Here's what happens in this class:

- It initializes a `Scanner` object to take input from the user.

- It creates an instance of the `Librarian` class to manage library operations.

- It displays a menu-driven interface for users to choose between two roles: librarian or member.

- If the user chooses to log in as a librarian:
  - It displays a sub-menu for librarian-specific operations like registering members, removing members, adding books,removing books,viewing list of all books in the library and viewing the list of members with their issued books and fines.
  - It takes user input and performs the chosen operation.

- If the user chooses to log in as a member:
  - It takes the member's name and phone number as input.
  - It verifies the member's credentials and logs them in if they are registered.
  - It displays a sub-menu for member-specific operations like listing available books, listing borrowed books, issuing books, returning books, and paying fines.
  - It takes user input and performs the chosen member operation.

- The program keeps running until the user chooses to exit.

### Book Class (`Book.java`):

The `Book` class represents a book entity in the library system. It contains the following attributes and methods:

- Attributes:
  - `title`: Stores the title of the book.
  - `author`: Stores the author of the book.
  - `copies`: Stores the total number of copies of the book.
  - `book_id`: Unique identifier for each book.
  - `book_status`: Indicates whether the book is available for borrowing (1) or already issued (-1).
  - `due_date`: Stores the due date for the book, calculated based on the issue date.

- Methods:
  - `Fine_calculate(Book book)`: Calculates the fine for an overdue book based on the due date and the current date. It returns the fine amount.

### Librarian Class (`Librarian.java`):

The `Librarian` class manages library operations and contains a list of members and books. Here's what it does:

- It stores two lists: `members` (to keep track of registered members) and `books` (to manage books in the library).

- Methods:
  - `registerMember(String name, int age, long phone)`: Registers a new member with the given name, age, and phone number. Assigns a unique member ID to each member.

  - `removeMember(long phone)`: Removes a member with the specified phone number from the library.

  - `addBook(String title, String author, int copies)`: Adds a new book to the library with the specified title, author, and the number of copies. Assigns a unique book ID to each copy.

  - `removeBook(int book_id)`: Removes a book with the specified book ID from the library, provided it's not already issued to a member.

  - `displayAvailableBooks()`: Lists all available books in the library.

  - `ViewMemberBooks(Member member)`: Lists books borrowed by a specific member.

  - `payFine(Member member)`: Allows a member to pay their fines.

  - `issueBook(Member member, int book_id)`: Allows a member to borrow a book and sets its status to issued.

  - `returnBook(Member member, int book_id)`: Allows a member to return a book and calculates fines if it's overdue.

  - `viewAllMembers()`: Lists all registered members along with their borrowed books and fines.

### Member Class (`Member.java`):

The `Member` class represents a library member and contains their information, including name, age, phone number, member ID, borrowed books, and fines. Here's what it does:

- It stores member-specific information and a list of books that the member has borrowed.

- Methods:
  - `addMyBook(Book book)`: Adds a book to the list of books borrowed by the member.

  - `delMyBook(Book book)`: Removes a book from the list of books borrowed by the member.

These classes work together to create a functional Library Management System where librarians can manage members and books, and members can borrow, return, and manage their borrowed books and fines.


