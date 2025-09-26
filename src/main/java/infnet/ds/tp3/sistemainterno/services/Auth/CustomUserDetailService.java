package infnet.ds.tp3.sistemainterno.services.Auth;

import infnet.ds.tp3.sistemainterno.models.Teacher;
import infnet.ds.tp3.sistemainterno.repositories.TeacherRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {
    private final TeacherRepository teacherRepository;

    public CustomUserDetailService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByUsername(username);

        if (teacher == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.withUsername(teacher.getUsername())
                .password(teacher.getPassword())
                .roles("TEACHER")
                .build();
    }
}
