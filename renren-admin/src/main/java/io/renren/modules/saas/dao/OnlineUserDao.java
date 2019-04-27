package io.renren.modules.saas.dao;

import io.renren.modules.saas.entity.EntCompanyEntity;
import io.renren.modules.saas.entity.OnlineUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 14:20:40
 */
@Mapper
public interface OnlineUserDao extends BaseMapper<OnlineUserEntity> {

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
    void save(OnlineUserEntity ou);
}
