package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName) {
        System.out.println(" Adding book to library: \"" + bookName + "\"");
        
        if (bookRepository != null) {
            bookRepository.saveBook(bookName);
            System.out.println(" BookRepository is initialized. Dependency injection passed.");
            System.out.println(" Book has been successfully added.\n");
        } else {
            System.out.println(" BookRepository is not initialized. Dependency injection failed.");
        }
    }
}
