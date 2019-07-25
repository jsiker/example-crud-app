package com.noyo.example.crud.controllers;

import com.noyo.example.crud.models.User;
import com.noyo.example.crud.models.UserIdVersionCompositeKey;
import com.noyo.example.crud.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    UserService userService;

    void setUserService(UserService userService) {
        this.userService = userService;
    }

    // method to return only latest record
    @RequestMapping(value = "/{id}/newest", method = RequestMethod.GET)
    public ResponseEntity<User> getNewestUserFromId(@PathVariable("id") Integer id) {
        UserIdVersionCompositeKey key = new UserIdVersionCompositeKey(id, 0);
        Optional<User> user = userService.getById(key);
        if (user == null || !user.isPresent()) {
            logger.debug("User with id " + id + "does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found User: " + user);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    // method to return specific version of record
    @RequestMapping(value = "/{id}/{version}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserFromIdAndVersion(@PathVariable("id") Integer id, @PathVariable Integer version) {
        version = version == null ? 0 : version;
        UserIdVersionCompositeKey key = new UserIdVersionCompositeKey(id, version);
        Optional<User> user = userService.getById(key);
        if (user == null || !user.isPresent()) {
            logger.debug("User with id " + id + " and " + version + "does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found User: " + user);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    // method to return all versions of a single record
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsersFromId(@PathVariable("id") Integer id) {
        List<User> users = userService.getAllById(id);
        if (users == null || users.isEmpty()) {
            logger.debug("User with id " + id + " does not exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.debug("Found User: " + users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // method to get all records
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        if (users == null || users.isEmpty()) {
            logger.debug("No users exist");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.debug("Found " + users.size() + " users");
        logger.debug(Arrays.toString(users.toArray()));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
      if (user.getNumOfVersions() != null) throw new IllegalArgumentException("Setting numOfVersions not allowed!");
        // set to int for smaller numbers for testing purposes, can be long or BigInt to ensure many more IDs
        if (user.getKey() == null) user.setKey(new UserIdVersionCompositeKey(new Random().nextInt(50000), 0));
        if (user.getNumOfVersions() == null) user.setNumOfVersions(1);
        userService.save(user);
        logger.debug("Added user by Id: " + user.getKey().getId());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /*
        Method to update a user. We bump the current 0 (newest) record to the back of the numOfVersions list,
        thus keeping a record of historical changes. New record replaces this as version 0

        Makes one seek to get current newest version and two writes, one to update the now second newest record
        and another to save the 'new' newest record.
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUser(@RequestBody User updated) {
        updated.getKey().setVersion(0);
        Optional<User> existingUser = userService.getById(updated.getKey());
        if (existingUser == null || !existingUser.isPresent()) {
            logger.debug("User with id " + updated.getKey().getId() + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            // make a new object to avoid EntityManager overriding context
            User existing = existingUser.get();
            UserIdVersionCompositeKey key =
                    new UserIdVersionCompositeKey(existing.getKey().getId(), existing.getNumOfVersions());

            User userToCommit = User.newUser()
                    .key(key)
                    .firstName(existing.getFirstName())
                    .middleName(existing.getMiddleName())
                    .lastName(existing.getLastName())
                    .email(existing.getEmail())
                    .age(existing.getAge())
                    .numOfVersions(existing.getNumOfVersions() + 1)
                    .build();
            userService.save(userToCommit);

            updated.setNumOfVersions(userToCommit.getNumOfVersions());
            updated.getKey().setVersion(0);
            userService.save(updated);

            return new ResponseEntity<Void>(HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        // if entity is present, there should always be a version 0
        UserIdVersionCompositeKey key = new UserIdVersionCompositeKey(id, 0);
        Optional<User> user = userService.getById(key);
        if (user == null || !user.isPresent()) {
            logger.debug("User with id " + id + " does not exist");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        } else {
            userService.delete(id);
            logger.debug("User with id " + id + " deleted");
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
    }
}
