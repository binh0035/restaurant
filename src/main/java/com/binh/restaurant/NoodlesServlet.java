package com.binh.restaurant;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class NoodlesServlet extends HttpServlet {
    private static Logger mLogger = Logger.getLogger(NoodlesServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        Properties prop = new Properties();

        prop.setProperty("log4j.rootLogger", "debug, stdout, D");
//      ### 输出信息到控制抬 ###
        prop.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        prop.setProperty("log4j.appender.stdout.Target", "System.out");
        prop.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.stdout.layout.ConversionPattern",
                "[%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} method:%l%n%m%n");

//      ### 输出DEBUG 级别以上的日志到=E://logs/error.log ###
        prop.setProperty("log4j.appender.D", "org.apache.log4j.DailyRollingFileAppender");
        prop.setProperty("log4j.appender.D.File", "${user.home}/debug_logs/log.log");
        prop.setProperty("log4j.appender.D.Append", "true");
        prop.setProperty("log4j.appender.D.Threshold", "DEBUG");
        prop.setProperty("log4j.appender.D.layout", "org.apache.log4j.PatternLayout");
        prop.setProperty("log4j.appender.D.layout.ConversionPattern",
                "%-d{yyyy-MM-dd HH:mm:ss}  [ %t:%r ] - [ %p ]  %m%n");

        PropertyConfigurator.configure(prop);
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String vegetable = req.getParameter("vegetable");

        if (vegetable == null) {
            vegetable = "Tomato";
            mLogger.debug("面条里加了默认的 " + vegetable + "。");
        } else {
            mLogger.debug("面条里加了 " + vegetable + "。");
        }


        writer.println("<html><body>");
        writer.println("<h1> Noodles with " + vegetable + "</h1>");
        writer.println("</body></html>");
    }
}