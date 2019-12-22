package Main;

import java.io.File;

import Servlets.LogInServlet;
import Servlets.RegistrationServlet;
import Servlets.TestServlet;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;


public class Main {

    public final static String TARGET_PATH = "mainrep2/backend/javaserver/target";
    public final static String WEBINF_PATH = "/WEB-INF/classes";

    //TODO@ Проверка загрузки
    public static void main(String[] args) throws Exception{
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8081);

        Context ctx = tomcat.addContext("/", new File("").getAbsolutePath());
        Tomcat.addServlet(ctx, "MyServlet", new TestServlet());
        Tomcat.addServlet(ctx, "RegServlet", new RegistrationServlet());
        Tomcat.addServlet(ctx, "LogInServlet", new LogInServlet());
        File additionWebInfClasses = new File(TARGET_PATH);
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, WEBINF_PATH,
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        ctx.addServletMappingDecoded("/checkreg", "RegServlet");
        ctx.addServletMappingDecoded("/checklog", "LogInServlet");
        tomcat.start();
        System.out.println("Server started");
        tomcat.getServer().await();
    }
}