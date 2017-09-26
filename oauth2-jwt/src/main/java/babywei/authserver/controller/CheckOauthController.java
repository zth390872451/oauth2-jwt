package babywei.authserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/5.
 */
@RestController
@RequestMapping("/check")
public class CheckOauthController {

    @RequestMapping("/isExistOauth")
    public String isExistOauth(@RequestParam(value = "accessToken")String accessToken){
        System.out.println("accessToken = " + accessToken);
        return accessToken;
    }

}
