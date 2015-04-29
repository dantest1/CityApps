package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UserEntity")
public class UserEntity extends Base {

	@IsQueryParam
	@NotNull
	@Column(nullable = false)
	private String userURL;
	@IsQueryParam
	@NotNull
	@Column(nullable = false)
	private String entityURL;

	@Column(length = 100)
	private String roleInEntity;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private RIGHT rightInEntity;

	public String getEntityURL() {
		return entityURL;
	}

	public RIGHT getRightInEntity() {
		return rightInEntity;
	}

	public String getRoleInEntity() {
		return roleInEntity;
	}

	public String getUserURL() {
		return userURL;
	}

	public void setEntityURL(String entityURL) {
		this.entityURL = entityURL;
	}

	public void setRightInEntity(RIGHT rightInEntity) {
		this.rightInEntity = rightInEntity;
	}

	public void setRoleInEntity(String roleInEntity) {
		this.roleInEntity = roleInEntity;
	}

	public void setUserURL(String userURL) {
		this.userURL = userURL;
	}

}
