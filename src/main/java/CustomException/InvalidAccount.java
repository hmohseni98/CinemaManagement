package CustomException;

public class InvalidAccount extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";

    public InvalidAccount() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Invalid Account" + TEXT_RESET;
    }
}
