package uz.pixel.springmongo.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import uz.pixel.springmongo.Gender;

import java.util.Date;

@Document
@Getter
@Setter
public class Student {

    @Id
    private String id;
    private String name;
    private Integer age;
    private Date birthDate;
    private Gender gender;

    private String groupId;

}
