package by.mycom.ita.exception.enums;

public enum DescribeForDataNotFoundException {
    NOT_FOUND_EXCEPTION("Not found ");

    private final String message;

    DescribeForDataNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
