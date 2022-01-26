package ir.cinema;

public class Ticket {
    private int cinema_id;
    private String film_name;
    private String date;
    private String startAt;
    private String endAt;
    private int capacity;
    private float price;

    public Ticket(){}
    public Ticket(int cinema_id, String film_name, String date,
                  String startAt, String endAt, int capacity, float price) {
        this.cinema_id = cinema_id;
        this.film_name = film_name;
        this.date = date;
        this.startAt = startAt;
        this.endAt = endAt;
        this.capacity = capacity;
        this.price = price;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
