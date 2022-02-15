package com.mycompany.roadtripplanner.security.services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.roadtripplanner.entities.Adress;
import com.mycompany.roadtripplanner.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Date birthday;
	private Adress Adress;
	private String profilPicture;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String firstName, String lastName, String email, Date birthday, com.mycompany.roadtripplanner.entities.Adress adress, String profilPicture, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		Adress = adress;
		this.profilPicture = profilPicture;
		this.password = password;
		this.authorities = authorities;
	}
	/*
	public UserDetailsImpl(String id, String firstName, String lastName, String email, Date birthday, String profilPicture, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthday = birthday;
		this.profilPicture = profilPicture;
		this.password = password;
		this.authorities = authorities;
	}*/
/*public UserDetailsImpl(String id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}*/

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail(),
				user.getBirthday(),
				user.getAdress(),
				user.getProfilPicture(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	public String getFirsName() {
		return firstName;
	}
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public Adress getAdress() {
		return Adress;
	}

	public void setAdress(Adress adress) {
		Adress = adress;
	}

	public String getLastName() {
		return lastName;
	}

	public Object getBirthday() {
		return birthday;
	}

	public String getProfilPicture() {
		return profilPicture;
	}
}
