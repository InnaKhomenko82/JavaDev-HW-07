package ua.goit.servlets;

import ua.goit.models.Developer;
import ua.goit.models.dto.DevCompanyDto;
import ua.goit.repository.QueryRepositoryHibernateImpl;
import ua.goit.service.CompanyService;
import ua.goit.service.DevCompanyService;
import ua.goit.service.DeveloperService;
import ua.goit.util.HandleBodyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/developers/*")
public class DeveloperServlet extends HttpServlet{

    private final DeveloperService developerService= new DeveloperService(Developer.class);
    private final CompanyService companyService = CompanyService.getInstance();
    private final DevCompanyService devCompanyService = new DevCompanyService(DevCompanyDto.class);
    private final QueryRepositoryHibernateImpl repo = new QueryRepositoryHibernateImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String[] split = req.getRequestURI().split("/");
        if (split.length==3 && "new".equals(split[2])){
            req.setAttribute("developer", new Developer());
            req.setAttribute("listCompany", companyService.findAll());
            System.out.println(companyService.findAll());
            req.getRequestDispatcher("/developer/developer.jsp").forward(req, resp);
            return;
        }
        if (split.length==3){
            Developer developer = developerService.findById(Long.valueOf(split[2])).get();
            req.setAttribute("developer", developer);
            req.setAttribute("listCompany", companyService.findAll());
            req.getRequestDispatcher("/developer/developer.jsp").forward(req, resp);
            return;
        }
            String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Optional<Developer> developer = developerService.findById(Long.valueOf(deleteId));
            if (developer.isPresent()){
                developerService.deleteById(Long.valueOf(deleteId));
            }
            resp.sendRedirect("/developers");
        } else {
            List<Developer> developers = developerService.findAll();
            req.setAttribute("developers", developers);
            req.getRequestDispatcher("/developer/developers.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        HandleBodyUtil.getModelFromStream(req.getInputStream(), DevCompanyDto.class)
                .ifPresent(devCompanyService::createEntity);
        req.getRequestDispatcher("/developer/developer.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put");
        HandleBodyUtil.getModelFromStream(req.getInputStream(), DevCompanyDto.class)
                .ifPresent(devCompanyService::updateEntity);
        System.out.println("****");
        req.getRequestDispatcher("/developer/developer.jsp").forward(req, resp);
    }
}
