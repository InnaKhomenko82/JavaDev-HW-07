<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project Management System</title>
    <%@ include file="headers.jsp" %>
</head>
<body>
<%@ include file="navigation.jsp" %>

<div class="container">

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
                   <option value="${element}">${element}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">developer id</th>
                <th scope="col">developer name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="element" items="${devsSkillField}">
                <tr>
                    <td><c:out value = "${element.id}"/></td>
                    <td><c:out value = "${element.name}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <%--<div class="row">
        <h4>Developers with skill level</h4>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="skillsLevel">skill level</label>
            <select class="form-select" id="skillsLevel" name="skillsLevel"
                    aria-label="Floating label select example">
                <option>Select skill level</option>
                <c:forEach var="element" items="${listSkillLevel}">
                    <option value="${element}">${element}</option>
                </c:forEach>
            </select>
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
            <c:forEach var="element" items="${devsSkillField}">
                <tr>
                    <td><c:out value = "${element.id}"/></td>
                    <td><c:out value = "${element.name}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>--%>

    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <button onclick="save()" type="button" class="btn btn-primary">Apply</button>
            </div>
        </div>
    </div>

</div>
<script>

    let skillsField = document.getElementById('skillsField');

    function save() {
        let body = skillsField.value;
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
