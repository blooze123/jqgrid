package io.renren.modules.saas.service.impl;

import io.renren.common.utils.R;
import io.renren.modules.saas.entity.AuthenEntity;
import io.renren.modules.saas.entity.EntAuthenticationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.saas.dao.AuthenLogDao;
import io.renren.modules.saas.entity.AuthenLogEntity;
import io.renren.modules.saas.service.AuthenLogService;


@Service("authenLogService")
public class AuthenLogServiceImpl extends ServiceImpl<AuthenLogDao, AuthenLogEntity> implements AuthenLogService {
    @Autowired
    private AuthenLogDao authenLogDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AuthenLogEntity> page = this.page(
                new Query<AuthenLogEntity>().getPage(params),
                new QueryWrapper<AuthenLogEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<EntAuthenticationEntity> selectAll() {
        return authenLogDao.selectAll();
    }

    @Override
    public List<EntAuthenticationEntity> selectByCondition(EntAuthenticationEntity authenticationEntity) {
        return authenLogDao.selectByCondition(authenticationEntity);
    }

    @Override
    public R selectBycompanyCode(EntAuthenticationEntity authenticationEntity) {
        List<EntAuthenticationEntity> authenlist=authenLogDao.selectBycompanyCode(authenticationEntity);
        return R.ok().put("authenlist",authenlist);
    }

    @Override
    public List<AuthenLogEntity> selectAuthenLog(String companyCode) {
        return authenLogDao.selectAuthenLog(companyCode);
    }

}
