package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String bookName) {
        System.out.println("Adding new book: \"" + bookName + "\"");
        bookRepository.saveBook(bookName);
        System.out.println("Book successfully added to the collection.");
    }
}
