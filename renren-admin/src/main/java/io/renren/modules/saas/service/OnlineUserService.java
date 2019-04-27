package io.renren.modules.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.saas.entity.EntCompanyEntity;
import io.renren.modules.saas.entity.OnlineUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 14:20:40
 */
public interface OnlineUserService extends IService<OnlineUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取所有的公司
     * @return
     */
    List<EntCompanyEntity> getAllCompany();

    /**
     * 获取公司总人数
     * @param companyId
     * @return
     */
    Integer getCompanyTotal(Integer companyId);

    /**
     * 获取公司异常用户人数
     * @param companyId
     * @return
     */
    Integer getAbnormalUser(Integer companyId);

    /**
     * 获取公司未注册用户数
     * @param companyId
     * @return
     */
    Integer getUnregisteredUser(Integer companyId);

    /**
     * 插入数据
     * @param ou
     */
    void insert(OnlineUserEntity ou);
}

