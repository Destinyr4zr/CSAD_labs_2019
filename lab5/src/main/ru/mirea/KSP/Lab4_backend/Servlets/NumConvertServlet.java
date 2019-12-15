package main.ru.mirea.KSP.Lab4_backend.Servlets;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import main.ru.mirea.KSP.Lab4_backend.Main;
import main.ru.mirea.KSP.Lab4_backend.NumConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumConvertServlet extends FreemarkerServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int inputradix = Integer.parseInt(request.getParameter("inputradix"));
        int outputradix = Integer.parseInt(request.getParameter("outputradix"));
        String entry = request.getParameter("entry");
        try {
            NumConverter converter = new NumConverter(entry, inputradix, outputradix);
            Main.templwriter.writeTaskTemplate(converter.getResult().toString(),response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
