package cn.zzuli.controller;

import cn.zzuli.entity.ProductCategory;
import cn.zzuli.entity.User;
import cn.zzuli.service.ProductCategoryService;
import cn.zzuli.service.UserService;
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
@RequestMapping("/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

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

        PageInfo pageInfo =  productCategoryService.queryList(pageNum, pageSize);
        // 点击搜索按钮后，搜索内容依然存在搜索框
        model.addAttribute("pageInfo", pageInfo);
        return "product-category-list";
    }

    /**
     * 删除 异步
     */
    @RequestMapping("delete/{productCategoryId}")
    @ResponseBody
    public boolean delete(@PathVariable String productCategoryId) {
        boolean b = productCategoryService.removeById(productCategoryId);
        return b;
    }

    // 转向增加
    @RequestMapping("/toAdd")
    public String toAdd() {
        return "product-category-add";
    }   // 增加
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody ProductCategory productCategory) {
        return productCategoryService.save(productCategory);
    }

    // 去修改
    @RequestMapping("/toEdit/{productCategoryId}")
    public String toEdit(Model model, @PathVariable String productCategoryId) {
        ProductCategory productCategory = productCategoryService.getById(productCategoryId);
        model.addAttribute("productCategory", productCategory); // 传给前端
        return "product-category-edit";
    }

    // 修改
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody ProductCategory productCategory) {
        return productCategoryService.updateById(productCategory);
    }
}
