package main.ru.mirea.KSP.Lab4_backend;

import main.ru.mirea.KSP.Lab4_backend.Database.PencilsService;
import main.ru.mirea.KSP.Lab4_backend.Servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {
    public static PencilsService pencil_table_service;
    public static final String FRONTEND_PATH = "E:/Projects/5sem/KSPlabs/Lab45/Lab4_with_Jetty/src/main/resources/Lab4_frontend";
    public static final TemplateWriter templwriter = new TemplateWriter();
    public static void main(String[] args) throws Throwable {
        Server server = new Server(1488);
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setResourceBase(FRONTEND_PATH);
        webapp.setParentLoaderPriority(true);
        webapp.addServlet(IndexServlet.class, "/index");
        webapp.addServlet(TableServlet.class, "/table");
        webapp.addServlet(TaskServlet.class, "/task");
        webapp.addServlet(NumConvertServlet.class, "/post");
        webapp.addServlet(DeleteServlet.class, "/delete");
        webapp.addServlet(UpdateServlet.class, "/update");
        webapp.addServlet(InsertServlet.class, "/insert");
        server.setHandler(webapp);
        server.start();
        System.out.println("Server started");
        pencil_table_service = new PencilsService();
        System.out.println("Database loaded");
    }
}
