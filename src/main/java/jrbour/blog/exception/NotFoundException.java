package jrbour.blog.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id, String subject){
        super(String.format("The %s with the id : %d is not found", subject, id));
    }
}
