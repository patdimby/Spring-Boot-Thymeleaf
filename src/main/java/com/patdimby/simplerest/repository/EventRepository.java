package com.patdimby.simplerest.repository;

import com.patdimby.simplerest.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

}
