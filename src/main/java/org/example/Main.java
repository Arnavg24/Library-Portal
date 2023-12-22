package org.example;
import java.util.Scanner;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Librarian librarian = new Librarian();

        while(true){
            System.out.println("Library Portal Initialized… ");
            System.out.println("---------------------------------");
            System.out.println("1. Enter as a librarian");
            System.out.println("2. Enter as a member");
            System.out.println("3. Exit");
            System.out.println("---------------------------------");

            int choice =scanner.nextInt();
            scanner.nextLine();

            if(choice==1){
                while(true) {
                    System.out.println("---------------------------------");
                    System.out.println("1.Register a member");
                    System.out.println("2.Remove a member");
                    System.out.println("3.Add a book");
                    System.out.println("4.Remove a book");
                    System.out.println("5.View all members along with their books and fines to be paid");
                    System.out.println("6.View all books");
                    System.out.println("7.Back");


                    int mem = scanner.nextInt(); //mem is the option which the librarian will choose
                    scanner.nextLine();

                    if (mem==1){
                        System.out.println("Name: ");
                        String name = scanner.nextLine();

                        System.out.println("Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Phone no.");
                        long phone =scanner.nextLong();

                        System.out.println("-----------");

                        librarian.registerMember(name,age,phone);
                    }

                    if (mem==2){
                        System.out.println("Enter the phone number:");
                        long phone = scanner.nextLong();
                        librarian.removeMember(phone);
                    }

                    if(mem==3){
                        System.out.println("Title:");
                        String title = scanner.nextLine();

                        System.out.println("Author:");
                        String author = scanner.nextLine();

                        System.out.println("Copies:");
                        int copies = scanner.nextInt();

                        librarian.addBook(title,author,copies);
                        System.out.println("Book added successfully!");
                    }

                    if(mem==4){
                        System.out.println("Book id:");
                        int book_id= scanner.nextInt();

                        librarian.removeBook(book_id);
                    }

                    if(mem==5){
                        System.out.println("All members with their books and fines to be paid:");
                        System.out.println();

                        librarian.viewAllMembers();
                    }

                    if(mem==6){
                        System.out.println("All the books in the library:");
                        System.out.println();

                        librarian.ViewAllBooks();
                    }
                    if(mem==7) {
                        break;
                    }
                }
            } else if (choice ==2) {
                boolean memberLoggedIn = false;
                Member member = null;
                while(!memberLoggedIn) {
                    System.out.println("Name:");
                    String name = scanner.nextLine();

                    System.out.println("Phone no.");
                    long phone = scanner.nextLong();
                    scanner.nextLine();

                    int num = librarian.getIndexByPhoneNumber(phone);
                    if (num == -1) {
                        System.out.println("Member with name: " + name + " and Phone No.:" + phone + " doesn't exist ");
                        continue; // Ask for name and phone again

                    }
                    else {
                        member = librarian.getMembers().get(num);
                        if(!name.equals(member.getName())){
                            System.out.println("The name and Phone number don't match");
                        }
                        else {
                            memberLoggedIn = true;
                        }
                    }
                }
                System.out.println("Welcome " + member.getName() + ". Member ID: " + member.getMemberID());
                System.out.println("--------------");
                while(true){
                    System.out.println("1. List Available Books");
                    System.out.println("2. List My Books");
                    System.out.println("3. Issue Book");
                    System.out.println("4. Return Book");
                    System.out.println("5. Pay Fine");
                    System.out.println("6. Back");
                    System.out.println("--------------");

                    int mem = scanner.nextInt();
                    scanner.nextLine();
                    if (mem == 1) {
                        System.out.println("The available book are as follows: ");
                        System.out.println();
                        librarian.displayAvailableBooks();
                    }

                    if (mem == 2) {
                        librarian.ViewMemberBooks(member);
                    }

                    if (mem == 3) {
                        System.out.println("Enter the book id you want to be issued: ");
                        int book_id = scanner.nextInt();
                        scanner.nextLine();
                        librarian.issueBook(member, book_id);
                    }

                    if (mem == 4) {
                        System.out.println("Enter Book ID:");
                        int book_id = scanner.nextInt();
                        scanner.nextLine();

                        librarian.returnBook(member, book_id);
                    }

                    if (mem == 5) {
                        librarian.payFine(member);
                    }

                    if(mem==6){
                        break;
                    }
                }
            } else if (choice ==3) {
                System.out.println("Exiting");
                scanner.close();
                System.exit(0);
            }
            else {
                System.out.println("Invalid option chosen.Please select a valid option");
            }
        }

    }
}