package be.pxl.auctions.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class AuctionIsFinishedTest {
    @Test
    public void returnsFalseWhenTodayIsBeforeEndDate(){
        Auction auction = new Auction();
        auction.setEndDate(LocalDate.now().plusDays(1));
        assertFalse(auction.isFinished());
    }

    @Test
    public void returnsFalseWhenBeforeDateIsToday(){
        Auction auction = new Auction();
        auction.setEndDate(LocalDate.now());
        assertFalse(auction.isFinished());
    }

    @Test
    public void returnsTrueWhenTodayIsAfterEndDate(){
        Auction auction = new Auction();
        auction.setEndDate(LocalDate.now().minusDays(1));
        assertTrue(auction.isFinished());
    }
}
