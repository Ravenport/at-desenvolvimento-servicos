package infnet.ds.tp3.sistemainterno.services;

import infnet.ds.tp3.sistemainterno.models.Grade;
import infnet.ds.tp3.sistemainterno.models.SchoolClasses;
import infnet.ds.tp3.sistemainterno.models.Student;
import infnet.ds.tp3.sistemainterno.repositories.GradeRepository;
import infnet.ds.tp3.sistemainterno.repositories.SchoolClassesRepository;
import infnet.ds.tp3.sistemainterno.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassesService {
    private final StudentRepository studentRepository;
    private final SchoolClassesRepository schoolClassesRepository;
    private final GradeRepository gradeRepository;

    public SchoolClassesService(
            StudentRepository studentRepository,
            SchoolClassesRepository schoolClassesRepository,
            GradeRepository gradeRepository
    ) {
        this.studentRepository = studentRepository;
        this.schoolClassesRepository = schoolClassesRepository;
        this.gradeRepository = gradeRepository;
    }

    public SchoolClasses createSchoolClasses(SchoolClasses schoolClasses) {
        schoolClassesRepository.save(schoolClasses);
        return schoolClasses;
    }

    public Optional<SchoolClasses> findSchoolClasses(String id) {
        return schoolClassesRepository.findById(id);
    }

    public SchoolClasses updateSchoolClasses(String id, SchoolClasses updatedSchoolClasses) {
        SchoolClasses schoolClasses = schoolClassesRepository.findById(id).get();

        schoolClasses.setName(updatedSchoolClasses.getName());
        schoolClasses.setCode(updatedSchoolClasses.getCode());
        schoolClasses.setGrades(updatedSchoolClasses.getGrades());
        schoolClasses.setStudents(updatedSchoolClasses.getStudents());

        schoolClassesRepository.save(schoolClasses);
        return schoolClasses;
    }

    public List<Student> findAllApproved(SchoolClasses schoolClass) {
        List<Grade> approvedGrades = gradeRepository.findAllApproved();
        List<Student> students = new ArrayList<>();
        for (Grade grade : approvedGrades) {
            students.add(studentRepository.findById(grade.getStudent().getId()).get());
        }

        return students;
    }

    public List<Student> findAllReproved(SchoolClasses schoolClass) {
        List<Grade> reprovedGrades = gradeRepository.findAllReproved();
        List<Student> students = new ArrayList<>();
        for (Grade grade : reprovedGrades) {
            students.add(studentRepository.findById(grade.getStudent().getId()).get());
        }

        return students;
    }

    public void deleteSchoolClasses(String id) {
        schoolClassesRepository.deleteById(id);
    }

    public ArrayList<SchoolClasses> findAllSchoolClassess() {
        return new ArrayList<>(schoolClassesRepository.findAll());
    }
}
