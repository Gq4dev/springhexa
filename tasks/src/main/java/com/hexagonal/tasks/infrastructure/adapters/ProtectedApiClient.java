package com.hexagonal.tasks.infrastructure.adapters;
import com.hexagonal.tasks.infrastructure.cache.TokenStore;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;


@Component
public class ProtectedApiClient {

    private final RestTemplate restTemplate;
    private final TokenStore tokenStore;
    private static final String PROTECTED_URL = "https://api-test-gq.vercel.app/api/protegido";
    private static final String VALIDATE_TOKEN_URL = "https://api-test-gq.vercel.app/api/auth/verify-token";

    public ProtectedApiClient(RestTemplate restTemplate, TokenStore tokenStore) {
        this.restTemplate = restTemplate;
        this.tokenStore = tokenStore;
    }


    public boolean validateToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<Void> response = restTemplate.exchange(VALIDATE_TOKEN_URL, HttpMethod.GET, request, Void.class);

            // Si la respuesta es OK, el token es válido
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            // Registrar el error en caso de una excepción
            System.out.println("Error al validar el token: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener información protegida
    public Map getProtectedInfo() {
        String token = tokenStore.getToken()
                .orElseThrow(() -> new RuntimeException("Token no encontrado. Debes autenticarte primero."));

        // Validar el token antes de intentar acceder a la información protegida
        if (!validateToken(token)) {
            throw new RuntimeException("El token ya no es válido.");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        try {
            ResponseEntity<Map> response = restTemplate.exchange(PROTECTED_URL, HttpMethod.GET, request, Map.class);

            // Verificar si la respuesta fue exitosa
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Error al acceder a la API protegida. Código de estado: " + response.getStatusCode());
            }


            return response.getBody();
        } catch (Exception e) {
            // Registrar cualquier excepción ocurrida
            System.out.println("Error al obtener la información protegida: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la información protegida.", e);
        }
    }
}
