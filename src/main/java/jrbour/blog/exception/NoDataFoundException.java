package jrbour.blog.exception;

// Difference between RuntimeException and Exception
// Exception should be checked, so we need to add a try/catch block
// RuntimeException are not checked
// https://stackoverflow.com/questions/2190161/difference-between-java-lang-runtimeexception-and-java-lang-exception
public class NoDataFoundException extends RuntimeException {
    public NoDataFoundException(){
        super ("No data found");
    }
}
