package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 在线用户实体类
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 14:20:40
 */
@Data
@TableName("saas_online_user")
public class OnlineUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 日期
	 */
	private Date createDate;
	/**
	 * 总用户数
	 */
	private Integer total;
	/**
	 * 在线用户数
	 */
	private Integer online;
	/**
	 * 活跃度
	 */
	private String activity;
	/**
	 * 异常用户数
	 */
	private Integer abnormalUser;
	/**
	 * 未注册用户数 
	 */
	private Integer unregisteredUser;
    /**
     * 公司
     */
	private EntCompanyEntity company;

}
