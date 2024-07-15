package cn.zzuli.controller;

import cn.zzuli.entity.CustomerCategory;
import cn.zzuli.entity.Customer;
import cn.zzuli.service.CustomerCategoryService;
import cn.zzuli.service.CustomerService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//customer

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

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
                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                       @RequestParam(value = "customerName", defaultValue = "", required = false) String customerName,
                       @RequestParam(value = "registrationDate", defaultValue = "", required = false) String registrationDate
    ) {

        PageInfo pageInfo =  customerService.queryList(pageNum, pageSize, customerName, registrationDate);
        // 点击搜索按钮后，搜索内容依然存在搜索框
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("customerName", customerName);
        model.addAttribute("registrationDate", registrationDate);
        return "customer-list";
    }

    /**
     * 删除 异步
     */
    @RequestMapping("delete/{customerId}")
    @ResponseBody
    public boolean delete(@PathVariable String customerId) {
        boolean b = customerService.removeById(customerId);
        return b;
    }

    // 转向增加
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<CustomerCategory> categoryList = customerCategoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "customer-add";
    }   // 增加


    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    // 去修改
    @RequestMapping("/toEdit/{customerId}")
    public String toEdit(Model model, @PathVariable String customerId) {

        List<CustomerCategory> categoryList = customerCategoryService.list();
        model.addAttribute("categoryList", categoryList);

        Customer customer = customerService.getById(customerId);
        model.addAttribute("customer", customer); // 传给前端
        return "customer-edit";
    }

    // 修改
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody Customer customer) {
        return customerService.updateById(customer);
    }
}
