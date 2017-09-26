package babywei.authserver.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/5.
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Secured("ROLE_MEMBER")
    @RequestMapping("/isExistOauth")
    public String isExistOauth(@RequestParam(value = "accessToken")String accessToken){
        System.out.println("member accessToken = " + accessToken);
        return accessToken;
    }

}
