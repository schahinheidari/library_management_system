package com.example.library_management.controller;

import com.example.library_management.entity.Author;
import com.example.library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/save")
    public ResponseEntity<Author> save(@RequestBody Author author){
        Author savedAuthor = authorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Author>> listAllAuthors() {
        List<Author> authors = authorService.findAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        author.setId(id);
        authorService.update(id, author);
        return new ResponseEntity<>(author, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}
