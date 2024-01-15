package Items.Shop.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static Items.Shop.enums.Permissions.*;

@RequiredArgsConstructor
public enum Roles {

    USER(Collections.emptySet()),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE
            )
    )
    ;

    @Getter
    private final Set<Permissions> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
       var authorities = getPermissions()
                .stream()
                .map(permissions1 -> new SimpleGrantedAuthority(permissions1.name()))
                .collect(Collectors.toList());
       authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
