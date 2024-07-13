package cn.zzuli.service.impl;

import cn.zzuli.entity.Books;
import cn.zzuli.entity.User;
import cn.zzuli.mapper.BooksMapper;
import cn.zzuli.mapper.UserMapper;
import cn.zzuli.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo queryList(int pageNum, int pageSize, String username, String fullName) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            wrapper.eq("username", username); // 精确查询
        }
        if (StringUtils.isNotBlank(fullName)){
            wrapper.like("full_name", fullName); // 模糊查询
        }

        List<User> userList = userMapper.selectList(wrapper);

        return new PageInfo(userList);
    }
}
