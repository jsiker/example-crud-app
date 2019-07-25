package com.noyo.example.crud.controllers;

import com.noyo.example.crud.models.User;
import com.noyo.example.crud.models.UserIdVersionCompositeKey;
import com.noyo.example.crud.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private UserController userController;

    @Mock
    private UserService userService;

    private User user = new User();

    private static Integer id;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userController = new UserController();
        userController.setUserService(userService);
        userController.addUser(user);
        id = user.getKey().getId();
        when(userService.getById(any(UserIdVersionCompositeKey.class))).thenReturn(Optional.ofNullable(user));
        when(userService.getAllById(id)).thenReturn(Collections.singletonList(user));
    }

    @Test
    public void getSpecificUserById() {
        Assert.assertEquals(HttpStatus.OK, userController.getNewestUserFromId(id).getStatusCode());
    }

    @Test
    public void getAllUsersById() {
        Assert.assertEquals(HttpStatus.OK, userController.getAllUsersFromId(id).getStatusCode());
    }

    @Test
    public void addUser() {
        User userToAdd = new User();
        Assert.assertTrue(HttpStatus.CREATED == userController.addUser(userToAdd).getStatusCode());
    }

    @Test
    public void updateUser() {
        UserIdVersionCompositeKey key = new UserIdVersionCompositeKey(1, 0);
        User userV2 = new User();
        userV2.setKey(key);
        // update user already made in setUp, numOfVersions goes from 1 to 2
        Assert.assertTrue(HttpStatus.OK == userController.updateUser(userV2).getStatusCode());
        Assert.assertTrue(userV2.getNumOfVersions() == 2);

    }

//    @Test
    public void deleteUser() {
        User userToDelete = new User();
        userController.addUser(userToDelete);
        Assert.assertTrue(HttpStatus.GONE == userController.deleteUser(userToDelete.getKey().getId()).getStatusCode());
        Assert.assertTrue(HttpStatus.NOT_FOUND == userController.getAllUsersFromId(userToDelete.getKey().getId()).getStatusCode());
    }

}