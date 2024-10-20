package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.domain.book.api.AladinClient;
import io.ggogit.ggogit.domain.book.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AladinServiceImpl implements AladinService {

    private final AladinClient aladinClient;

    @Override
    public List<Book> fetchBookDataApi(String query) {
        return aladinClient.fetchBooks(query);
    }
}
