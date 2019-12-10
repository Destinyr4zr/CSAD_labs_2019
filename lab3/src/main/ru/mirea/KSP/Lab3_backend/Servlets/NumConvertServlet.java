package main.ru.mirea.KSP.Lab3_backend.Servlets;

import main.ru.mirea.KSP.Lab3_backend.NumConverter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NumConvertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int inputradix = Integer.parseInt(req.getParameter("inputradix"));
        int outputradix = Integer.parseInt(req.getParameter("outputradix"));
        String entry = req.getParameter("entry");
        try {
            NumConverter converter = new NumConverter(entry, inputradix, outputradix);
            resp.setCharacterEncoding("utf-8");
            resp.setStatus(200);
            resp.setHeader("Access-Control-Allow-Origin", "*");
            PrintWriter returner = resp.getWriter();
            returner.print(converter.getResult().toString());
            returner.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("/task.html");
        view.forward(req, resp);
    }
}
