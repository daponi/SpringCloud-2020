package com.atguigu.www.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.www.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerBlockHandler {

    /**
     * 官方文档规定外部类做异常处理需要static，这个类没有纳入Spring容器管理，只能用静态，
     * 不是static！用的时候还得new！在ccontroller里new，不是又耦合了？
     *
     * @SentinelResource注解调用CustomerBlockHandler类中的方法是通过获取该类的字节码文件通过反射的方式去拿到这些具体的方法的，
     * 如果是非静态，即使用反射还是会通过类对象
     *
     * 兜底方法和源方法 参数必须是一样的   兜底还的多加一个参数  BlockException
     * 出错的，看看controller下的方法里有没有传参数，传了参数就会报错
     */
    public static CommonResult handleException(BlockException exception){
      log.info("=========成功访问兜底函数handleException");
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler");
    }

    public static CommonResult handleException2(BlockException exception){
      log.info("=========成功访问兜底函数handleException2222222");
        return new CommonResult(2020,"自定义的限流处理信息......CustomerBlockHandler222222");
    }

}
