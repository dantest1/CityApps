package ro.mysmartcity.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Description(description = "Event entity contains all information about the organized event (e.g. hackathon)")
@Entity
@Table(name = "Event", indexes = { @Index(name = "name_idx", columnList = "name", unique = true),
		@Index(name = "startDate_idx", columnList = "startDate", unique = false),
		@Index(name = "endDate_idx", columnList = "endDate", unique = false), @Index(name = "city_idx", columnList = "city", unique = false),
		@Index(name = "status_idx", columnList = "status", unique = false) })
public class Event extends Base {

	@IsQueryParam
	@Description(description = "Name of the organized event. Should be unique in the entire platform. Include also the year, for periodic events, in order to differentiate them")
	@NotNull
	@Column(nullable = false)
	private String name;

	@Description(description = "Event description")
	@Column(length = 1000, nullable = false)
	@NotNull
	private String description;

	@Description(description = "Event starte date, including time")
	@IsQueryParam
	private Date startDate;

	@Description(description = "Event end/finish date, including time")
	@IsQueryParam
	private Date endDate;

	@Description(description = "City where the event is organized")
	@IsQueryParam
	private String city;

	@Description(description = "Address/location where the event is organized")
	@IsQueryParam
	private String location;

	@Description(description = "Detailed agenda")
	@Column(length = 1000)
	private String agenda;

	@Description(description = "Event awards - first 3 places")
	private String awards;

	@Description(description = "Main categories of event. E.g. Web, Smart City, Mobile, Hardware ...")
	private String[] categories;

	@Description(description = "Web page of the event")
	private String eventWebPage;

	@Description(description = "Status of the entity, which is INACTIVE, by default, till its activation")
	@IsQueryParam
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
