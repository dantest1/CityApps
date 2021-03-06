package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "OrganizationEntity", indexes = { @Index(name = "organizationURL_idx", columnList = "organizationURL", unique = false),
		@Index(name = "entityURL_idx", columnList = "entityURL", unique = false) })
public class OrganizationEntity extends Base {

	@IsQueryParam
	@NotNull
	@Column(nullable = false)
	private String organizationURL;
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
