package uy.com.antel;

import java.util.ArrayList;
import java.util.Date;

public class IMMController {

    private ArrayList<Ticket> tickets;
    private static int minCost = 15; // costo cada 15 minutos;

    public IMMController() {
        tickets = new ArrayList<Ticket>();
    }

    public int salesRequest(int agencyId, String carReg, Date salesDate, Date startDate, int minutes, float price){
        return -1;
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


}
