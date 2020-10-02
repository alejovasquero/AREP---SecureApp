package edu.escuelaing.arep.webservices;


import java.util.ArrayList;

import static edu.escuelaing.arep.webservices.SparkWebApp.getPort;
import static spark.Spark.*;
import static spark.Spark.post;

public class SimpleWebService {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "123456", null, null);
        staticFileLocation("/public");
        post("/result", (req, resp) -> {
            System.out.println(req.body());
            if(!SparkWebApp.checkAuthentication(req, resp)){
                resp.redirect("/login.html");
                resp.status(403);
                return "403";
            } else {
                resp.type("application/json");
                String[] nums = req.body().split(",");
                ArrayList<Integer> ans = new ArrayList<Integer>();
                for(String a: nums){
                    ans.add(Integer.parseInt(a));
                }
                resp.status(200);
                return "{\"result\": "+ calculateMean(ans)+"}";
            }
        });
    }

    /**
     * Calcula el promedio de una lista de enteros
     * @param num Lista de enteros
     * @return Promedio de la lista
     */
    public static float calculateMean(ArrayList<Integer> num){
        int ans = 0;
        System.out.println(num);
        for(int a: num){
            ans+=a;
        }
        float re =(float)ans/(float)num.size();
        return re;

    }
}
