package cn.zzuli.service.impl;

import cn.zzuli.entity.Customer;
import cn.zzuli.entity.CustomerCategory;
import cn.zzuli.entity.Customer;
import cn.zzuli.mapper.CustomerMapper;
import cn.zzuli.mapper.CustomerCategoryMapper;
import cn.zzuli.mapper.CustomerMapper;
import cn.zzuli.service.CustomerService;
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

//customer
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerCategoryMapper customerCategoryMapper;

    @Override
    public PageInfo queryList(int pageNum, int pageSize, String customerName, String registrationDate) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 查询条件
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(customerName)) {
            wrapper.like("customer_name", customerName); // 精确查询
        }
        if (StringUtils.isNotBlank(registrationDate)) {
            wrapper.eq("registration_date", registrationDate); // 模糊查询
        }

        List<Customer> customerList = customerMapper.selectList(wrapper);

        // 循环
        customerList.forEach(customer -> {
            CustomerCategory customerCategory = customerCategoryMapper.selectById(customer.getCategoryId());
            // 对象存入
            customer.setCustomerCategory(customerCategory);
        });

        return new PageInfo(customerList);
    }
}
