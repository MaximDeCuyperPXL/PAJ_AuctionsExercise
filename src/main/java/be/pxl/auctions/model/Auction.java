package be.pxl.auctions.model;

import be.pxl.auctions.util.exception.InvalidBidException;
import org.springdoc.api.OpenApiResourceNotFoundException;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "auctions")
public class Auction {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private LocalDate endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "auction")
    public List<Bid> bids = new ArrayList<>();

    public Auction() {
    }

    public boolean isFinished(){
        return LocalDate.now().isAfter(endDate);
    }

    public Optional<Bid> findHighestBid(){
        if (bids.isEmpty()){
            return  Optional.empty();
        }
        return bids.stream().max(Comparator.comparing(Bid::getAmount));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public void addBid(Bid bid){
        Optional<Bid> highestBid = findHighestBid();
        if (highestBid.isPresent()){
            if (highestBid.get().getAmount() > bid.getAmount()){
                throw new InvalidBidException("There is already a higher bid!");
            }
            if (highestBid.get().getUser().equals(bid.getUser())){
                throw new InvalidBidException("This user already has the current highest bid!");
            }
        }
        bids.add(bid);
    }
}
