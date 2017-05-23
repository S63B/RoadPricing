package Project.Rest;

import Project.Services.CarManagementService;
import Project.Services.CarOwnerService;
import Project.Services.CarService;
import Project.Services.OwnerService;
import Project.model.CarDTO;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Car_Ownership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Nino Vrijman on 16-5-2017.
 */
@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*")
@Controller
public class CarRest {
    private CarService carService;
    private OwnerService ownerService;
    private CarOwnerService carOwnershipService;
    private CarManagementService carManagementService;

    @Autowired
    public CarRest(CarService carService, OwnerService ownerService, CarOwnerService carOwnershipService, CarManagementService carManagementService) {
        this.carService = carService;
        this.ownerService = ownerService;
        this.carOwnershipService = carOwnershipService;
        this.carManagementService = carManagementService;
    }

    /**
     * Gets a car based on it's id.
     * @param id The id of the car.
     * @return A ResponseEntity containing the car with the given id.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@RequestParam(value = "id") int id) {
        Object result;
        Car car = carService.getById(id);
        HttpStatus status;
        if (car == null) {
            status = HttpStatus.BAD_REQUEST;
            result = car;
        } else {
            status = HttpStatus.OK;
            Car_Ownership latestOwnership = carOwnershipService.getLatest(car);
            CarDTO carDTO = new CarDTO(car, latestOwnership);
            result = carDTO;
        }
        return new ResponseEntity<>(result, status);
    }

    /**
     * Creates a new car.
     * @param ownerId The id of the owner of the car.
     * @param licensePlateString The license of the license plate.
     * @param licenseExpirationDate The expiration date of the license plate
     * @param carPurchaseDate The purchase date of the car.
     * @param energyLabel The energy label of the car.
     * @return A ResponseEntity containing the created car.
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Car> create(  @RequestParam(value = "userId") int ownerId,
                                        @RequestParam(value = "licensePlate") String licensePlateString,
                                        @RequestParam(value = "expirationDate") String licenseExpirationDate,
                                        @RequestParam(value = "purchaseDate") String carPurchaseDate,
                                        @RequestParam(value = "energylabel") String energyLabel) {
        Car createdCar = carManagementService.create(licensePlateString, licenseExpirationDate, energyLabel, ownerId, carPurchaseDate);
        HttpStatus status = createdCar == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        System.out.println(createdCar);
        return new ResponseEntity<>(createdCar, status);
    }

    /**
     * Updates an existing car.
     * @param carId The id of the car which should be updated.
     * @param licensePlateString The license of the license plate.
     * @param licenseExpirationDate The expiration date of the license plate
     * @param carPurchaseDate The purchase date of the car.
     * @param energyLabel The energy label of the car.
     * @param trackerId The id of the tracker that is being used by the car.
     * @return A ResponseEntity containing the updated car.
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Car> update( @RequestParam(value = "carId") int carId,
                            @RequestParam(value = "licensePlate") String licensePlateString,
                            @RequestParam(value = "expirationDate") String licenseExpirationDate,
                            @RequestParam(value = "purchaseDate") String carPurchaseDate,
                            @RequestParam(value = "energylabel") String energyLabel,
                            @RequestParam(value = "trackerId") String trackerId) {
        Car updatedCar = carService.update(carId, licensePlateString, energyLabel, trackerId);
        HttpStatus status = updatedCar == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(updatedCar, status);
    }
}
