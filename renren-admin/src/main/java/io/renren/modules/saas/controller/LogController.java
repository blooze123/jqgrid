package io.renren.modules.saas.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.saas.entity.LogEntity;
import io.renren.modules.saas.service.LogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author YQ13512
 * @email 501658204@qq.com
 * @date 2019-04-25 10:08:08
 */
@RestController
@RequestMapping("saas/log")
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("saas:log:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = logService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("saas:log:info")
    public R info(@PathVariable("id") Long id){
        LogEntity log = logService.getById(id);

        return R.ok().put("log", log);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("saas:log:save")
    public R save(@RequestBody LogEntity log){
        logService.save(log);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("saas:log:update")
    public R update(@RequestBody LogEntity log){
        ValidatorUtils.validateEntity(log);
        logService.updateById(log);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("saas:log:delete")
    public R delete(@RequestBody Long[] ids){
        logService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
