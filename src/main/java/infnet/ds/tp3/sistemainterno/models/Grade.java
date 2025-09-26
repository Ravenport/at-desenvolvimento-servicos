package infnet.ds.tp3.sistemainterno.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "grades")
public class Grade {

    @Id
    private String id;

    private Integer grade;

    private boolean isApproved;

    private SchoolClasses schoolClasses;

    private Student student;
}
