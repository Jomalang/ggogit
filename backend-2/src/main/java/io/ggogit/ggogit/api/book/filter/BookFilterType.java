package io.ggogit.ggogit.api.book.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
public enum BookFilterType {
        TITLE(0L, "title", "제목"),
        AUTHOR(1L, "author", "저자"),
        PUBLISHER(2L, "publisher", "출판사");

        private final Long number;
        private final String value;
        private final String description;


        // number로 BookFilterType을 찾는 메서드
        public static BookFilterType fromNumber(Long number) {
            for (BookFilterType filter : BookFilterType.values()) {
                if (filter.getNumber().equals(number)) {
                    return filter;
                }
            }
            throw new IllegalArgumentException("No BookFilterType with number " + number);
        }
        // value로 BookFilterType을 찾는 메서드
        public static BookFilterType fromValue(String value) {
            for (BookFilterType filter : BookFilterType.values()) {
                if (filter.getValue().equals(value)) {
                    return filter;
                }
            }
            throw new IllegalArgumentException("No BookFilterType with value " + value);
        }
        // description로 BookFilterType을 찾는 메서드
        public static BookFilterType fromDescription(String description) {
            for (BookFilterType filter : BookFilterType.values()) {
                if (filter.getDescription().equals(description)) {
                    return filter;
                }
            }
            throw new IllegalArgumentException("No BookFilterType with description " + description);
        }

        // number로 BookFilterType의 value을 찾는 메서드
        public static String findNameByNum(Long number) {
            for (BookFilterType filter : BookFilterType.values()) {
                if (filter.getNumber().equals(number)) {
                    return filter.getValue();
                }
            }
            throw new IllegalArgumentException("No BookFilterType with number " + number);
        }
    // number로 BookFilterType의 value을 찾는 메서드
    public static String findNameByDecription(String description) {
        for (BookFilterType filter : BookFilterType.values()) {
            if (filter.getDescription().equals(description)) {
                return filter.getValue();
            }
        }
        throw new IllegalArgumentException("No BookFilterType with description " + description);
    }
//        public static Sort createSort(BookFilterType filter, BookFilterType sort) {
//            switch (filter) {
//                case TITLE:
//                    if (sort == ASC)
//                        return Sort.by("updateTime").ascending();
//                   return Sort.by("updateTime").descending();
//              case AUTHOR:
//                    if (sort == DECS)
//                       return Sort.by("title").ascending();
//                  return Sort.by("title").descending();
//             default:
//                if (sort == ASC)
//                   return Sort.by("leafCount").ascending();
//              return Sort.by("leafCount").descending();
//     }
//}

}
