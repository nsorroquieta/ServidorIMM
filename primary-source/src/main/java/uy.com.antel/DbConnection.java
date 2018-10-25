package uy.com.antel;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DbConnection {

    protected static String DATASOURCE_CONTEXT = "java:jboss/datasources/MySqlDS_immDB";
    protected Context ctx = null;
    protected DataSource ds = null;
    protected ResultSet rs = null;
    protected Connection conn = null;
    protected PreparedStatement ps = null;


    public DbConnection() {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(DATASOURCE_CONTEXT);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getRs(){
        return this.rs;
    }

 /*   public boolean executeQuery(String query) {
        try {
            conn = ds.getConnection();
            if(metodo == Metodo.INSERT){
                ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
            }else{
                ps = conn.prepareStatement(query);
                rs = ps.executeQuery();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
*/
/*
    public void loadData(Sistema s){
        try {
            conn = ds.getConnection();
            String query = "SELECT * FROM editorial";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("nombre");
                Editorial e = new Editorial(name);
                e.setFecha(rs.getDate("fecha"));
                e.setId(rs.getInt("id"));
                e.loadPublicaciones();
                s.addEditorial(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

*/







}