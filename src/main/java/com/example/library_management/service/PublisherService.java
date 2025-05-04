package com.example.library_management.service;

import com.example.library_management.entity.Category;
import com.example.library_management.entity.Publisher;
import com.example.library_management.repository.PublisherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    
    @Autowired
    private PublisherDao publisherDao;

    public List<Publisher> getAllPublishers(){
        return publisherDao.findAll();
    }

    public Publisher getPublisherById(int id){
        return publisherDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
    }

    public Publisher save(Publisher publisher){
        return publisherDao.save(publisher);
    }

    public Publisher update(int id, Publisher updatePublisher){
        return publisherDao.findById(id)
                .map(existingPublisher -> {
                    existingPublisher.setName(updatePublisher.getName());
                    return publisherDao.save(existingPublisher);
                })
                .orElseThrow(() -> new RuntimeException("ID Not Found" + id));
    }

    public void deletePublisherById(int id){
        publisherDao.findById(id)
                .orElseThrow(() -> new RuntimeException("ID Not Found"));
        publisherDao.deleteById(id);
    }
}
