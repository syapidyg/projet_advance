package com.advance.pharmacie.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class ApiResponse<T> {

    boolean success;
    String message;
    Date timestamps;
    T data;
    int code;
}
