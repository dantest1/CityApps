package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "OrganizationEntity")
public class OrganizationEntity extends Base {

	@IsQueryParam
	@Column(nullable = false)
	private String organizationURL;
	@IsQueryParam
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

	public String getOrganizationURL() {
		return organizationURL;
	}

	public RIGHT getRightInEntity() {
		return rightInEntity;
	}

	public String getRoleInEntity() {
		return roleInEntity;
	}

	public void setEntityURL(String entityURL) {
		this.entityURL = entityURL;
	}

	public void setOrganizationURL(String organizationURL) {
		this.organizationURL = organizationURL;
	}

	public void setRightInEntity(RIGHT rightInEntity) {
		this.rightInEntity = rightInEntity;
	}

	public void setRoleInEntity(String roleInEntity) {
		this.roleInEntity = roleInEntity;
	}

}
