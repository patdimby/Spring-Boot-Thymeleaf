package com.patdimby.simplerest.repository;

import com.patdimby.simplerest.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
@Repository
public interface TeamRepository extends JpaRepository<Team, Long>{

}
