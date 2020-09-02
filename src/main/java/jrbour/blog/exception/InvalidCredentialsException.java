package jrbour.blog.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(){
        super("Invalid credentials");
    }
}
