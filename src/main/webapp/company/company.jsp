<%@ page import="ua.goit.models.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company</title>
    <%@ include file="../headers.jsp" %>
</head>
<body>
<%@ include file="../navigation.jsp" %>
<%ua.goit.models.Company company = (Company) request.getAttribute("company"); %>

<div class="container">
    <div class="row">
        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
            <div class="btn-group me-2" role="group" aria-label="Second group">
                <a href="/companies" type="button" class="btn btn-success">All companies</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="mb-3">
            <label for="id" class="form-label">id</label>
            <input type="text" disabled class="form-control"
                   value="${company.id}"
                   id="id" placeholder="id">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">name</label>
            <input type="text" class="form-control"
                   value="${company.name}"
                   id="name" placeholder="name">
        </div>
        <div class="mb-3">
            <label for="quantityStaff" class="form-label">quantity staff</label>
            <input type="text" class="form-control"
                   value="${company.quantityStaff}"
                   id="quantityStaff" placeholder="quantityStaff">
        </div>
        <div class="row">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group me-2" role="group" aria-label="Second group">
                    <button onclick="save()" type="button" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    let id = document.getElementById('id');
    let name = document.getElementById('name');
    let quantityStaff = document.getElementById('quantityStaff');
    function save() {
        let body = {
            <% if(company.getId() != null) {%>
            id: id.value, <% } %>
            name: name.value,
            quantityStaff: quantityStaff.value,
        }
        <% if(company.getId() == null) {%>
        let url = '/companies';
        let method = 'POST';
        <% } else { %>
        let url = '/companies/<%= company.getId() %>';
        let method = 'PUT';
        <% } %>
        fetch(url, {
            method: method,
            body: JSON.stringify(body)
        })
            .then( _ => {
                    window.location.href = '/companies';
                }
            );
    }
</script>
</body>
</html>
