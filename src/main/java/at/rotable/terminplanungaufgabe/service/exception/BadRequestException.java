package at.rotable.terminplanungaufgabe.service.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        super(message);
    }
}