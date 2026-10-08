package com.library.book.service;

import com.library.book.entity.Book;
import com.library.book.exception.ResourceNotFoundException;
import com.library.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book addBook(Book book) {
        if (repository.existsByIsbn(book.getIsbn()))
            throw new IllegalArgumentException("A book with this ISBN already exists");
        return repository.save(book);
    }

    public List<Book> getBooks() {
        return repository.findAll();
    }

    public Book getBook(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book updateBook(Long id, Book input) {
        Book book = getBook(id);
        if (!book.getIsbn().equals(input.getIsbn()) && repository.existsByIsbn(input.getIsbn()))
            throw new IllegalArgumentException("A book with this ISBN already exists");
        book.setTitle(input.getTitle());
        book.setAuthor(input.getAuthor());
        book.setIsbn(input.getIsbn());
        return repository.save(book);
    }

    public void deleteBook(Long id) {
        repository.delete(getBook(id));
    }
}
