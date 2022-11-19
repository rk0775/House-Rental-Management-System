<%--
    Document   : adminPage
    Created on : Dec 25, 2020, 11:01:10 AM
    Author     : Rohit
--%>
<% //this code for privacy of this page 
    User loginUser=(User)session.getAttribute("loginUser");
    if(loginUser==null || loginUser.getType().equalsIgnoreCase("normal")){
        response.sendRedirect("Login.jsp");
        session.setAttribute("logoutAction","invalid");//this sesion for wrong attempt of userPage

        return;
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body onload="removeLoader()">
        <%@include file="header.jsp"%>
    <div class="container-fluid">
        <span class="w-o d-none"><i class="reset-screen fa fa-tv text-muted"></i></span>

        <div class="row ">
            <div class="col-1 col-sm-2 bg-dark admin-sidebar ">
                <div class="admin-option p-0">
                    <div class="setting-option">
                        <i class=" full-screen text-light fa fa-tv px-md-1"></i>
                        <i class="d-none d-sm-inline arrow-icon text-right px-md-1 text-light fa fa-arrow-left"></i>
                    </div>
                    <hr>
                
                <h6 class="admin-text text-left my-3 pointer" onclick="dashBoard()"><span class="tag-pic p-0 fa fa-1x fa fa-handshake "></span>  <span class="d-none d-md-inline"><span class="px-2 tag-name">Info</span></span> </h6>
                <h6 class="admin-text text-left my-3 pointer" onclick="house()"><span class="tag-pic p-0 fa fa-1x fa-building"></span>  <span class="d-none d-md-inline"><span class="px-2 tag-name">Houses</span></span> </h6>
                <h6 class="admin-text text-left my-3 pointer" onclick="agent()"><span class="tag-pic p-0 fa fa-1x fa-user-circle"></span> <span class="d-none d-md-inline"><span class="px-2 tag-name">Agents</span></span> </h6>
                <h6 class="admin-text text-left my-3 pointer" onclick="client()"><span class="tag-pic p-0 fa fa-1x fa-users"></span> <span class="d-none d-md-inline"><span class="px-2 tag-name">Client</span></span></h6>
                <h6 class="admin-text text-left my-3 pointer UserProperty "><span class="tag-pic p-0 fa fa-1x fa-users"></span> <span class="d-none d-md-inline"><span class="px-2 tag-name ">Propertys</span></span></h6>
                <h6 class="admin-text text-left my-3 pointer " onclick="tenants()" ><span class="tag-pic p-0 fa fa-1x fa-users"></span> <span class="d-none d-md-inline"><span class="px-2 tag-name ">Tenants</span></span></h6>
                <h6 class="admin-text text-left my-3 pointer"><span class="tag-pic p-0 fa fa-1x">&#xf3d1;</span> <span class="d-none d-md-inline"><span class="px-2 tag-name">Payment</span></span></h6>
                 
                </div>
            </div>
            
            <div class=" col-11 col-sm-10 admin-workSpace">
                <script>dashBoard()</script>
                
                <!--div class="row">
                    <div class="col-12 col-sm-6">
                        
                    </div>
                    <div class="col-12 col-sm-6" >
                       
                          <div class="card" style="height: 250px; overflow:auto;">
                              <div class="alert-danger card-header mt-0 mb-0 pt-0 pb-0"><h6>New Tenants</h6></div>
                              <div class="card-body mt-0 mb-0 pt-0 pb-0">
                                <table class="table table-sm ">
                                  <tr>
                                      <td>ID</td>
                                      <td>Name</td>
                                      <td>Date</td>
                                      <td>Check</td>
                                  </tr>
                                </table>
                              </div>
                           </div>
                    </div>
                </div-->
                
            </div>
           
        </div>
    </div>
    
    
    <script>
         $(".tag-name").click(function(){
            
            $(".tag-name").removeClass("tag-name-color");
           $(this).addClass("tag-name-color"); 
        });
          //this jquery function remove the header special activity and add the dark background...
            headerBackgroundDark(); 
            
     </script>
    </body>
</html>
