<%@ page import="ro.teamnet.zth.appl.domain.Location" %>
<%@ page import="java.util.List" %>
<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="ro.teamnet.zth.api.annotations.Id" %>
<%--
  Created by IntelliJ IDEA.
  User: Viorelt
  Date: 06.11.2014
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Locations List</title>
</head>
<body>

<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Street address</td>
        <td>Postal code</td>
        <td>City</td>
        <td>State province</td>
    </tr>
    </thead>
    <tbody>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        LocationDao location = new LocationDao();
    %>
    <tr>
        <!--TODO de completat cu cod pentru a afisa detaliile locatiei cu id-ul trimis din locationlist.jsp in momentul in care se acceseaza link-ul 'View'-->
        <td>
            <%=
                location.getLocationById(Integer.parseInt(request.getParameter("id"))).getId()
            %>
        </td>
        <td>
            <%=
            location.getLocationById(Integer.parseInt(request.getParameter("id"))).getCity()
            %>
        </td>
        <td>
            <%=
            location.getLocationById(Integer.parseInt(request.getParameter("id"))).getPostalCode()
            %>
        </td>
        <td>
            <%=
            location.getLocationById(Integer.parseInt(request.getParameter("id"))).getStateProvince()
            %>
        </td>
        <td>
            <%=
            location.getLocationById(Integer.parseInt(request.getParameter("id"))).getStreetAddress()
            %>
        </td>

    </tr>

    </tbody>
</table>
<a href="locationList.jsp">Locations List</a>
</body>
</html>
