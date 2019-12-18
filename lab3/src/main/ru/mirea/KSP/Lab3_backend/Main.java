package main.ru.mirea.KSP.Lab3_backend;

import main.ru.mirea.KSP.Lab3_backend.Servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static ArrayList<HashMap<String,String>> penciltable;

    public static void main(String[] args) throws Throwable {
        penciltable= new ArrayList<>();
        Server server = new Server(1488);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase("E:/Projects/5sem/KSPlabs/Выполнено/Lab3/Lab3_with_Jetty/src/main/resources/Lab3_frontend");
        webapp.setParentLoaderPriority(true);
        webapp.addServlet(IndexServlet.class, "/index");
        webapp.addServlet(TableServlet.class, "/table");
        webapp.addServlet(TaskServlet.class, "/task");
        webapp.addServlet(NumConvertServlet.class, "/post");
        webapp.addServlet(DeleteServlet.class, "/delete");
        webapp.addServlet(UpdateServlet.class, "/update");
        webapp.addServlet(InsertServlet.class, "/insert");
        webapp.addServlet(LoadTable.class, "/table/load");
        server.setHandler(webapp);
        server.start();
        System.out.println("Server started");
    }
}
