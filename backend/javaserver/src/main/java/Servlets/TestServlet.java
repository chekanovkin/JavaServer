package Servlets;

import DataSets.MarksDataSet;
import DataSets.StudentsDataSet;
import DataSets.TeachersDataSet;
import Services.MarkService;
import Services.StudentService;
import Services.TeacherService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "MyServlet",
        urlPatterns = {"/hello"}
)
public class TestServlet extends HttpServlet {

    private final StudentService service = new StudentService();
    private final MarkService service1 = new MarkService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        try{
            String str = "{\"email\":\"muhaha@mail.ru\",\"surname\":\"Zara\",\"name\":\"Muha\",\"patronymic\":\"Lola\",\"role\":\"student\",\"password\":\"12345678Qq\"}";
            Gson gson = new Gson();
            StudentsDataSet student = gson.fromJson(str, StudentsDataSet.class);
            //service.addUser(student.getName(), student.getSurname(), student.getEmail(), student.getPassword(), student.getRegDate());
            //StudentsDataSet student = service.getCurUserByLogin("ymnyaga@yandex.ru");
            String json = gson.toJson(student);
            /*MarksDataSet mark = new MarksDataSet("84/100");
            service1.addMark("84/100");
            String json = gson.toJson(mark);*/
            out.write(json.getBytes());
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
