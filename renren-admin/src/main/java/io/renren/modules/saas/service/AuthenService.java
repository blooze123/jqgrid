package io.renren.modules.saas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.saas.entity.AuthenEntity;

import java.util.Map;

/**
 * 
 *
 * @author blooze
 * @email 1459264166@qq.com
 * @date 2019-04-25 11:34:20
 */
public interface AuthenService extends IService<AuthenEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R selectAll();
}

