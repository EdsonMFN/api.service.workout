package api.workout.config;

import api.workout.exception.handles.AuthenticationError;
import api.workout.exception.handles.BuinessException;
import api.workout.rest.response.ResponseAuthenticationError;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AuthenticationErrorDecoder implements ErrorDecoder {

    private static final String PONTO = ". ";
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() != null && response.status() >= 400 && response.status() <= 500 ){
            try {
                String body = Util.toString(response.body().asReader());
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);

                ResponseAuthenticationError errorMessage =
                        objectMapper.readValue(body, ResponseAuthenticationError.class);

                if (response.status() >= 400){
                    StringBuilder builder = new StringBuilder();
                    builder.append(errorMessage.getError()).append(PONTO)
                            .append(errorMessage.getErrorDescription()).append(PONTO)
                            .append(errorMessage.getErrorUri()).append(PONTO);

                    throw new AuthenticationError(builder.toString());
                }else {
                    throw new BuinessException(errorMessage.getError());
                }
            } catch (IOException e) {
                logger.error("{} {}: {}", e.getClass().getName(), "Erro ao deserializar o body", e.getMessage());
                logger.error("{} {}", e.getClass().getName(), "Erro ao deserializar o body", e);
            }
        }
        return FeignException.errorStatus(methodKey, response);
    }
}