package ua.goit.servlets;

import ua.goit.models.Developer;
import ua.goit.models.Project;
import ua.goit.repository.CrudRepository;
import ua.goit.repository.RepositoryFactory;
import ua.goit.service.DeveloperService;
import ua.goit.service.ProjectService;
import ua.goit.util.HandleBodyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/projects/*")
public class ProjectServlet extends HttpServlet {

    private final ProjectService projectService= new ProjectService(Project.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String[] split = req.getRequestURI().split("/");
        if (split.length==3 && "new".equals(split[2])){
            req.setAttribute("project", new Project());
            req.getRequestDispatcher("/project/project.jsp").forward(req, resp);
        }
        if (split.length==3){
            Project project = projectService.findById(Long.valueOf(split[2])).get();
            req.setAttribute("project", project);
            req.getRequestDispatcher("/project/project.jsp").forward(req, resp);
        }
        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Optional<Project> project = projectService.findById(Long.valueOf(deleteId));
            if (project.isPresent()){
                System.out.println("Deleting entity: " + project.get());
                projectService.deleteById(Long.valueOf(deleteId));
            }
            resp.sendRedirect("/projects");
        }else {
            List<Project> projects = projectService.findAll();
            req.setAttribute("projects", projects);
            req.getRequestDispatcher("/project/projects.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class)
                .ifPresent(project -> {projectService.createEntity(project);});
        req.getRequestDispatcher("/project/project.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Project.class)
                .ifPresent(project -> {projectService.updateEntity(project);});
        req.getRequestDispatcher("/project/project.jsp").forward(req, resp);
    }
}
