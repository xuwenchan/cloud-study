package com.feign.service;
/*
* 使用Fegin调用第三方接口，以下是例子
*
*
* */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
* 完整的url=https://test.cn/user/auth?token=
* 然后在自己的控制器里调用此接口就完成了
* */


@FeignClient(name="test",url="https://test.cn")
@Component
public interface CallThirdInterfaceUserFeign {

    @RequestMapping(method = RequestMethod.POST,value="/user/auth",consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String ssoCheck(String token);

}
