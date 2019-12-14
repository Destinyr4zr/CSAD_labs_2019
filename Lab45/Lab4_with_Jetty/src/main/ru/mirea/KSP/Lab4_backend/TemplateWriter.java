package main.ru.mirea.KSP.Lab4_backend;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TemplateWriter {
    public void writeNumConverterTemplate(String result, HttpServletResponse response) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setClassForTemplateLoading(TemplateWriter.class, "/");
        cfg.setDirectoryForTemplateLoading(new File(Main.FRONTEND_PATH + "/ftl"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        Template template = cfg.getTemplate("task.ftl");
        response.setCharacterEncoding("utf-8");
        PrintWriter returner = response.getWriter();
        template.process(map, returner);
        returner.flush();
    }

    public void writeTable()
    {}
}
