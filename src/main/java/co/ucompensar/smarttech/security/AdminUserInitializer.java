package co.ucompensar.smarttech.security;

import co.ucompensar.smarttech.entity.AdminUser;
import co.ucompensar.smarttech.repository.AdminUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    @Bean
    CommandLineRunner initializeAdminUser(
            AdminUserRepository repository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            String username = getEnvironmentVariable(
                    "SMARTTECH_ADMIN_USERNAME",
                    "admin"
            );

            String email = getEnvironmentVariable(
                    "SMARTTECH_ADMIN_EMAIL",
                    "admin@smarttech.com"
            );

            String password = getEnvironmentVariable(
                    "SMARTTECH_ADMIN_PASSWORD",
                    "SmartTechAdmin123"
            );

            AdminUser adminUser = repository
                    .findByUsername(username)
                    .orElseGet(AdminUser::new);

            adminUser.setUsername(username);
            adminUser.setEmail(email);
            adminUser.setEnabled(true);

            if (adminUser.getPassword() == null
                    || !passwordEncoder.matches(
                            password,
                            adminUser.getPassword())) {

                adminUser.setPassword(
                        passwordEncoder.encode(password)
                );
            }

            repository.save(adminUser);

            System.out.println(
                    "Administrador verificado correctamente."
            );
        };
    }

    private String getEnvironmentVariable(
            String variableName,
            String defaultValue) {

        String value = System.getenv(variableName);

        if (value == null || value.isBlank()) {
            return defaultValue;
        }

        return value;
    }
}