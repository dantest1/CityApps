package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Organization", indexes = { @Index(name = "name_idx", columnList = "name", unique = true) })
public class Organization extends Base {

	@IsQueryParam
	@NotNull
	@Column(length = 100, unique = true)
	private String name;
	private String description;

	@Column(length = 100)
	private String website;

	@Column(length = 100)
	private String facebook;

	public String getDescription() {
		return description;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getName() {
		return name;
	}

	public String getWebsite() {
		return website;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

}
