package uk.gov.homeoffice.springtechtest.service;

import org.springframework.stereotype.Service;
import uk.gov.homeoffice.springtechtest.models.dto.UserPayload;
import uk.gov.homeoffice.springtechtest.models.entities.User;
import uk.gov.homeoffice.springtechtest.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserPayload createUser(UserPayload user) {
        User userItem = new User(user.getName());
        userItem = userRepository.save(userItem);
        return new UserPayload(userItem.getId(), userItem.getName());
    }

    public UserPayload getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            return null;
        } else {
            return new UserPayload(user.get().getId(), user.get().getName());
        }
    }

    public List<UserPayload> getAllUsers() {
        return userRepository.findAll()
                .stream().map(user -> new UserPayload(user.getId(), user.getName()))
                .toList();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
