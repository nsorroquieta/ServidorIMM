package uy.com.antel;

import javax.jws.WebService;
import java.util.Calendar;
import java.util.Date;

@WebService
public class WebServiceIMM {

    public String test(String inputText)
    {
        Date fechayhora = new Date();
        Date fechayhora2 = new Date();
        Ticket ticket = new Ticket("MAA1020", fechayhora, fechayhora2, 10);

        return "Respues de WebServiceIMM: " + inputText;
    }

    public String comprarTicket(int AgenciaVenta, String carResgistry, String salesDateTime, String startDateTime, int minutes){
        Date fecha = Calendar.getInstance().getTime();
        Ticket t = new Ticket(carResgistry, fecha, fecha, 10);
        t.setAgencyId(1);
        t.saveMe();
        int precioMinutos = 60;
        int total = minutes * precioMinutos;
        return "El precio a pagar es: "+total;
    }

}
