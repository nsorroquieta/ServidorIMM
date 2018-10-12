package uy.com.antel;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketPersist extends DbConnection {

    private Ticket ticket;

    public TicketPersist(Ticket ticket) {
        this.ticket = ticket;
    }



    private String formatDateToDB(Date fecha){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(fecha);
    }

    public boolean guardarDatos(){
        try {

            String salesDate = this.formatDateToDB(ticket.getSalesDateTime());
            String startDate = this.formatDateToDB(ticket.getStartDateTime());
            String query = "INSERT INTO tickets (agenciaId, matricula, cantMinutos, fechaVenta, fechaHoraIni) VALUES("+ticket.getAgencyId()+", '"+ticket.getCarRegistration()+"', "+ticket.getMinutes()+", '"+salesDate+"', '"+startDate+"')";
            conn = ds.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                int insertID = rs.getInt(1);
                if(insertID > 0){
                    ticket.setTicketId(insertID);
                }
                System.out.println("Insert Id "+insertID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
