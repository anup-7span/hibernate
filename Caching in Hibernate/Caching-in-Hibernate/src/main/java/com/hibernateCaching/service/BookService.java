package com.hibernateCaching.service;

import com.hibernateCaching.entity.Book;
import com.hibernateCaching.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Book saveBook(String title) {
        Book book = new Book();
        book.setTitle(title);
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Cacheable("books")
    public Book getCachedBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void updateBookTitle(Long id, String newTitle) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(newTitle);
        }
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void saveBookWithError(String title) {
        Book book = new Book();
        book.setTitle(title);
        bookRepository.save(book);

        // Simulate an error to trigger a rollback
        if ("error".equals(title)) {
            throw new RuntimeException("Simulated error");
        }
    }

    @Transactional
    public void updateBookTitleWithError(Long id, String newTitle) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(newTitle);

            // Simulate an error to trigger a rollback
            if ("error".equals(newTitle)) {
                throw new RuntimeException("Simulated error");
            }
        }
    }
}