package ro.mysmartcity.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "Event")
public class Event extends Base {

	@IsQueryParam
	@Column(nullable = false)
	private String name;

	@Column(length = 1000, nullable = false)
	private String description;

	private Date startDate;
	private Date endDate;

	@IsQueryParam
	private String location;

	@Column(length = 1000)
	private String agenda;
	private String awards;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private STATUS status = STATUS.INACTIVE;

	private String[] categories;
	private String eventWebPage;

	public Event() {
		super();
	}

	public Event(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public String getAgenda() {
		return agenda;
	}

	public String getAwards() {
		return awards;
	}

	public String[] getCategories() {
		return categories;
	}

	public String getDescription() {
		return description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getEventWebPage() {
		return eventWebPage;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEventWebPage(String eventWebPage) {
		this.eventWebPage = eventWebPage;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

}
