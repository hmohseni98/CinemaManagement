package Entity;

public class Reserve {
    private int ticket_id;
    private int customer_id;
    private int count;
    private float total_price;

    public Reserve(){}

    public Reserve(int ticket_id, int customer_id, int count, float total_price) {
        this.ticket_id = ticket_id;
        this.customer_id = customer_id;
        this.count = count;
        this.total_price = total_price;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }
}
