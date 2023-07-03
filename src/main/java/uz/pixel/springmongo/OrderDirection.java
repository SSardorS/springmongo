package uz.pixel.springmongo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderDirection {
    ASC(1), DESC(-1);

    private final int direction;
}
