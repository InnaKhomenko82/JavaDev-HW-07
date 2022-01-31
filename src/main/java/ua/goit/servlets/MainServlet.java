package ua.goit.servlets;

import ua.goit.models.Developer;
import ua.goit.models.Project;
import ua.goit.models.Skill;
import ua.goit.repository.QueryRepositoryHibernateImpl;
import ua.goit.service.CompanyService;
import ua.goit.service.SkillService;
import ua.goit.util.HandleBodyUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet("/")
public class MainServlet extends HttpServlet{

    private final SkillService skillService = SkillService.getInstance();

    private final QueryRepositoryHibernateImpl repo = new QueryRepositoryHibernateImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("listSkill", skillService.findAll());
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        Optional<Skill> modelFromStream = HandleBodyUtil.getModelFromStream(req.getInputStream(), Skill.class);
       /* modelFromStream.ifPresent(skill -> {
            repo.listDevsWithSkill()

                });*/

        List<Developer> developers = repo.listOfDevs();
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
