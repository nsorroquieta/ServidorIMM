package uy.com.antel;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QueriesIMM extends DbConnection {
    public QueriesIMM() {    }

    private String formatDateToDB(Date fecha){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(fecha);
    }

    public boolean ticketVigente(int nroTicket, int agencia){
        boolean existe= false;
        try{
            //InitialContext initContext = new InitialContext();
            String query = "select * from tickets where id="+nroTicket +" and agencyId ='"+agencia+"' and Status='ACTIVO'";
            conn = ds.getConnection();
            ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            existe = rs.next();
            rs.close();
            ps.close();
            conn.close();
            return existe;
        }catch(Exception e){}

        return existe;
    }

    public  void anulaTicket(int nroTicket, int agencia){
        if (!ticketVigente(nroTicket,agencia)){
            System.out.println("NO EXISTE TIKET-AGENCIA");
        }else{
            try{
                conn = ds.getConnection();
                String query1 = "update tickets set Status='ANULADO' where id="+ nroTicket+ "";
                System.out.println("Update tickets anulado"+nroTicket);
                ps = conn.prepareStatement(query1);
                ps.executeUpdate();

                String query3 = " insert into anulaciones (idTicketAnul,agencyId,anulDate) values (?,?,?)";
                ps = conn.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
                ps.setInt (1, nroTicket);
                ps.setInt (2, agencia);
                String anulDate = this.formatDateToDB(Calendar.getInstance().getTime());
                ps.setString (3, anulDate);
                ps.execute();

                System.out.println("Insert anulaciones - "+nroTicket +"-"+agencia+"-"+anulDate);
                conn.close();
            }catch(Exception e){e.printStackTrace();}
        }
    }
}
