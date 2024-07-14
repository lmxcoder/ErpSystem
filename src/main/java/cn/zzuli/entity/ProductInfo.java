package cn.zzuli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@TableName("product_info")
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId(value = "product_id", type = IdType.AUTO)
    private Integer productId;

    /**
     * 产品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 产品描述
     */
    @TableField("description")
    private String description;

    /**
     * 产品价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 产品分类ID，关联到product_category表的主键
     */
    @TableField("category_id")
    private Integer categoryId;

    // 非数据库字段
    // tablefield链接数据库字段
    @TableField(exist = false)
    private ProductCategory productCategory;

    /**
     * 制造商
     */
    @TableField("manufacturer")
    private String manufacturer;

    /**
     * 库存量
     */
    @TableField("inventory")
    private Integer inventory;

    /**
     * 上市日期
     */
    @TableField("release_date")
    private LocalDate releaseDate;


}
