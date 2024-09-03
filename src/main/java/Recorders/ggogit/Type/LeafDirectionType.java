package Recorders.ggogit.Type;

import lombok.Getter;

@Getter
public enum LeafDirectionType {
    START("start", "시작"),
    END("end", "끝"),
    LEFT("left", "왼쪽"),
    RIGHT("right", "오른쪽"),
    SIDE("side", "옆"),
    DOWN("down", "아래");

    private final String value;
    private final String description;

    LeafDirectionType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public static LeafDirectionType of(String value) {
        for (LeafDirectionType type : LeafDirectionType.values()) {
            if (type.value.equals(value))
                return type;
        }
        throw new IllegalArgumentException("LeafDirectionType의 value 인자를 잘못 받았습니다.");
    }
}
