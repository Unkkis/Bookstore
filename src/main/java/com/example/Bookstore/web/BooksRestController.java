package com.example.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;


@RestController
public class BooksRestController {

		@Autowired
		private BookRepository bookRepository;
		
		//return list of all books
		@GetMapping("/books")
		public Iterable<Book> getBooks() {//fetch and return books
			return bookRepository.findAll();
		}
		
		//list book by id
		@GetMapping("/books/{id}")
		public Optional<Book> findBook(@PathVariable("id")Long bookId) {
			return bookRepository.findById(bookId);
		}
		
		//delete book (by id)
		@DeleteMapping("/books/{id}")
		public Iterable<Book> deleteBook(@PathVariable("id")Long bookId) {
			bookRepository.deleteById(bookId);
			return bookRepository.findAll();
		}
		
		//add new book
		@PostMapping("/books")
		public Book newBook(@RequestBody Book newBook) {
			return bookRepository.save(newBook);
		}
		
		//edit existing book
		@PutMapping("books/{id}")
		public Book editBook(@RequestBody Book editedBook, @PathVariable Long id) {
			editedBook.setId(id);
			return bookRepository.save(editedBook);
		}
		
		
}
