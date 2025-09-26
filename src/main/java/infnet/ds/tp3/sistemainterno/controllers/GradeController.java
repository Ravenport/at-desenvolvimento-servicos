package infnet.ds.tp3.sistemainterno.controllers;

import infnet.ds.tp3.sistemainterno.models.Grade;
import infnet.ds.tp3.sistemainterno.services.GradeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grade")
public class GradeController {
    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/create")
    public ResponseEntity<Grade> createGrade(@RequestBody Grade grade) {
        Grade storedGrade = gradeService.createGrade(grade);

        return ResponseEntity.status(HttpStatus.CREATED).body(storedGrade);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Grade> readGrade(@PathVariable String id) {
        Optional<Grade> result = gradeService.findGrade(id);
        Grade grade = null;

        if (result.isPresent()) {
            grade = result.get();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(grade);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable String id, @RequestBody Grade updatedGrade) {
        Grade grade = gradeService.updateGrade(id, updatedGrade);

        return ResponseEntity.ok().body(grade);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Grade> deleteGrade(@PathVariable String id) {
        gradeService.deleteGrade(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/read/all")
    public ResponseEntity<List<Grade>> readGrades() {
        ArrayList<Grade> grades = gradeService.findAllGrades();

        return ResponseEntity.status(HttpStatus.FOUND).body(grades);
    }
}
