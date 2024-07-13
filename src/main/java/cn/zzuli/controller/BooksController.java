package cn.zzuli.controller;

import cn.zzuli.entity.Books;
import cn.zzuli.service.BooksService;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

//    // 查询
//    @RequestMapping("/list")
//    public String list(Model model){
//        List<Books> list = booksService.list();
//        model.addAttribute("list", list);
//        return "book_list";
//    }

    /**
     *  查询 搜索
     * pageNum: 当前页
     * pageSize: 每页数据条数
     */
    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                       @RequestParam(value = "title", defaultValue = "", required = false) String title,
                       @RequestParam(value = "author", defaultValue = "", required = false) String author,
                       @RequestParam(value = "publicationDate", defaultValue = "", required = false) String publicationDate
                    ) {
        PageInfo pageInfo =  booksService.queryList(pageNum, pageSize, title, author, publicationDate);
        // 点击搜索按钮后，搜索内容依然存在搜索框
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("publicationDate", publicationDate);
        return "book_list";
    }

    // 去增加页面
    @RequestMapping("/toSave")
    public String list(){
        return "book_save";
    }

    // 增加
    @RequestMapping("/save")
    public String save(Books books){
        boolean b = booksService.save(books);
        if (b){
            // 成功
            return "redirect:/books/list";
        }
        return "error";
    }

    // 删除
    @RequestMapping("/delete/{bookId}")
    public String delete(@PathVariable String bookId){
        boolean b = booksService.removeById(bookId);
        if (b){
            // 成功
            return "redirect:/books/list";
        }
        return "error";
    }

    // id 查询
    @RequestMapping("/queryBookById/{bookId}")
    public String queryBookById(@PathVariable String bookId, Model model){
        Books book = booksService.getById(bookId);
        model.addAttribute("book", book);
        return "book_update";
    }

    // 修改
    @RequestMapping("/update")
    public String update(Books books){
        boolean b = booksService.updateById(books);
        if (b){
            // 成功
            return "redirect:/books/list";
        }
        return "error";
    }

}
