package uy.com.antel;

import java.util.ArrayList;
import java.util.Date;

public class IMMController {

    private static IMMController instance;
    private ArrayList<Ticket> tickets;
    private static int minCost = 15; // costo cada 15 minutos;

    public static IMMController getInstance(){
        if(instance == null){
            instance = new IMMController();
        }
        return instance;
    }

    private IMMController() {
        tickets = new ArrayList<Ticket>();
    }

    public int salesRequest(int agencyId, String carReg, Date salesDate, Date startDate, int minutes, float price){
        /*
        if(salesDate == null) throw new DateException("fecha de venta invalida");
        if(startDate == null) throw new DateException("fecha de comienzo invalida");
         */
        Ticket  t = new Ticket(agencyId, carReg, salesDate, startDate, minutes);
        t.setAgencyID(agencyId);
        t.setPrice(price);
        t.saveMe();
        return t.getTicketID();
    }

    public float calculateCost(int minutes){
        //calculo que los minutos sean multiplos de 15
        float retorno = -1;
        if((minutes % 15) == 0){
            int cantCuartos = minutes / 15;
            retorno = cantCuartos * minCost;
        }
        return retorno;
    }

    /*private Ticket getTicketById(int ticketId){
        return new Ticket(ticketId);
    }

    public int cancellationRequest(int agencyId, int ticketId){
        if(this.getTicketById(ticketId).getAgencyID() == agencyId){
            //realizar cancelacion return id
            return 0;
        }
        return -1;
    }
*/
}
