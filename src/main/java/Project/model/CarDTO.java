package Project.model;

import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.Tracker;
import org.joda.time.DateTime;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
public class CarDTO {
    private Car car;
    private DateTime purchaseDate;
    private Tracker tracker;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public DateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(DateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Creates a Car Data Transfer Object which helps serializing a car object to JSON.
     * @param car A car instance which should be serialized.
     * @param ownership A car ownership record which should be serialized.
     */
    public CarDTO(Car car, Car_Ownership ownership) {
        this.car = car;
        this.purchaseDate = ownership.getPurchaseDate();
        if (car.getTracker() == null) {
            this.tracker = new Tracker();
        } else {
            this.tracker = car.getTracker();
        }
    }
}
