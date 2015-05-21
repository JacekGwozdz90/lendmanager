package lendmanager.account;

import org.springframework.data.annotation.Id;

public class Account {

	@Id
	private String id;
	private String email;
	private String passwordHash;
	private Role role;

	
	public Account() {
	}
	
	public Account(String email, String passwordHash) {
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public Account(String email, String passwordHash, Role role) {
		this.email = email;
		this.passwordHash = passwordHash;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
