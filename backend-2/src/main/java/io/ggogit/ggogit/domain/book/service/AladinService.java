package io.ggogit.ggogit.domain.book.service;


import io.ggogit.ggogit.domain.book.entity.Book;

import java.util.List;

public interface AladinService {
    List<Book> fetchBookDataApi(String query);
}
