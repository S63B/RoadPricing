package Project.Rest;

import Project.Services.PayPalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/paypal")
@Controller
public class PayPalRest {

    private final PayPalService service;

    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    @Autowired
    public PayPalRest(PayPalService service, HttpServletRequest request, HttpServletResponse resp) {
        this.service = service;
        this.req = request;
        this.resp = resp;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ResponseEntity<Object> createPayment() {
//        return service.createPayment(req, resp);
        Payment payment = service.createPayment(req, resp);

        HttpHeaders httpHeaders = new HttpHeaders();

        try {
            httpHeaders.setLocation(new URI(payment.getLinks().get(0).getHref()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String getPayment(@RequestParam String id) {
        Payment payment = service.getPayment(id);
        return payment.toJSON();

//        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
