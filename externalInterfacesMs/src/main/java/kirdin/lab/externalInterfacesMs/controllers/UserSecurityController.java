package kirdin.lab.externalInterfacesMs.controllers;

import kirdin.lab.externalInterfacesMs.Security.UserSecurityDetailsService;
import kirdin.lab.models.jpa.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@ComponentScan({"kirdin.lab.externalInterfacesMs.Security"})
public class UserSecurityController {
    private final UserSecurityDetailsService service;
    @Autowired
    UserSecurityController(UserSecurityDetailsService userSecurityDetailsService){
        this.service = userSecurityDetailsService;
    }

    @PostMapping("/add_user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserSecurity add(@RequestBody UserSecurity userSecurity){
        return service.addUser(userSecurity);
    }
}
