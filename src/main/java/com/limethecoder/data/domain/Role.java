package com.limethecoder.data.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    @Id
    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
