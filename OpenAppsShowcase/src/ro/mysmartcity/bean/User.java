package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User", indexes = { @Index(name = "email_idx", columnList = "email", unique = true),
		@Index(name = "firstname_idx", columnList = "firstname", unique = false),
		@Index(name = "lastname_idx", columnList = "lastname", unique = false) })
public class User extends Base {

	@IsQueryParam
	@NotNull
	@Column(unique = true, length = 50, nullable = false)
	private String email;

	@IsQueryParam
	@Column(length = 50, nullable = false)
	@NotNull
	private String firstname;

	@IsQueryParam
	@Column(length = 50, nullable = false)
	@NotNull
	private String lastname;

	private String description;

	@Column(length = 20)
	private String phone;

	@Column(length = 100)
	private String facebook;

	@Column(length = 100)
	private String linkedin;

	@Column(length = 100)
	private String git;

	private boolean platformAdmin;

	public String getDescription() {
		return description;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getGit() {
		return git;
	}

	public String getLastname() {
		return lastname;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public String getPhone() {
		return phone;
	}

	public boolean isPlatformAdmin() {
		return platformAdmin;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setGit(String git) {
		this.git = git;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPlatformAdmin(boolean platformAdmin) {
		this.platformAdmin = platformAdmin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
