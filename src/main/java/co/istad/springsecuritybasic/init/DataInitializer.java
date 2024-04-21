package co.istad.springsecuritybasic.init;

import co.istad.springsecuritybasic.model.Authority;
import co.istad.springsecuritybasic.model.Role;
import co.istad.springsecuritybasic.repository.AuthorityRepository;
import co.istad.springsecuritybasic.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @PostConstruct
    void initData(){
        intiRole();
        initAuthorities();
        roleAuthoritiesInit();
    }


    void intiRole() {
        List<String> roles = List.of("ADMIN","USER","AUTHOR");
        if (roleRepository.count() == 0){
            roles.forEach(role ->{
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            });
        }
    }

    void initAuthorities() {
        List<String> authorities = List.of("READ", "WRITE","DELETE");
        if (authorityRepository.count() == 0) {
            authorities.forEach(authority ->{
                Authority newAuthority = new Authority();
                newAuthority.setName(authority);
                authorityRepository.save(newAuthority);
            });
        }
    }

    void roleAuthoritiesInit() {
        Set<Role> roles = new HashSet<>(roleRepository.findAll());
        Set<Authority> authorities = new HashSet<>(authorityRepository.findAll());

        for (Role role : roles) {
            try {
                switch (role.getName()) {
                    case "USER":
                        role.setAuthorities(filterAuthorities(authorities, "READ"));
                        break;
                    case "AUTHOR":
                        role.setAuthorities(filterAuthorities(authorities,"WRITE","READ"));
                        break;
                    case "ADMIN":
                        role.setAuthorities(authorities);
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected role: " + role.getName());
                }
                roleRepository.save(role);
            } catch (Exception e) {
                System.out.println("Error assigning authorities to role: " + role.getName());
                e.printStackTrace();
            }
        }
    }


        private Set<Authority> filterAuthorities(Set<Authority> authorities, String... names) {
            Set<Authority> filtered = new HashSet<>();
            for (Authority authority : authorities) {
                for (String name : names) {
                    if (authority.getName().equals(name)) {
                        filtered.add(authority);
                    }
                }
            }
            return filtered;
        }





}
