package ro.mysmartcity.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Base {

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
