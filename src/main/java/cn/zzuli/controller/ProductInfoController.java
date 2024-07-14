package cn.zzuli.controller;

import cn.zzuli.entity.ProductCategory;
import cn.zzuli.entity.ProductInfo;
import cn.zzuli.entity.User;
import cn.zzuli.service.ProductCategoryService;
import cn.zzuli.service.ProductInfoService;
import cn.zzuli.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/productInfo")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

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
                       @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                       @RequestParam(value = "productName", defaultValue = "", required = false) String productName,
                       @RequestParam(value = "releaseDate", defaultValue = "", required = false) String releaseDate
    ) {

        PageInfo pageInfo =  productInfoService.queryList(pageNum, pageSize, productName, releaseDate);
        // 点击搜索按钮后，搜索内容依然存在搜索框
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("productName", productName);
        model.addAttribute("releaseDate", releaseDate);
        return "product-info-list";
    }

    /**
     * 删除 异步
     */
    @RequestMapping("delete/{productInfoId}")
    @ResponseBody
    public boolean delete(@PathVariable String userId) {
        boolean b = productInfoService.removeById(userId);
        return b;
    }

    // 转向增加
    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        List<ProductCategory> categoryList = productCategoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "user-add";
    }   // 增加
    @RequestMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody ProductInfo productInfo) {
        // 初始化密码
        return productInfoService.save(productInfo);
    }

    // 去修改
    @RequestMapping("/toEdit/{userId}")
    public String toEdit(Model model, @PathVariable String userId) {
        ProductInfo productInfo = productInfoService.getById(userId);
        model.addAttribute("productInfo", productInfo); // 传给前端
        return "user-edit";
    }

    // 修改
    @RequestMapping("/update")
    @ResponseBody
    public boolean update(@RequestBody ProductInfo productInfo) {
        return productInfoService.updateById(productInfo);
    }
}
