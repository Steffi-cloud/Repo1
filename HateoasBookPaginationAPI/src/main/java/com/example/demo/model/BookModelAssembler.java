package com.example.demo.model;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.demo.controller.BookController;
import com.example.demo.exception.ResourceNotFoundException;
@Component
public class BookModelAssembler implements RepresentationModelAssembler<Book, EntityModel<Book>> {

    @Override
    public EntityModel<Book> toModel(Book book) {
        try {
			return EntityModel.of(book,
			        linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel(),
			        linkTo(methodOn(BookController.class).getAllBooks()).withRel("books"));
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
