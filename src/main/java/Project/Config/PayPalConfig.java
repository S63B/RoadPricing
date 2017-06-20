package Project.Config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {

    private String clientId;
    private String clientSecret;
    private String mode;

    public PayPalConfig(){
        clientId = "AWjtzHomcYx_Y6xjizDaEViq_7x9sBQYQd56ECLBRnWodv5yTy5WEftwLFWe6YHgcJQNJOkvbSg9RGq_";
        clientSecret = "EA3AiaKGtLuzD-Gj5NMp2KxZ1iUucAihNv_jDNIFQQjVhZ1oJS7TeV66lLHBa6i-SvvzkKfztZKLXPGr";
        mode = "sandbox";
    }

    @Bean
    public APIContext apiContext() throws PayPalRESTException {
        return new APIContext(clientId, clientSecret, mode);
    }
}