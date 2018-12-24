package lv.citadele.resource.security;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class TokenInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = TokenStorage.get(TokenStorage.KEY_TOKEN);
        request.getHeaders().add("Access-Token", token);
        return execution.execute(request, body);
    }
}
