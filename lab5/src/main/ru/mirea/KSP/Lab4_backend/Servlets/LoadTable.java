package main.ru.mirea.KSP.Lab4_backend.Servlets;

import com.google.gson.Gson;
import main.ru.mirea.KSP.Lab4_backend.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoadTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setStatus(200);
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setContentType("application/json");
            PrintWriter returner = resp.getWriter();
            if (Main.pencil_table_service.countPencils()>0)
            {
                String tablejson = new Gson().toJson(Main.pencil_table_service.getPencils("all",""));
                resp.setHeader("tabstatus","Таблица загружена");
                returner.print(tablejson);
            }
            else {
                resp.setHeader("tabstatus","Таблица пуста");
            }
            returner.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
