package in.saurabh.StudentServer.Exception;

public class EmailAlredyExistException extends RuntimeException{
    public EmailAlredyExistException(String message){
        super(message);
    }
}

