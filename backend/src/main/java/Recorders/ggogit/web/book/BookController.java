package Recorders.ggogit.web.book;


import Recorders.ggogit.domain.book.service.BookService;
import Recorders.ggogit.domain.book.view.BookDetailView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/detail")
    public String detail(
            @RequestParam(value = "id") Long bookId,
            Model model
    ) {
        BookDetailView book = bookService.get(bookId);
        model.addAttribute("book", book);
        return "view/book/index";
    }
}
