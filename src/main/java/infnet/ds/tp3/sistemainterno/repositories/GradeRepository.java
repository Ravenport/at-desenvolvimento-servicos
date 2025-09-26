package infnet.ds.tp3.sistemainterno.repositories;

import infnet.ds.tp3.sistemainterno.models.Grade;
import infnet.ds.tp3.sistemainterno.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends MongoRepository<Grade, String> {
    @Query("{'isApproved': true}")
    List<Grade> findAllApproved();

    @Query("{'isApproved': false}")
    List<Grade> findAllReproved();
}
