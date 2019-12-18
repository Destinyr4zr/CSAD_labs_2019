package ru.mirea.KSP.LAB2;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetSocketAddress;


public class Main {
    private static NumConverter converter;
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new InfoHandler());
        server.createContext("/get", new GetHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("The server is running");
    }
    static class InfoHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws IOException {

            StringBuilder response = new StringBuilder();
            response.append("<html><body>");
            response.append("Работу выполнил: Судариков Кирилл Алексеевич" + "<br/>");
            response.append("Номер индивидуального задания: 5" + "<br/>");
            response.append("Текст индивидуального задания: \n5)Конвертер систем счисления. На вход поступает число, которое мы хотим конвертировать; система счисления конвертируемого числа; система счисления, в которую мы хотим преобразовать число. \nЧисла должны поступать в виде строки с некоторым разделителем; в массиве; списком чисел." + "<br/>");
            response.append("</body></html>");
            Main.writeResponse(httpExchange, response.toString());
        }
    }

    static class GetHandler implements HttpHandler {
        public void handle(HttpExchange httpExchange) throws  IOException {
            StringBuilder response = new StringBuilder();

            String parms = httpExchange.getRequestURI().getQuery(), userinput = null;
            String[] attr = parms.split("&");
            int inputradix = 0, outputradix = 0;
            for (int i = 0; i < 3; i++) {
                String[] getterattr = attr[i].split("=");
                switch (i) {
                    case 0:
                        inputradix = Integer.parseInt(getterattr[1]);
                        break;
                    case 1:
                        outputradix = Integer.parseInt(getterattr[1]);
                        break;
                    case 2:
                        userinput = getterattr[1];
                        break;
                }
            }
            response.append("<html><body>");
            response.append("Исходные данные: ").append(parms).append("<br/>");
            try {
                converter = new NumConverter(userinput, inputradix, outputradix);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.append("Результат: ").append(converter.getResult()).append("<br/>");
            response.append("</body></html>");
            Main.writeResponse(httpExchange, response.toString());
        }
    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        String encoding = "UTF-8";
        httpExchange.getResponseHeaders().set("Content-Type", "text/html; charset=" + encoding);
        httpExchange.sendResponseHeaders(200, 0);
        Writer w = new OutputStreamWriter(httpExchange.getResponseBody(),encoding);
        w.write(response);
        w.close();
    }
}
