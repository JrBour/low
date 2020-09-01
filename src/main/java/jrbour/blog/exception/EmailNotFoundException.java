package jrbour.blog.exception;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String email){
        super(String.format("User not found with email : %s", email));
    }
}
