package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.api.book.dto.BookCategoryResponse;

public interface BookCategoryService {
    BookCategoryResponse list(String query, int size, int page);
}
