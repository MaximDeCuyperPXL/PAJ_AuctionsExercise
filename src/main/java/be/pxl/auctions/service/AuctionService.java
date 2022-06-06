package be.pxl.auctions.service;

import be.pxl.auctions.dao.AuctionsRepository;
import be.pxl.auctions.dao.BidRepository;
import be.pxl.auctions.dao.UserRepository;
import be.pxl.auctions.model.Auction;
import be.pxl.auctions.model.Bid;
import be.pxl.auctions.model.User;
import be.pxl.auctions.rest.resource.AuctionCreateResource;
import be.pxl.auctions.rest.resource.AuctionDTO;
import be.pxl.auctions.rest.resource.AuctionDetailsDTO;
import be.pxl.auctions.rest.resource.BidCreateResource;
import be.pxl.auctions.util.exception.AuctionNotFoundException;
import be.pxl.auctions.util.exception.UserNotFoundException;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    private final AuctionsRepository auctionsRepository;
    private final UserRepository userRepository;
    private final BidRepository bidRepository;

    @Autowired
    public AuctionService(AuctionsRepository auctionsRepository, UserRepository userRepository, BidRepository bidRepository) {
        this.auctionsRepository = auctionsRepository;
        this.userRepository = userRepository;
        this.bidRepository = bidRepository;
    }

    public AuctionDTO createAuction(AuctionCreateResource auctionCreateResource){
        Auction auction = new Auction();
        auction.setEndDate(auctionCreateResource.getEndDate());
        auction.setDescription(auctionCreateResource.getDescription());
        Auction newAuction = auctionsRepository.save(auction);
        return new AuctionDTO(newAuction);
    }

    public AuctionDetailsDTO getAuctionById(long auctionId){
        return auctionsRepository.findById(auctionId).map(AuctionDetailsDTO::new).orElseThrow(
                () -> new AuctionNotFoundException("Not able to find auction with id [" + auctionId + "]"));
    }

    public List<AuctionDTO> getAllAuctions(){
        return auctionsRepository.findAll().stream().map(AuctionDTO::new).collect(Collectors.toList());
    }

    public BidCreateResource addBidToResource(long auctionId, BidCreateResource bidCreateResource){
        Optional<Auction> foundAuction = auctionsRepository.findById(auctionId);
        if (foundAuction.isPresent()) {
            Auction auction = foundAuction.get();
            Optional<User> user = userRepository.findUserByEmail(bidCreateResource.getEmail());
            if (user.isPresent()) {
                Bid bid = new Bid();
                bid.setUser(user.get());
                bid.setAmount(bidCreateResource.getPrice());
                bid.setDate(LocalDate.now());
                bid.setAuction(auction);
                auction.addBid(bid);
                bidRepository.save(bid);
                return bidCreateResource;
            } else {
                //User other exception!
                throw new UserNotFoundException("User doesn't exist!");
            }
        }else{
            throw new AuctionNotFoundException("Auction doesn't exist!");
        }
    }
}
