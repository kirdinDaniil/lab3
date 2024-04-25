package kirdin.lab.services;

import kirdin.lab.config.UserSecurityDetails;
import kirdin.lab.dal.models.UserSecurity;
import kirdin.lab.dal.repositories.UserSecurityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@ComponentScan({"kirdin.lab.dal.repositories"})
public class UserSecurityDetailsService implements UserDetailsService {
    private UserSecurityRepository userSecurityRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserSecurity> userSecurity = userSecurityRepository.findByUsername(username);
        return userSecurity.map(UserSecurityDetails::new)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("not found user with username %s", username)));

    }

    public UserSecurity add(UserSecurity userSecurity){
        userSecurity.setPassword(passwordEncoder.encode(userSecurity.getPassword()));
        return userSecurityRepository.save(userSecurity);
    }
}
