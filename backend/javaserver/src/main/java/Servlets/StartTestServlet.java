package Servlets;

import DataSets.TeachersDataSet;
import DataSets.TestsDataSet;
import Services.TestService;
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
        name = "StartTestServlet",
        urlPatterns = {"/startTest"}
)
public class StartTestServlet extends HttpServlet {

    TestService service = new TestService();
    private LinkedHashMap<String, String> responsejson = new LinkedHashMap<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Gson gson = new Gson();
        String testId = req.getHeader("test_id");
        try {
            TestsDataSet test = service.getTestById(Integer.parseInt(testId));
            StringBuilder sb = new StringBuilder();
            sb.append(test.getTeacher_id().getName());
            sb.append(test.getTeacher_id().getSurname());
            sb.append(test.getTeacher_id().getPatronymic());
            responsejson.put("status", "OK");
            responsejson.put("author", gson.toJson(sb.toString(), String.class));
            responsejson.put("description", gson.toJson(test.getAbout_test(), String.class));
            responsejson.put("solution time", gson.toJson(test.getSolution_time(), int.class));
            responsejson.put("attempts", gson.toJson(test.getAttempts(), int.class));
        }catch (Exception e){
            e.printStackTrace();
            responsejson.put("Status", "failed");
        }
        ServletOutputStream out = resp.getOutputStream();
        out.write(gson.toJson(responsejson).getBytes());
        out.flush();
        out.close();
    }
}
