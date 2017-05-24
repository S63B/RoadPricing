package Project.model;

import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import org.joda.time.DateTime;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
public class CarDTO {
    private Car car;
    private DateTime purchaseDate;

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

    /**
     * Creates a Car Data Transfer Object which helps serializing a car object to JSON.
     * @param car A car instance which should be serialized.
     * @param ownership A car ownership record which should be serialized.
     */
    public CarDTO(Car car, Car_Ownership ownership) {
        this.car = car;
        this.purchaseDate = ownership.getPurchaseDate();
    }
}
