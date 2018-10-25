package uy.com.antel;

import java.util.Date;

public class Ticket {

    private Integer ticketID;
    private Integer agencyID;
    private String carRegistration;
    private Date startDate;
    private Date saleDate;
    private Integer minutes;
    private Float price;


    public Ticket(Integer agencyID, String carRegistration, Date saleDate, Date startDate, Integer minutes) {
        this.setCarRegistration(carRegistration);
        this.setStartDate(startDate);
        this.setMinutes(minutes);
        this.setSaleDate(saleDate);
    }

    public Integer getTicketID() {
        return ticketID;
    }

    public void setTicketID(Integer ticketID) {
        this.ticketID = ticketID;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(Integer agencyID) {
        this.agencyID = agencyID;
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public void saveMe() {
        TicketPersist tp = new TicketPersist(this);
        tp.guardarDatos();
    }
}
