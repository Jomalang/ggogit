package io.ggogit.ggogit.domain.book.repository.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ggogit.ggogit.domain.book.entity.Book;
import io.ggogit.ggogit.domain.book.entity.QBook;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static io.ggogit.ggogit.domain.book.entity.QBook.book;

@Repository
@RequiredArgsConstructor
public class BookQueryRepositoryImpl implements BookQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Book> findByFilter(String filter, String query, Pageable pageable){

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(filter.equals("title") && query != null) booleanBuilder.and(book.title.contains(query));
        if(filter.equals("author") && query != null) booleanBuilder.and(book.author.contains(query));
        if(filter.equals("publisher") && query != null) booleanBuilder.and(book.publisher.contains(query));

        List<Book> result = queryFactory
                .selectFrom(book)
                .where(booleanBuilder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(book.id.desc())
                .fetch();

        return result;


    }
}
