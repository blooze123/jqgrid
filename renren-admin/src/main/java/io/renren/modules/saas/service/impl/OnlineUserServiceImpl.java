package io.renren.modules.saas.service.impl;

import io.renren.modules.saas.entity.EntCompanyEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.saas.dao.OnlineUserDao;
import io.renren.modules.saas.entity.OnlineUserEntity;
import io.renren.modules.saas.service.OnlineUserService;

import javax.annotation.Resource;


@Service("onlineUserService")
public class OnlineUserServiceImpl extends ServiceImpl<OnlineUserDao, OnlineUserEntity> implements OnlineUserService {

    @Resource
    private OnlineUserDao ouDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OnlineUserEntity> page = this.page(
                new Query<OnlineUserEntity>().getPage(params),
                new QueryWrapper<OnlineUserEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 获取所有的公司
     * @return
     */
    @Override
    public List<EntCompanyEntity> getAllCompany() {
        return ouDao.getAllCompany();
    }

    /**
     * 获取公司总人数
     * @param companyId
     * @return
     */
    @Override
    public Integer getCompanyTotal(Integer companyId) {
        return ouDao.getCompanyTotal(companyId);
    }

    /**
     * 获取公司异常用户人数
     * @param companyId
     * @return
     */
    @Override
    public Integer getAbnormalUser(Integer companyId) {
        return ouDao.getAbnormalUser(companyId);
    }

    /**
     * 获取公司未注册用户数
     * @param companyId
     * @return
     */
    @Override
    public Integer getUnregisteredUser(Integer companyId) {
        return ouDao.getUnregisteredUser(companyId);
    }

    /**
     * 插入数据
     * @param ou
     */
    @Override
    public void insert(OnlineUserEntity ou) {
        ouDao.save(ou);
    }

}
