package com.example.Bookstore.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository crepository;
	
	@GetMapping("/index")
	public String books(Model model) {
		
		return "main";
	}
	
	//mainpage
	@GetMapping(value = {"/", "/main"})
	public String book(Model model) {
		
		return "main";
	}
	
	//list books 
	@RequestMapping(value = {"/", "/booklist", "/index"})
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//add new book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value =("/add"))
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());

		return "addbook";
	}
	
	//save book
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value ="/save")
	public String save(@Valid Book book, BindingResult result, Model model) {
		if (result.hasErrors()) {
		//	model.addAttribute("books", book);
			model.addAttribute("categories", crepository.findAll());
	
			return "addbook";
		}
		
		repository.save(book);

		return "redirect:booklist";
	}
	
	//delete book
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/delete/{id}")
	public String deleteBook(@PathVariable("id")Long bookId) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	//edit book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value ="/edit/{id}")
	public String addBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
}
