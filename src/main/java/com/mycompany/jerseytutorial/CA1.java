/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jerseytutorial;

import java.net.URL;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/project/")
public class CA1 {

    @GET
    @Path("/2/weather")
    @Produces(MediaType.APPLICATION_XML)
    public Response Weather(@QueryParam("city") String city, @QueryParam("mode") String mode) {
        String appid = "989fe0db2bd1016e06130ded324ce0c3";
        String requestURL = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&mode=" + mode + "&appid=" + appid;
        Client c = ClientBuilder.newClient();
        Response r = c.target(requestURL).request().get();
        return r;
    }

    @GET
    @Path("/1/converter")
    public Response ConvertNumbers(@QueryParam("param") String param) {
        String output = "";
        String romans = "";
        int result = 0;
        //Check for integer
        if (param.matches("[0-9]+")) {
            int reminder = Integer.parseInt(param);
            while (reminder >= 1000) {
                romans += "M";
                reminder -= 1000;
            }
            while (reminder >= 900) {
                romans += "CM";
                reminder -= 900;
            }
            while (reminder >= 500) {
                romans += "D";
                reminder -= 500;
            }
            while (reminder >= 400) {
                romans += "CD";
                reminder -= 400;
            }
            while (reminder >= 100) {
                romans += "C";
                reminder -= 100;
            }
            while (reminder >= 90) {
                romans += "XC";
                reminder -= 90;
            }
            while (reminder >= 50) {
                romans += "L";
                reminder -= 50;
            }
            while (reminder >= 40) {
                romans += "XL";
                reminder -= 40;
            }
            while (reminder >= 10) {
                romans += "X";
                reminder -= 10;
            }
            while (reminder >= 9) {
                romans += "IX";
                reminder -= 9;
            }
            while (reminder >= 5) {
                romans += "V";
                reminder -= 5;
            }
            while (reminder >= 4) {
                romans += "IV";
                reminder -= 4;
            }
            while (reminder >= 1) {
                romans += "I";
                reminder -= 1;
            }
            output = "Your converted number : " + romans;
        } else {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < param.length(); i++) {
                char letter = param.charAt(i);
                if (letter == 'M') {
                    numbers.add(1000);
                }
                if (letter == 'D') {
                    numbers.add(500);
                }
                if (letter == 'C') {
                    numbers.add(100);
                }
                if (letter == 'L') {
                    numbers.add(50);
                }
                if (letter == 'X') {
                    numbers.add(10);
                }
                if (letter == 'V') {
                    numbers.add(5);
                }
                if (letter == 'I') {
                    numbers.add(1);
                }
            }
            numbers.add(0);
            for (int i = 0; i < numbers.size() - 1; i++) {
                if (numbers.get(i) >= numbers.get(i + 1)) {
                    result += numbers.get(i);
                } else {
                    result += numbers.get(i + 1) - numbers.get(i);
                    i += 1;
                }
            }
            output = "Your converted roman is:" + result;
        }

        return Response.status(200).entity(output).build();
    }
}
