package com.mycompany.roadtripplanner.payload.response;

import java.util.Date;
import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String id;
	private String firstName;
	private String lastName;
	private Date birthday;
	private String profilPicture;
	private String email;
	private List<String> roles;

	public JwtResponse(String accessToken, String id,String firstName,String lastName,Date birthday,String profilPicture, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.firstName=firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.profilPicture = profilPicture;
		this.email = email;
		this.roles = roles;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getProfilPicture() {
		return profilPicture;
	}

	public void setProfilPicture(String profilPicture) {
		this.profilPicture = profilPicture;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public List<String> getRoles() {
		return roles;
	}
}
