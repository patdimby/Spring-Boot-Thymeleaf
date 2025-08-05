package com.patdimby.simplerest.validation;


import com.patdimby.simplerest.constant.ErrorType;
import com.patdimby.simplerest.dto.UserDto;
import com.patdimby.simplerest.model.ErrorModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<ErrorModel> validateRequest(UserDto user){

        List<ErrorModel> errorModelList = new ArrayList<>();

        if(null != user && user.getEmail() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Email cannot be empty");

            errorModelList.add(errorModel);
        }

        if(null != user && user.getPassword() == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Password cannot be empty");

            errorModelList.add(errorModel);
        }
        return errorModelList;
    }
}
