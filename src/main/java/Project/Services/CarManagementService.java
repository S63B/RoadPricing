package Project.Services;

import com.S63B.domain.Entities.*;
import com.S63B.domain.Enums.EnergyLabel;
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
    private CarOwnerService ownershipService;
    private OwnerService ownerService;
    private LicensePlateService licensePlateService;
    private TrackerService trackerService;

    @Autowired
    public CarManagementService(CarService carService, CarOwnerService ownershipService, OwnerService ownerService, LicensePlateService licensePlateService, TrackerService trackerService) {
        this.carService = carService;
        this.ownershipService = ownershipService;
        this.ownerService = ownerService;
        this.licensePlateService = licensePlateService;
        this.trackerService = trackerService;
    }

    public Car create(String licensePlateString, String licenseExpirationDateString, String energylabel, int ownerId, String carPurchaseDateString, String trackerSerialNumber) {
        LicensePlate licensePlate = licensePlateService.findByLicense(licensePlateString);
        if (licensePlate == null) {
            licensePlate = createLicensePlate(licensePlateString, DateTime.parse(licenseExpirationDateString, DateTimeFormat.forPattern("DD-MM-YYYY")));
        }

        Car newCar = new Car(licensePlate, EnergyLabel.valueOf(energylabel));
        newCar = carService.create(newCar);

        Tracker carTracker = trackerService.findBySerialNumber(trackerSerialNumber);
        if (carTracker == null) {
            carTracker = createCarTracker(trackerSerialNumber, "NETHERLANDS", newCar);
        }
        newCar.setTracker(carTracker);

        Owner carOwner = ownerService.getById(ownerId);

        Car_Ownership carOwnership = new Car_Ownership(newCar, carOwner, DateTime.parse(carPurchaseDateString, DateTimeFormat.forPattern("DD-MM-YYYY")));
        carOwnership = ownershipService.create(carOwnership);

        List<Car_Ownership> ownedCars = carOwner.getOwnedCars();
        ownedCars.add(carOwnership);
        carOwner.setOwnedCars(ownedCars);
        ownerService.update(carOwner);

        return newCar;
    }

    public LicensePlate createLicensePlate(String licensePlateString, DateTime licenseExpirationDate) {
        LicensePlate newLicensePlate = new LicensePlate(licensePlateString, licenseExpirationDate);
        return licensePlateService.create(newLicensePlate);
    }

    public Tracker createCarTracker(String serialNumber, String country, Car car) {
        Tracker newTracker = new Tracker(serialNumber, country);
        newTracker.setCar(car);
        return this.trackerService.create(newTracker);
    }

    public Car update(int carId, String licensePlateString, String licenseExpirationDateString, String carPurchaseDate, String energyLabel, String trackerSerialNumber) {
        Car updatedCar = carService.getById(carId);
        Car_Ownership ownership = ownershipService.getLatest(updatedCar);

        DateTime licenseExpirationDate = DateTime.parse(licenseExpirationDateString, DateTimeFormat.forPattern("DD-MM-YYYY"));
        if (hasLicensePlateChanged(updatedCar.getLicensePlate(), licensePlateString, licenseExpirationDate)) {
            LicensePlate updatedLicensePlate = licensePlateService.findByLicense(licensePlateString);
            if (updatedLicensePlate == null) {
                updatedLicensePlate = createLicensePlate(licensePlateString, licenseExpirationDate);
            }
            updatedCar.setLicensePlate(updatedLicensePlate);
        }

        DateTime purchaseDate = DateTime.parse(carPurchaseDate, DateTimeFormat.forPattern("DD-MM-YYYY"));
        if (ownership.getPurchaseDate().getMillis() != purchaseDate.getMillis()) {
            ownership.setPurchaseDate(purchaseDate);
            ownershipService.update(ownership);
        }

        if (!updatedCar.getEnergyLabel().equals(EnergyLabel.valueOf(energyLabel))) {
            updatedCar.setEnergyLabel(EnergyLabel.valueOf(energyLabel));
        }

        if (updatedCar.getTracker() == null
                || updatedCar.getTracker().getSerialNumber() == null
                || !updatedCar.getTracker().getSerialNumber().equals(trackerSerialNumber)) {
            Tracker carTracker = trackerService.findBySerialNumber(trackerSerialNumber);
            if (carTracker == null) {
                carTracker = createCarTracker(trackerSerialNumber, "NETHERLANDS", updatedCar);
            }
            updatedCar.setTracker(carTracker);
        }

        return this.carService.update(updatedCar);
    }

    public boolean hasLicensePlateChanged(LicensePlate oldLicensePlate, String newLicensePlateString, DateTime newExpirationDate) {
        return !oldLicensePlate.getLicense().equals(newLicensePlateString)
                || oldLicensePlate.getExpirationDate().getMillis() != newExpirationDate.getMillis();
    }
}
