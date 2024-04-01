package com.sumkin.hw3.services;

import com.sumkin.hw3.exception.ResourceNotFoundException;
import com.sumkin.hw3.model.User;
import com.sumkin.hw3.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Setter
@Getter
@Log4j2
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        log.info("creating user = " + user);
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        log.info("getting all users");
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        log.info(users.size() + " users found");
        return users;
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("No such user found");
        }
        userRepository.deleteById(id);
        log.info("User with id = " + id + " deleted successfully");
    }

    @Transactional
    public User updateUser(User user, Long id) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            log.info("User with id = " + id + " updated successfully");
            return userRepository.save(existingUser);
        } else {
            throw new ResourceNotFoundException("No such user found");
        }

    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such user found"));
    }

}
