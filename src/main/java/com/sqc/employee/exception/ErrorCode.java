package com.sqc.employee.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public enum ErrorCode {
    STUDENT_NOT_EXIT(404, "fail", HttpStatus.NOT_FOUND);
    int code;
    String message;
    HttpStatus status;
}
