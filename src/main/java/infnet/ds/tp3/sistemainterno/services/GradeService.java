package infnet.ds.tp3.sistemainterno.services;

import infnet.ds.tp3.sistemainterno.models.Grade;
import infnet.ds.tp3.sistemainterno.repositories.GradeRepository;
import infnet.ds.tp3.sistemainterno.repositories.SchoolClassesRepository;
import infnet.ds.tp3.sistemainterno.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GradeService {
    private final StudentRepository studentRepository;
    private final SchoolClassesRepository schoolClassesRepository;
    private final GradeRepository gradeRepository;

    public GradeService(
            StudentRepository studentRepository,
            SchoolClassesRepository schoolClassesRepository,
            GradeRepository gradeRepository
    ) {
        this.studentRepository = studentRepository;
        this.schoolClassesRepository = schoolClassesRepository;
        this.gradeRepository = gradeRepository;
    }

    public Grade createGrade(Grade grade) {
        gradeRepository.save(grade);
        return grade;
    }

    public Optional<Grade> findGrade(String id) {
        return gradeRepository.findById(id);
    }

    public Grade updateGrade(String id, Grade updatedGrade) {
        Grade grade = gradeRepository.findById(id).get();
        grade.setGrade(updatedGrade.getGrade());
        grade.setApproved(isApproved(updatedGrade));
        grade.setSchoolClasses(updatedGrade.getSchoolClasses());
        grade.setStudent(updatedGrade.getStudent());

        gradeRepository.save(grade);
        return grade;
    }

    public void deleteGrade(String id) {
        gradeRepository.deleteById(id);
    }

    private boolean isApproved(Grade grade) {
        return grade.getGrade() >= 7;
    }

    public ArrayList<Grade> findAllGrades() {
        return new ArrayList<>(gradeRepository.findAll());
    }
}
