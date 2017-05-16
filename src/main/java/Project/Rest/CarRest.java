package Project.Rest;

import Project.Services.CarService;
import Project.Services.OwnerService;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by Nino Vrijman on 16-5-2017.
 */
@RestController
@RequestMapping("/car")
@Controller
public class CarRest {
    @Autowired
    private CarService carService;
    @Autowired
    private OwnerService ownerService;

    @RequestMapping(method = RequestMethod.GET)
    public Response getById(@RequestParam(value = "id") int id) {
        Car car = carService.getById(id);
        return Response.status(OK).entity(car).build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response create( @RequestParam(value = "userId") int userId,
                            @RequestParam(value = "licensePlate") String licensePlate,
                            @RequestParam(value = "expirationDate") String expirationDate,
                            @RequestParam(value = "purchaseDate") String purchaseDate,
                            @RequestParam(value = "energylabel") String energyLabel) {
        Owner owner = ownerService.getById(userId);
        Car newCar = carService.create(owner, licensePlate, new DateTime(expirationDate), new DateTime(purchaseDate), energyLabel);
        if (newCar == null) {
            return Response.status(BAD_REQUEST).entity(newCar).build();
        } else {
            return Response.status(OK).entity(newCar).build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Response update( @RequestParam(value = "carId") int carId,
                            @RequestParam(value = "licensePlate") String licensePlate,
                            @RequestParam(value = "expirationDate") String expirationDate,
                            @RequestParam(value = "purchaseDate") String purchaseDate,
                            @RequestParam(value = "energylabel") String energyLabel) {
        Car updatedCar = carService.update(carId, licensePlate, energyLabel, trackerId);
        if (updatedCar == null) {
            return Response.status(BAD_REQUEST).entity(updatedCar).build();
        } else {
            return Response.status(OK).entity(updatedCar).build();
        }
    }
}
