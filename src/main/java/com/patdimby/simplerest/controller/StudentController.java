package com.patdimby.simplerest.controller;

import com.patdimby.simplerest.model.Student;
import com.patdimby.simplerest.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/save")
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/findByEmail")
    public Student findStudent(@RequestParam("email") String email) {
        return repository.findByEmail(email);
    }

    @PatchMapping("/update")
    public Student findStudent(@RequestParam("student_id") Long id, @RequestParam("new_email") String email) {
        Optional<Student> student = repository.findById(id);
        if (student.isPresent()) {
            Student studentObj = student.get();
            studentObj.setEmail(email);
            return repository.save(studentObj);
        }
        return null;
    }

    @GetMapping("/findByEmailDomain")
    public List<Student> findStudentByDomain(@RequestParam("domain") String domain) {
        return repository.findByDomain(domain);
    }

}
