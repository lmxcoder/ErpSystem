package cn.zzuli.service;

import cn.zzuli.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
public interface CustomerService extends IService<Customer> {

    PageInfo queryList(int pageNum, int pageSize, String customerName, String registrationDate);
}
