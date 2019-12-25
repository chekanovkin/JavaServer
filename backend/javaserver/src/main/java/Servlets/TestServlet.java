package Servlets;

import DataSets.*;
import Services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(
        name = "TestServlet",
        urlPatterns = {"/hello"}
)
public class TestServlet extends HttpServlet {

    private final StudentService service = new StudentService();
    private final MarkService service1 = new MarkService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        TestService testService = new TestService();
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        try{
            Gson gson = new Gson();
            /*TestsDataSet test1 = new TestsDataSet("firstTest", "25.12.2019", true, "Matchin");
            QuestionsDataSet q1 = new QuestionsDataSet("How many 2+2?", "text question");
            QuestionsDataSet q2 = new QuestionsDataSet("How many 2+3?", "text question");
            QuestionsDataSet q3 = new QuestionsDataSet("How many 3+3?", "text question");
            AnswersDataSet ansForQ1_1 = new AnswersDataSet("2", false);
            AnswersDataSet ansForQ1_2 = new AnswersDataSet("3", false);
            AnswersDataSet ansForQ1_3 = new AnswersDataSet("4", true);
            AnswersDataSet ansForQ1_4 = new AnswersDataSet("5", false);
            AnswersDataSet ansForQ2_1 = new AnswersDataSet("2", false);
            AnswersDataSet ansForQ2_2 = new AnswersDataSet("3", false);
            AnswersDataSet ansForQ2_3 = new AnswersDataSet("4", false);
            AnswersDataSet ansForQ2_4 = new AnswersDataSet("5", true);
            AnswersDataSet ansForQ3_1 = new AnswersDataSet("3", false);
            AnswersDataSet ansForQ3_2 = new AnswersDataSet("4", false);
            AnswersDataSet ansForQ3_3 = new AnswersDataSet("5", false);
            AnswersDataSet ansForQ3_4 = new AnswersDataSet("6", true);
            q1.addAnswer(ansForQ1_1);
            q1.addAnswer(ansForQ1_2);
            q1.addAnswer(ansForQ1_3);
            q1.addAnswer(ansForQ1_4);
            q2.addAnswer(ansForQ2_1);
            q2.addAnswer(ansForQ2_2);
            q2.addAnswer(ansForQ2_3);
            q2.addAnswer(ansForQ2_4);
            q3.addAnswer(ansForQ3_1);
            q3.addAnswer(ansForQ3_2);
            q3.addAnswer(ansForQ3_3);
            q3.addAnswer(ansForQ3_4);
            test1.addQuestion(q1);
            test1.addQuestion(q2);
            test1.addQuestion(q3);
            testService.addFullTest(test1);
            questionService.addFullQuestion(q1);
            questionService.addFullQuestion(q2);
            questionService.addFullQuestion(q3);
            answerService.addFullAnswer(ansForQ1_1);
            answerService.addFullAnswer(ansForQ1_2);
            answerService.addFullAnswer(ansForQ1_3);
            answerService.addFullAnswer(ansForQ1_4);
            answerService.addFullAnswer(ansForQ2_1);
            answerService.addFullAnswer(ansForQ2_2);
            answerService.addFullAnswer(ansForQ2_3);
            answerService.addFullAnswer(ansForQ2_4);
            answerService.addFullAnswer(ansForQ3_1);
            answerService.addFullAnswer(ansForQ3_2);
            answerService.addFullAnswer(ansForQ3_3);
            answerService.addFullAnswer(ansForQ3_4);*/
            TestsDataSet test1 = testService.getTestById(1);
            Set<QuestionsDataSet> q = test1.getQuestions_id();
            //String questions = gson.toJson(q);
            ObjectMapper mapper = new ObjectMapper();
            String str = mapper.writeValueAsString(test1);
            //out.write(questions.getBytes());
            out.write(str.getBytes());
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
