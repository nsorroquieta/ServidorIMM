package uy.com.antel;

import java.util.Date;

public class Ticket {

    private int ticketId;
    private int agencyId;
    private String carRegistration;
    private Date salesDateTime;
    private Date startDateTime; //Falta indicar hora de comienzo
    private int minutes; //Faltan los minutos.
    private float totalPrice; //Esto debería pasarlo la intendencia.


    public Ticket(String carRegistration, Date salesDateTime, Date startDateTime, int minutes) {
        this.carRegistration = carRegistration;
        this.salesDateTime = salesDateTime;
        this.startDateTime = startDateTime;
        this.minutes = minutes;
    }

    public Ticket(int ticketId){

    }

    public void saveMe(){
        TicketPersist tp = new TicketPersist(this);
        tp.guardarDatos();
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }

    public Date getSalesDateTime() {
        return salesDateTime;
    }

    public void setSalesDateTime(Date salesDateTime) {
        this.salesDateTime = salesDateTime;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public int getMinutes() {
        return this.minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }}
