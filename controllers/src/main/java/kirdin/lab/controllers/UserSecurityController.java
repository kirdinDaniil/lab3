package kirdin.lab.controllers;

import kirdin.lab.dal.models.UserSecurity;
import kirdin.lab.services.UserSecurityDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@ComponentScan(basePackages = "kirdin.lab.services")
@RequestMapping("cat_api/security")
@RestController
public class UserSecurityController {
    private final UserSecurityDetailsService service;

    @PostMapping("/add")
    public UserSecurity add(@RequestBody UserSecurity userSecurity){
        return service.add(userSecurity);
    }
}
