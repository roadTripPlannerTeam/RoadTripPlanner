package com.mycompany.roadtripplanner.controllers.controllerAuth;


import com.mycompany.roadtripplanner.entities.User;
import com.mycompany.roadtripplanner.entities.roles.ERole;
import com.mycompany.roadtripplanner.entities.roles.Role;
import com.mycompany.roadtripplanner.payload.request.LoginRequest;
import com.mycompany.roadtripplanner.payload.request.SignupRequest;
import com.mycompany.roadtripplanner.payload.response.JwtResponse;
import com.mycompany.roadtripplanner.payload.response.MessageResponse;
import com.mycompany.roadtripplanner.repositories.AuthRepository.RoleRepository;
import com.mycompany.roadtripplanner.repositories.AuthRepository.UserRepository;
import com.mycompany.roadtripplanner.security.jwt.JwtUtils;
import com.mycompany.roadtripplanner.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepositoryAuth;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		System.out.println(jwt);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getFirsName(),
												 userDetails.getLastName(),
												(Date) userDetails.getBirthday(),
												 userDetails.getProfilPicture(),
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		System.out.println(signUpRequest);

		if (userRepositoryAuth.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstName(),
							 signUpRequest.getLastName(),
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
		  					 signUpRequest.getBirthday(),
							 signUpRequest.getAdress(),
							 signUpRequest.getProfilPicture());
		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {;
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."+ user));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role admin is not found."+strRoles+roles + user));

					roles.add(adminRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role user is not found."+roles+ user));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepositoryAuth.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
