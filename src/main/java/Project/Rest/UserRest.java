package Project.Rest;

import Project.Services.OwnerService;
import com.S63B.domain.Entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

import java.util.Collections;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by Nino Vrijman on 2-5-2017.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Controller
public class UserRest {
    @Autowired
    private OwnerService ownerService;

    /**
     * Gets a owners (users) personal information.
     * @param id The ID of a owner (user).
     * @return A response object containing all personal information of a owner (user).
     */
    @RequestMapping(method = RequestMethod.GET)
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
    
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public Response update(@RequestParam(value = "id") int id,
                           @RequestParam(value = "address") String address,
                           @RequestParam(value = "residence") String residence) {
        if (ownerService.update(id, address, residence)) {
            return Response.status(OK).entity(true).build();
        } else {
            return Response.status(BAD_REQUEST).entity(false).build();
        }
    }
}
