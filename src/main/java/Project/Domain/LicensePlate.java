package Project.Domain;

import java.time.LocalDate;

/**
 * Created by Nekkyou on 11-4-2017.
 */
public class LicensePlate {
	private int id;
	private String license;
	private LocalDate expirationDate;

	//region Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	//endregion


}
