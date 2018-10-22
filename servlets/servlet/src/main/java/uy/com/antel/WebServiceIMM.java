package uy.com.antel;

import javax.jws.WebService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public int comprarTicket(int agencyId, String carResgistry, String salesDateTime, String startDateTime, int minutes){
        IMMController controller = IMMController.getInstance();
        float price = controller.calculateCost(minutes);
        Date salesDate = this.getDate(salesDateTime);
        Date startDate = this.getDate(startDateTime);
        int retorno = controller.salesRequest(agencyId, carResgistry,salesDate,startDate, minutes, price);
        return retorno;
    }

    private Date getDate(String fecha) {
        Date date1 = null;
        try {
            date1 = (Date) new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }

    public float calcularCosto(int minutos){
        IMMController immcontroller = IMMController.getInstance();
        return immcontroller.calculateCost(minutos);
    }


    public int cancellationRequest(int agencyId, int ticketid){
        IMMController controller = IMMController.getInstance();
        return controller.cancellationRequest(agencyId, ticketid);
    }

}
