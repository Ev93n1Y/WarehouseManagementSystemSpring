package application.security;

import application.model.dto.UserDto;
import application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JdbcUserDetails implements UserDetailsService {
    private final UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            UserDto dto = service.findByEmail(username);
            return new UserDetails() {
                @Override
                public List<SimpleGrantedAuthority> getAuthorities() {
                    return dto.getRoles().stream()
                            .map(roleDao ->
                                    new SimpleGrantedAuthority(roleDao.getRole()))
                            .collect(Collectors.toList());
                }

                @Override
                public String getPassword() {
                    return dto.getPassword();
                }

                @Override
                public String getUsername() {
                    return dto.getEmail();
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
            };
        } catch (ResponseStatusException e) {
            throw new UsernameNotFoundException(username);
        }

    }
}
