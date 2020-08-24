package jrbour.blog.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id){
        super(String.format("The data with the id : %s is not found", id));
    }
}
