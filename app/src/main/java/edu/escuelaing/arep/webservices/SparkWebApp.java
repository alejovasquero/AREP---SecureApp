package edu.escuelaing.arep.webservices;

import edu.escuelaing.arep.security.AuthProvider;
import edu.escuelaing.arep.trust.URLReader;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 * @author Alejandro Vasquez
 */
public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        URLReader.main(null);
        secure("keystores/ecikeystoreapp.p12", "123456", null, null);
        staticFileLocation("/public");
        post("/result", (req, resp) -> {
            System.out.println("POSTING");
            if(!SparkWebApp.checkAuthentication(req, resp)){
                resp.redirect("/login.html");
                resp.status(403);
                System.out.println("FAILING");
                return "403";
            } else {
                resp.status(200);
                resp.type("application/json");
                return URLReader.readURL("https://ec2-54-237-2-49.compute-1.amazonaws.com:8091/result" , req.body());
            }
        });
        post("/login", (req, resp) -> doLogin(req, resp));
        get("/", (req, resp) ->  {
            if(!SparkWebApp.checkAuthentication(req, resp)){
                resp.redirect("/login.html");
                resp.status(403);
                return "403";
            } else {
                resp.status(200);
                return "HELLO";
            }
        });
        System.out.println(URLReader.readURL("https://ec2-54-237-2-49.compute-1.amazonaws.com:8091/result" , "1,2,3,4,5"));

    }


    /**
     * Realiza el login en cookies al usuario
     * @param req Request del usuario
     * @param resp Respuesta al usuario
     * @return Mensaje de respuesta
     */
    public static String doLogin(Request req, Response resp){
        String user = req.queryParams("username");
        String password = req.queryParams("password");
        System.out.println(user);
        System.out.println(password);
        if(AuthProvider.isUser(user, password)){
            req.session().attribute("username",user);
            req.session().attribute("password",password);
        }
        return "OK";
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
