package it.elearnpath.siav.registry.exception;

public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errorMessage = "A generic error occured";

    public BadRequestException() {
        super();
    }

    public BadRequestException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

