package be.pxl.auctions.rest.resource;

import be.pxl.auctions.model.Auction;
import be.pxl.auctions.model.Bid;
import be.pxl.auctions.util.exception.AuctionNotFoundException;
import org.springdoc.api.OpenApiResourceNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionDetailsDTO extends AuctionDTO{
    private List<BidDTO> bids;

    public AuctionDetailsDTO(Auction auction) {
        super(auction);
        bids = auction.getBids().stream().map(BidDTO::new).collect(Collectors.toList());
    }

    public List<BidDTO> getBids() {
        return bids;
    }

    public void setBids(List<BidDTO> bids) {
        this.bids = bids;
    }
}
