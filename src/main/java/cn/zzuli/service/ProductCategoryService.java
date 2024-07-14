package cn.zzuli.service;

import cn.zzuli.entity.ProductCategory;
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
public interface ProductCategoryService extends IService<ProductCategory> {

    PageInfo queryList(int pageNum, int pageSize);

}
