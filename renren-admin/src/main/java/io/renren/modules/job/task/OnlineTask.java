package io.renren.modules.job.task;

import org.springframework.stereotype.Component;

@Component("onlineTask")
public class OnlineTask implements ITask{

    @Override
    public void run(String params) {
        System.out.println("我就看看参数是什么:"+params);
    }
}
