package io.renren.modules.saas.service.impl;

import io.renren.common.utils.R;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.saas.dao.AuthenDao;
import io.renren.modules.saas.entity.AuthenEntity;
import io.renren.modules.saas.service.AuthenService;


@Service("authenService")
public class AuthenServiceImpl extends ServiceImpl<AuthenDao, AuthenEntity> implements AuthenService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AuthenEntity> page = this.page(
                new Query<AuthenEntity>().getPage(params),
                new QueryWrapper<AuthenEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R selectAll() {
        return null;
    }

}
