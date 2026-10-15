package uz.pdp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.dao.AuthUserDao;
import uz.pdp.dto.RegisterDTO;
import uz.pdp.entity.AuthUser;
import uz.pdp.enums.Role;

import java.util.Optional;

@Component
public class AuthService {


    private final AuthUserDao authUserDao;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthUserDao authUserDao, PasswordEncoder passwordEncoder) {
        this.authUserDao = authUserDao;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterDTO dto) {
        Optional<AuthUser> optional = authUserDao.findByUsername(dto.username());
        if (optional.isPresent()) return;

      AuthUser authUser = AuthUser.builder()
                .fullName(dto.fullName())
                .username(dto.username())
                .password(passwordEncoder.encode( dto.password() ) )
                .role(Role.USER)
                .build();
        authUserDao.save(authUser);
    }
}
