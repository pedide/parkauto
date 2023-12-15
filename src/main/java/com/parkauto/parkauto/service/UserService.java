package com.parkauto.parkauto.service;

import com.parkauto.parkauto.entity.User;
import com.parkauto.parkauto.exception.EmailNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    //Méthode qui permet de réinitialiser le MDP de l'utilisateur
    void resetPassword(String email)throws EmailNotFoundException;

    List<User> getUsers();
    void deleteUser(long id);
    UserDetailsService userDetailsService();

    User addNewUser(String firstname, String lastname, String username, String password, String email, String role, boolean b, boolean b1, MultipartFile profileImage);
}
