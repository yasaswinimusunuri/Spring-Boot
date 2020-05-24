package com.Rest.H2.SpringBoot;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
class HospitalController {

  private final HospitalRepository repository;
  private final HospitalResourceAssembler assembler;

  HospitalController(HospitalRepository repository,HospitalResourceAssembler assembler) {
    this.repository = repository;
    this.assembler = assembler;
  }

 
  // Aggregate root

  @GetMapping("/hospitals")
  CollectionModel<EntityModel<Hospital>> all()  {

	  List<EntityModel<Hospital>> hospitals = repository.findAll().stream()
			    .map(assembler::toModel)
			    .collect(Collectors.toList());
    return new CollectionModel<>(hospitals,
      linkTo(methodOn(HospitalController.class).all()).withSelfRel());
  }
  
  @PostMapping("/hospitals")
  Hospital newHospital(@RequestBody Hospital newHospital) {
    return repository.save(newHospital);
  }

  // Single item

  @SuppressWarnings("deprecation")
@GetMapping("/hospitals/{id}")
  EntityModel<Hospital> one(@PathVariable int id) {

	  Hospital hospital = repository.findById(id)
      .orElseThrow(() -> new HospitalNotFoundException(id));

    return assembler.toModel(hospital);
  }

  @PutMapping("/hospitals/{id}")
  Hospital replaceHospital(@RequestBody Hospital newHospital, @PathVariable int id) {

    return repository.findById(id)

    		.map(hospital -> {
    			hospital.setHospitalName(newHospital.getHospitalName());
    			hospital.setLocation(newHospital.getLocation());
    	        return repository.save(hospital);
    	      })
    	      .orElseGet(() -> {
    	    	  newHospital.setHospitalId(id);
    	        return repository.save(newHospital);
    	      });
    	  }

    	  @DeleteMapping("/hospitals/{id}")
    	  void deleteHospital(@PathVariable int id) {
    	    repository.deleteById(id);
    	  }
    	}