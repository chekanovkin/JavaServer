package Servlets;

import DataSets.GroupsDataSet;
import DataSets.StudentsDataSet;
import DataSets.TeachersDataSet;
import DataSets.TestsDataSet;
import Services.StudentService;
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
import java.util.Set;

@WebServlet(
        name = "LogInServlet",
        urlPatterns = {"/checklog"}
)

public class LogInServlet extends HttpServlet {

    private TestService test_service = new TestService();
    private StudentService stud_service = new StudentService();
    private TeacherService teacher_service = new TeacherService();
    private List<TestsDataSet> deprecatedTests = new ArrayList<>();
    private List<TestsDataSet> actualTests = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String login = gson.fromJson(req.getHeader("body"), String.class);
        try {
            if(stud_service.getCurUserByLogin(login) != null){
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
                out.write(gson.toJson("allowed tests:").getBytes());
                out.write(gson.toJson(cur_tests).getBytes());
                out.write(gson.toJson("passed tests:").getBytes());
                out.write(gson.toJson(student.getPassedTests_id()).getBytes());
                out.flush();
                out.close();
            } else if(teacher_service.getCurUserByLogin(login) != null){
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
                out.write(gson.toJson("deprecated tests:").getBytes());
                out.write(gson.toJson(deprecatedTests).getBytes());
                out.write(gson.toJson("actual tests:").getBytes());
                out.write(gson.toJson(actualTests).getBytes());
                out.flush();
                out.close();
            } else {
                ServletOutputStream out = resp.getOutputStream();
                out.write(gson.toJson("Error : no such user was found").getBytes());
                out.flush();
                out.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
