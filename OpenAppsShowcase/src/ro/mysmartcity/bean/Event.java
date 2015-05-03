package ro.mysmartcity.bean;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Event", indexes = { @Index(name = "name_idx", columnList = "name", unique = true),
		@Index(name = "startDate_idx", columnList = "startDate", unique = false),
		@Index(name = "endDate_idx", columnList = "endDate", unique = false), @Index(name = "location_idx", columnList = "location", unique = false),
		@Index(name = "status_idx", columnList = "status", unique = false) })
public class Event extends Base {

	@IsQueryParam
	@NotNull
	@Column(nullable = false)
	private String name;

	@Column(length = 1000, nullable = false)
	@NotNull
	private String description;

	@IsQueryParam
	private Date startDate;
	@IsQueryParam
	private Date endDate;

	@IsQueryParam
	private String location;

	@Column(length = 1000)
	private String agenda;
	private String awards;

	private String[] categories;
	private String eventWebPage;

	@IsQueryParam
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private STATUS status = STATUS.INACTIVE;

	public Event() {
		super();
	}

	public Event(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "Event [name=" + name + ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", location="
				+ location + ", agenda=" + agenda + ", awards=" + awards + ", categories=" + Arrays.toString(categories) + ", eventWebPage="
				+ eventWebPage + ", status=" + status + "]";
	}

	public static void main(String[] args) {
		try {
			Event.class.getDeclaredField("status");

			Event e = new Event();

			System.out.println(e);

			Event.class.getDeclaredField("status").set(e, STATUS.ACTIVE);

			System.out.println(e);
		} catch (Exception e) {
			// throw new
			// NoSuchMethodException("Activate not supported on Class: " +
			// entity.getName());
			e.printStackTrace();
		}
	}
}
