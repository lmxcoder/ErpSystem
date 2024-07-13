package cn.zzuli.mapper;

import cn.zzuli.entity.Books;
import cn.zzuli.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectList(QueryWrapper<Object> wrapper);
}
