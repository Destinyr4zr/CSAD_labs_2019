package main.ru.mirea.KSP.Lab4_backend.Servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import main.ru.mirea.KSP.Lab4_backend.Main;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Main.templwriter.writeIndexTemplate(resp);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
