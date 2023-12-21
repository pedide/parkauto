package com.parkauto.parkauto.service;

import com.parkauto.parkauto.entity.User;
import com.parkauto.parkauto.exception.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    //Méthode qui permet de réinitialiser le MDP de l'utilisateur
    void resetPassword(String email)throws EmailNotFoundException;

    List<User> getUsers();
    void deleteUser(long id);
    UserDetailsService userDetailsService();
    User updateProfileImage(String username,MultipartFile profileImage) throws NotAnImageFileException;
    User updateUser(String currentUsername,String firstname, String lastname, String username,String password, String email, String role, boolean active, boolean isNotLocked, MultipartFile profileImage) throws NotAnImageFileException, IOException, EmailNotFoundException, UserNotFoundException, EmailExistException, UsernameExistException;


    User addNewUser(String firstname, String lastname, String username, String password, String email, String role, boolean b, boolean b1, MultipartFile profileImage);
}
