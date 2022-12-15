package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.data.model.UserModel;

public interface UserService {
    User createUser(User user);

    User read();

    void delete();

    User getLoggedInUser();
}
