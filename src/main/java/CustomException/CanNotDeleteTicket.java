package CustomException;

public class CanNotDeleteTicket extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    public CanNotDeleteTicket() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Can Not Delete Ticket" + TEXT_RESET;
    }
}
