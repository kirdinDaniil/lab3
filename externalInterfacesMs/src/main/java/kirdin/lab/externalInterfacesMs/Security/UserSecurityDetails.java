package kirdin.lab.externalInterfacesMs.Security;

import kirdin.lab.models.jpa.UserSecurity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserSecurityDetails implements UserDetails {
    private UserSecurity userSecurity;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> test = new ArrayList<>();
        test.add(userSecurity.getRole());
        return test.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Long getOwnerId(){
        return userSecurity.getOwner().getId();
    }

    @Override
    public String getPassword() {
        return userSecurity.getPassword();
    }

    @Override
    public String getUsername() {
        return userSecurity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
