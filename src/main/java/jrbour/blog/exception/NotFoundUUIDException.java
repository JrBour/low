package jrbour.blog.exception;

import java.util.UUID;

public class NotFoundUUIDException extends RuntimeException {
    public NotFoundUUIDException(UUID id, String subject){
        super(String.format("The %s with id %d is not found", subject, id));
    }
}
