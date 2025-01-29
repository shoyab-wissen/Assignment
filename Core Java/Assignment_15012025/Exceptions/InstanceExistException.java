package Assignment_15012025.Exceptions;

public class InstanceExistException extends RuntimeException {
    public InstanceExistException(String message) {
        super(message);
    }

    public InstanceExistException() {
        super();
    }

}
