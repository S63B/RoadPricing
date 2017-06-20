package Project.Rest;

import Project.Services.InvoiceService;
import Project.Services.OwnerService;
import com.S63B.domain.Entities.Invoice;
import com.S63B.domain.Entities.Owner;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Stan
 */
@RestController
@Controller
@CrossOrigin(origins = "*")
public class InvoiceRest {

	private OwnerService ownerService;
	private final InvoiceService invoiceService;

	@Autowired
	public InvoiceRest(InvoiceService invoiceService, OwnerService ownerService) {
		this.invoiceService = invoiceService;
		this.ownerService = ownerService;
	}

	@RequestMapping(value = "/invoices", method = RequestMethod.GET)
	public Response getInvoicesFromUser(@RequestParam(value = "user") int userId) {
		Owner foundOwner = ownerService.getById(userId);
		List<Invoice> invoices = invoiceService.getInvoicesFromOwner(foundOwner);
		return Response.ok().entity(invoices).build();
	}

	@RequestMapping(value = "/invoices/{user_id}", method = RequestMethod.POST)
	public Response createInvoice(@RequestParam(value = "user") int user_id) {
		Invoice invoice = invoiceService.createInvoice(user_id, 0.50, new DateTime(), new DateTime(), "NL");
		return Response.ok().entity(invoice).build();
	}

}
