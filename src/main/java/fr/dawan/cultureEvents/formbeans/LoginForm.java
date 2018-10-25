package fr.dawan.cultureEvents.formbeans;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
	
	@NotEmpty
	@Pattern(regexp="\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message="Invalid Email")
	private String username;
	
	@NotEmpty
	@Size(min=4, max=50)
	private String password;

	
	public LoginForm(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public LoginForm() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
