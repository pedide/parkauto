package com.parkauto.parkauto.service.impl;

import com.parkauto.parkauto.entity.Role;
import com.parkauto.parkauto.entity.User;
import com.parkauto.parkauto.exception.EmailNotFoundException;
import com.parkauto.parkauto.repository.IUserRepository;
import com.parkauto.parkauto.service.EmailService;
import com.parkauto.parkauto.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
     IUserRepository userRepository;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private EmailService emailService;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, EmailService emailService){
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

public List<User> getUsers(){
return userRepository.findAll();
}

public void deleteUser(long id){
        userRepository.deleteById(id);
}
    //Méthode qui permet de réinitialiser le MDP de l'utilisateur
@Override
    public void resetPassword(String email)throws EmailNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user == null){
            throw new EmailNotFoundException("No user Found by Email");
        }
        String lien = "URL vers API ";
        LOGGER.info("Lien "+lien);
        emailService.sendResetPassword(user.get().getEmail(),user.get().getFirstname(),lien);
    }

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User is not found")
                        );
            }
        };
    }

    @Override
    public User addNewUser(String firstname, String lastname, String username, String password, String email, String role, boolean active, boolean isNotLocked, MultipartFile profileImage) {
        User user = new User();
        String encodedPassword = encodePassword(password);

        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setLastname(username);
        user.setEmail(email);
        user.setRole(Role.USER);  //User is not allow to be an admin in register
        user.setProfileImageURL(setProfilImageUrl(username));
        user.setPassword(encodedPassword);
        userRepository.save(user);
        LOGGER.info("Votre mot de passe"+ password);
        emailService.sendConfirmRegister(email,
                firstname,password);

        return user;
    }

    private String encodePassword(String password) {
    }

    private String setProfilImageUrl(String username) {
        return 
    }

}
