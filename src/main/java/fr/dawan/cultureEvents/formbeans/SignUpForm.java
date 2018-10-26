package fr.dawan.cultureEvents.formbeans;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import fr.dawan.cultureEvents.beans.User.Gender;

public class SignUpForm {

	@NotEmpty
	private String name;

	@NotEmpty
	private String gender;

	@NotEmpty
	@Pattern(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Invalid Email")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 50)
	private String password;

	@NotEmpty
	private String address;

	@NotEmpty
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private String dateOfBirth;

	public SignUpForm() {

	}

	public SignUpForm(String name, String gender, String email, String password, String address, String dateOfBirth) {
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}