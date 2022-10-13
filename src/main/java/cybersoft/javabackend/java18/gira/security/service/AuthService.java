package cybersoft.javabackend.java18.gira.security.service;

import cybersoft.javabackend.java18.gira.common.exception.GiraBussinessException;
import cybersoft.javabackend.java18.gira.security.dto.LoginDTO;
import cybersoft.javabackend.java18.gira.security.jwt.JwtUtils;
import cybersoft.javabackend.java18.gira.user.model.User;
import cybersoft.javabackend.java18.gira.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface AuthService {
    String login(LoginDTO dto);
}

@Service
class AutServiceImpl implements AuthService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwt;

    AutServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtUtils jwt) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    @Override
    public String login(LoginDTO dto) {
        User user = repository.findByUsername(dto.getUsername())
                .orElseThrow(
                        () -> new GiraBussinessException("User is not existed")
                );
        if(passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return jwt.generateJwt(dto.getUsername());
        }
        throw new GiraBussinessException("Password is not correct");
    }
}
