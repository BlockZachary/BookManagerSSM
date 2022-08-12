package book.manager.controller.page;

import book.manager.service.AuthService;
import book.manager.service.BookService;
import book.manager.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/page/admin")
public class AdminPageController {

    @Resource
    AuthService service;

    @Resource
    BookService bookService;

    @Resource
    StatService statService;

    @RequestMapping("/index")
    public String index(HttpSession session, Model model){
        model.addAttribute("user",service.findUser(session));
        model.addAttribute("borrowList",bookService.getBorrowDetailsList());
        model.addAttribute("stat",statService.getGlobalStat());
        return "/admin/index";
    }

    @RequestMapping("/book")
    public String book(HttpSession session, Model model){
        model.addAttribute("user",service.findUser(session));
        model.addAttribute("bookList",bookService.getAllBook());
        return "/admin/book";
    }

    @RequestMapping("/add-book")
    public String addbook(HttpSession session, Model model){
        model.addAttribute("user",service.findUser(session));
        return "/admin/add-book";
    }
}
