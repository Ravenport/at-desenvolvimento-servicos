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
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolClassesRepository schoolClassesRepository;
    private final GradeRepository gradeRepository;

    public StudentService(
            StudentRepository studentRepository,
            SchoolClassesRepository schoolClassesRepository,
            GradeRepository gradeRepository
    ) {
        this.studentRepository = studentRepository;
        this.schoolClassesRepository = schoolClassesRepository;
        this.gradeRepository = gradeRepository;
    }

    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public Optional<Student> findStudent(String id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(String id, Student updatedStudent) {
        Student student = studentRepository.findById(id).get();

        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setTelephone(updatedStudent.getTelephone());
        student.setSchoolClasses(updatedStudent.getSchoolClasses());
        student.setGrades(updatedStudent.getGrades());

        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    public ArrayList<Student> findAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }
}
