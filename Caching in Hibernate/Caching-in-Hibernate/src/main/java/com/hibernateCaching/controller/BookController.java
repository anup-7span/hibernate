package com.hibernateCaching.controller;

import com.hibernateCaching.entity.Book;
import com.hibernateCaching.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/cached/{id}")
    public Book getCachedBook(@PathVariable Long id) {
        return bookService.getCachedBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestParam String title) {
        return bookService.saveBook(title);
    }

    @PutMapping("/{id}")
    public void updateBookTitle(@PathVariable Long id, @RequestParam String newTitle) {
        bookService.updateBookTitle(id, newTitle);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/error")
    public void saveBookWithError(@RequestParam String title) {
        bookService.saveBookWithError(title);
    }

    @PutMapping("/error/{id}")
    public void updateBookTitleWithError(@PathVariable Long id, @RequestParam String newTitle) {
        bookService.updateBookTitleWithError(id, newTitle);
    }
}