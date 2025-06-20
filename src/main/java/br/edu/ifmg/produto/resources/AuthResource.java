package br.edu.ifmg.produto.resources;

import br.edu.ifmg.produto.dtos.NewPasswordDTO;
import br.edu.ifmg.produto.dtos.RequestTokenDTO;
import br.edu.ifmg.produto.entities.PasswordRecover;
import br.edu.ifmg.produto.entities.User;
import br.edu.ifmg.produto.repository.PasswordRecoverRepository;
import br.edu.ifmg.produto.repository.UserRepository;
import br.edu.ifmg.produto.services.AuthService;
import br.edu.ifmg.produto.services.UserService;
import br.edu.ifmg.produto.services.exceptions.ResourceNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody RequestTokenDTO dto){

        authService.createRecoverToken(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "new-password")
    public ResponseEntity<Void> createNewPassword(@Valid @RequestBody NewPasswordDTO dto){
        authService.saveNewPassword(dto);
        return ResponseEntity.noContent().build();
    }


}
