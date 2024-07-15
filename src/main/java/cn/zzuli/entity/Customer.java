package cn.zzuli.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    /**
     * 客户名称
     */
    @TableField("customer_name")
    private String customerName;

    /**
     * 联系人姓名
     */
    @TableField("contact_person")
    private String contactPerson;

    /**
     * 联系电话
     */
    @TableField("contact_phone")
    private String contactPhone;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 注册时间
     */
    @TableField("registration_date")
    private LocalDate registrationDate;

    /**
     * 关联的用户ID，但不设置外键约束
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 客户银行账号
     */
    @TableField("bank_account")
    private String bankAccount;

    /**
     * 开户行名称
     */
    @TableField("bank_name")
    private String bankName;

    /**
     * 客户分类ID，关联到customer_category表的主键
     */
    @TableField("category_id")
    private Integer categoryId;

    // 非数据库字段
    // tablefield链接数据库字段
    @TableField(exist = false)
    private CustomerCategory customerCategory;


}
