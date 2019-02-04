package com.ripcitysoftware.apm;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

  @Override
  @EntityGraph(value = "graph.person.address", type = EntityGraphType.LOAD)
  Page<Person> findAll(Pageable pageable);
}
