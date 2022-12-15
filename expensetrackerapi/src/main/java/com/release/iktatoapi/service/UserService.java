package com.release.iktatoapi.service;

import com.release.iktatoapi.data.entity.User;
import com.release.iktatoapi.data.model.UserModel;

public interface UserService {
    User createUser(UserModel user);

    User read();

    User update(UserModel user);

    void delete();

    User getLoggedInUser();
}
