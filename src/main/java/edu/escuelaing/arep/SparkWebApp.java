package edu.escuelaing.arep;

import edu.escuelaing.arep.security.AuthProvider;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

/**
 * @author Alejandro Vasquez
 */
public class SparkWebApp {

    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "123456", null, null);
        staticFileLocation("/public");
        before((req, res) -> {
            boolean follow  = AuthProvider.isAuthenticated(req);
            String page = req.pathInfo();
            System.out.println(page);
            if(!(follow || page.equals("/login"))){
                res.redirect("/login.html");
            }
        });
        get("/hello", (req, res) -> "Hello World");
        post("/login", (req, resp) -> doLogin(req, resp));
    }



    private static String doLogin(Request req, Response resp){
        String user = req.queryParams("username");
        String password = req.queryParams("password");
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
}
