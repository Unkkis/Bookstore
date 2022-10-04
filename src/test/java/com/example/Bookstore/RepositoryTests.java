package com.example.Bookstore;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;


@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
class RepositoryTests {

	@Autowired
	BookRepository bookRepository;
	@Autowired
	CategoryRepository crepository;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void findBooksCategory() {
	Book book = (Book) bookRepository.findByTitle("From Dusk till Dawn");
	assertEquals(book.getCategory(), "Novelli");

	}
	
	@Test
	public void findBookByName() {
		List<Book> books = bookRepository.findByTitle("Kasvoton kuolema");
		assertEquals(books.size(), 1);
	}
	
	@Test
	@Order(1)
	public void createBook() {
		bookRepository.save(new Book("Uusi kirja", "Teuvo Testaaja", 2020, "1234567-12", 99, crepository.findByName("Dekkari").get(0)));
		List<Book> books = bookRepository.findByTitle("Uusi kirja");
		assertEquals(books.size(), 1);
	}
	
	
	@Test
	@Order(3)
	public void deleteBook() {
		//tehdään kirja
		bookRepository.save(new Book("Uusi kirja", "Teuvo Testaaja", 2020, "1234124-1231", 99));
		//etsitään se uusi kirja
		List<Book> books = bookRepository.findByTitle("Uusi kirja");
		System.out.println();
		System.out.println("kirja on; " + books);
		//ja poistetaan se
		bookRepository.deleteAll(books);
		assertEquals(bookRepository.findByTitle("Uusi kirja"), false);
	}

}
