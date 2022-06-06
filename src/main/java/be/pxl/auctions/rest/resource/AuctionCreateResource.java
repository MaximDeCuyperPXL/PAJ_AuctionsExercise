package be.pxl.auctions.rest.resource;

import java.time.LocalDate;

public class AuctionCreateResource {
    private LocalDate endDate;
    private String Description;

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
