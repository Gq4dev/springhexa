package com.hexagonal.tasks.infrastructure.adapters;

import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;
import com.hexagonal.tasks.domain.ports.out.ExternalServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ExternalServiceAdapter implements ExternalServicePort {
    //  https://jsonplaceholder.typicode.com/todos/
    //  https://jsonplaceholder.typicode.com/users/
    // https://api-test-gq.vercel.app/api/


    private final RestTemplate restTemplate;

    public ExternalServiceAdapter() {
        restTemplate = new RestTemplate();
    }


    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long taskId) {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/" + taskId;
        ResponseEntity<JsonPlaceHolderTodo> response = restTemplate.getForEntity(apiUrl, JsonPlaceHolderTodo.class);

        JsonPlaceHolderTodo todo = response.getBody();
        if (todo == null) {
            return null;
        }

        apiUrl = "https://jsonplaceholder.typicode.com/users/" + todo.getUserId();

        ResponseEntity<JsonPlaceHolderUser> userResponse = restTemplate.getForEntity(apiUrl, JsonPlaceHolderUser.class);

        JsonPlaceHolderUser user = userResponse.getBody();

        if (user == null) {
            return null;
        }

        return new AdditionalTaskInfo(user.getId(), user.getName(), user.getEmail(), user.getPhone());
    }

    private static class JsonPlaceHolderTodo {
        private Long id;
        private Long userId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    private static class JsonPlaceHolderUser {
        private Long id;
        private String name;
        private String email;
        private String phone;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}