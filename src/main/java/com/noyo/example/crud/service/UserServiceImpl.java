package com.noyo.example.crud.service;

import com.noyo.example.crud.models.User;
import com.noyo.example.crud.models.UserIdVersionCompositeKey;
import com.noyo.example.crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> getById(Serializable id) {
        return userRepository.findById((UserIdVersionCompositeKey) id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllById(Integer id) {
        return userRepository.findAllById(id);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
