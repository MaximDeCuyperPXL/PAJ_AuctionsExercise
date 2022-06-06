package be.pxl.auctions.dao;

import be.pxl.auctions.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionsRepository extends JpaRepository<Auction, Long> {
}
