package io.renren.modules.saas.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.saas.entity.OnlineUserEntity;
import io.renren.modules.saas.service.OnlineUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 14:20:40
 */
@RestController
@RequestMapping("saas/onlineuser")
public class OnlineUserController {
    @Autowired
    private OnlineUserService onlineUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("saas:onlineuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = onlineUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("saas:onlineuser:info")
    public R info(@PathVariable("id") Long id){
        OnlineUserEntity onlineUser = onlineUserService.getById(id);

        return R.ok().put("onlineUser", onlineUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("saas:onlineuser:save")
    public R save(@RequestBody OnlineUserEntity onlineUser){
        onlineUserService.save(onlineUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("saas:onlineuser:update")
    public R update(@RequestBody OnlineUserEntity onlineUser){
        ValidatorUtils.validateEntity(onlineUser);
        onlineUserService.updateById(onlineUser);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("saas:onlineuser:delete")
    public R delete(@RequestBody Long[] ids){
        onlineUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
