package be.pxl.auctions.rest.resource;

import be.pxl.auctions.model.Auction;
import be.pxl.auctions.model.Bid;
import be.pxl.auctions.rest.advice.RestResponseEntityExceptionHandler;
import org.springdoc.api.OpenApiResourceNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AuctionDTO {
    private long id;
    private String description;
    private LocalDate endDate;
    private boolean finished;
    private int numberOfBids;
    private double highestBid;
    private String highestBidBy;

    public AuctionDTO() {
    }

    public AuctionDTO(Auction auction) {
        id = auction.getId();
        description = auction.getDescription();
        endDate = auction.getEndDate();
        this.finished = isFinished();
        if (!auction.getBids().isEmpty()){
            List<Bid> bids = auction.getBids();
            this.numberOfBids = bids.size();
            Bid bid = bids.stream().max(Comparator.comparing(Bid::getAmount))
                    .orElseThrow(() -> new OpenApiResourceNotFoundException("max bid not found"));
            this.highestBid = bid.getAmount();
            this.highestBidBy = bid.getUser().getEmail();
        }
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

    public int getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(int numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public double getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(double highestBid) {
        this.highestBid = highestBid;
    }

    public String getHighestBidBy() {
        return highestBidBy;
    }

    public void setHighestBidBy(String highestBidBy) {
        this.highestBidBy = highestBidBy;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return endDate.isBefore(LocalDate.now());
    }
}
