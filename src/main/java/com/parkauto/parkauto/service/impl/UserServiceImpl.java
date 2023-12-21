package com.parkauto.parkauto.service.impl;

import com.parkauto.parkauto.entity.Role;
import com.parkauto.parkauto.entity.User;
import com.parkauto.parkauto.exception.*;
import com.parkauto.parkauto.repository.IUserRepository;
import com.parkauto.parkauto.service.EmailService;
import com.parkauto.parkauto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.parkauto.parkauto.constant.UserImplConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.MediaType.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static com.parkauto.parkauto.constant.FileConstant.*;

@Service
@Transactional
@Qualifier("UserDetailsService")
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService,UserDetailsService {
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
     IUserRepository userRepository;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository,BCryptPasswordEncoder passwordEncoder, EmailService emailService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

public List<User> getUsers(){
return userRepository.findAll();
}

public void deleteUser(long id){
        userRepository.deleteById(id);
}

    @Override
    public UserDetailsService userDetailsService() {
        return null;
    }

    //Méthode qui permet de réinitialiser le MDP de l'utilisateur
@Override
    public void resetPassword(String email)throws EmailNotFoundException {
        User user = userRepository.findUserByEmail(email);

        if(user == null){
            throw new EmailNotFoundException("No user Found by Email");
        }
        String lien = "URL vers API ";
        LOGGER.info("Lien "+lien);
        emailService.sendResetPassword(user.getEmail(),user.getFirstname(),lien);
    }

   /* @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findUserByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User is not found")
                        );
            }
        };
    }*/

    @Override
    public User addNewUser(String firstname, String lastname, String username, String password, String email, String role, boolean active, boolean isNotLocked, MultipartFile profileImage) {
        User user = new User();
        String encodedPassword = encodePassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setLastname(username);
        user.setEmail(email);
        user.setRole(getRoleEnumName(role));  //User is not allow to be an admin in register
        user.setProfileImageURL(setProfilImageUrl(lastname));
        user.setAuthorities(getRoleEnumName(role).getAuthorities());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        LOGGER.info("Votre mot de passe"+ password);
        emailService.sendConfirmRegister(email,
                firstname,password);

        return user;
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String setProfilImageUrl(String lastname) {
        return TEMP_PROFILE_IMAGE_BASE_URL+DEFAULT_USER_IMAGE_PATH+FORWARD_SLASH+lastname;
    }

    public User updateProfileImage(String username, MultipartFile profileImage) throws NotAnImageFileException{
        try {
            User user = validateNewUsernameAndEmail(username,null, null);
            saveProfileImage(user,profileImage);
            return user;
        }catch (Exception e){
            
        }
    return null;
    }

    @Override
    public User updateUser(String currentUsername,String newFirstname, String newLastname, String newUsername,String newPassword, String newEmail, String role, boolean active, boolean isNotLocked, MultipartFile profileImage) throws NotAnImageFileException, IOException, EmailNotFoundException, UserNotFoundException, EmailExistException, UsernameExistException {

        String encodedPassword = encodePassword(newPassword);
        User currentUser = validateNewUsernameAndEmail(currentUsername,newUsername,newEmail);

        currentUser.setFirstname(newFirstname);
        currentUser.setLastname(newLastname);
        currentUser.setUsername(newUsername);
        currentUser.setPassword(encodedPassword);
        currentUser.setEmail(newEmail);
        currentUser.setActive(active);
        currentUser.setNotLocked(isNotLocked);
        currentUser.setRole(getRoleEnumName(role));
        currentUser.setAuthorities(getRoleEnumName(role).getAuthorities());
        userRepository.save(currentUser);
        saveProfileImage(currentUser,profileImage);

        System.out.println("The new username is :"+newUsername);

        return currentUser;
    }

    private void saveProfileImage(User user, MultipartFile profileImage) throws NotAnImageFileException, IOException {
        if(profileImage != null){
            if(!Arrays.asList(IMAGE_JPEG_VALUE,IMAGE_PNG_VALUE,IMAGE_GIF_VALUE).contains(profileImage.getContentType())){
                throw new NotAnImageFileException(profileImage.getOriginalFilename()+NOT_AN_IMAGE_FILE);
            }
            Path userFolder = Paths.get(USER_FOLDER + user.getLastname()).toAbsolutePath().normalize();

            if(!Files.exists(userFolder)){
                Files.createDirectories(userFolder);
                LOGGER.info(DIRECTORY_CREATED);

            }
            Files.deleteIfExists(Paths.get(userFolder+user.getLastname()+DOT+JPG_EXTENSION));
            Files.copy(profileImage.getInputStream(),userFolder.resolve(user.getLastname()+DOT+JPG_EXTENSION),REPLACE_EXISTING);
            user.setProfileImageURL(setProfilImageUrl(user.getLastname()));
            userRepository.save(user);
            LOGGER.info(FILE_SAVED_IN_SYSTEM+profileImage.getOriginalFilename());

        }
    }

    //ValidateNewUsernameAndEmail() est appelée par register() et elle vérifie si les valeurs Username et Email appartiennent déjà à un utilisateur
    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {

        User userByNewUsername = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);

        if(StringUtils.isNotBlank(currentUsername)){
            User currentUser =  findUserByUsername(newUsername);

            if(currentUser == null){
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME +currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId()) ){
                throw new UsernameExistException(USERNAME_ALREADY_EXIST);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId()) ){
                throw new EmailExistException(EMAIL_ALREADY_EXIST);
            }
            return currentUser;

        }else{
            if(userByNewUsername != null){
                throw  new UsernameExistException(USERNAME_ALREADY_EXIST+userByNewUsername);

            }
            if(userByNewEmail != null){
                throw  new EmailExistException(EMAIL_ALREADY_EXIST+userByNewEmail);

            }

        }

        return null;
    }

    private User findUserByEmail(String newEmail) {
        return userRepository.findUserByEmail(newEmail);
    }

    private User findUserByUsername(String newUsername) {
        return userRepository.findUserByUsername(newUsername);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);

        if(user == null){
            LOGGER.error(USER_NOT_FOUND_BY_USERNAME+username);
            throw new UsernameNotFoundException(USER_NOT_FOUND_BY_USERNAME+username);

        }else {
            validateLoginAttempt(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);

            return user;
        }

    }

    //validateLoginAttempt() bloquer un utilisateur si celui-ci a exécuté trop de tentaive de connexion avec un mauvais MDP
    private void validateLoginAttempt(User user) {
    }
}
