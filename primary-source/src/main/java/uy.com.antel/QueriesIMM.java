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
            String query = "select * from tickets where nroTicket="+nroTicket +" and Agencia ='"+agencia+"' and Estado='ACTIVO'";
            conn = ds.getConnection();
            //ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();

            //DataSource ds = (DataSource)initContext.lookup("java:jboss/datasources/MySqlDS");
            //Connection conexion = ds.getConnection();
            //PreparedStatement ps = conexion.prepareStatement("select * from tickets where nroTicket="+nroTicket +" and Agencia ='"+agencia+"' and Estado='ACTIVO'");
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

    public  void anulaTicket(int nroTicket, int agencia) throws Exception{
        // TicketAnulacion t = new TicketAnulacion();
        if (!ticketVigente(nroTicket,agencia)){
            //NO EXISTE TICKET ACTIVO PARA ESA AGENCIA CON ESE No.
        }else{
            try{
                //InitialContext initContext = new InitialContext();
                //DataSource ds = (DataSource)initContext.lookup("java:jboss/datasources/MySqlDS");
                //Connection conexion = ds.getConnection();
                conn = ds.getConnection();

                String query1 = "update tickets set Estado='ANULADO' where nroTicket="+ nroTicket+ "";
                ps = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();

                //String query2=
                //PreparedStatement ps2 = conexion.prepareStatement("select SecIdAnul from secuencias where SecId = 1");
                //ResultSet listAnul = ps2.executeQuery();
                //listAnul.next();
                //int proxAnul = listAnul.getInt("SecIdAnul");

                String query3 = " insert into anulaciones (idTicketAnul,agencyId,anulDate) values (?,?,?)";
                ps = conn.prepareStatement(query3, Statement.RETURN_GENERATED_KEYS);
                //PreparedStatement ps3 = conexion.prepareStatement(query);
                //ps.setInt (1,proxAnul);
                ps.setInt (1, nroTicket);
                ps.setInt (2, agencia);
                String anulDate = this.formatDateToDB(Calendar.getInstance().getTime());
                //SimpleDateFormat formato=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
                //String fechaAnulacion =formato.format(Calendar.getInstance().getTime());
                ps.setString (3, anulDate);
                ps.execute();

                //PreparedStatement ps4 = conexion.prepareStatement("update secuencias set SecIdAnul="+ ++proxAnul + " where SecId = 1");
                //ps4.executeUpdate();
                conn.close();

            }catch(Exception e){throw new Exception();}
        }}
}
