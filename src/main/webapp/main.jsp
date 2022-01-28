<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.goit.models.Skill" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Management System</title>
    <%@ include file="headers.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>

<% List<Skill> listSkill = (List<Skill>) request.getAttribute("listSkill"); %>

<div class="container">

    <div class="row">
        <h4>Salary by project</h4>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="project">project</label>
            <select class="form-select" id="project" name="project"
                    value=''
                    aria-label="Floating label select example">
                <option>Select a project</option>
                <c:forEach var="element" items="${listProject}">
                    <option value='${element}'>${element.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">project</th>
                <th scope="col">sum</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${salary}">
                <tr>
                    <td><c:out value = "${element.key}"/></td>
                    <td><c:out value = "${element.value}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="row">
        <h4>Developers in a project</h4>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="project">project</label>
            <select class="form-select" id="project" name="project"
                    value=''
                    aria-label="Floating label select example">
                <option>Select project</option>
                <c:forEach var="element" items="${listProject}">
                    <option value='${element}'>${element.name}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">project</th>
                <th scope="col">developer</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${listProjectDevelopers}">
                <c:forEach var="developer" items="${element.developers}">
                    <tr>
                        <td><c:out value = "${element.name}"/></td>
                        <td><c:out value = "${developer.lastName}"/></td>

                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="row">
        <h4>Developers with skill field</h4>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="skillsField">skill field</label>
            <select class="form-select" id="skillsField" name="skillsField"
                    aria-label="Floating label select example">
                <option>Select skill field</option>

                <c:forEach var="element" items="${listSkill}">
                    <c:choose>
                        <c:when test="${element.id == skillsField.id}">
                            <option selected value='${element}'>${element.skillsField}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${element.id}">${element.skillsField}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <button onclick="save()" type="button" class="btn btn-primary">Apply</button>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">field</th>
                <th scope="col">developer name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${developersIndustry}">
                <tr>
                    <td><c:out value = "${element.lastName}"/></td>
                    <td><c:out value = "${element.firstName}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="row">
        <h4>Developers with skill level</h4>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="skill">skill level</label>
            <select class="form-select" id="skill" name="skill"
                    value=''
                    aria-label="Floating label select example">
                <option>Select skill level</option>
                <c:forEach var="element" items="${listSkill2}">
                    <option value='${element}'>${element.level}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">skill level</th>
                <th scope="col">developer name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${developersLevel}">
                <tr>
                    <td><c:out value = "${element.lastName}"/></td>
                    <td><c:out value = "${element.firstName}"/></td>
                    <td><c:out value = "${element.surname}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="row">
        <h4>Project information</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">start date</th>
                <th scope="col">project name</th>
                <th scope="col">quantity of devs</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${listProjectInfo}">
                <tr>
                    <td><c:out value = "${element.value}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>

    let skill = document.getElementById('skill');

    function save() {
        let body = skill.value;
        let url = '';
        let method = 'POST';

        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
            .then( _ => {
                    window.location.href = '';
                });
    }
</script>
</body>
</html>
