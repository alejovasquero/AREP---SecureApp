package edu.escuelaing.arep.webservices;


import edu.escuelaing.arep.security.AuthProvider;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import static spark.Spark.*;
import static spark.Spark.post;

public class SimpleWebService {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "123456", null, null);
        staticFileLocation("/public");
        post("/result", (req, resp) -> {
            System.out.println(req.body());
            if(!checkAuthentication(req, resp)){
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

    /**
     * Cambia el puerto de respuesta, dependiendo del entorno de despliegue
     * @return Puerto a trabajar
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    /**
     * Verifica que el request tenga autenticación
     * @param req Request del usuario
     * @param res Respuesta del usuario
     * @return Si el usuario tiene autenticación
     */
    public static boolean checkAuthentication(Request req, Response res){
        boolean follow  = AuthProvider.isAuthenticated(req);
        String page = req.pathInfo();
        System.out.println(follow);
        return follow || page.equals("/login.html");
    }
}
