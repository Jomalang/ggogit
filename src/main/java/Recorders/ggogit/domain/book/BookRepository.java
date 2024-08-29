package Recorders.ggogit.domain.book;

import java.util.List;

public interface BookRepository {

    List<Book> findAll();
}
