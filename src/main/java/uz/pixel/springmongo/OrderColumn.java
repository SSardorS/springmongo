package uz.pixel.springmongo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderColumn {

    ID("_id"),
    NAME("name"),
    AGE("age"),
    BIRTH_DATE("birth_date"),
    GENDER("gender"),

    GROUP_ID("group_id");

    private final String column;



}
