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

            String saleDate = this.formatDateToDB(ticket.getSaleDate());
            String startDate = this.formatDateToDB(ticket.getStartDate());
            String query = "INSERT INTO tickets (agencyId, carRegistration, minutes, saleDate, startDate) VALUES("+ticket.getAgencyID()+", '"+ticket.getCarRegistration()+"', "+ticket.getMinutes()+", '"+saleDate+"', '"+startDate+"')";
            conn = ds.getConnection();
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
            {
                int insertID = rs.getInt(1);
                if(insertID > 0){
                    ticket.setTicketID(insertID);
                }
                System.out.println("Insert Id "+insertID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /*
    public void loadTicket(Ticket t){
        try {
            String query = "SELECT * FROM tickets WHERE nroTicket = "+t.getTicketId();
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if(rs.next())
            {
                t.setAgencyId(rs.getInt("agenciaId"));
                t.setCarRegistration(rs.getString("matricula"));
                t.setMinutes(rs.getInt("cantMinutos"));
                t.setSalesDateTime(rs.getDate("fechaVenta"));
                t.setStartDateTime(rs.getDate("fechaHoraIni"));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    */
}
