package ua.goit.servlets;

import ua.goit.models.Company;
import ua.goit.service.CompanyService;
import ua.goit.util.HandleBodyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/companies/*")
public class CompanyServlet extends HttpServlet {

    private final CompanyService companyService= new CompanyService(Company.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String[] split = req.getRequestURI().split("/");
        if (split.length==3 && "new".equals(split[2])){
            req.setAttribute("company", new Company());
            req.getRequestDispatcher("/company/company.jsp").forward(req, resp);
        }
        if (split.length==3){
            Company company = companyService.findById(Long.valueOf(split[2])).get();
            req.setAttribute("company", company);
            req.getRequestDispatcher("/company/company.jsp").forward(req, resp);
        }

        String deleteId = req.getParameter("deleteId");
        if (deleteId != null) {
            Optional<Company> company = companyService.findById(Long.valueOf(deleteId));
            if (company.isPresent()) {
                System.out.println("Deleting entity: " + company.get());
                companyService.deleteById(Long.valueOf(deleteId));
            }
            resp.sendRedirect("/companies");
        } else {
            List<Company> companies = companyService.findAll();
            req.setAttribute("companies", companies);
            req.getRequestDispatcher("/company/companies.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Company.class)
                .ifPresent(company -> {companyService.createEntity(company);});
        req.getRequestDispatcher("/company/company.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HandleBodyUtil.getModelFromStream(req.getInputStream(), Company.class)
                .ifPresent(company -> {companyService.updateEntity(company);});
        req.getRequestDispatcher("/company/company.jsp").forward(req, resp);
    }
}
