package com.example.library_management.service;

import com.example.library_management.entity.Author;
import com.example.library_management.entity.Book;
import com.example.library_management.repository.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    
    @Autowired
    private BookDao bookDao;
    
    public List<Book> findAllBooks(){
        return bookDao.findAll();
    }

    public Book getBookById(int id){
        return bookDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
    }

    public Book save(Book book){
        return bookDao.save(book);
    }

    public Book update(int id, Book updateBook){
        return bookDao.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(updateBook.getName());
                    return bookDao.save(existingAuthor);
                })
                .orElseThrow(() -> new RuntimeException("ID Not Found" + id));
    }

    public void deleteBookById(int id){
        bookDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
        bookDao.deleteById(id);
    }
}
