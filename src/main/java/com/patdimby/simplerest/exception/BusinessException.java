package com.patdimby.simplerest.exception;

import com.patdimby.simplerest.model.ErrorModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class BusinessException extends Exception {

    private final transient List<ErrorModel> errorList;

    public BusinessException(List<ErrorModel> errorList){
        this.errorList = errorList;
    }
}
