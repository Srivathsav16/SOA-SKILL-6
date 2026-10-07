package com.library.book.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> notFound(ResourceNotFoundException ex){return response(HttpStatus.NOT_FOUND,ex.getMessage());}
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>> conflict(IllegalArgumentException ex){return response(HttpStatus.CONFLICT,ex.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex){
        Map<String,String> errors=new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e->errors.put(e.getField(),e.getDefaultMessage()));
        Map<String,Object> body=new LinkedHashMap<>(); body.put("status",400); body.put("errors",errors);
        return ResponseEntity.badRequest().body(body);
    }
    private ResponseEntity<Map<String,String>> response(HttpStatus status,String message){
        Map<String,String> body=new LinkedHashMap<>(); body.put("error",message); body.put("status",String.valueOf(status.value()));
        return ResponseEntity.status(status).body(body);
    }
}
