package main.ru.mirea.KSP.Lab4_backend.Servlets;

import main.ru.mirea.KSP.Lab4_backend.Main;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (Main.pencil_table_service.countPencils()>0)
            Main.templwriter.writeTableTemplate("Таблица загружена",Main.pencil_table_service.getPencils("all",""),resp);
            else
                Main.templwriter.writeTableTemplate("Таблица отсутствует",null,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
