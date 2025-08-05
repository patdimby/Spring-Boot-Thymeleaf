package com.patdimby.simplerest.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ErrorModel {
    public String code;
    public String message;
}
