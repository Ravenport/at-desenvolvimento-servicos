package infnet.ds.tp3.sistemainterno.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
@PreAuthorize("hasRole('TEACHER')")
public class Student {
    @Id
    private String id;

    private String name;
    private String id_document;
    private String email;
    private String telephone;

    private List<SchoolClasses> schoolClasses;

    private List<Grade> grades;
}
