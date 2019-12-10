package main.ru.mirea.KSP.Lab3_backend.Servlets;

import main.ru.mirea.KSP.Lab3_backend.Main;
import main.ru.mirea.KSP.Lab3_backend.NumConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Main.penciltable.remove(Integer.parseInt(req.getParameter("id"))-1);
        try {
            resp.setContentType("text/html");
            resp.setStatus(200);
            resp.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter returner = resp.getWriter();
            returner.print("Строка "+req.getParameter("id")+" удалена");
            returner.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
