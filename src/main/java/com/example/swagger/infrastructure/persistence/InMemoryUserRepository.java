package com.example.swagger.infrastructure.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.swagger.domain.model.identity.ContactInformation;
import com.example.swagger.domain.model.identity.EmailAddress;
import com.example.swagger.domain.model.identity.Person;
import com.example.swagger.domain.model.identity.Sex;
import com.example.swagger.domain.model.identity.TelephoneNumber;
import com.example.swagger.domain.model.identity.User;
import com.example.swagger.domain.model.identity.UserForRegistration;
import com.example.swagger.domain.model.identity.UserNotFoundException;
import com.example.swagger.domain.model.identity.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> inmemoryWithUsername = initialize();

    private Map<String, User> initialize() {
        // @formatter:off
        Map<String, User> users = new HashMap<>();
        users.put(
                "yushi.koga" ,
                new User(
                        UUID.fromString("8A1E74BD-FBC9-43B2-9AAC-0D356022F887"),
                        "yushi.koga",
                        "passw0rd",
                        new Person(
                                "yushi",
                                "koga",
                                Sex.MALE,
                                new ContactInformation(
                                        new TelephoneNumber("090-1234-5678"),
                                        new EmailAddress("yushi.koga@sample.co.jp")))));
        users.put(
                "risa.koga",
                new User(
                        UUID.fromString("B7276E65-B029-42E5-B069-7015480BD36B"),
                        "risa.koga0914",
                        "pazzw0rd",
                        new Person(
                                "risa",
                                "koga",
                                Sex.FEMALE,
                                new ContactInformation(
                                        new TelephoneNumber("090-5678-1234"),
                                        new EmailAddress("risa.koga@sample.co.jp")))));
        users.put("baby.koga",
                new User(
                        UUID.fromString("C4E4E1BF-1C94-41BC-AE55-001B728C86B9"),
                        "baby.in.near.future",
                        "p@ssw0rd",
                        new Person(
                                "baby",
                                "koga",
                                Sex.UNKNOWN,
                                null)));
        // @formatter:on

        return users;
    }

    @Override
    public User userFromUsername(String username) {
        return inmemoryWithUsername.get(username);
    }

    public List<User> findAll() {
        return new ArrayList<>(inmemoryWithUsername.values());
    }
    @Override
    public User add(UserForRegistration userForRegistration) {
        if(inmemoryWithUsername.containsKey(userForRegistration.getUsername())) {
            throw new IllegalStateException("specified username already exists");
        }
        User user = new User(
                UUID.randomUUID(),
                userForRegistration.getUsername(),
                userForRegistration.getPassword(),
                userForRegistration.getPerson()
                );
        inmemoryWithUsername.put(user.getUsername(), user);
        return user;
    }

    @Override
    public void update(User user) {
        inmemoryWithUsername.put(user.getUsername(), user);
    }

    @Override
    public User userFromId(UUID id) {
        return this.convertKeyFromUsernameToId().get(id.toString());
    }
    

    @Override
    public void remove(UUID id) {
        Map<String, User> users = convertKeyFromUsernameToId();
        User user = inmemoryWithUsername.remove(users.get(id.toString()).getUsername());
        if(user == null) {
            throw new UserNotFoundException("specified user is not found");
        }
    }

    @Override
    public void updateFullName(UUID id, String firstName, String lastName) {
        Map<String, User> users = convertKeyFromUsernameToId();
        User user = users.get(id.toString());
        if(user == null) {
            throw new UserNotFoundException("specified user is not found");
        }
        Person updatedPersion = new Person(
                firstName,
                lastName,
                user.getPerson().getSex(),
                user.getPerson().getContactInformation());
        
        user.setPerson(updatedPersion);
        inmemoryWithUsername.put(user.getUsername(), user);
    }

    @Override
    public void updateSex(UUID id, Sex sex) {
        Map<String, User> users = convertKeyFromUsernameToId();
        User user = users.get(id.toString());
        if(user == null) {
            throw new UserNotFoundException("specified user is not found");
        }
        Person updatedPersion = new Person(
                user.getPerson().getFirstName(),
                user.getPerson().getLastName(),
                sex,
                user.getPerson().getContactInformation());
        user.setPerson(updatedPersion);
        inmemoryWithUsername.put(user.getUsername(), user);
    }

    @Override
    public void updateTelephoneNumber(UUID id, TelephoneNumber telephoneNumber) {
        Map<String, User> users = convertKeyFromUsernameToId();
        User user = users.get(id.toString());
        if(user == null) {
            throw new UserNotFoundException("specified user is not found");
        }
        ContactInformation updatedContactInformation = new ContactInformation(
                telephoneNumber,
                user.getPerson().getContactInformation().getEmailAddress());
        Person updatedPersion = new Person(
                user.getPerson().getFirstName(),
                user.getPerson().getLastName(),
                user.getPerson().getSex(),
                updatedContactInformation);
        user.setPerson(updatedPersion);
        inmemoryWithUsername.put(user.getUsername(), user);
    }

    @Override
    public void updateEmailAddress(UUID id, EmailAddress emailAddress) {
        Map<String, User> users = convertKeyFromUsernameToId();
        User user = users.get(id.toString());
        if(user == null) {
            throw new UserNotFoundException("specified user is not found");
        }
        ContactInformation updatedContactInformation = new ContactInformation(
                user.getPerson().getContactInformation().getTelephoneNumber(),
                emailAddress);
        Person updatedPersion = new Person(
                user.getPerson().getFirstName(),
                user.getPerson().getLastName(),
                user.getPerson().getSex(),
                updatedContactInformation);
        user.setPerson(updatedPersion);
        inmemoryWithUsername.put(user.getUsername(), user);
    }
    
    private Map<String, User> convertKeyFromUsernameToId() {
        log.info(inmemoryWithUsername.toString());
        Map<String, User> converted = inmemoryWithUsername.entrySet().stream().collect(Collectors.toMap(user -> user.getValue().getId().toString(), user -> user.getValue()));
        return converted;
    }

}
