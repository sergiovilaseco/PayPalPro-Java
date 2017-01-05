/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands.DoProDirect;

import Commands.Command;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author seromero
 */
public class doProDirect implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            URL url = new URL("https://api-3t.sandbox.paypal.com/nvp");

            String API_UserName = "PayPal-Pro-User_api1.fake.com";
            String API_Password = "PayPal-Pro-Password";
            String API_Signature = "PayPal-Pro-Signature";

            String version = "204.0";

            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            String METHOD = "DoDirectPayment";
            String PAYMENTACTION = request.getParameter("PAYMENTACTION");
            //String IPADDRESS         = request.getParameter("IPADDRESS");     
            String CURRENCYCODE = request.getParameter("CURRENCYCODE");
            String AMT = request.getParameter("AMT");
            String CREDITCARDTYPE = request.getParameter("CREDITCARDTYPE");
            String ACCT = request.getParameter("ACCT");
            String EXPDATE = request.getParameter("EXPDATE");
            String CVV2 = request.getParameter("CVV2");
            String FIRSTNAME = request.getParameter("FIRSTNAME");
            String LASTNAME = request.getParameter("LASTNAME");
            String STREET = request.getParameter("STREET");
            String CITY = request.getParameter("CITY");
            String STATE = request.getParameter("STATE");
            String COUNTRYCODE = request.getParameter("COUNTRYCODE");

            String postData = "METHOD=" + METHOD + "&VERSION=" + version + "&PWD=" + API_Password + "&USER=" + API_UserName + "&SIGNATURE=" + API_Signature
                    + "&PAYMENTACTION=" + PAYMENTACTION + "&AMT=" + AMT + "&CURRENCYCODE=" + CURRENCYCODE + "&CREDITCARDTYPE=" + CREDITCARDTYPE + "&ACCT=" + ACCT
                    + "&EXPDATE=" + EXPDATE + "&CVV2=" + CVV2 + "&FIRSTNAME=" + FIRSTNAME + "&LASTNAME=" + LASTNAME + "&STREET=" + STREET + "&CITY=" + CITY + "&STATE=" + STATE
                    + "&COUNTRYCODE=" + COUNTRYCODE;

            con.setRequestProperty("Content-length", String.valueOf(postData.length()));

            con.setDoOutput(true);
            con.setDoInput(true);

            DataOutputStream output = new DataOutputStream(con.getOutputStream());

            output.writeBytes(postData);
            output.close();

            int code = con.getResponseCode();
            
            if (con.getResponseCode() == 200){
                DataInputStream input = new DataInputStream(con.getInputStream());
            int c;
            StringBuilder resultBuf = new StringBuilder();
            while ((c = input.read()) != -1) {
                resultBuf.append((char) c);
            }
            input.close();

            HttpSession session = request.getSession();
            
            session.setAttribute("result", resultBuf.toString());
            
            return "result.jsp";
            }else{
                throw new IOException("Connetion error");
            }
            

        } catch (IOException ex) {

            if (ex instanceof MalformedURLException) {
                MalformedURLException e = (MalformedURLException) ex;

                e.printStackTrace();

                return e.getMessage();
            }

            ex.printStackTrace();
            return ex.getMessage();
        }
    }
}
