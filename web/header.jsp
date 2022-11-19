<%-- 
    Document   : header.jsp
    Created on : Dec 10, 2020, 9:39:48 AM
    Author     : Rohit
--%>
<%@page import="entities.User"%>
<%@include file="files/FileProvider.jsp"%>
<!DOCTYPE html>
<%//at the first we find the user login or not
    User user=(User)session.getAttribute("loginUser");    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header page</title>
    </head>
    <body>
        <%--background loader--%>
        <div class="loader">
            <img src="files/images/webImg/ajax-loader.gif">
        </div>
        <%-- include the search bar --%>
               <div class='search-back'>
            <div class="searchBar pb-0">
                <div class="bg-success search-header"></div>
                <div class='my-5 mx-5'>
                    
                <h4 class="stitle">Search Property <span class="close closeSearch">X</span></h4>
                
                <form action='AllPropertyServlet' class="mt-5 SerachHouseForm" method="post">
                 
                    <label class="">Keyword</label>
                     <%
                       if(session.getAttribute("sHouse")!=null){%>
                       <input class="form-control p-3 sHouseName" value="<%= session.getAttribute("sHouse") %>"  name='shouse' placeholder="Keyword">
                       <%}else{%>
                        <input class="form-control p-3 sHouseName" value='all' name='shouse' placeholder="Keyword">   
                       <%}
                    %>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label class="">Types</label>
                            <select class="sHouseType form-control" name='type'>
                                <%
                                    if(session.getAttribute("sType")!=null){%>
                                    <option value="<%= session.getAttribute("sType") %>">for <%= session.getAttribute("sType") %></option>
                                    <%}
                                %>
                                <option value="all">All Types</option>
                                <option value="rent">For Rent</option>
                                <option value="sale">For Sale</option>
                                <option value="open">Open House</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>All City</label>
                             <select class=" form-control sHouseCity" name='city'>
                                 <%
                                    if(session.getAttribute("sCity")!=null){%>
                                    <option value="<%= session.getAttribute("sCity") %>"><%= session.getAttribute("sCity") %></option>
                                    <%}
                                %>
                                <option value="all">All Citys</option>
                                <option value="nashik">Nashik</option>
                                <option value="pune">Pune</option>
                                <option value="goa">Goa</option>
                                <option value="nandurbar">Nandurbar</option>
                                <option value="mumbai">Mumbai</option>
                                <option value="delhi">Delhi</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label>Bedrooms</label>
                            <select class="form-control sHouseBed" name="bed">
                                 <%
                                    if(session.getAttribute("sBed")!=null){%>
                                    <option value="<%= session.getAttribute("sBed") %>"><%= session.getAttribute("sBed") %></option>
                                    <%}
                                %>
                                <option value="all">Any</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="more3">More than 3</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Garages</label>
                            <select class="form-control sHouseGarage" name='garage'>
                                 <%
                                    if(session.getAttribute("sGarage")!=null){%>
                                    <option value="<%= session.getAttribute("sGarage") %>"><%= session.getAttribute("sGarage") %></option>
                                    <%}
                                %>
                                <option value="all">Any</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6">
                        <label>Bathrooms</label>
                         <select class="form-control sHouseBath" name='bath'>
                              <%
                                    if(session.getAttribute("sBath")!=null){%>
                                    <option value="<%= session.getAttribute("sBype") %>"><%= session.getAttribute("sBath") %></option>
                                    <%}
                                %>
                                <option value="all">Any</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                          </select>
                        </div>
                        <div class="col-md-6">
                        <label>Minimum Price</label>
                         <select class="form-control sHousePrice" name='price'>
                               <%
                                    if(session.getAttribute("sPrice")!=null){%>
                                    <option class='text-muted bg-success' value="<%= session.getAttribute("sPrice") %>"><%= session.getAttribute("sPrice") %></option>
                                    <%}
                                %>
                                <option value="all">Unlimite</option>
                                <option value="8000"> 8000</option>
                                <option value="10000">10,000</option>
                                <option value="12000">12,000</option>
                                <option value="14000">14,000</option>
                          </select>
                        </div>
                    </div>
                    <div class=" mt-5">
                        <button type='submit' class="btn btn-lg btn-primary">Search Property</button>
                    </div>
                </form>
                </div>
                <div class="d-none d-sm-block bg-success search-footer"></div>
            </div>
    </div>
        

        <%-- navbar disign  --%>
            <nav class=" navbar main-nav navbar-expand-sm navbar-light sticky-top p-1 headerBackground">
                <div class="container">
                    <div class="m-0 p-0 navbar-brand d-none d-sm-block"><img src="files/images/webImg/logo2.png" alt="logo" style="max-height: 90px; max-width: 90px; width:auto;"></div>
                    <div class=" navbar-brand d-sm-none m-0 p-0"><img src=" files/images/webImg/mlogo.png" alt="logo" style="max-height: 80px; max-width: 95px; width:auto;"></div>
             <ul class="d-sm-none d-flex h-menu ml-auto">
                <li class="m" ><a class="m text-light" >Register</a></li>
                <li class="m"><a class="m text-light">Login</a></li>
                <li class="m search"><a class=""><i class="text-light mt-1 fa fa-search"></i></a></li>
            </ul>
            <button class="navbar-toggler " data-toggle="collapse" data-target="#pnav"><span class="navbar-toggler-icon"></span></button>
            
            <div id="pnav" class=" collapse navbar-collapse">
            <ul class="navbar-nav ml-3">
                <li class="nav-item"><a class="nav-link nav-link-menu mn text-light" href="index.jsp">Home</a></li>
                <li class="nav-item"><a class="nav-link nav-link-menu mn text-light" href="AllProperty.jsp" >Property</a></li>
                <li class="nav-item"><a class="nav-link nav-link-menu mn text-light">About</a></li>       
                <li class="nav-item"><a class="nav-link nav-link-menu mn text-light">Contact</a></li>
            </ul>
                
             <ul class="d-none d-sm-flex navbar-nav ml-auto"> 
                    <%
                    if(user==null){
                 %>
                    <li class="nav-item"><a href="Resister.jsp" class="nav-link nav-link-menu mn text-light">Register</a></li>
                    <li class="nav-item"><a href="Login.jsp" class="nav-link nav-link-menu mn text-light">Login</a></li>
                 <%
                    }else{
                          String name[]=user.getName().split(" ");
                  %>
                    <li class="nav-item pt-1"><a href="<% if(user.getType().equalsIgnoreCase("admin")){%>adminPage.jsp<%}else{%>userPage.jsp<%}%>" class="nav-link nav-link-menu mn text-light capitalize"><%= name[0] %></a></li>
                    <li class="nav-item pt-1"><a  class="nav-link nav-link-menu mn text-light logout">Logout</a></li>
                  <%
                    }
                 %> <li class=" ml-3 nav-item search"><a class="nav-link"><i class="text-light mt-1 fa fa-search"></i></a></li>
            </ul>
            </div>
                </div>
                
        </nav> 
            
            <div class="back-to-top">
                <i class="fa fa-arrow-up"> </i>
            </div>
        
        <script>
            $(document).ready(function(){
                $(".navbar-toggler").click(function(){
                    $(".main-nav").toggleClass("bg-secondary");
                })
            })

        </script>
    </body>
</html>
