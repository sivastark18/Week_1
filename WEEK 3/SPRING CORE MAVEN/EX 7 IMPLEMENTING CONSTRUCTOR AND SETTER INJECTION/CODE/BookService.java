package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;
    private String libraryName;
    public BookService(String libraryName) {
        this.libraryName = libraryName;
    }
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void registerBook(String title) {
        System.out.println("[" + libraryName + "] Registering Book: " + title);
        if (bookRepository != null) {
            bookRepository.saveBook(title);
            System.out.println("Book registered successfully.");
        } else {
            System.out.println("BookRepository not injected.");
        }
    }
}
