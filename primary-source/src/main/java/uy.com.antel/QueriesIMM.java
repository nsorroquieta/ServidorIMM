package uy.com.antel;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public void verificaUsuario(String usuario, String pass) throws SQLException, Exception{
        boolean encontro = false;
        conn = ds.getConnection();
        String query1 = "select * from usuarios where IdUsuario = '"+usuario+"' and Password = '"+ pass+ "' ";
        ps = conn.prepareStatement(query1);
        ResultSet rs = ps.executeQuery();
        encontro = rs.next();
        /*while (rs.next()) {
            if (rs.getString("Password").equals(pass)){
                encontro = true;
            }
        }*/
        rs.close();
        ps.close();
        conn.close();
        if (!encontro) {
            throw new Exception("No se encontro usuario, verifique!");
        }
    }

    /*public List<Ticket> obtenerTickets()throws SQLException{
        try{
            conn = ds.getConnection();
            String query1 = "select * from tickets ";
            ps = conn.prepareStatement(query1);
            ResultSet rs = ps.executeQuery();

            List<Ticket> respuesta = new ArrayList<Ticket>();
            Ticket tick;
            //Calendar calendarFechaHoraVenta = GregorianCalendar.getInstance();
            //Calendar calendarFechaHoraEsta = GregorianCalendar.getInstance();

            Date calendarFechaHoraVenta;// = GregorianCalendar.getInstance();
            Date calendarFechaHoraEsta;// = GregorianCalendar.getInstance();
            Date date;
            Date date2;
            String fechaVentaBase = "";
            String fechaEstaBase = "";
            String fechaVenta = "";
            String fechaEsta = "";
            SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] arrayHoraVenta;
            String[] arrayHoraEsta;
            while (rs.next()) {
                tick = new Ticket();
                tick.setTicketID(Integer.parseInt(rs.getString("id")));
                tick.setAgencyID(rs.getInt("agencyId"));
                tick.setCarRegistration(rs.getString("carRegistration"));
                tick.setMinutes(Integer.parseInt(rs.getString("minutes")));
                //tick.setPrice(rs.getInt("price"));
                //tick.setStatus(rs.getString("Status"));

                fechaVentaBase = rs.getString("saleDate");
                fechaEstaBase = rs.getString("startDate");

                arrayHoraVenta = fechaVentaBase.split("\\.");
                arrayHoraEsta = fechaEstaBase.split("\\.");

                fechaVenta = arrayHoraVenta[0];
                fechaEsta= arrayHoraEsta[0];
                date =new Date();
                date2 =new Date();
                //calendarFechaHoraVenta = GregorianCalendar.getInstance();
                //calendarFechaHoraEsta = GregorianCalendar.getInstance();
                date = formato.parse(fechaVenta);
                date2 = formato.parse(fechaEsta);

                calendarFechaHoraVenta.setTime(date);
                calendarFechaHoraEsta.setTime(date2);

                tick.setSaleDate(calendarFechaHoraVenta);
                tick.setStartDate(calendarFechaHoraEsta);

                respuesta.add(tick);
            }
            rs.close();
            ps.close();
            conn.close();
            return respuesta;

        }catch(Exception e){
            return null;
        }

    }*/
}
