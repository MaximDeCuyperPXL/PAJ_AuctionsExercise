package be.pxl.auctions.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class AuctionFindHighestBidTest {
    @Test
    public void returnsEmptyOptionalWhenBidsIsEmpty(){
        Auction auction = new Auction();
        assertEquals(Optional.empty(), auction.findHighestBid());
    }

    @Test
    public void returnsBidWithHighestAmount(){
        Auction auction = new Auction();
        Bid bid1 = new Bid();
        bid1.setAmount(100);
        Bid bid2 = new Bid();
        bid2.setAmount(15000);
        List<Bid> bids = new ArrayList<>();
        bids.add(bid1);
        bids.add(bid2);
        auction.setBids(bids);

        assertTrue(auction.findHighestBid().isPresent());
        assertEquals(bid2.getAmount(), auction.findHighestBid().get().getAmount());
    }
}
