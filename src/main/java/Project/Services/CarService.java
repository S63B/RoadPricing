package Project.Services;

import Project.Dao.CarDao;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.LicensePlate;
import com.S63B.domain.Entities.Owner;
import com.S63B.domain.Enums.EnergyLabel;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Nino Vrijman on 16-5-2017.
 */
@Service
public class CarService {
    @Autowired
    private CarDao carDao;

    public Car getById(int id) {
        return carDao.getById(id);
    }

    public Car create(Owner carOwner, String licensePlate, DateTime expirationDate, DateTime purchaseDate, String energyLabel) {
        EnergyLabel carEnergyLabel = EnergyLabel.valueOf(energyLabel);
        Car newCar = new Car(new LicensePlate(licensePlate, expirationDate), carEnergyLabel);

        Car_Ownership newCarOwnership = new Car_Ownership(newCar, carOwner, purchaseDate);
        // perist car ownership

        List<Car_Ownership> ownedCarsByUser = carOwner.getOwnedCars();
        ownedCarsByUser.add(newCarOwnership);
        carOwner.setOwnedCars(ownedCarsByUser);
        // persist user

        return carDao.create(newCar);
    }

    public Car update(int carId, String licensePlate, String energyLabel, String trackerId) {
        return null;
    }
}
