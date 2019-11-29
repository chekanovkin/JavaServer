package Servlets;

import DataSets.StudentsDataSet;
import DataSets.TeachersDataSet;
import Services.StudentService;
import Services.TeacherService;
import Services.UserService_Interface;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "RegServlet",
        urlPatterns = {"/check"}
)
public class RegistrationServlet extends HttpServlet {

    private UserService_Interface service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Gson gson = new Gson();
        StudentsDataSet stud = gson.fromJson(req.getHeader("body"), StudentsDataSet.class);
        if(stud.getRole().equals("student")){
            service = new StudentService();
            StudentsDataSet student = gson.fromJson(req.getHeader("body"), StudentsDataSet.class);
            try {
                if(service.getCurUserByLogin(stud.getEmail()) != null){
                    ServletOutputStream out = resp.getOutputStream();
                    out.write(gson.toJson("Already exists").getBytes());
                    out.flush();
                    out.close();
                } else{
                    service.addUser(student.getName(), student.getSurname(), student.getEmail(), student.getPassword(), student.getRegDate());
                    ServletOutputStream out = resp.getOutputStream();
                    out.write(gson.toJson("OK").getBytes());
                    out.flush();
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if(stud.getRole().equals("teacher")){
            service = new TeacherService();
            TeachersDataSet teacher = gson.fromJson(req.getHeader("body"), TeachersDataSet.class);
            try {
                if(service.getCurUserByLogin(stud.getEmail()) != null){
                    ServletOutputStream out = resp.getOutputStream();
                    out.write(gson.toJson("No such login").getBytes());
                    out.flush();
                    out.close();
                } else{
                    service.addUser(teacher.getName(), teacher.getSurname(), teacher.getEmail(), teacher.getPassword(), teacher.getRegDate());
                    ServletOutputStream out = resp.getOutputStream();
                    out.write(gson.toJson("OK").getBytes());
                    out.flush();
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        } else{
            ServletOutputStream out = resp.getOutputStream();
            out.write(gson.toJson("BAD_REQUEST").getBytes());
            out.flush();
            out.close();
        }
    }
}