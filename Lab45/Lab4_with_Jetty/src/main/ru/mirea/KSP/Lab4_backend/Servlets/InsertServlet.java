package main.ru.mirea.KSP.Lab4_backend.Servlets;

import main.ru.mirea.KSP.Lab4_backend.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class InsertServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Main.pencil_table_service.createPencil(req.getParameter("date"), req.getParameter("col"), req.getParameter("length"));
            resp.setContentType("text/html");
            resp.setStatus(200);
            resp.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter returner = resp.getWriter();
            returner.print(Main.pencil_table_service.countPencils()+" Cтрока добавлена</br>");
            returner.print("Данные:</br> Дата производства карандаша: "+req.getParameter("date")+"</br>Цвет карандаша: "+req.getParameter("col")+"</br>Длина карандаша: "+req.getParameter("length"));
            returner.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
