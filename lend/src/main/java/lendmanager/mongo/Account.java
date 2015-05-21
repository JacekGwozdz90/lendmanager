package lendmanager.mongo;

import org.springframework.data.annotation.Id;

public class Account {

	@Id
	private String id;

	private String userName;
	private String passwordHash;

	public Account(String userName, String passwordHash) {
		this.userName = userName;
		this.passwordHash = passwordHash;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

}
