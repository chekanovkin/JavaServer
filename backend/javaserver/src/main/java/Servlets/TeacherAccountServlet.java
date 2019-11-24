package Servlets;

import DataSets.TeachersDataSet;
import DataSets.TestsDataSet;
import Services.TeacherService;
import Services.TestService;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "TeacherAccServlet",
        urlPatterns = {"/getTeacherAcc"}
)

public class TeacherAccountServlet extends HttpServlet {

    private TestService test_service = new TestService();
    private TeacherService teacher_service = new TeacherService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        String login = gson.fromJson(req.getHeader("body"), String.class);
        List<TestsDataSet> deprecatedTests = new ArrayList<>();
        List<TestsDataSet> actualTests = new ArrayList<>();

        try {
            TeachersDataSet teacher = teacher_service.getCurUserByLogin(login);
            List<TestsDataSet> allTests = test_service.getTestsByTeacher(teacher.getId());
            for(TestsDataSet test : allTests){
                if(!test.isDeprecated()){
                    actualTests.add(test);
                } else {
                    deprecatedTests.add(test);
                }
            }
            ServletOutputStream out = resp.getOutputStream();
            out.write("deprecated tests:".getBytes());
            out.write(gson.toJson(deprecatedTests).getBytes());
            out.write("actual tests:".getBytes());
            out.write(gson.toJson(actualTests).getBytes());
            out.flush();
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
