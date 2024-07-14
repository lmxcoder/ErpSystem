package cn.zzuli.service.impl;

import cn.zzuli.entity.Books;
import cn.zzuli.entity.ProductCategory;
import cn.zzuli.entity.User;
import cn.zzuli.mapper.BooksMapper;
import cn.zzuli.mapper.ProductCategoryMapper;
import cn.zzuli.service.ProductCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public PageInfo queryList(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        List<ProductCategory> productCategoryList = productCategoryMapper.selectList(null);

        return new PageInfo(productCategoryList);
    }
}
