package ua.goit.servlets;

import ua.goit.models.Developer;
import ua.goit.models.Skill;
import ua.goit.repository.QueryRepositoryHibernateImpl;
import ua.goit.service.SkillService;
import ua.goit.util.HandleBodyStringUtil;
import ua.goit.util.HandleBodyUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet("/")
public class MainServlet extends HttpServlet{

    private final QueryRepositoryHibernateImpl repo = new QueryRepositoryHibernateImpl();
    private List<Developer> devsSkillField = new ArrayList<>();
    private List<Developer> devsSkillLevel = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listSkill", repo.listOfSkillsField());
        req.setAttribute("listSkillLevel", repo.listOfSkillsLevel());

        req.setAttribute("devsSkillField", devsSkillField);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        System.out.println(req.getHeaderNames());
        HandleBodyStringUtil.getModelFromStream(req.getInputStream()).ifPresent(skill -> {
            devsSkillField = repo.listDevsWithSkill(skill.replaceAll("\"", ""));
        });
//        HandleBodyStringUtil.getModelFromStream(req.getInputStream()).ifPresent(skill -> {
//            devsSkillLevel = repo.listDevsWithLevel(skill.replaceAll("\"", ""));
//        });
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
