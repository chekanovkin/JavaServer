package Main;

import java.io.File;
import java.util.Scanner;

import DataSets.StudentsDataSet;
import Services.MarkService;
import Services.StudentService;
import Servlets.RegistrationServlet;
import Servlets.TestServlet;
import org.apache.catalina.Context;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class Main {

    //TODO@ Проверка загрузки
    public static void main(String[] args) throws Exception{
        String webappDirLocation = "C:\\Users\\ymnya\\IdeaProjects\\mainrep\\backend\\javaserver\\src\\main\\webapp";
        Tomcat tomcat = new Tomcat();
        MarkService service = new MarkService();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.

        tomcat.setPort(8080);

        /*StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);*/
        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());
        Tomcat.addServlet(ctx, "MyServlet", new TestServlet());
        Tomcat.addServlet(ctx, "RegServlet", new RegistrationServlet());

        ctx.addServletMappingDecoded("/hello", "MyServlet");
        ctx.addServletMappingDecoded("/registration", "RegServlet");
        tomcat.start();
        System.out.println("Server started");
        tomcat.getServer().await();
    }
}
