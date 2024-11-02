package com.techtracers.lockitemapi.users.controller;

import com.crudjpa.controller.CrudController;
import com.crudjpa.enums.MapFrom;
import com.techtracers.lockitemapi.security.domain.enums.Roles;
import com.techtracers.lockitemapi.security.domain.models.Role;
import com.techtracers.lockitemapi.security.domain.services.IRoleService;
import com.techtracers.lockitemapi.shared.exception.InvalidCreateResourceException;
import com.techtracers.lockitemapi.shared.exception.InvalidRequestException;
import com.techtracers.lockitemapi.users.domain.model.User;
import com.techtracers.lockitemapi.users.domain.service.IUserService;
import com.techtracers.lockitemapi.users.mapping.UserMapper;
import com.techtracers.lockitemapi.users.resources.CreateUserResource;
import com.techtracers.lockitemapi.users.resources.UpdateUserResource;
import com.techtracers.lockitemapi.users.resources.UserResource;
import com.techtracers.lockitemapi.users.resources.request.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${lockitem.users.path}")
public class UsersController extends CrudController<User, Long, UserResource, CreateUserResource, UpdateUserResource> {
    private final IUserService userService;
    private final IRoleService roleService;
    private final PasswordEncoder encoder;

    public UsersController(IUserService userService, UserMapper mapper, PasswordEncoder encoder, IRoleService roleService) {
        super(userService, mapper);
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    private void validateUserExists(String username) {
        Optional<User> user = userService.findUserByUsername(username);
        if (user.isPresent())
            throw new InvalidCreateResourceException("A user with username %s already exists".formatted(username));

    }

    @Override
    protected boolean isValidCreateResource(CreateUserResource createUserResource) {
        validateUserExists(createUserResource.getUsername());
        return true;
    }

    @Override
    protected boolean isValidUpdateResource(UpdateUserResource updateUserResource) {
        if (updateUserResource.getUsername() != null)
            validateUserExists(updateUserResource.getUsername());
        return true;
    }

    @Override
    protected User fromCreateResourceToModel(CreateUserResource resource) {
        Optional<Role> role = roleService.findByName(Roles.valueOf((String) resource.getRole()));
        if (role.isEmpty())
            throw new InvalidCreateResourceException("Role not found");

        resource.setRole(role.get());
        return super.fromCreateResourceToModel(resource);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
        return getById(id);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResource>> getAllUsers() {
        return getAll();
    }

    private void validateBindingResult(BindingResult result) {
        if (result.hasErrors())
            throw new InvalidCreateResourceException(getErrorsFromResult(result));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> createUser(@Valid @RequestBody CreateUserResource resource, BindingResult result) {
        validateBindingResult(result);
        return insert(resource);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> updateUserById(@PathVariable Long id, @RequestBody UpdateUserResource resource, BindingResult result) {
        validateBindingResult(result);
        return update(id, resource);
    }


    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> deleteUserById(@PathVariable Long id) {
        return delete(id);
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> login(@Valid @RequestBody LoginRequest request, BindingResult result) {
        validateBindingResult(result);
        Optional<User> user = userService.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (user.isEmpty())
            throw new InvalidRequestException("Invalid credentials");

        return ResponseEntity.ok(this.fromModelToResource(user.get(), MapFrom.GET));
    }
}
