package Servlets;

import DataSets.GroupsDataSet;
import DataSets.StudentsDataSet;
import DataSets.TestsDataSet;
import Services.StudentService;
import Services.TestService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(
        name = "StudAccServlet",
        urlPatterns = {"/getStudentAcc"}
)

public class StudentAccountServlet extends HttpServlet {

    private TestService test_service = new TestService();
    private StudentService stud_service = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        String login = gson.fromJson(req.getHeader("body"), String.class);
        try {
            StudentsDataSet student = stud_service.getCurUserByLogin(login);
            List<TestsDataSet> cur_tests = test_service.getAllPublicTests();
            Set<GroupsDataSet> curGroups = student.getGroups_id();
            for(GroupsDataSet group : curGroups){
                cur_tests.addAll(group.getTest_id());
            }
            for(TestsDataSet test : cur_tests){
                if(test.isDeprecated()){
                    cur_tests.remove(test);
                }
            }
            ServletOutputStream out = resp.getOutputStream();
            out.write("allowed tests:".getBytes());
            out.write(gson.toJson(cur_tests).getBytes());
            out.write("passed tests:".getBytes());
            out.write(gson.toJson(student.getPassedTests_id()).getBytes());
            out.flush();
            out.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
