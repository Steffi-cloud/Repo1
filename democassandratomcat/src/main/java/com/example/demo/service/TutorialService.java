package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tutorial;
import com.example.demo.repository.TutorialsRepository;
@Service
public class TutorialService {

	@Autowired
	private TutorialsRepository repo;
	
	
	public Tutorial saveTutorial(Tutorial tutorials) {
		return repo.insert(tutorials);
	}
	
	public List<Tutorial> getAllTutorials(){
		return repo.findAll();
	}
	
	public Tutorial getTutorialById(UUID id) {
		return repo.findById(id)
		        .orElseThrow(() ->
		            new RuntimeException("Tutorial not found: " + id)
		        );
	}
	
	public Tutorial updateTutorial(Tutorial tutorials) {
		return repo.save(tutorials);
	}
	
	public List<Tutorial> updateAllTutorials(List<Tutorial> tutorials) {

	    if (tutorials == null || tutorials.isEmpty()) {
	        return List.of(); // explicit no-op
	    }

	    List<Tutorial> updated = new ArrayList<>();

	    for (Tutorial incoming : tutorials) {

	        UUID id = incoming.getId();
	        if (id == null) {
	            throw new IllegalArgumentException("Tutorial id must not be null");
	        }

	        Tutorial existing = repo.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Tutorial not found for id: " + id)
	                );

	        // ---- explicit field merge ----
	        if (incoming.getDescription() != null) {
	            existing.setDescription(incoming.getDescription());
	        }

	        if (incoming.getTemplatedesc() != null) {
	            existing.setTemplatedesc(incoming.getTemplatedesc());
	        }

	        updated.add(existing);
	    }

	    // one write per row (still Cassandra upserts, but intentional)
	    return repo.saveAll(updated);
	}

}
