package cn.zzuli.controller;

import cn.zzuli.entity.CustomerCategory;
import cn.zzuli.service.CustomerCategoryService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/customerCategory")
public class CustomerCategoryController {
    @Autowired
    private CustomerCategoryService customerCategoryService;

    /**
     *  查询 搜索
     * pageNum: 当前页
     * pageSize: 每页数据条数
     */
    @RequestMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        PageInfo pageInfo =  customerCategoryService.queryList(pageNum, pageSize);
        // 点击搜索按钮后，搜索内容依然存在搜索框
        model.addAttribute("pageInfo", pageInfo);
        return "customer-category-list";
    }

    /**
     * 删除 异步
     */
    @RequestMapping("delete/{customerCategoryId}")
    @ResponseBody
    public boolean delete(@PathVariable String customerCategoryId) {
        boolean b = customerCategoryService.removeById(customerCategoryId);
        return b;
    }

    // 转向增加
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "customer-category-add";
    }   // 增加
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody CustomerCategory customerCategory) {
        return customerCategoryService.save(customerCategory);
    }

    // 去修改
    @RequestMapping("/toEdit/{customerCategoryId}")
    public String toEdit(Model model, @PathVariable String customerCategoryId) {
        CustomerCategory customerCategory = customerCategoryService.getById(customerCategoryId);
        model.addAttribute("customerCategory", customerCategory); // 传给前端
        return "customer-category-edit";
    }

    // 修改
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody CustomerCategory customerCategory) {
        return customerCategoryService.updateById(customerCategory);
    }
}
