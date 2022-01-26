package CustomException;

public class NotAuthorizedAccount extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    public NotAuthorizedAccount() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Not Authorized Account" + TEXT_RESET;
    }
}
