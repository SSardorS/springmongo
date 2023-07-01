package uz.pixel.springmongo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentDto {

    private String name;
    private Integer age;
    private Date birthDate;
    private String gender;

    private String groupId;

}
