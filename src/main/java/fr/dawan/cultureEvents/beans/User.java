package fr.dawan.cultureEvents.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class User implements Serializable {

	public enum Gender {
		M, MME
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", length = 55, nullable = false)
	private String name;
	@Column(name = "gender", length = 55, nullable = false)
	private Gender gender;
	@Column(name = "email", length = 55, nullable = false)
	private String email;
	@Column(name = "password", length = 55, nullable = false)
	private String password;
	@Column(name = "admin", nullable = false)
	private boolean admin;
	@Column(name = "address", length = 55, nullable = false)
	private String address;

	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Version
	private int version;

	// Events sauvegardés:
	//@ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="user_eventsid")
	@Column(name="eventsId")
	private List<Integer> eventsId;

	public User() {
		eventsId = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
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

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<Integer> getEventsId() {
		return eventsId;
	}

	public void setEventsId(List<Integer> eventsId) {
		this.eventsId = eventsId;
	}

	public void setId(long id) {
		this.id = id;
	}

	

}
