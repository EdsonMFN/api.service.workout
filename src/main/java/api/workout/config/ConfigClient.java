package api.workout.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

@Configuration
public class ConfigClient {

    @Bean
    public Feign.Builder feingnBuider(){
        return Feign.builder()
                .logger(new Slf4jLogger(ConfigClient.class))
                .logLevel(Logger.Level.FULL)
                .options(new Request.Options(10000,10000))
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .errorDecoder(new AuthenticationErrorDecoder());
    }
}
