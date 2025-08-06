package com.patdimby.simplerest.repository;

import com.patdimby.simplerest.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
