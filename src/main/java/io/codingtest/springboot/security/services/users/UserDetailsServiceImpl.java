package io.codingtest.springboot.security.services.users;

import io.codingtest.springboot.models.users.ERole;
import io.codingtest.springboot.models.users.Role;
import io.codingtest.springboot.models.users.User;
import io.codingtest.springboot.repository.RoleRepository;
import io.codingtest.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService, UserService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return user.map(UserDetailsImpl::new).get();
    }


    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        Optional<User> user = userRepo.findByUsername(username);
        return user.get();
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to database", user.getUsername());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public Optional<Role> getRole(ERole name) {
        return roleRepo.findByName(name);
    }

    @Override
    public void addRoleToUser(String username, ERole roleName) throws UsernameNotFoundException {

        log.info("Saving role {} to user {}", roleName, username);
        Optional<User> user = userRepo.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        Optional<Role> role = roleRepo.findByName(roleName);
        user.get().getRoles().add(role.get());
    }
}
