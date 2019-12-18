package main.ru.mirea.KSP.Lab4_backend;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import main.ru.mirea.KSP.Lab4_backend.Database.PencilsModel;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateWriter {
    public void writeTaskTemplate(String result, HttpServletResponse response) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<>();
        map.put("result", result);
        writeTemplate(map, "task.ftl", response);
    }

    public void writeIndexTemplate(HttpServletResponse response) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<>();
        writeTemplate(map, "index.ftl", response);
    }

    public void writeTableTemplate(String result, List<PencilsModel> table, HttpServletResponse response) throws IOException, TemplateException {
        Map<String, Object> map = new HashMap<>();
        if (table != null)
            map.put("table", table);
        map.put("result", result);
        writeTemplate(map, "table.ftl", response);
    }

    private Configuration setConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setClassForTemplateLoading(TemplateWriter.class, "/");
        cfg.setDirectoryForTemplateLoading(new File(Main.FRONTEND_PATH + "/ftl"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return cfg;
    }

    private void writeTemplate(Map<String, Object> map, String templatepath, HttpServletResponse response) throws IOException, TemplateException {
        Template template = setConfiguration().getTemplate(templatepath);
        response.setCharacterEncoding("utf-8");
        PrintWriter returner = response.getWriter();
        Path filePath = Paths.get(Main.FRONTEND_PATH+"/ftl/menu.ftl");
        String menu = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        map.put("menu", menu);
        StringWriter sw = new StringWriter();
        template.process(map, sw);
        System.out.println(sw);
        template.process(map, returner);
        returner.flush();
    }
}
