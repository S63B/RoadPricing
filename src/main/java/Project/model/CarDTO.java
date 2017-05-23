package Project.model;

import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.LicensePlate;
import com.S63B.domain.Enums.EnergyLabel;
import org.joda.time.DateTime;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
public class CarDTO {
    private int id;
    private LicensePlate licensePlate;
    private EnergyLabel energyLabel;
    private boolean isStolen;
    private DateTime purchaseDate;
    private int trackerId;

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

    public EnergyLabel getEnergyLabel() {
        return energyLabel;
    }

    public void setEnergyLabel(EnergyLabel energyLabel) {
        this.energyLabel = energyLabel;
    }

    public boolean isStolen() {
        return isStolen;
    }

    public void setStolen(boolean stolen) {
        isStolen = stolen;
    }

    public DateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(DateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(int trackerId) {
        this.trackerId = trackerId;
    }

    /**
     * Creates a Car Data Transfer Object which helps serializing a car object to JSON.
     * @param car A car instance which should be serialized.
     * @param ownership A car ownership record which should be serialized.
     */
    public CarDTO(Car car, Car_Ownership ownership) {
        this.id = car.getId();
        this.licensePlate = car.getLicensePlate();
        this.energyLabel = car.getEnergyLabel();
        this.isStolen = car.isStolen();
        this.purchaseDate = ownership.getPurchaseDate();
        if (car.getTracker() == null) {
            this.trackerId = -1;
        } else {
            this.trackerId = car.getTracker().getId();
        }
    }
}
