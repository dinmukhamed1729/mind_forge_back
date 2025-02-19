package din.kz.mind_forge_back.controller;


import din.kz.mind_forge_back.model.request.AuthRequest;
import din.kz.mind_forge_back.model.request.RegistrationRequest;
import din.kz.mind_forge_back.model.response.AuthResponse;
import din.kz.mind_forge_back.model.response.RegistrationResponse;
import din.kz.mind_forge_back.config.security.CustomUserDetailsService;
import din.kz.mind_forge_back.config.security.JwtUtil;
import din.kz.mind_forge_back.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RegistrationService registrationService;
    private final CustomUserDetailsService userDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        var user = userDetailsService.loadUserByUsername(authRequest.getUsername());
        var token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/registration")
    public RegistrationResponse register(@RequestBody RegistrationRequest registrationRequest) {
        System.out.println(registrationRequest.getPassword() + " " + registrationRequest.getUsername());
        return registrationService.registration(registrationRequest);
    }
}
