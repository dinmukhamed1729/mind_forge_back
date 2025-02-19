package din.kz.mind_forge_back.mapper;

import din.kz.mind_forge_back.model.entity.Role;
import din.kz.mind_forge_back.model.entity.User;
import din.kz.mind_forge_back.model.request.RegistrationRequest;
import din.kz.mind_forge_back.model.response.RegistrationResponse;
import din.kz.mind_forge_back.config.security.CustomUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", source = "roles", qualifiedByName = "mapRolesToAuthorities")
    CustomUserDetails toCustomUserDetails(User user);

    @Named("mapRolesToAuthorities")
    default Collection<GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        return roles.stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    User registrationRequestToUser(RegistrationRequest registrationRequest);

    RegistrationResponse toRegistrationResponse(User user);
}
