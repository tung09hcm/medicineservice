package com.ryo.medicineservice.exception;

import com.ryo.medicineservice.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse<Void>> handlingRuntimeException(RuntimeException exception){
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_ERROR.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_ERROR.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse<Void>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse<Void> apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.status(errorCode.getHttpStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleJsonParseError(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(ErrorCode.USER_EXISTED.getCode())
                        .message("Invalid request body: " + ex.getMostSpecificCause().getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handlingValidation(MethodArgumentNotValidException exception){
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e){
            ApiResponse<?> apiResponse = new ApiResponse<>();
            apiResponse.setCode(ErrorCode.UNCATEGORIZED_ERROR.getCode());
            apiResponse.setMessage(ErrorCode.UNCATEGORIZED_ERROR.getMessage());
            return ResponseEntity.badRequest().body(apiResponse);
        }

        ApiResponse<?> apiResponse = new ApiResponse<>();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ApiResponse<?>> handlingHttpMethodException(HttpRequestMethodNotSupportedException exception){
        return ResponseEntity.badRequest().body(
                ApiResponse.builder()
                        .code(ErrorCode.HTTP_METHOD_NOT_SUPPORTED.getCode())
                        .message("Invalid request body: " + ErrorCode.HTTP_METHOD_NOT_SUPPORTED.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<?>> handlingAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getHttpStatusCode())
                .body(ApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

}
