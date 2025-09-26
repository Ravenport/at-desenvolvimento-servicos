package infnet.ds.tp3.sistemainterno.controllers;

import infnet.ds.tp3.sistemainterno.models.SchoolClasses;
import infnet.ds.tp3.sistemainterno.models.Student;
import infnet.ds.tp3.sistemainterno.services.SchoolClassesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/school/classes")
public class SchoolClassesController {
    private final SchoolClassesService schoolClassesService;

    public SchoolClassesController(
            SchoolClassesService schoolClassesService) {
        this.schoolClassesService = schoolClassesService;
    }

    @PostMapping("/create")
    public ResponseEntity<SchoolClasses> createSchoolClasses(@RequestBody SchoolClasses schoolClasses) {
        SchoolClasses storedSchoolClasses = schoolClassesService.createSchoolClasses(schoolClasses);

        return ResponseEntity.status(HttpStatus.CREATED).body(storedSchoolClasses);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<SchoolClasses> readSchoolClasses(@PathVariable String id) {
        Optional<SchoolClasses> result = schoolClassesService.findSchoolClasses(id);
        SchoolClasses schoolClasses = null;

        if (result.isPresent()) {
            schoolClasses = result.get();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(schoolClasses);
    }

    @GetMapping("/read/approved/{id}")
    public ResponseEntity<List<Student>> getApprovedStudents(@PathVariable String id) {
        Optional<SchoolClasses> result = schoolClassesService.findSchoolClasses(id);
        SchoolClasses schoolClass = new SchoolClasses();

        if (result.isPresent()) {
            schoolClass = result.get();
        }

        List<Student> approvedStudents = schoolClassesService.findAllApproved(schoolClass);

        return ResponseEntity.status(HttpStatus.FOUND).body(approvedStudents);
    }

    @GetMapping("/read/reproved/{id}")
    public ResponseEntity<List<Student>> getReprovedStudents(@PathVariable String id) {
        Optional<SchoolClasses> result = schoolClassesService.findSchoolClasses(id);
        SchoolClasses schoolClass = new SchoolClasses();

        if (result.isPresent()) {
            schoolClass = result.get();
        }

        List<Student> reprovedStudents = schoolClassesService.findAllReproved(schoolClass);

        return ResponseEntity.status(HttpStatus.FOUND).body(reprovedStudents);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SchoolClasses> updateSchoolClasses(@PathVariable String id, @RequestBody SchoolClasses updatedSchoolClasses) {
        SchoolClasses schoolClasses = schoolClassesService.updateSchoolClasses(id, updatedSchoolClasses);

        return ResponseEntity.ok().body(schoolClasses);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SchoolClasses> deleteSchoolClasses(@PathVariable String id) {
        schoolClassesService.deleteSchoolClasses(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<SchoolClasses>> readSchoolClassess() {
        ArrayList<SchoolClasses> schoolClassess = schoolClassesService.findAllSchoolClassess();

        return ResponseEntity.status(HttpStatus.FOUND).body(schoolClassess);
    }
}
