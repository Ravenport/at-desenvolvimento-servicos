package infnet.ds.tp3.sistemainterno.repositories;

import infnet.ds.tp3.sistemainterno.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
