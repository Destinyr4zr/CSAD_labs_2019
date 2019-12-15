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
            int index = Integer.parseInt(req.getParameter("id"));
            Main.pencil_table_service.setPencil(index,req.getParameter("date"), req.getParameter("col"), req.getParameter("length"));
            String result = "Строка с индексом "+index+" обновлена</br>"
                    +"В таблице "+Main.pencil_table_service.countPencils()+" значений</br>"+
                    "Данные: дата производства карандаша: "+req.getParameter("date")+"</br>цвет карандаша: "+req.getParameter("col")+"</br>длина карандаша: "+req.getParameter("length");
            Main.templwriter.writeTableTemplate(result,Main.pencil_table_service.getPencils("all",""),resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
