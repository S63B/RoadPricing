package Project.Rest;

import Project.Services.AuthenticationService;
import Project.Services.CarOwnerService;
import Project.Services.OwnerService;
import com.S63B.domain.Entities.Car;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by Nino Vrijman on 2-5-2017.
 */
@RestController
@RequestMapping("/owner")
@CrossOrigin(origins = "*")
@Controller
public class OwnerRest {
    private OwnerService ownerService;
    private CarOwnerService carOwnerService;
    private AuthenticationService authService;

    @Autowired
    public OwnerRest(OwnerService ownerService, CarOwnerService carOwnerService, AuthenticationService authService) {
        this.ownerService = ownerService;
        this.carOwnerService = carOwnerService;
        this.authService = authService;
    }

    /**
     * Gets a owners (users) personal information.
     * @param id The ID of a owner (user).
     * @return A response object containing all personal information of a owner (user).
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getUserInfo(@RequestParam(value = "id") int id) {
        Object result = null;
        Owner owner = ownerService.getById(id);
        if (owner != null) {
            owner.setOwnedCars(Collections.emptyList());
            owner.setInvoices(Collections.emptyList());
            result = owner;
        }
        return Response.status(OK).entity(result).build();
    }

    /**
     * Updates an existing owner
     * @param id The id of the owner.
     * @param address The updated address of the owner.
     * @param residence The updated residence of the owner.
     * @return A Response object containing the updated owner.
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public Response update(@RequestParam(value = "id") int id,
                           @RequestParam(value = "address") String address,
                           @RequestParam(value = "residence") String residence) {
        Owner originalOwner = ownerService.getById(id);
        originalOwner.setAddress(address);
        originalOwner.setResidence(residence);
        Owner updatedOwner = ownerService.update(originalOwner);
        if (updatedOwner == null) {
            return Response.status(OK).entity(true).build();
        } else {
            return Response.status(BAD_REQUEST).entity(false).build();
        }
    }

    /**
     * Gets all cars from a owner.
     * @param ownerId The id of the owner.
     * @return A ResponseEntity containing the cars owned by the given owner.
     */
    @RequestMapping(value = "{ownerId}/cars", method = RequestMethod.GET)
    public ResponseEntity<List<Car>> getByCarOwner(@PathVariable("ownerId") int ownerId) {
        Owner owner = ownerService.getById(ownerId);
        HttpStatus status;
        if (owner == null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(Collections.emptyList(), status);
        } else {
            List<Car> ownedCars = carOwnerService.getCarsByOwner(owner);
            status = HttpStatus.OK;
            return new ResponseEntity<>(ownedCars, status);
        }
    }

    /**
     * Get the currently logged in owner
     * @return The logged in Owner
     */
    @RequestMapping(value = "loggedin", method = RequestMethod.GET)
    public ResponseEntity<Owner> getLoggedinUser() {
        String loggedInUser = authService.getLoggedinUser();
        Owner owner = ownerService.getByUsername(loggedInUser);

        HttpStatus status;
        if (owner != null) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(owner, status);
    }

    /**
     * Update the password from the logged in owner
     * @param password The new password
     * @return the owner object which password has been changed
     */
    @RequestMapping(value = "alterpassword", method = RequestMethod.POST)
    public ResponseEntity<Owner> updatePassword(@RequestParam("password") String password) {
        String loggedInUser = authService.getLoggedinUser();
        Owner owner = ownerService.getByUsername(loggedInUser);

        String hashedPassword = authService.encodeString(password);
        owner.setPassword(hashedPassword);
        ownerService.update(owner);

        HttpStatus status;
        if (owner != null) {
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<>(owner, status);
    }
}
