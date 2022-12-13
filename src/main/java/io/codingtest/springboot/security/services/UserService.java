package io.codingtest.springboot.security.services;

import io.codingtest.springboot.models.users.ERole;
import io.codingtest.springboot.models.users.Role;
import io.codingtest.springboot.models.users.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Boolean existsByUsername(String username);

    User getUser(String username);

    List<User> getUsers();

    User saveUser(User user);

    Role saveRole(Role role);

    Optional<Role> getRole(ERole name);

    void addRoleToUser(String username, ERole roleName);


}
