package cn.zzuli.service.impl;

import cn.zzuli.entity.CustomerCategory;
import cn.zzuli.entity.ProductCategory;
import cn.zzuli.mapper.CustomerCategoryMapper;
import cn.zzuli.service.CustomerCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class CustomerCategoryServiceImpl extends ServiceImpl<CustomerCategoryMapper, CustomerCategory> implements CustomerCategoryService {

    @Autowired private CustomerCategoryMapper customerCategoryMapper;

    @Override
    public PageInfo queryList(int pageNum, int pageSize) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        List<CustomerCategory> customerCategoryList = customerCategoryMapper.selectList(null);

        return new PageInfo(customerCategoryList);
    }
}
