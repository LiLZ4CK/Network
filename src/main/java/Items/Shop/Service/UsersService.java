package Items.Shop.Service;

import Items.Shop.Entity.Users;
import Items.Shop.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService{

    @Autowired
    private UsersRepository URepo;
    public void save(Users x){

        URepo.save(x);
    }
    public boolean if_exist(String Username){

        return URepo.existsByUsername(Username);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users usr = URepo.findByUsername(username);
        if(usr == null){
            throw new UsernameNotFoundException("User with username '" + username + "' not found");
        }

        List<SimpleGrantedAuthority> authorities = usr.getRole().getAuthorities();
        return new org.springframework.security.core.userdetails.User(username,usr.getPassword(),authorities);
    }
}
