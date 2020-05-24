package com.Rest.H2.SpringBoot;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@Component
class HospitalResourceAssembler implements RepresentationModelAssembler<Hospital, EntityModel<Hospital>> {

  @Override
  public EntityModel<Hospital> toModel(Hospital hospital) {

    return new EntityModel<>( hospital,
      linkTo(methodOn(HospitalController.class).one( hospital.getHospitalId())).withSelfRel(),
      linkTo(methodOn(HospitalController.class).all()).withRel(" hospitals"));
  }
}