package Project.Services;

import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import com.S63B.domain.Entities.LicensePlate;
import com.S63B.domain.Entities.Owner;
import com.S63B.domain.Enums;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Nino Vrijman on 23-5-2017.
 */
@Service
@Transactional
public class CarManagementService {
    private CarService carService;
    private CarOwnerService carOwnerService;
    private OwnerService ownerService;
    private LicensePlateService licensePlateService;

    @Autowired
    public CarManagementService(CarService carService, CarOwnerService carOwnerService, OwnerService ownerService, LicensePlateService licensePlateService) {
        this.carService = carService;
        this.carOwnerService = carOwnerService;
        this.ownerService = ownerService;
        this.licensePlateService = licensePlateService;
    }

    public Car create(String licensePlateString, String licenseExpirationDateString, String energylabel, int ownerId, String carPurchaseDateString) {
        LicensePlate licensePlate = licensePlateService.findByLicense(licensePlateString);
        if (licensePlate == null) {
            licensePlate = new LicensePlate(licensePlateString, DateTime.parse(licenseExpirationDateString, DateTimeFormat.forPattern("DD-MM-YYYY")));
        }
        licensePlate = licensePlateService.create(licensePlate);

        Car newCar = new Car(licensePlate, Enums.EnergyLabel.valueOf(energylabel));
        newCar = carService.create(newCar);

        Owner carOwner = ownerService.getById(ownerId);

        Car_Ownership carOwnership = new Car_Ownership(newCar, carOwner, DateTime.parse(carPurchaseDateString, DateTimeFormat.forPattern("DD-MM-YYYY")));
        carOwnership = carOwnerService.create(carOwnership);

        List<Car_Ownership> ownedCars = carOwner.getOwnedCars();
        ownedCars.add(carOwnership);
        carOwner.setOwnedCars(ownedCars);
        ownerService.update(carOwner);

        return newCar;
    }

    public Car update() {
        return null;
    }
}
