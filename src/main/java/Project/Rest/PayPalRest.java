package Project.Rest;

import Project.Config.PayPalPaymentIntent;
import Project.Config.PayPalPaymentMethod;
import Project.Services.InvoiceService;
import Project.Services.PayPalService;
import Project.Utils.URLUtils;
import com.S63B.domain.Entities.Invoice;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/paypal")
public class PayPalRest {
    private final PayPalService payPalService;
    private final InvoiceService invoiceService;

    @Autowired
    public PayPalRest(PayPalService payPalService, InvoiceService invoiceService) {
        this.payPalService = payPalService;
        this.invoiceService = invoiceService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/pay")
    public ResponseEntity<String> pay(HttpServletRequest request, @RequestParam("price") double price, @RequestParam("invoice_id") String invoice_id){
        String cancelUrl = URLUtils.getBaseURl(request) + "/paypal/cancel";
        String successUrl = URLUtils.getBaseURl(request) + "/paypal/success";
        try {
            Payment payment = payPalService.createPayment(
                    price,
                    "EUR",
                    PayPalPaymentMethod.PAYPAL,
                    PayPalPaymentIntent.SALE,
                    "Invoice payment",
                    cancelUrl,
                    successUrl,
                    invoice_id);
            for(Links links : payment.getLinks()){
                if(links.getRel().equals("approval_url")){
                    return new ResponseEntity<String>("{ \"href\": \"" + links.getHref() + "\" }", HttpStatus.OK);
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET, value = "cancel")
    public String cancelPay(){
        return "cancel";
    }

    @RequestMapping(method = RequestMethod.GET, value = "success")
    public RedirectView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if(payment.getState().equals("approved")){
                for (Transaction transaction : payment.getTransactions()){
                    String invoiceNumber = transaction.getInvoiceNumber();
                    Invoice invoice = invoiceService.getInvoiceById(Integer.parseInt(invoiceNumber));
                    invoice.setPaymentStatus(1);
                    invoiceService.saveInvoice(invoice);
                    break;
                }

                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("http://192.168.24.120:4202/");
                return redirectView;
//                return "success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://192.168.24.120:4202/");
        return redirectView;
    }
}
