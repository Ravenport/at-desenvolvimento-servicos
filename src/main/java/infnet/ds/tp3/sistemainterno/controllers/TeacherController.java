package infnet.ds.tp3.sistemainterno.controllers;

import infnet.ds.tp3.sistemainterno.models.Teacher;
import infnet.ds.tp3.sistemainterno.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher teacherSalvo = teacherService.createTeacher(teacher);

        return ResponseEntity.status(HttpStatus.CREATED).body(teacherSalvo);
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Teacher> readTeacher(@PathVariable String id) {
        Optional<Teacher> result = teacherService.findTeacher(id);
        Teacher teacher = null;

        if (result.isPresent()) {
            teacher = result.get();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(teacher);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String id, @RequestBody Teacher updatedTeacher) {
        Teacher teacher = teacherService.updateTeacher(id, updatedTeacher);

        return ResponseEntity.ok().body(teacher);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<Teacher>> readTeachers() {
        ArrayList<Teacher> teacher = teacherService.findAllTeacheres();

        return ResponseEntity.status(HttpStatus.FOUND).body(teacher);
    }
}
