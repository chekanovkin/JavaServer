package Servlets;

import DataSets.*;
import Services.TeacherService;
import Services.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@WebServlet(
        name = "MakeTestServlet",
        urlPatterns = {"/makeTest"}
)

public class MakeTestServlet extends HttpServlet {

    TestService service = new TestService();
    TeacherService teach_service = new TeacherService();
    private LinkedHashMap<String, String> responsejson = new LinkedHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String teacherId = req.getHeader("teacher_id");
        ObjectMapper mapper = new ObjectMapper();
        TestsDataSet newTest = mapper.readValue(req.getHeader("body"), TestsDataSet.class);
       // TestsDataSet newTest = gson.fromJson(req.getHeader("body"), TestsDataSet.class);
        if(newTest.getAnswers_id().size() == 0){
            responsejson.put("status","Error : test can't be empty");
        } else {
            try {
                TeachersDataSet creator = teach_service.getCurUserById(Integer.parseInt(teacherId));
                creator.addTest(newTest);
                service.addFullTest(newTest);
                responsejson.put("status","OK");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        ServletOutputStream out = resp.getOutputStream();
        out.write(gson.toJson(responsejson).getBytes());
        out.flush();
        out.close();
    }
}
