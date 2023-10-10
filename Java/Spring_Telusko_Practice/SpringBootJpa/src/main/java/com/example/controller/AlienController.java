package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.dao.AlienRepo;
import com.example.model.Alien;

@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;

	@RequestMapping("/")
	 public String home() {
		 return "home";
	 }
	
	@PostMapping(path="/alien",consumes={"application/json"})
	public Alien addAlien(Alien alien) {
		repo.save(alien);
		return alien;
		
	}
	
	@GetMapping("/aliens")
	public List<Alien> getAliens() {
			return repo.findAll();
	}
	
	@RequestMapping("/aliens/{aid}")
	public Optional<Alien> getAlien(@PathVariable int aid) {
			return repo.findById(aid);
	}

	@DeleteMapping("/alien/{aid}")
	public String deleteAlien(@PathVariable("aid") int aid) {
			Alien a = repo.getOne(aid);
			repo.delete(a);
			
			return "Deleted";
	}
	
	@PutMapping(path="/alien",consumes={"application/json"})
	public Alien saveOrUpdateAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
		
	}


}
