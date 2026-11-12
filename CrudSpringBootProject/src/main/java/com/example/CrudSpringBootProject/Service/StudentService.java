package com.example.CrudSpringBootProject.Service;

import com.example.CrudSpringBootProject.entity.Student;
import com.example.CrudSpringBootProject.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository studentrepo;
    StudentService(StudentRepository studentrepo ){

        this.studentrepo=studentrepo;
    }
   public Student createStudent(Student studentreq){
       System.out.println("Inside Student service");
        Student studentresp=studentrepo.save(studentreq);
        return studentresp;
   }
   public Student getStudent(Long id){
        //using optional bcoz findbyid returntype is optional
        Optional<Student> getstudent=studentrepo.findById(id);
        if(getstudent.isPresent()){
            // if id is softly deleted then get method return null;
            Student stud=getstudent.get();
            if(stud.isDeleted()) return null;
            //else return student by id
            return getstudent.get();
        }
        return null;
   }
   public List<Student> getAllStudent(){
        List<Student>l1=studentrepo.findAll();
        if(l1.isEmpty()){
            return null;
        }
        return l1;
    }
    public Student updateStudent(Long id,Student udpatedstd){
        Optional<Student> existingdstd=studentrepo.findById(id);
        if(existingdstd.isEmpty()){
            return null;
        }
        Student student=existingdstd.get();
        // if id is softly deleted it can't be updated.
        if(student.isDeleted()){
            return null;
        }

        Student toupdate=existingdstd.get();
        toupdate.setName(udpatedstd.getName());
        toupdate.setAge(udpatedstd.getAge());
        toupdate.setEmail(udpatedstd.getEmail());
        toupdate.setId(udpatedstd.getId());
        toupdate.setRollno(udpatedstd.getRollno());
        toupdate.setSubject(udpatedstd.getSubject());
        Student updatedstudent=studentrepo.save(toupdate);
        return updatedstudent;

    }
    public String deletebyId(Long id) {
        Optional<Student> existingdstd = studentrepo.findById(id);
        if (existingdstd.isEmpty()) {
            return null;
        }
        studentrepo.deleteById(id);
        return "Done";
    }
    public String deleteAll(){
        if (studentrepo.findAll().isEmpty()) {
            return "NO DATA FOUND";
        }
        studentrepo.deleteAll();
        return "All Deleted";
    }
    public boolean deleteStudentSoftly(Long id){
        Optional<Student> existingdstd = studentrepo.findById(id);
         if(existingdstd.isEmpty()){
             return false;
         }
         Student stud=existingdstd.get();
         stud.setDeleted(true);
         studentrepo.save(stud);
         return true;
    }
    }

