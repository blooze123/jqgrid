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

import io.renren.modules.saas.entity.AuthenEntity;
import io.renren.modules.saas.service.AuthenService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author blooze
 * @email 1459264166@qq.com
 * @date 2019-04-25 11:34:20
 */
@RestController
@RequestMapping("saas/authen")
public class AuthenController {
    @Autowired
    private AuthenService authenService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("saas:authen:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authenService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{authenId}")
    @RequiresPermissions("saas:authen:info")
    public R info(@PathVariable("authenId") Integer authenId){
        AuthenEntity authen = authenService.getById(authenId);

        return R.ok().put("authen", authen);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("saas:authen:save")
    public R save(@RequestBody AuthenEntity authen){
        authenService.save(authen);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("saas:authen:update")
    public R update(@RequestBody AuthenEntity authen){
        ValidatorUtils.validateEntity(authen);
        authenService.updateById(authen);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("saas:authen:delete")
    public R delete(@RequestBody Integer[] authenIds){
        authenService.removeByIds(Arrays.asList(authenIds));

        return R.ok();
    }

    @RequestMapping("/selectAll")
    public R selectAll(){
        return authenService.selectAll();
    }

}
