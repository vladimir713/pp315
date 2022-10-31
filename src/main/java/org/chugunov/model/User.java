package org.chugunov.model;

import lombok.Data;

/**
 * @author Vladimir Chugunov
 */

@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User(Long id, String name, String lastName, Byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }
    public User() {

    }
}
