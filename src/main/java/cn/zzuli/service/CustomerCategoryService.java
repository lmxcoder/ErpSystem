package cn.zzuli.service;

import cn.zzuli.entity.CustomerCategory;
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
public interface CustomerCategoryService extends IService<CustomerCategory> {

    PageInfo queryList(int pageNum, int pageSize);
}
