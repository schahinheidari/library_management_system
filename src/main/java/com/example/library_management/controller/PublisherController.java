package com.example.library_management.controller;

import com.example.library_management.entity.Publisher;
import com.example.library_management.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    
    @Autowired
    private PublisherService publisherService;

    @PostMapping("/save")
    public ResponseEntity<Publisher> save(@RequestBody Publisher publisher){
        Publisher savedPublisher = publisherService.save(publisher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable int id) {
        Publisher publisher = publisherService.getPublisherById(id);
        if (publisher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Publisher>> listAllCategories() {
        List<Publisher> categories = publisherService.getAllPublishers();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Publisher> updatePublisher(@PathVariable int id, @RequestBody Publisher publisher) {
        Publisher existingPublisher = publisherService.getPublisherById(id);
        if (existingPublisher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        publisher.setId(id);
        publisherService.update(id, publisher);
        return new ResponseEntity<>(publisher, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        Publisher publisher = publisherService.getPublisherById(id);
        if (publisher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        publisherService.deletePublisherById(id);
        return ResponseEntity.noContent().build();
    }
}
