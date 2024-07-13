package cn.zzuli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author LMX
 * @since 2024年7月10日
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("books")
@ToString
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍ID
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Integer bookId;

    /**
     * 书名
     */
    @TableField("title")
    private String title;

    /**
     * 作者
     */
    @TableField("author")
    private String author;

    /**
     * 出版日期
     */
    @TableField("publication_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    /**
     * ISBN号
     */
    @TableField("isbn")
    private String isbn;

    /**
     * 图书类型或类别
     */
    @TableField("genre")
    private String genre;

    /**
     * 书籍语言
     */
    @TableField("language")
    private String language;


}
