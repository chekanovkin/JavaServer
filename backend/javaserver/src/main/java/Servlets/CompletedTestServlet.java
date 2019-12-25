package Servlets;

import DataSets.AnswersDataSet;
import DataSets.MarksDataSet;
import DataSets.StudentsDataSet;
import DataSets.TestsDataSet;
import Services.AnswerService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;

@WebServlet(
        name = "CompletedTestServlet",
        urlPatterns = {"/completeTest"}
)

public class CompletedTestServlet extends HttpServlet {

    TestService test_service = new TestService();
    StudentService stud_service = new StudentService();
    private LinkedHashMap<String, String> responsejson = new LinkedHashMap<>();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String testId = req.getHeader("test_id");
        String studentId = req.getHeader("student_id");
        AnswersDataSet[] answers = gson.fromJson(req.getHeader("answers"), AnswersDataSet[].class);
        HashSet<AnswersDataSet> answerList = new HashSet<>(Arrays.asList(answers));
        try{
            int numberOfRightAnswers = 0;
            TestsDataSet test = test_service.getTestById(Integer.getInteger(testId));
            StudentsDataSet student = stud_service.getCurUserById(Integer.getInteger(studentId));
            for(AnswersDataSet a : answerList){
                if(a.isRight()){
                    numberOfRightAnswers++;
                }
            }
            MarksDataSet mark = new MarksDataSet();
            mark.setTest_id(test);
            mark.addStudent(student);
            int markInpercent = numberOfRightAnswers/answerList.size();
            mark.setMark(markInpercent + "%");
            student.addPassedTests(test);
            student.setAnswer_id(answerList);
            responsejson.put("status", "OK");
        } catch (Exception e){
            e.printStackTrace();
            responsejson.put("status", "failed");
        }
        ServletOutputStream out = resp.getOutputStream();
        out.write(gson.toJson(responsejson).getBytes());
        out.flush();
        out.close();
    }
}
