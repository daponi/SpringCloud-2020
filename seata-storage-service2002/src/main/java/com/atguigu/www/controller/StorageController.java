package com.atguigu.www.controller;

import com.atguigu.www.domain.CommonResult;
import com.atguigu.www.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("/storage/decrease")
    public CommonResult decrease(Long productId,Integer count){
        log.info("===============成功访问：/storage/decrease");
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
