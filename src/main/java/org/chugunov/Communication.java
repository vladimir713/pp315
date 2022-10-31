package org.chugunov;

import org.chugunov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    private final String URL = "http://94.198.50.185:7081/api/users";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    public HttpHeaders getCookie() {
        return cookie;
    }

    private HttpHeaders cookie;

    public void setCookie(HttpHeaders cookie) {
        this.cookie = cookie;
    }

    public List<User> getAll() {

        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(
                URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() { });
        setCookie(responseEntity.getHeaders());
        return responseEntity.getBody();
    }
    public User getUser(Long id) {
        return null;
    }

    public void saveUser(User user, HttpHeaders headers) {
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, user, String.class, headers);
        System.out.println(responseEntity.getBody());
    }

    public void deleteUser(Long id) {

    }
}
