package Project.Domain;

import java.time.LocalDate;

/**
 * Created by Nekkyou on 11-4-2017.
 */
public class Car_Ownership {
	private Car car;
	private User user;
	private LocalDate purchaseDate;

	public Car_Ownership(Car car, User user, LocalDate purchaseDate) {
		this.car = car;
		this.user = user;
		this.purchaseDate = purchaseDate;
	}

	//region Getters and setters
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	//endregion
}
