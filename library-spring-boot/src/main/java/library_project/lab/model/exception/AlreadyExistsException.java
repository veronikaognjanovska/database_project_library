package library_project.lab.model.exception;


public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException() {
        super(String.format("Already exists"));
    }
}
