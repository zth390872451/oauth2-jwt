package babywei.authserver.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/5.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Secured("ROLE_ADMIN")
    @RequestMapping("/isExistOauth")
    public String isExistOauth(@RequestParam(value = "accessToken")String accessToken){
        System.out.println("admin accessToken = " + accessToken);
        return accessToken;
    }

}
