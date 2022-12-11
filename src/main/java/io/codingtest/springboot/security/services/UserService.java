package io.codingtest.springboot.security.services;

import io.codingtest.springboot.models.Role;
import io.codingtest.springboot.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Boolean existsByUsername(String username);

    User getUser(String username);

    List<User> getUsers();

    User saveUser(User user);

    Role saveRole(Role role);

    Optional<Role> getRole(String name);

    void addRoleToUser(String username, String roleName);


}
