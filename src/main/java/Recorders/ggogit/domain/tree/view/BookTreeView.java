package Recorders.ggogit.domain.tree.view;

import Recorders.ggogit.domain.book.entity.Book;
import Recorders.ggogit.domain.tree.entity.Tree;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookTreeView {
    private Long bookId;        //nullable 일수도 있을것같음
    private String bookCategory; //Book entity와 자료형 다름
    private Long bookTotalPage;
    private String bookTitle;
    private String bookAuthor;
    private String bookTranslator; //Book entity에 없음
    private String bookPublisher;
    private Integer bookPublishedYear;
    private Boolean bookComplete;
    private Long treeId;
    private Long memberId ;
    private Long seedId;
    private String title;
    private String description;
    private Boolean visibility;
    private Date createdAt;
    private Integer readingPage ;
    private String coverImageName;


    public Book toBook(){
        Book book;

        book = Book.builder()
                .id(this.getBookId())
                .memberId(this.getMemberId())
                .totalPage(this.getBookTotalPage())
                .title(this.getTitle())
                .author(this.getBookAuthor())
                .publisher(this.getBookPublisher())
                .build();
        return book;
    }

    public Tree toTree(){
        Tree tree;

        tree = Tree.builder()
                .memberId(this.getMemberId())
                .title(this.getTitle())
                .description(this.getDescription())
                .visibility(this.getVisibility())
                .build();

        return tree;
    }

}
