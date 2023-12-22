package org.example;


import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int age;
    private long phone;

    private int memberID;

    private List<Book> MyBooks;

    private long fine;

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setFine(long fine) {
        this.fine = fine;
    }

    public long getFine() {
        return fine;
    }

    public void setMyBooks(List<Book> myBooks) {
        MyBooks = myBooks;
    }

    public List<Book> getMyBooks() {
        return MyBooks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getPhone() {
        return phone;
    }

    public Member(String name, int age, long phone){
        this.name=name;
        this.phone=phone;
        this.age=age;
        this.MyBooks = new ArrayList<>();
        this.fine=0;

    }


    public void addMyBook(Book book){
        MyBooks.add(book);
    }

    public void delMyBook(Book book){
        MyBooks.remove(book);
    }
}
