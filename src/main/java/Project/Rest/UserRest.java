package Project.Rest;

import Project.Services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by Nino Vrijman on 2-5-2017.
 */
@RestController
@RequestMapping("/user")
@Controller
public class UserRest {
    @Autowired
    private InvoiceService service;

    /**
     * Gets a users personal information.
     * @param id The ID of a user.
     * @return A response object containing all personal information of a user.
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Response getUserInfo(@RequestParam(value = "id") int id) {
        return Response.status(OK).entity("Test rest method: " + id).build();
    }
}
