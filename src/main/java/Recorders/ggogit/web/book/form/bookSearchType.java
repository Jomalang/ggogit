package Recorders.ggogit.web.book.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class bookSearchType {

    String description;
    String value;

    @Setter
    String isChecked;

    public static List<bookSearchType> createBookSearchTypes() {
        List<bookSearchType> bookSearchTypes = new ArrayList<bookSearchType>();
        bookSearchTypes.add(new bookSearchType("제목", "t", "checked"));
        bookSearchTypes.add(new bookSearchType("저자", "a", null));

        return bookSearchTypes;
    }


}
