package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(readOnly = true)
public class BookService  {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks () {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).get();
    }

//    @Transactional(propagation = Propagation.REQUIRED)
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

//    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBook (Long bookId) {
        bookRepository.deleteById(bookId);
    }

//    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateBook (Book book, Long bookId) {
       Book bookUpdated = bookRepository.findById(bookId).get();
       bookUpdated.setAuthor(book.getAuthor());
       bookUpdated.setTitle(book.getTitle());

        return bookRepository.save(bookUpdated);
    }

//    @Transactional(propagation = Propagation.REQUIRED)
    public Book updateBook(Map<String, String> updates, Long bookId) {
        final Book book = findBookById(bookId);
        updates.keySet()
                .forEach(key -> {
                    switch (key) {
                        case "author":
                            book.setAuthor(updates.get(key));
                            break;
                        case "title":
                            book.setTitle(updates.get(key));
                    }
                });
        return bookRepository.save(book);
    }


}
