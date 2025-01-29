package Assignment_29012025;

public class EmployeeException extends Exception {
    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException() {
        super();
    }

}

class InvalidAgeException extends EmployeeException {
    public InvalidAgeException(String message) {
        super(message);
    }

    public InvalidAgeException() {
        super();
    }
}

class IncorrectChoiceException extends EmployeeException {
    public IncorrectChoiceException(String message) {
        super(message);
    }

    public IncorrectChoiceException() {
        super();
    }
}

class IdAlreadyExistsException extends EmployeeException {
    public IdAlreadyExistsException(String message) {
        super(message);
    }

    public IdAlreadyExistsException() {
        super();
    }
}

class IdNotFoundException extends EmployeeException {
    public IdNotFoundException(String message) {
        super(message);
    }

    public IdNotFoundException() {
        super();
    }
}

class InvalidIdException extends EmployeeException {
    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException() {
        super();
    }
}

class InvalidNameException extends EmployeeException {
    public InvalidNameException(String message) {
        super(message);
    }

    public InvalidNameException() {
        super();
    }
}