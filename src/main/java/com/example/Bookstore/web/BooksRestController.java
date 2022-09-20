package com.example.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@RestController
public class BooksRestController {

		@Autowired
		BookRepository bookRepository;
		
		//return list of books
		@GetMapping("/books")
		public Iterable<Book> getBooks() {//fetch and return books
			return bookRepository.findAll();
		}
		
		@GetMapping("/books/{id}")
		public Optional<Book> findBook(@PathVariable("id")Long bookId) {
			return bookRepository.findById(bookId);
		}
}
