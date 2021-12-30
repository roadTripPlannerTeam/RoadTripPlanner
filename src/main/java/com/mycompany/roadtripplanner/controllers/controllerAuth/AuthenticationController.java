package com.mycompany.roadtripplanner.controllers.controllerAuth;

import com.mycompany.roadtripplanner.configurations.Auth.JwtTokenUtil;
import com.mycompany.roadtripplanner.payload.JwtRequest;
import com.mycompany.roadtripplanner.payload.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(name = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) throws Exception {
            authenticate(authRequest.getUsername(), authRequest.getPassword());
            final UserDetails userDetails = userDetailsService.loadUserByUsername(
                    authRequest.getUsername()
            );
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User desactivited");
        } catch (BadCredentialsException e) {
            throw new Exception("Username or password is incorrect");
        }
    }

}
