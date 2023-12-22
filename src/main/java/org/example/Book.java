package org.example;

import java.time.LocalTime;
import java.time.Duration;

public class Book {
    private String title;
    private String author;
    private int copies;
    private int book_id;

    private int book_status; //1 if ready for issue -1 if not
    private LocalTime due_date;

    public LocalTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalTime due_date) {
        this.due_date = due_date;
    }

    public void setBook_status(int book_status) {
        this.book_status = book_status;
    }

    public int getBook_status() {
        return book_status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCopies() {
        return copies;
    }

    public int getBook_id() {
        return book_id;
    }

    public Book(String title, String author,int copies){
//        this.book_id=book_id;
        this.title=title;
        this.author=author;
        this.copies=copies;
        this.book_status=1;
    }

    public long Fine_calculate(Book book){
        if(book!=null) {
            LocalTime due_date = book.getDue_date();
            LocalTime current_date = LocalTime.now();
            if(due_date!=null && current_date!=null) {
                Duration duration = Duration.between(due_date, current_date);
                long seconds = duration.getSeconds();
                long fine;
                fine = Math.max(0, seconds) * 3;
                return fine;
            }
        }
        return 0;
    }









}
