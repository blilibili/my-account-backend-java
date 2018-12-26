package com.account.account.service.user;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public interface UserService {
    HashMap Login(String code) throws IOException;
}
