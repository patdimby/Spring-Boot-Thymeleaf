package com.patdimby.simplerest.service;

import com.patdimby.simplerest.constant.ErrorType;
import com.patdimby.simplerest.dto.UserDto;
import com.patdimby.simplerest.exception.BusinessException;
import com.patdimby.simplerest.exception.ResourceAlreadyExistsException;
import com.patdimby.simplerest.exception.ResourceNotFoundException;
import com.patdimby.simplerest.mapper.UserConverter;
import com.patdimby.simplerest.model.ErrorModel;
import com.patdimby.simplerest.model.User;
import com.patdimby.simplerest.model.UserRole;
import com.patdimby.simplerest.repository.UserRepository;
import com.patdimby.simplerest.validation.UserValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    public User save(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(existing -> {
            throw new ResourceAlreadyExistsException("User with same email already registered.");
        });

        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        // Default = USER
        if (user.getRole() == null) {
            user.setRole(UserRole.ROLE_USER);
        }

        return userRepository.save(user);
    }

    public User saveUserDto(UserDto userDto) {
        User user = userConverter.convertDtoToModel(userDto);
        userRepository.save(user);
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find user with ID : " + id));
    }

    public void deleteById(Long id, User currentUser) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible : Cannot find user with ID : " + id);
        }

        if (currentUser.getRole() != UserRole.ROLE_ADMIN) {
            throw new AccessDeniedException(
                    "🚫 Access denied : only administrator can delete an user.");
        }

        userRepository.deleteById(id);
    }

    public User updateUser(User updatedUser, User currentUser) {
        User existing = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User to update not found."));

        if (currentUser.getRole() != UserRole.ROLE_ADMIN) {
            throw new AccessDeniedException(
                    "🚫 Access denied : only administrator can update an user.");
        }

        // 🔁 Update fields.
        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        existing.setRole(updatedUser.getRole());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            // If password is already encrypted, no need encryption
            if (!updatedUser.getPassword().startsWith("$2a$")) {
                existing.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            } else {
                existing.setPassword(updatedUser.getPassword());
            }
        }

        return userRepository.save(existing);
    }

    public void deleteAllUsers(User currentUser) {
        if (currentUser.getRole() != UserRole.ROLE_ADMIN) {
            throw new AccessDeniedException("Access denied : only administrator can delete users.");
        }

        userRepository.deleteAll();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean login(UserDto userModel) throws BusinessException {
        logger.debug("Entering method login");
        //empty check of email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        boolean result = false;
        User userEntity = userRepository.findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());

        if(userEntity == null){

            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect email or password");

            errorList.add(errorModel);
            logger.warn("Invalid login attempt");
            throw new BusinessException(errorList);
        }else {
            result = true;
            logger.info("Login was success");
        }
        logger.debug("Exiting method login");
        return result;
    }

    public Long register(UserDto userModel) throws BusinessException {

        //empty check of email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if(!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        //check if user already exist
        Optional<User> ue = userRepository.findByEmail(userModel.getEmail());

        if(ue.isPresent()){

            List<ErrorModel> errorList = new ArrayList<>();

            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("User with this email already exist, try another email");

            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }

        User userEntity = userConverter.convertDtoToModel(userModel);
        User userEntity1 = userRepository.save(userEntity);

        return userEntity1.getId();
    }
}
