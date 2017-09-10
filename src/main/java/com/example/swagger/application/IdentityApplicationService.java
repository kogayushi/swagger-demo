package com.example.swagger.application;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.swagger.application.command.AuthenticateUserCommand;
import com.example.swagger.application.command.ChangeEmailAddressCommand;
import com.example.swagger.application.command.ChangeFullNameCommand;
import com.example.swagger.application.command.ChangeSexCommand;
import com.example.swagger.application.command.ChangeTelephoneNumberCommand;
import com.example.swagger.application.command.FindUserCommand;
import com.example.swagger.application.command.RegisterUserCommand;
import com.example.swagger.domain.model.identity.AuthenticationService;
import com.example.swagger.domain.model.identity.ContactInformation;
import com.example.swagger.domain.model.identity.EmailAddress;
import com.example.swagger.domain.model.identity.Person;
import com.example.swagger.domain.model.identity.Sex;
import com.example.swagger.domain.model.identity.TelephoneNumber;
import com.example.swagger.domain.model.identity.User;
import com.example.swagger.domain.model.identity.UserDescriptor;
import com.example.swagger.domain.model.identity.UserForRegistration;
import com.example.swagger.domain.model.identity.UserNotFoundException;
import com.example.swagger.domain.model.identity.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class IdentityApplicationService {

    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;
    
    @Transactional(readOnly = true)
    public UserDescriptor authenticateUser(AuthenticateUserCommand command) {
        return this.authenticationService.authenticatedUser(command.getUsername(), command.getPassword());
    }
    
    @Transactional(readOnly = true)
    public UserDescriptor findUser(FindUserCommand command) {
        
        User user = this.userRepository.userFromId(UUID.fromString(command.getId()));
        
        if( user == null ) {
            throw new UserNotFoundException("specified User not found : id " + command.getId());
        }
        return user.userDescriptor();
    }
    
    @Transactional(readOnly = true)
    public List<UserDescriptor> getUsers(final int offset, final int size) {
        
        List<User> users = this.userRepository.findAll();
        
        if( users.isEmpty() ) {
            throw new UserNotFoundException("any user doesn't exist");
        }
        return users.stream().map(User::userDescriptor).skip(offset).limit(size).collect(Collectors.toList());
    }

    @Transactional
    public UserDescriptor registerUser(RegisterUserCommand command) {
        UserForRegistration user = new UserForRegistration(
                command.getUsername(),
                command.getPassword(),
                new Person(
                        command.getFirstName(),
                        command.getLastName(),
                        Sex.of(command.getSex()),
                        new ContactInformation(
                                new TelephoneNumber(command.getTelephoneNumber()),
                                new EmailAddress(command.getEmailAddress()
                                        )
                                )
                        )
                );
        User registeredUser = userRepository.add(user);
        return registeredUser.userDescriptor();
    }
    
    @Transactional
    public void changeFullName(ChangeFullNameCommand command) {
        User user = this.userRepository.userFromId(UUID.fromString(command.getId()));
        if(user == null) {
            throw new UserNotFoundException("user not found : id '" + command.getId() + "'");
        }
        Person person = user.getPerson();
        user.setPerson(
                new Person(
                        command.getFirstName(),
                        command.getLastName(),
                        person.getSex(),
                        person.getContactInformation()
                        )
                );
        userRepository.update(user);
    }
    
    @Transactional
    public void changeSex(ChangeSexCommand command) {
        UUID id = UUID.fromString(command.getId());
        User user = this.userRepository.userFromId(id);
        if(user == null) {
            throw new UserNotFoundException("user not found : id '" + command.getId() + "'");
        }
        userRepository.updateSex(id, Sex.of(command.getSex()));
    }
    
    @Transactional
    public void changeTelephoneNumber(ChangeTelephoneNumberCommand command) {
        UUID id = UUID.fromString(command.getId());
        User user = this.userRepository.userFromId(id);
        if(user == null) {
            throw new UserNotFoundException("user not found : id '" + command.getId() + "'");
        }
        userRepository.updateTelephoneNumber(id, new TelephoneNumber(command.getTelephoneNumber()));
    }
    @Transactional
    public void changeEmailAddress(ChangeEmailAddressCommand command) {
        UUID id = UUID.fromString(command.getId());
        User user = this.userRepository.userFromId(id);
        if(user == null) {
            throw new UserNotFoundException("user not found : id '" + command.getId() + "'");
        }
        userRepository.updateEmailAddress(id, new EmailAddress(command.getEmailAddress()));
    }
}
