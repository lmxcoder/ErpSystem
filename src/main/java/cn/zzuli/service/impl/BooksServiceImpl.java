package cn.zzuli.service.impl;

import cn.zzuli.entity.Books;
import cn.zzuli.mapper.BooksMapper;
import cn.zzuli.service.BooksService;
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
public class BooksServiceImpl extends ServiceImpl<BooksMapper, Books> implements BooksService {
    @Autowired
    private BooksMapper booksMapper;

    @Override
    public PageInfo queryList(int pageNum, int pageSize, String title, String author, String publicationDate) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);

        // 查询条件
        QueryWrapper<Books> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(title)){
            wrapper.like("title", title);
        }
        if (StringUtils.isNotBlank(author)){
            wrapper.like("author", author);
        }
        if (StringUtils.isNotBlank(publicationDate)){
            wrapper.eq("publication_date", publicationDate);
        }

        List<Books> booklist = booksMapper.selectList(wrapper);

        return new PageInfo(booklist);
    }
}
