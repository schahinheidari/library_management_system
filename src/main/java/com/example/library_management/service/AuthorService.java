package com.example.library_management.service;

import com.example.library_management.entity.Author;
import com.example.library_management.repository.AuthorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorDao authorDao;

    public List<Author> findAllAuthors(){
        return authorDao.findAll();
    }

    public Author getAuthorById(int id){
        return authorDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
    }

    public Author save(Author author){
        return authorDao.save(author);
    }

    public Author update(int id, Author updateAuthor){
        return authorDao.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(updateAuthor.getName());
                    return authorDao.save(existingAuthor);
                        })
                .orElseThrow(() -> new RuntimeException("ID Not Found" + id));
    }

    public void deleteAuthorById(int id){
        authorDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
        authorDao.deleteById(id);
    }

}
