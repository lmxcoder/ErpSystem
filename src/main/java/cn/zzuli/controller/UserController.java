package cn.zzuli.controller;

import cn.zzuli.entity.User;
import cn.zzuli.service.BooksService;
import cn.zzuli.service.UserService;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  查询 搜索
     * pageNum: 当前页
     * pageSize: 每页数据条数
     */
    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                       @RequestParam(value = "username", defaultValue = "", required = false) String username,
                       @RequestParam(value = "fullName", defaultValue = "", required = false) String fullName
                ) {

        PageInfo pageInfo =  userService.queryList(pageNum, pageSize, username, fullName);
        // 点击搜索按钮后，搜索内容依然存在搜索框
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("username", username);
        model.addAttribute("fullName", fullName);
        return "user-list";
    }

    /**
     * 删除 异步
     */
    @RequestMapping("delete/{userId}")
    @ResponseBody
    public boolean delete(@PathVariable String userId) {
        boolean b = userService.removeById(userId);
        return b;
    }

    // 转向增加
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user-add";
    }   // 增加
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody User user) {
        // 初始化密码
        user.setPassword("123456");
        return userService.save(user);
    }

    // 去修改
    @RequestMapping("/toEdit/{userId}")
    public String toEdit(Model model, @PathVariable String userId) {
        User user = userService.getById(userId);
        model.addAttribute("user", user); // 传给前端
        return "user-edit";
    }

    // 修改
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody User user) {
        return userService.updateById(user);
    }
}
