package cn.zzuli.mapper;

import cn.zzuli.entity.Books;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface BooksMapper extends BaseMapper<Books> {

    List<Books> selectList(QueryWrapper<Object> wrapper);
}
