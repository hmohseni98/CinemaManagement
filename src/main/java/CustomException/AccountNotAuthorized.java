package CustomException;

public class AccountNotAuthorized extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    public AccountNotAuthorized() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Account Not Authorized" + TEXT_RESET;
    }
}
