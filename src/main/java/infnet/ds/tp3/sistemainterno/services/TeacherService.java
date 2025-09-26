package infnet.ds.tp3.sistemainterno.services;

import infnet.ds.tp3.sistemainterno.models.Teacher;
import infnet.ds.tp3.sistemainterno.repositories.TeacherRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public TeacherService(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Teacher createTeacher(Teacher teacher) throws InvalidParameterException {
        if (teacherRepository.findByUsername(teacher.getUsername()) != null) {
            throw new InvalidParameterException("The teacher already exists!");
        }

        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacherRepository.save(teacher);
        return teacher;
    }

    public Optional<Teacher> findTeacher(String id) {
        return teacherRepository.findById(id);
    }

    public Teacher updateTeacher(String id, Teacher updatedTeacher) {
        Teacher teacher = teacherRepository.findById(id).get();

        teacher.setUsername(updatedTeacher.getUsername());
        teacher.setPassword(passwordEncoder.encode(updatedTeacher.getPassword()));

        teacherRepository.save(teacher);
        return teacher;
    }

    public void deleteTeacher(String id) {
        teacherRepository.deleteById(id);
    }

    public ArrayList<Teacher> findAllTeacheres() {
        return new ArrayList<>(teacherRepository.findAll());
    }
}
