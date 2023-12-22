package org.example;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Librarian {
    private List<Member> members;
    private List<Book> books;

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Librarian(){
        this.members = new ArrayList<>();
        this.books = new ArrayList<>();
    }


    private int reg_check(long phone){
        for (Member member : members){
            if(member.getPhone() == phone){
                return 1;
            }
        }
        return -1;
    }

    public int getIndexByPhoneNumber(long phoneNumber) {
        for (int i = 0; i < members.size(); i++) {
            Member member = members.get(i);
            if (member.getPhone() == phoneNumber) {
                return i;
            }
        }
        return -1;
    }



    int mem =0;
    //have to check on how to use member id
    public void registerMember(String name,int age,long phone){
        if(reg_check(phone) == 1){
            System.out.println("The member is already registered");
        }
        else {
            mem++;
            Member member = new Member(name, age, phone);
            members.add(member);
            member.setMemberID(mem);
            System.out.println("Member successfully registered with member id <"+ mem+">");
        }
    }

    public void removeMember(long phone){
        if(reg_check(phone) ==1){
            int index=getIndexByPhoneNumber(phone);
            Member member=members.get(index);
            if (!member.getMyBooks().isEmpty()){
                System.out.println("The member has an issued book please wait till they return the book ");
                return;
            }
            if(member.getFine()>0){
                System.out.println("The member needs to clear its dues first");
                return;
            }
            members.remove(index);
            System.out.println("Member with phone number "+ phone+ " is successfully removed");

        }
        else{
            System.out.println("No such member exists");
        }
    }

    public int book_id_check(int book_id){
        for (Book book : books){
            if(book.getBook_id() == book_id){
                return 1;
            }
        }
        return -1;
    }

    private int book_title_check(String title){
        for (Book book : books){
            if(book.getTitle().equals(title)){
                return 1;
            }
        }
        return -1;
    }

    int count=0;
    public void addBook(String title,String author,int copies){
        for(int j =0;j<copies;j++){
            count++;
            Book book = new Book(title,author,copies);
            books.add(book);
            book.setBook_id(count);
        }
    }

    private int getIndexByBookId(int book_id) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getBook_id() == book_id) {
                return i;
            }
        }
        return -1;
    }
    public void removeBook(int book_id){
        if(book_id_check(book_id)==-1){
            System.out.println("The entered book id does not exist");
        }
        else{
            int index = getIndexByBookId(book_id);
            Book book=books.get(index);
            if(book.getBook_status()==-1){
                System.out.println("The entered book is already issued and cannot be removed currently");
                return;
            }
            books.remove(index);
            System.out.println("Book with id "+ book_id +" is removed");
        }
    }

    public void ViewAllBooks(){
        if(books.isEmpty()){
            System.out.println("No books available.");
        }
        else{
            System.out.println("---------------------");
            for(Book book :books){
                System.out.println("Book ID: "+ book.getBook_id());
                System.out.println("Title: "+  book.getTitle());
                System.out.println("Author: "+ book.getAuthor());
                System.out.println();
            }
        }
    }


    public void displayAvailableBooks(){
        for(Book book : books){
            if (book.getBook_status()==1){
                System.out.println("Book ID: " + book.getBook_id());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println();
            }
        }
    }

    public void ViewMemberBooks(Member member){
        if(member!=null) {
            if (member.getMyBooks().isEmpty()) {
                System.out.println("No books are issued to you.");
            } else {
                System.out.println("---------------------");
                for (Book book : member.getMyBooks()) {
                    System.out.println("Book ID: " + book.getBook_id());
                    System.out.println("Title: " + book.getTitle());
                    System.out.println("Author: " + book.getAuthor());
                    System.out.println();
                }
            }
        }
    }
    private long MemberFine(Member member){
        long totalFine=0;
        if(member!=null){
            for(Book book : member.getMyBooks()) {
                long fine = book.Fine_calculate(book);
                totalFine += fine;
            }
//            long Final_Fine = member.getFine() + totalFine;
//            member.setFine(Final_Fine);
            return totalFine;
        }
        return 0;
    }



    public void payFine(Member member){
//        MemberFine(member);
        System.out.println("You had a total fine of Rs. "+ member.getFine()+".It has been paid successfully!");
        member.setFine(0);

    }

    public void issueBook(Member member,int book_id){
        if(book_id_check(book_id)==-1){
            System.out.println("The title you have mentioned is not available ");
            return;
        }
        if(member.getMyBooks().size()>=2){
            System.out.println("You cannot borrow more than 2 books at a time");
        }
        int index =getIndexByBookId(book_id);
        Book book = books.get(index);

        if (book.getBook_status() == -1) {
            System.out.println("The book is already issued and not available for borrowing.");
            return;
        }
        if(MemberFine(member)>0){
            System.out.println("Please clear your fines first.");
            return;
        }
        LocalTime currentDateAndTime = LocalTime.now();
        LocalTime DueDate = currentDateAndTime.plusSeconds(10);
        book.setDue_date(DueDate);
        book.setBook_status(-1);
        member.addMyBook(book);

        System.out.println("Book issued successfully with due date: " + DueDate);
    }

    public void returnBook(Member member,int book_id){
        if (member != null) {
            int index = getIndexByBookId(book_id);
            Book book = books.get(index);

            book.setBook_status(1);
            long fineForReturnedBook = book.Fine_calculate(book);
            long totalFine = member.getFine() + fineForReturnedBook;
            member.setFine(totalFine);
//            long fine = member.getFine();
//            member.setFine(fine+book.Fine_calculate(book));
//            long fine = MemberFine(member);
//            member.setFine(fine);
            System.out.println("The book has been returned and your fine for this book is : " +fineForReturnedBook+" .The due date was "+book.getDue_date()+" and the current date is"+ LocalTime.now());
            member.delMyBook(book);
        }
    }

    public void viewAllMembers(){
        System.out.println("-------------");

        for(Member member : members){
            System.out.println("Member Name: " + member.getName());
            System.out.println("Phone Number: " + member.getPhone());
            System.out.println();
            if(!member.getMyBooks().isEmpty()) {
                System.out.println("Borrowed Books:");
                for (Book book : member.getMyBooks()) {
                    System.out.println("Book id:" + book.getBook_id());
                    System.out.println("Book title: " + book.getTitle());
                    System.out.println("Due Date: " + book.getDue_date());
                    System.out.println();
                }
            } else if (member.getMyBooks().isEmpty()) {
                System.out.println("No borrowed books");
                System.out.println();

            }
            long fine =MemberFine(member); //member.getFine();
            System.out.println("Fine to be paid: " +fine +" rupees");
            System.out.println();
        }
    }
}