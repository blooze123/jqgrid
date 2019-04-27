package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2019-04-08 15:33:16
 */
@Data
@TableName("ent_invoice")
public class EntInvoiceEntity extends EntBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 抬头id
	 */
	@TableId
	private Integer invoiceId;
	/**
	 * 抬头编号
	 */
	private String invoiceCode;
	/**
	 * 公司id
	 */
	private String companyCode;
	/**
	 * 租户code
	 */
	private String corpCode;
	/**
	 * 开户银行
	 */
	private String bank;
	/**
	 * 电话号码
	 */
	private String telephone;
	/**
	 * 注册地址
	 */
	private String address;
	/**
	 * 公司名
	 */
	private String companyName;
	/**
	 * 银行账户
	 */
	private String account;
	/**
	 * 创建人
	 */
	private String createBy;
	/**
	 * 修改人
	 */
	private String updateBy;


}
