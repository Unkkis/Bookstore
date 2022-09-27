package com.example.Bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import com.example.Bookstore.domain.ApplicationUser;
import com.example.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save couple of new books to DB");
			
			crepository.save(new Category(""));
			crepository.save(new Category("Dekkari"));
			crepository.save(new Category("Novelli"));
			crepository.save(new Category("Dokumentti"));
			
			
			repository.save(new Book("Kasvoton kuolema", "Henning Mankell", 1989, "", 12.00, crepository.findByName("Dekkari").get(0))); //, crepository.findById(1).get(0)
			repository.save(new Book("Riian verikoirat", "Henning Mankell", 1990, "", 13.00, crepository.findByName("Dekkari").get(0)));
			repository.save(new Book("From Dusk till Dawn", "Robert Rodriques", 1996, "66546044-55", 19.99, crepository.findByName("Novelli").get(0)));
			repository.save(new Book("a Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 29.99, crepository.findByName("Novelli").get(0)));
			
			//Create users: admin/admin user/user
			ApplicationUser user1 = new ApplicationUser("Jussi", "Kosonen", "ADMIN", "admin", "$2a$10$FndAP653l6sY1f/UuWv/LeESJ1thxEEXx7Or/P1iR3aKZZiIBAtGC");
			ApplicationUser user2 = new ApplicationUser("Jussi", "Kosonen", "USER", "user", "$2a$10$amK1FwmFcC5BctacyKiSW.3ZG5JTD3Jky0Usvjuli3m3EPVToasV6");
			urepository.save(user1);
			urepository.save(user2);
		
			log.info("fetch all books from DB");
			for (Book book: repository.findAll()) {
				log.info(book.toString()); 
			}
			
		
		};
	}

}
