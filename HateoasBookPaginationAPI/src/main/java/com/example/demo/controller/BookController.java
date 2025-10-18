package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.BookModelAssembler;
import com.example.demo.repository.BookRepository;
@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookModelAssembler bookModelAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Book>> getAllBooks() {
        List<EntityModel<Book>> books = bookRepository.findAll().stream()
                .map(bookModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(books,
                linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        return bookModelAssembler.toModel(book);
    }
    
    
    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book){
    	return bookRepository.save(book);
    }
}
