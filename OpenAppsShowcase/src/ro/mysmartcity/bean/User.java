package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "User", indexes = { @Index(name = "email_idx", columnList = "email", unique = true),
		@Index(name = "name_idx", columnList = "name", unique = false) })
public class User extends Base {

	@IsQueryParam
	@NotNull
	@Column(unique = true, length = 50, nullable = false)
	private String email;

	@IsQueryParam
	@Column(length = 50, nullable = false)
	@NotNull
	private String name;

	private String description;

	@Column(length = 20)
	private String phone;

	@Column(length = 100)
	private String facebook;

	@Column(length = 100)
	private String linkedin;

	@Column(length = 100)
	private String git;

	public String getDescription() {
		return description;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getGit() {
		return git;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public String getPhone() {
		return phone;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setGit(String git) {
		this.git = git;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", description=" + description + ", phone=" + phone + ", facebook=" + facebook
				+ ", linkedin=" + linkedin + ", git=" + git + "]";
	}

}
