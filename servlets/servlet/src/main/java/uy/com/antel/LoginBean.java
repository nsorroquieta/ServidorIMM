package uy.com.antel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped

public class LoginBean {
    private String usuario;
    private String nombre;
    private String password;

    public LoginBean() {
    }
    public String getUsuario() {
        return usuario;
    }
    public String getNombre() {return nombre;}
    public String getPassword() { return password; }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPassword(String password) {
        this.password = password;
    }


    public String entrar(){

        IMMController controller= IMMController.getInstance();
        try{
            controller.verifUsuPass(usuario, password);
            return "index.xhtml";
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage("idMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    e.getMessage(),"El usuario o password es incorrecto"));
            return null;
        }
    }
}
