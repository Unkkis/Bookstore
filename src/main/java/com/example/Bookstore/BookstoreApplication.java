package com.example.Bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repository) {
		return (args) -> {
			log.info("save couple of new books to DB");
			repository.save(new Book("From Dusk till Dawn", "Robert Rodriques", "1996", "66546044-55", "19,99"));
			repository.save(new Book("a Farewell to Arms", "Ernest Hemingway", "1929", "1232323-21", "29,99"));
			repository.save(new Book("Animal Farm", "George Orwell", "1945", "2212343-5", "24,99"));
		
			log.info("fetch all books from DB");
			for (Book book: repository.findAll()) {
				log.info(book.toString()); 
			}
		
		};
	}
}
