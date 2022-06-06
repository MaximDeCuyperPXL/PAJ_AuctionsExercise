package be.pxl.auctions.rest;

import be.pxl.auctions.rest.resource.AuctionCreateResource;
import be.pxl.auctions.rest.resource.AuctionDTO;
import be.pxl.auctions.rest.resource.AuctionDetailsDTO;
import be.pxl.auctions.rest.resource.BidCreateResource;
import be.pxl.auctions.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/auctions")
public class AuctionRest {
    @Autowired
    private AuctionService auctionService;

    @PostMapping
    public ResponseEntity<AuctionDTO> createAuction(@RequestBody AuctionCreateResource auctionCreateResource){
        return new ResponseEntity<>(auctionService.createAuction(auctionCreateResource), HttpStatus.CREATED);
    }

    @GetMapping("{auctionId}")
    public AuctionDetailsDTO getAuctionById(@PathVariable("auctionId") long auctionId){
        return auctionService.getAuctionById(auctionId);
    }

    @GetMapping
    public List<AuctionDTO> getAllAuctions(){
        return auctionService.getAllAuctions();
    }

    @PostMapping("{auctionId}/bids")
    public ResponseEntity<BidCreateResource> addBidToAuction
            (@PathVariable("auctionId") long auctionId, @RequestBody BidCreateResource bidCreateResource){
        return new ResponseEntity<>(auctionService.addBidToResource(auctionId, bidCreateResource), HttpStatus.ACCEPTED);
    }
}
