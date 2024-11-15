package com.techtracers.lockitemapi.users.mapping;

import com.crudjpa.mapping.IEntityMapper;
import com.techtracers.lockitemapi.shared.mapping.EnhancedModelMapper;
import com.techtracers.lockitemapi.users.domain.model.User;
import com.techtracers.lockitemapi.users.resources.CreateUserResource;
import com.techtracers.lockitemapi.users.resources.LoginResponse;
import com.techtracers.lockitemapi.users.resources.UpdateUserResource;
import com.techtracers.lockitemapi.users.resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapper implements IEntityMapper<User, UserResource, CreateUserResource, UpdateUserResource> {
    @Autowired
    EnhancedModelMapper mapper;


    @Override
    public User fromCreateResourceToModel(CreateUserResource resource) {
        return mapper.map(resource, User.class);
    }

    @Override
    public void fromCreateResourceToModel(CreateUserResource resource, User user) {
        mapper.map(resource, user);
    }

    @Override
    public UserResource fromModelToResource(User user) {
        return mapper.map(user, UserResource.class);
    }

    @Override
    public User fromUpdateResourceToModel(UpdateUserResource user) {
        return mapper.map(user, User.class);
    }

    @Override
    public void fromUpdateResourceToModel(UpdateUserResource updateUserResource, User user) {
        mapper.map(updateUserResource, user);
    }

    public LoginResponse fromModelToLoginResponse(User user) {
        return mapper.map(user, LoginResponse.class);
    }
}
