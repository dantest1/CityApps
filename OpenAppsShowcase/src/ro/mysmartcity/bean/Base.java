package ro.mysmartcity.bean;

import java.text.SimpleDateFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Base {

	public static final SimpleDateFormat DATE_PATTERN = new SimpleDateFormat("dd.MM.yyyy");
	public static final SimpleDateFormat DATE_TIME_PATTERN = new SimpleDateFormat("dd.MM.yyyy H:mm");

	public enum LICENSE {
		CC, FREE, COMMERCIAL, OTHER
	}

	public enum RIGHT {
		ADMIN, USER
	}

	public enum STATE {
		NOT_STARTED, STARTED, ALPHA, BETA, FINAL
	}

	public enum STATUS {
		ACTIVE, INACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
