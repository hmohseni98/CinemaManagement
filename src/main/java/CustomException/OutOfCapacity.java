package CustomException;

public class OutOfCapacity extends RuntimeException{
    private static final String TEXT_RED = "\u001B[31m";
    private static final String TEXT_RESET = "\u001B[0m";
    public OutOfCapacity() {
    }

    @Override
    public String toString() {
        return TEXT_RED + "Out Of Capacity" + TEXT_RESET;
    }
}
