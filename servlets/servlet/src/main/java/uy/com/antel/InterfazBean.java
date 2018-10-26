/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.antel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author e567713
 */
@ManagedBean
@RequestScoped
public class InterfazBean {

    private List<Ticket> tickets;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    public InterfazBean() {
        /*tickets = new ArrayList<Ticket>();
        IMMController controller= IMMController.getInstance();
        try{
            for (Ticket tique: controller.obtenerTickets()){
                    tickets.add(tique);
            }
        }catch(Exception e){}
    */
    }
}
