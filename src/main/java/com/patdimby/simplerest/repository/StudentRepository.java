package com.patdimby.simplerest.repository;

import com.patdimby.simplerest.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByEmail(String email);

    @Query("SELECT s from Student s WHERE s.email LIKE %:domain")
    List<Student> findByDomain(@Param("domain") String domain);
}
