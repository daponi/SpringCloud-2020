package com.atguigu.www.myLoadBalance;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 本地手写负载均衡实现类
 */
@Component
public class MyLoadBalanceImpl implements MyLoadBalance {

    //原子整数
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 算得第几次访问
     * @return
     */
    public final int getIncremen(){
        int current, next;

        //自旋锁
        do {
            current=this.atomicInteger.get();
            next =current > Integer.MAX_VALUE ? 0 : current+1;
        }while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("================第几次访问？ next: "+next);
        return next;
    }

    /**
     * 算的负载均衡所选的服务器
     * @param serviceInstances
     * @return
     */
    @Override
    public ServiceInstance getInstances(List<ServiceInstance> serviceInstances) {
        //求模取算的负载均衡服务器的下标
        int serverIndex = getIncremen() % serviceInstances.size();
        return serviceInstances.get(serverIndex);
    }


}
