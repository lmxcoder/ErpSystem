package cn.zzuli.service.impl;

import cn.zzuli.entity.ProductCategory;
import cn.zzuli.entity.ProductInfo;
import cn.zzuli.entity.User;
import cn.zzuli.mapper.ProductCategoryMapper;
import cn.zzuli.mapper.ProductInfoMapper;
import cn.zzuli.mapper.UserMapper;
import cn.zzuli.service.ProductInfoService;
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
public class ProductInfoServiceImpl extends ServiceImpl<ProductInfoMapper, ProductInfo> implements ProductInfoService {
    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public PageInfo queryList(int pageNum, int pageSize, String productName, String releaseDate) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 查询条件
        QueryWrapper<ProductInfo> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(productName)){
            wrapper.like("product_name", productName); // 精确查询
        }
        if (StringUtils.isNotBlank(releaseDate)){
            wrapper.eq("release_date", releaseDate); // 模糊查询
        }

        List<ProductInfo> productInfoList = productInfoMapper.selectList(wrapper);

        // 循环
        productInfoList.forEach(productInfo -> {
            ProductCategory productCategory = productCategoryMapper.selectById(productInfo.getCategoryId());
            // 对象存入
            productInfo.setProductCategory(productCategory);
        });

        return new PageInfo(productInfoList);
    }
}
