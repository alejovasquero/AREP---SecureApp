package edu.escuelaing.arep.security;

import org.apache.commons.codec.digest.DigestUtils;
import spark.Request;

public class AuthProvider {

    private static String BURNED_USER = "usuario";
    //CON OBJETIVOS DE ESTUDIO, LA CONTRASEÑA SERÁ 'contraseña'
    public static String BURNED_PASSWORD = "edf9cf90718610ee7de53c0dcc250739239044de9ba115bb0ca6026c3e4958a5";

    /**
     * Verifica que el request tenga autentiocación
     * @param res Solicitud del usuario
     * @return Si el usuario tiene una autenticación válida
     */
    public static boolean isAuthenticated(Request  res){
        String username = res.session().attribute("username");
        String password = res.session().attribute("password");
        System.out.println(username);
        System.out.println(password);
        String username2 = res.queryParams("username");
        String password2 = res.queryParams("password");
        return (username!=null && password!=null && isUser(username, password))
                || (username2!=null && password2!=null && isUser(username2, password2));
    }

    /**
     * Verifica que el usuario exista
     * @param username Nombre de usuario
     * @param password Contraseña en texto plano
     * @return
     */
    public static boolean isUser(String username, String password){
        String sha256hex = DigestUtils.sha256Hex(password);
        return username.equals(BURNED_USER) && sha256hex.equals(BURNED_PASSWORD);
    }

}
