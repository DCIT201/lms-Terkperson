package org.example;

import java.util.ArrayList;

public class Book {
    private String Title;
    private String Author;
    private int YearPublished;
    private boolean isAvailable;

    //constractor
    public Book(String Title, String Author, int YearPublished){
        this.Title = Title;
        this.Author = Author;
        this.YearPublished = YearPublished;
        this.isAvailable  = true;
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return Title;
    }

    public int getYearPublished() {
        return YearPublished;
    }

    public boolean isAvailable(){
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // This is your working class.

}

class Library{
    private String name;
    private ArrayList<Book> books;

    //constructor
    public Library(String name){
        this.name = name;
        this.books = new ArrayList<>();
    }

    //Add a book to the library
    public boolean addBook(Book book){
        books.add(book);
        return false;
    }

    //Remove book from the library
    public boolean removeBook(Book book){
        return books.remove(book);
    }

    //List available books
    public void listAvailableBooks(){
        for (Book book: books){
            if (book.isAvailable()){
                System.out.println(book.getTitle() + "by" + book.getAuthor());
            }
        }
    }

    //find book by its title
    public Book findBookByTitle(String title){
        for(Book book: books){
            if(book.getTitle().equalsIgnoreCase(title) && book.isAvailable()){
                return book;
            }
        }
        return null; // if the book is not found
    }

    //find book by author
    public Book findBookByAuthor(String author){
        for(Book book: books){
            if(book.getAuthor().equalsIgnoreCase(author) && book.isAvailable()){
                return book;
            }
        }
        return null;
    }

    //Get the library's name
    public String getName() {
        return name;
    }
}

class Patron{
    private String name;
    private String patronID;
    private ArrayList<Book> borrowedBooks;

    // Constructor
    public Patron(String name, String patronID) {
        this.name = name;
        this.patronID = patronID;
        this.borrowedBooks = new ArrayList<>();
    }

    // Borrow a book from the library
    public boolean borrowBook(Library library, String title) {
        Book book = library.findBookByTitle(title);
        if (book != null) {
            borrowedBooks.add(book);
            book.setAvailable(false);  // Mark the book as unavailable
            return true;
        }
        return false; // If book is not available
    }

    // Return a book to the library
    public boolean returnBook(Library library, String title) {
        for (Book book : borrowedBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                borrowedBooks.remove(book);
                book.setAvailable(true);  // Mark the book as available again
                return library.addBook(book); // Optional: you may not need to add back if already in the library
            }
        }
        return false; // If the book is not in the borrowed list
    }//

    // Display borrowed books
    public void displayBorrowedBooks() {
        System.out.println(name + "'s borrowed books:");
        for (Book book : borrowedBooks) {
            System.out.println(book.getTitle() + " by " + book.getAuthor());
        }
    }

    // Get patron details
    public String getName() {
        return name;
    }

    public String getPatronID() {
        return patronID;
    }
}
