package Project.Services;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.*;
import java.util.logging.Logger;

@Service
@Transactional
public class PayPalService {

    private final String clientId = "AWjtzHomcYx_Y6xjizDaEViq_7x9sBQYQd56ECLBRnWodv5yTy5WEftwLFWe6YHgcJQNJOkvbSg9RGq_";
    private final String clientSecret = "EA3AiaKGtLuzD-Gj5NMp2KxZ1iUucAihNv_jDNIFQQjVhZ1oJS7TeV66lLHBa6i-SvvzkKfztZKLXPGr";
    private final String mode = "sandbox";

//    private final APIContext apiContext;

//    @Autowired
//    public PayPalService(APIContext apiContext) {
//        this.apiContext = apiContext;
//    }

    private static final Logger LOGGER = Logger
            .getLogger(String.valueOf(PayPalService.class));
    Map<String, String> map = new HashMap<String, String>();

    public Payment createPayment(HttpServletRequest req, HttpServletResponse resp) {
        Payment createdPayment = null;

        // ### Api Context
        // Pass in a `ApiContext` object to authenticate
        // the call and to send a unique request id
        // (that ensures idempotency). The SDK generates
        // a request id if you do not pass one explicitly.
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        if (req.getParameter("PayerID") != null) {
            Payment payment = new Payment();
            if (req.getParameter("guid") != null) {
                payment.setId(map.get(req.getParameter("guid")));
            }

            PaymentExecution paymentExecution = new PaymentExecution();
            paymentExecution.setPayerId(req.getParameter("PayerID"));
            try {

                createdPayment = payment.execute(apiContext, paymentExecution);

//                ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), Payment.getLastResponse(), null);
            } catch (PayPalRESTException e) {
                e.printStackTrace();
//                ResultPrinter.addResult(req, resp, "Executed The Payment", Payment.getLastRequest(), null, e.getMessage());
            }
        } else {
            // ###Details
            // Let's you specify details of a payment amount.
            Details details = new Details();
            details.setShipping("1");
            details.setSubtotal("5");
            details.setTax("1");

            // ###Amount
            // Let's you specify a payment amount.
            Amount amount = new Amount();
            amount.setCurrency("USD");
            // Total must be equal to sum of shipping, tax and subtotal.
            amount.setTotal("7");
            amount.setDetails(details);

            // ###Transaction
            // A transaction defines the contract of a
            // payment - what is the payment for and who
            // is fulfilling it. Transaction is created with
            // a `Payee` and `Amount` types
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction
                    .setDescription("This is the payment transaction description.");

            // ### Items
            Item item = new Item();
            item.setName("Ground Coffee 40 oz").setQuantity("1").setCurrency("USD").setPrice("5");
            ItemList itemList = new ItemList();
            List<Item> items = new ArrayList<Item>();
            items.add(item);
            itemList.setItems(items);

            transaction.setItemList(itemList);

            // The Payment creation API requires a list of
            // Transaction; add the created `Transaction`
            // to a List
            List<Transaction> transactions = new ArrayList<Transaction>();
            transactions.add(transaction);

            // ###Payer
            // A resource representing a Payer that funds a payment
            // Payment Method
            // as 'paypal'
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");

            // ###Payment
            // A Payment Resource; create one using
            // the above types and intent as 'sale'
            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);
            payment.setTransactions(transactions);

            // ###Redirect URLs
            RedirectUrls redirectUrls = new RedirectUrls();
            String guid = UUID.randomUUID().toString().replaceAll("-", "");
            redirectUrls.setCancelUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
            redirectUrls.setReturnUrl(req.getScheme() + "://"
                    + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath() + "/paymentwithpaypal?guid=" + guid);
            payment.setRedirectUrls(redirectUrls);

            // Create a payment by posting to the APIService
            // using a valid AccessToken
            // The return object contains the status;
            try {
                createdPayment = payment.create(apiContext);
                LOGGER.info("Created payment with id = "
                        + createdPayment.getId() + " and status = "
                        + createdPayment.getState());
                // ###Payment Approval Url

                Iterator<Links> links = createdPayment.getLinks().iterator();
                while (links.hasNext()) {
                    Links link = links.next();
                    if (link.getRel().equalsIgnoreCase("approval_url")) {
                        req.setAttribute("redirectURL", link.getHref());
                    }
                }
//                ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), Payment.getLastResponse(), null);
                map.put(guid, createdPayment.getId());
            } catch (PayPalRESTException e) {
                e.printStackTrace();
//                ResultPrinter.addResult(req, resp, "Payment with PayPal", Payment.getLastRequest(), null, e.getMessage());
            }
        }
        return createdPayment;
    }

    public Payment getPayment(String id){
        try {
            // ### Api Context
            // Pass in a `ApiContext` object to authenticate
            // the call and to send a unique request id
            // (that ensures idempotency). The SDK generates
            // a request id if you do not pass one explicitly.
            APIContext apiContext = new APIContext(clientId, clientSecret, mode);

            // Retrieve the payment object by calling the
            // static `get` method
            // on the Payment class by passing a valid
            // AccessToken and Payment ID
            Payment payment = Payment.get(apiContext,
                    id);
            LOGGER.info("Payment retrieved ID = " + payment.getId()
                    + ", status = " + payment.getState());
//            ResultPrinter.addResult(req, resp, "Get Payment", Payment.getLastRequest(), Payment.getLastResponse(), null);
            return payment;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
//            ResultPrinter.addResult(req, resp, "Get Payment", Payment.getLastRequest(), null, e.getMessage());
        }

        return null;
    }
}
