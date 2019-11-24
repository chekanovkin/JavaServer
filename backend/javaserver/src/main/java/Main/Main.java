package Main;

import java.io.File;
import Servlets.RegistrationServlet;
import Servlets.TestServlet;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;


public class Main {

    //TODO@ Проверка загрузки
    public static void main(String[] args) throws Exception{
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8081);

        Context ctx = tomcat.addContext("/", new File("mainrep2").getAbsolutePath());
        Tomcat.addServlet(ctx, "MyServlet", new TestServlet());
        Tomcat.addServlet(ctx, "RegServlet", new RegistrationServlet());
        File additionWebInfClasses = new File("mainrep2/backend/javaserver/target");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        ctx.addServletMappingDecoded("/check", "RegServlet");
        tomcat.start();
        System.out.println("Server started");
        tomcat.getServer().await();
    }
}