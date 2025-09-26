package infnet.ds.tp3.sistemainterno.controllers;

import infnet.ds.tp3.sistemainterno.models.Student;
import infnet.ds.tp3.sistemainterno.services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student storedStudent = studentService.createStudent(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(storedStudent);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Student> readStudent(@PathVariable String id) {
        Optional<Student> result = studentService.findStudent(id);
        Student student = null;

        if (result.isPresent()) {
            student = result.get();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(student);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String id, @RequestBody Student updatedStudent) {
        Student student = studentService.updateStudent(id, updatedStudent);

        return ResponseEntity.ok().body(student);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<Student>> readStudents() {
        ArrayList<Student> students = studentService.findAllStudents();

        return ResponseEntity.status(HttpStatus.FOUND).body(students);
    }
}
