package main.ru.mirea.KSP.Lab4_backend.Servlets;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import main.ru.mirea.KSP.Lab4_backend.Main;
import main.ru.mirea.KSP.Lab4_backend.TemplateWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TaskServlet extends FreemarkerServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Main.templwriter.writeTaskTemplate("", response);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
