package org.chugunov;

import org.chugunov.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void updateUser(User user, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println(responseEntity.getBody());
    }

    public void deleteUser(Long id, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(headers);
        System.out.println(restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class).getBody());
    }
}
