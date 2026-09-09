package co.ucompensar.smarttech.security;

import co.ucompensar.smarttech.entity.AdminUser;
import co.ucompensar.smarttech.repository.AdminUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminUserRepository adminUserRepository;

    public CustomUserDetailsService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {

        AdminUser adminUser = adminUserRepository
                .findByUsername(usernameOrEmail)
                .orElseGet(() ->
                        adminUserRepository.findByEmail(usernameOrEmail)
                                .orElseThrow(() ->
                                        new UsernameNotFoundException(
                                                "Usuario o correo no encontrado"
                                        )
                                )
                );

        return User.builder()
                .username(adminUser.getUsername())
                .password(adminUser.getPassword())
                .disabled(!adminUser.isEnabled())
                .roles("ADMIN")
                .build();
    }
}