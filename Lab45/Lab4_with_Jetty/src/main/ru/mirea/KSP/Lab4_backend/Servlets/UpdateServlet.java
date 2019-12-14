package main.ru.mirea.KSP.Lab4_backend.Servlets;

import main.ru.mirea.KSP.Lab4_backend.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Main.pencil_table_service.setPencil(Integer.parseInt(req.getParameter("id")),req.getParameter("date"), req.getParameter("col"), req.getParameter("length"));
            resp.setContentType("text/html");
            resp.setStatus(200);
            resp.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter returner = resp.getWriter();
            returner.print("Строка "+req.getParameter("id")+" обновлена</br>");
            returner.print("Данные: дата производства карандаша- "+req.getParameter("date")+"</br>цвет карандаша- "+req.getParameter("col")+"</br>длина карандаша- "+req.getParameter("length"));
            returner.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
