<%-- 
    Document   : userPage
    Created on : Jan 4, 2021, 12:09:10 PM
    Author     : Admin
--%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null || loginUser.getType().equalsIgnoreCase("admin")) {
        response.sendRedirect("Login.jsp");
        session.setAttribute("logoutAction", "invalid");//this sesion for wrong attempt of userPage
        return;
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <script> headerBackground();
          
        </script>
    </head>
    <body onload="removeLoader()">
        <%@include file="header.jsp"%>

        <div class="container">
            <div class="row mt-3 userDatabody">
                <script>user()</script>
            </div>    
            
            
            <!--div class="row">
                <div class="col-12 mt-3 mb-5 "><h3 class="text-muted ptsans-bold">Applied Property</h3></div>
                <div class="col-12 col-md-5 text-center">
                    <img class="img-fluid" src="files/images/webImg/property-1.jpg" style="max-height: 370px">
                    <p class="text-justify text-muted px-2 mt-4">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eos tempore praesentium neque voluptatem voluptate modi? Labore provident ex voluptatum deserunt.
                        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eos tempore praesentium neque voluptatem voluptate modi? Labore provident ex voluptatum deserunt.
                    </p>
                    <div class="text-right"><small>-Ram Surywanshi</small></div>
                </div>
                <div class="col-12 col-md-7">
                    <h4 class="ptsans-bold mb-0 pb-0">401 Key House</h4>
                    <small class="text-primary mt-0 pt-0">nashik</small>
                    
                    <p class="text-muted my-2">Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eos tempore praesentium neque voluptatem voluptate modi? Labore provident ex voluptatum deserunt.</p>
                    
                    <div class="row my-3">
                        <div class="col-md-4 col-6">
                            <div class="row ">
                                <div class="col-6 text-muted">Beds</div>
                                <div class="col-3 text-right">3</div>
                            </div>
                        </div>
                        <div class="col-md-4 col-6">
                            <div class="row">
                                <div class="col-6 text-muted">Bath</div>
                                <div class="col-3 text-right">3</div>
                            </div>
                        </div>
                        <div class="col-md-4 col-6">
                            <div class="row">
                                <div class="col-6 text-muted">Garage</div>
                                <div class="col-3 text-right">3</div>
                            </div>
                        </div>
                        <div class="col-md-4 col-6">
                            <div class="row">
                                <div class="col-6 text-muted">Area</div>
                                <div class="col-3 text-right">3</div>
                            </div>
                        </div>
                        <div class="col-md-4 col-6">
                            <div class="row">
                                <div class="col-6 text-muted">states</div>
                                <div class="col-3 text-right">rent</div>
                            </div>
                        </div>
                        <div class="col-md-4 col-6">
                            <div class="row">
                                <div class="col-6 text-muted">Owner</div>
                                <div class="col-3 text-right">Ram</div>
                            </div>
                        </div>
                       
                    </div>
                    
                    <div class="">
                        <h4 class="text-muted">Owner Detailes</h4>
                        <div class=""><span class="text-muted">Name :</span>Ram Surywnshi</div>
                    </div>
                    
                </div>
            </div-->
           
            
        </div>

        <div class="footer-space">
            <%@include file="footer.jsp" %>
        </div>

        <script>
            //this jquery function remove the header special activity and add the dark background...
            headerBackgroundDark();
        </script>    
    </body>
</html>
