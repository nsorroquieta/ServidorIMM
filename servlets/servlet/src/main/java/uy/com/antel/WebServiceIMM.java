package uy.com.antel;

import javax.jws.WebService;

@WebService
public class WebServiceIMM {

    public String test(String inputText)
    {
        return "Respues de WebServiceIMM: " + inputText;
    }
}
