package application.security;


import application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class SecurityUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       // SecurityProperties.User user = repository.findUserByUsername(username)
        //        .orElseThrow(() -> new UsernameNotFoundException("User not present"));
       // return user;
        return null;
    }
    public void createUser(UserDetails user) {
        //repository.save((User) user);
    }
}
