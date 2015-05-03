package ro.mysmartcity.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class Base {

	public static final String DATE_TIME_PATTERN = "dd.MM.yyyy H:mm";
	public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat(DATE_TIME_PATTERN);

	public enum LICENSE {
		FREE, COMMERCIAL, OTHER
	}

	public enum RIGHT {
		ADMIN, USER
	}

	public enum STATE {
		PROPOSED, STARTED, ALPHA, BETA, FINAL
	}

	public enum STATUS {
		ACTIVE, INACTIVE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date created;
	private Date updated;

	@PrePersist
	protected void onCreate() {
		created = updated = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public static boolean hasActivate(final Class<? extends Base> entityClass) {

		try {
			entityClass.getDeclaredMethod("setStatus", STATUS.class);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
