package io.ggogit.ggogit.domain.book.service;

import io.ggogit.ggogit.api.book.dto.BookCategoryResponse;
import io.ggogit.ggogit.domain.book.entity.BookCategory;
import io.ggogit.ggogit.domain.book.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;

    @Override
    public BookCategoryResponse list(String query, int size, int page) {
        if (query.isEmpty()) { // query가 비어있으면 전체 카테고리 목록을 반환
            PageRequest pageable = PageRequest.of(page - 1, size);
            List<BookCategory> bookCategories = bookCategoryRepository.findAll(pageable).getContent();
            return BookCategoryResponse.of(bookCategories);
        }
        List<BookCategory> bookCategories = bookCategoryRepository.findByNameContaining(query);
        return BookCategoryResponse.of(bookCategories);
    }
}
