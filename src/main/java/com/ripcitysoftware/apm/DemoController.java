package com.ripcitysoftware.apm;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
// if you're using IntelliJ, you'll want to follow this for lombok: https://projectlombok.org/setup/intellij
@Slf4j
public class DemoController {

  private PersonRepository personRepository;
  private DeadLock deadLock;

  @Autowired
  public DemoController(PersonRepository personRepository,
      DeadLock deadLock) {
    this.personRepository = personRepository;
    this.deadLock = deadLock;
  }

  @GetMapping("/persons")
  @ResponseBody
  public ResponseEntity<PagedResources<Person>> listPeople(Pageable pageable,
      PagedResourcesAssembler assembler) {

    Page<Person> people = personRepository.findAll(pageable);
    log.info("fetched {} person instances.", people.getTotalElements());

    return new ResponseEntity<PagedResources<Person>>(assembler.toResource(people), HttpStatus.OK);
  }

  @GetMapping("/person/{id}")
  @ResponseBody
  public Optional<Person> getPerson(@PathVariable long id) {
    return personRepository.findById(id);
  }

  @GetMapping("/lock")
  @ResponseBody
  public void startDeadlock() {
    deadLock.deadlock();
  }
}
