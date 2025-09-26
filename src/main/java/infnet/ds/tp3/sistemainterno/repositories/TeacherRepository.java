package infnet.ds.tp3.sistemainterno.repositories;

import infnet.ds.tp3.sistemainterno.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Teacher findByUsername(String username);
}
