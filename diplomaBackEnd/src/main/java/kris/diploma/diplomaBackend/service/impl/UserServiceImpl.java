package kris.diploma.diplomaBackend.service.impl;

import kris.diploma.diplomaBackend.model.User;
import kris.diploma.diplomaBackend.repository.UserRepository;
import kris.diploma.diplomaBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent())
            throw new IllegalArgumentException("Email already in use");

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long user_id) {
        boolean exists = userRepository.existsById(user_id);
        if (!exists)
            throw new IllegalStateException("User with id" + user_id + "does not exist.");
        userRepository.deleteById(user_id);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, String name, String email, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + "does not exist."));

        if (name != null && name.length() > 0 && !Objects.equals(user.getName(), name)) {
            user.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
            Optional<User> exists = userRepository.findUserByEmail(email);
            if(exists.isPresent())
                throw new IllegalStateException("Email taken");
            user.setEmail(email);
        }

        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
        }
    }
}
