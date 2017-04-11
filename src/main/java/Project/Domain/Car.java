package Project.Domain;

/**
 * Created by Nekkyou on 11-4-2017.
 */
public class Car {
	private int id;
	private LicensePlate licensePlate;
	private Rate rate;
	private char energyLabel;
	private boolean isStolen;

	//region Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LicensePlate getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(LicensePlate licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public char getEnergyLabel() {
		return energyLabel;
	}

	public void setEnergyLabel(char energyLabel) {
		this.energyLabel = energyLabel;
	}

	public boolean isStolen() {
		return isStolen;
	}

	public void setStolen(boolean stolen) {
		isStolen = stolen;
	}

	//endregion
}
