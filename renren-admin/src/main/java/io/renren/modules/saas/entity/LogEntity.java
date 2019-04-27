package io.renren.modules.saas.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 日志实体类
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 10:08:08
 */
@Data
@TableName("saas_log")
public class LogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 日志标题
	 */
	private String title;
	/**
	 * 请求地址
	 */
	private String url;
	/**
	 * 日志类型
	 */
	private Integer logType;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 操作日期
	 */
	private Date operationDate;
	/**
	 * 响应时间
	 */
	private Long responseTime;
	/**
	 * 响应次数
	 */
	private Integer responseNumber;
	/**
	 * 异常次数
	 */
	private Integer exceptionNumber;
	/**
	 * 设备数量
	 */
	private Integer equipmentQuantity;
	/**
	 * 浏览器种类数量
	 */
	private Integer browserNumber;
	/**
	 * 日志的类型(0：前台日志  1：后台日志)
	 */
	private Integer type;
    /**
     * 公司
     */
    private EntCompanyEntity company;

}
