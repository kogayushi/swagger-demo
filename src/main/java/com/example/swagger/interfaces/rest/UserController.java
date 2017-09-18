package com.example.swagger.interfaces.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.swagger.application.IdentityApplicationService;
import com.example.swagger.application.command.AuthenticateUserCommand;
import com.example.swagger.application.command.ChangeEmailAddressCommand;
import com.example.swagger.application.command.ChangeFullNameCommand;
import com.example.swagger.application.command.ChangeSexCommand;
import com.example.swagger.application.command.ChangeTelephoneNumberCommand;
import com.example.swagger.application.command.FindUserCommand;
import com.example.swagger.application.command.RegisterUserCommand;
import com.example.swagger.domain.model.identity.FailedAuthenticationException;
import com.example.swagger.domain.model.identity.UserDescriptor;
import com.example.swagger.domain.model.identity.UserNotFoundException;
import com.example.swagger.exception.ApplicationException;
import com.example.swagger.interfaces.rest.assmbler.AuthenticUserDtoAssembler;
import com.example.swagger.interfaces.rest.dto.AuthenticatedUserDto;
import com.example.swagger.interfaces.rest.dto.ChangeEmailAddressDto;
import com.example.swagger.interfaces.rest.dto.ChangeFullNameDto;
import com.example.swagger.interfaces.rest.dto.ChangeSexDto;
import com.example.swagger.interfaces.rest.dto.ChangeTelephoneNumberDto;
import com.example.swagger.interfaces.rest.dto.ErrorResponseDto;
import com.example.swagger.interfaces.rest.dto.UserDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final IdentityApplicationService identityApplicationService;
    private final AuthenticUserDtoAssembler authenticUserDtoAssembler;

    @ResponseBody
    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AuthenticatedUserDto getAuthenticatedUser(@RequestParam String username, @RequestParam String password) {

        UserDescriptor userDescriptor = identityApplicationService.authenticateUser(new AuthenticateUserCommand(username, password));

        return authenticUserDtoAssembler.toDto(userDescriptor);
    }
    
    @ResponseBody
    @GetMapping(path = "/users/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AuthenticatedUserDto getAuthenticatedUser(@PathVariable(value = "id") String id) {
        UserDescriptor userDescriptor = identityApplicationService.findUser(new FindUserCommand(id));
        return authenticUserDtoAssembler.toDto(userDescriptor);
    }
    
    @GetMapping(path = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<UserDescriptor> getUsers(
            @RequestParam(required = false, defaultValue = "0") final int offset,
            @RequestParam(required = false, defaultValue = "10") final int size) {
        return identityApplicationService.getUsers(offset, size);
    }
    
    @PostMapping(path = "/users", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public AuthenticatedUserDto register(@Validated @RequestBody UserDto user) {
        UserDescriptor userDescriptor = identityApplicationService.registerUser(
                new RegisterUserCommand(
                        user.getUsername(),
                        user.getPassword(),
                        user.getFirstName(),
                        user.getLastName(), 
                        user.getSex(), 
                        user.getTelephoneNumber(),
                        user.getEmailAddress()
                        )
                );
        return authenticUserDtoAssembler.toDto(userDescriptor);
    }
    
    @PostMapping(path = "/users/{id}/full-name", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void changeFullName(@PathVariable String id, @Validated @RequestBody ChangeFullNameDto fullName) {
        identityApplicationService.changeFullName(
                new ChangeFullNameCommand(
                        id,
                        fullName.getFirstName(),
                        fullName.getLastName()
                        )
                );
    }

    @PostMapping(path = "/users/{id}/sex", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void changeSex(@PathVariable String id, @Validated @RequestBody ChangeSexDto sex) {
        identityApplicationService.changeSex(
                new ChangeSexCommand(
                        id,
                        sex.getSex()
                        )
                );
    }

    @PostMapping(path = "/users/{id}/telephone-number", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void changeTelephoneNumber(@PathVariable String id, @Validated @RequestBody ChangeTelephoneNumberDto telephoneNumber) {
        identityApplicationService.changeTelephoneNumber(
                new ChangeTelephoneNumberCommand(
                        id,
                        telephoneNumber.getTelephoneNumber()
                        )
                );
    }
    
    @PostMapping(path = "/users/{id}/email-address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void changeEmailAddress(@PathVariable String id, @Validated  @RequestBody ChangeEmailAddressDto emailAddress) {
        identityApplicationService.changeEmailAddress(
                new ChangeEmailAddressCommand(
                        id,
                        emailAddress.getEmailAddress()
                        )
                );
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler({UserNotFoundException.class, FailedAuthenticationException.class})
    public ErrorResponseDto handleNotFoundSeriesException(ApplicationException ex) {
        return new ErrorResponseDto(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "[ " + ex.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + " : " + error.getDefaultMessage()).collect(Collectors.joining(", ")) + " ]");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ IllegalArgumentException.class, IllegalStateException.class })
    public ErrorResponseDto handleBadRequestSeriesException(Exception ex) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponseDto handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        return new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), "failed parsing json");
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleUnexpectedException(Exception ex) {
        log.error("unexpected error has occurrerd", ex);
        return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), "couldn't complete your request.");
    }
}
