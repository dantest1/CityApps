package ro.mysmartcity.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Description(description = "Project (often a software project, but not necessary) details")
@Entity
@Table(name = "Project", indexes = { @Index(name = "name_idx", columnList = "name", unique = false),
		@Index(name = "eventURL_idx", columnList = "eventURL", unique = false),
		@Index(name = "category_idx", columnList = "category", unique = false),
		@Index(name = "technologies_idx", columnList = "technologies", unique = false),
		@Index(name = "license_idx", columnList = "license", unique = false), @Index(name = "state_idx", columnList = "state", unique = false),
		@Index(name = "status_idx", columnList = "status", unique = false) })
public class Project extends Base {

	@Description(description = "The name of the project")
	@IsQueryParam
	@NotNull
	@Column(nullable = false)
	private String name;

	@Description(description = "Project description")
	@Column(length = 1000, nullable = false)
	@NotNull
	private String description;

	@Description(description = "Event where the project was developed")
	@IsQueryParam
	private String eventURL;

	@Description(description = "Project category in the event")
	@IsQueryParam
	@Column(length = 50)
	private String category;

	@Description(description = "Who are the beneficiaries of the project")
	@Column(length = 1000)
	private String beneficiaries;

	@Description(description = "Open Data Series used in the project")
	private String openDataSeries;

	@Description(description = "Project functionalities")
	@Column(length = 1000)
	private String functionalities;

	@Description(description = "Technologies used in the project")
	@IsQueryParam
	private String technologies;

	@Description(description = "Online url where the project is available")
	@Column(length = 100)
	private String onlineURL;

	@Description(description = "Github url of the project")
	@Column(length = 100)
	private String gitURL;

	@Description(description = "Project license")
	@Enumerated(EnumType.STRING)
	@IsQueryParam
	@Column(length = 50)
	private LICENSE license;

	@Description(description = "Project state")
	@Enumerated(EnumType.STRING)
	@IsQueryParam
	@Column(length = 20, nullable = false)
	private STATE state;

	@Description(description = "Project awards")
	private String award;

	@Description(description = "Project entity status. By default INACTIVE, till it will be activated by platform administrator")
	@IsQueryParam
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private STATUS status = STATUS.INACTIVE;

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

	public String getAward() {
		return award;
	}

	public String getBeneficiaries() {
		return beneficiaries;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getFunctionalities() {
		return functionalities;
	}

	public String getGitURL() {
		return gitURL;
	}

	public LICENSE getLicense() {
		return license;
	}

	public String getName() {
		return name;
	}

	public String getOnlineURL() {
		return onlineURL;
	}

	public String getOpenDataSeries() {
		return openDataSeries;
	}

	public STATE getState() {
		return state;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public void setBeneficiaries(String beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFunctionalities(String functionalities) {
		this.functionalities = functionalities;
	}

	public void setGitURL(String gitURL) {
		this.gitURL = gitURL;
	}

	public void setLicense(LICENSE license) {
		this.license = license;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOnlineURL(String onlineURL) {
		this.onlineURL = onlineURL;
	}

	public void setOpenDataSeries(String openDataSeries) {
		this.openDataSeries = openDataSeries;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}

	public String getEventURL() {
		return eventURL;
	}

	public void setEventURL(String eventURL) {
		this.eventURL = eventURL;
	}

	@Override
	public String toString() {
		return "Project [name=" + name + ", description=" + description + ", eventURL=" + eventURL + ", category=" + category + ", beneficiaries="
				+ beneficiaries + ", openDataSeries=" + openDataSeries + ", functionalities=" + functionalities + ", technologies=" + technologies
				+ ", onlineURL=" + onlineURL + ", gitURL=" + gitURL + ", license=" + license + ", state=" + state + ", award=" + award + ", status="
				+ status + "]";
	}

}
