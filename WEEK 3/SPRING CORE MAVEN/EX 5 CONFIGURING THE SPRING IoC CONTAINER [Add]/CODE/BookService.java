package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public void registerBook(String title) {
        System.out.println("Processing book registration: \"" + title + "\"");
        if (bookRepository != null) {
            bookRepository.saveBook(title);
            System.out.println("Book registered successfully.");
        } else {
            System.out.println("BookRepository dependency not initialized!");
        }
    }
}
