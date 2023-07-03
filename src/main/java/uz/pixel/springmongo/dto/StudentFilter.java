package uz.pixel.springmongo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentFilter {

    private int page;

    private int limit;
    private String columnName;
    private String direction;

}
