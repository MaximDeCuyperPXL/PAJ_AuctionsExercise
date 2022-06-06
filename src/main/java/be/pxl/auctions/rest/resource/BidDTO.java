package be.pxl.auctions.rest.resource;

import be.pxl.auctions.model.Bid;
import be.pxl.auctions.model.User;
import java.time.LocalDate;

public class BidDTO {
    private long id;
    private double amount;
    private LocalDate date;
    private User user;

    public BidDTO(Bid bid) {
        this.id = bid.getId();
        this.amount = bid.getAmount();
        this.date = bid.getDate();
        this.user = bid.getUser();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
