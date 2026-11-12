package com.example.CrudSpringBootProject.controller;

import com.example.CrudSpringBootProject.Service.StudentService;
import com.example.CrudSpringBootProject.entity.Student;
import com.example.CrudSpringBootProject.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;
     StudentController(StudentService studentService){
        this.studentService=studentService;
    }
    // creating student
    @PostMapping
    public String createStudent(@RequestBody Student student){
         //student.setDeleted(false);
        Student createStudent= studentService.createStudent(student);
        return "Student Created";
    }
    // read 1 student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student > getStudent(@PathVariable Long id){
       Student studresp=studentService.getStudent(id);
       if(studresp==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }
       return ResponseEntity.ok(studresp);
    }
   // read All Student detail
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent(){
         List<Student> s1=studentService.getAllStudent();
         if(s1==null){
             return ResponseEntity
                     .status(HttpStatus.NOT_FOUND)
                     .body(null);
         }
         return ResponseEntity.ok(s1) ;
    }
    //update student
    @PutMapping("/{id}")
    public ResponseEntity<Student > updateStudent(@PathVariable Long id,@RequestBody Student student){
        Student studresp=studentService.updateStudent(id,student);
        if(studresp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(studresp);
    }
    //delete by id
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        return studentService.deletebyId(id);
    }
    // deleteAll
    @DeleteMapping
    public String deleteAll(){
         return studentService.deleteAll();
    }
    // soft delete= dont actually delete content from databse jus mark deleted=true.
    // for this we can use patch option.
    @PatchMapping("/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
         Boolean isDeleted=studentService.deleteStudentSoftly(id);
         if(!isDeleted){
             return ResponseEntity.ok("Data Not Found");
         }
         return ResponseEntity.ok("Deleted");
    }
}
