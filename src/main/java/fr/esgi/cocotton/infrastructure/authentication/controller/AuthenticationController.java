package fr.esgi.cocotton.infrastructure.authentication.controller;

import fr.esgi.cocotton.application.authentication.Login;
import fr.esgi.cocotton.application.authentication.Register;
import fr.esgi.cocotton.application.authentication.dto.RegisterDTO;
import fr.esgi.cocotton.application.authentication.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final Login login;
    private final Register register;

    @Autowired
    public AuthenticationController(Login login,
                                    Register register) {
        this.login = login;
        this.register = register;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        HttpHeaders httpHeaders = login.execute(loginDTO);
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<?> save(@Valid @RequestBody RegisterDTO registerDTO) {
        URI uri = register.execute(registerDTO);
        return ResponseEntity.created(uri).build();
    }
}
