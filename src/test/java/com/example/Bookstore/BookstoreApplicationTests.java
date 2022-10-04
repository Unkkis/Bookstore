package com.example.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Bookstore.web.BookController;
import com.example.Bookstore.web.BooksRestController;

@SpringBootTest
class BookstoreApplicationTests {
	
	@Autowired
	BookController bookController;
	@Autowired
	BooksRestController bookRestController;
	
	@Test
	void contextLoads() {
		assertThat(bookController).isNotNull();
		assertThat(bookRestController).isNotNull();
	}

}
