<%-- 
    Document   : AllProperty
    Created on : Jan 15, 2021, 6:36:49 PM
    Author     : Admin
--%>

<%@page import="entities.House"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="removeLoader()">
        <!-- include the header -->
        <%@include file="header.jsp"  %>
        
        <div class="container">
            
            <div class="row my-4 py-2 right-border">
                <div class="col-12 col-md-6">
                    <h2 class="merienda-bold">Our Amazing Properties</h2>
                    <h6 class="merienda text-muted">Grid Property</h6>
                </div>
                <div class="col-12 col-md-6 text-right">
                    <h6><span class=" px-4 "><a href="index.jsp" class="a text-dark">Home</a></span> / <span class="px-4"><a class="text-muted a" href="AllProperty.jsp" >All Property</a></span></h6>
                </div>
            </div>
            
             <div class="text-right">
                 <div class="row col-12 col-sm-5 offset-sm-7 col-md-4 offset-md-8">
                     <select class="form-control AllPropertySearchKey" >
                         <option value="All" onclick="property()" >All</option>
                         <option value="New to old" onclick="property()">New to Old</option>
                         <option value="Rent" onclick="property()">For Rent</option>
                         <option value="Sale" onclick="property()">For Sale</option>
                     </select>
                 </div>
            </div>   
                
                 <div class="row my-3 AllPropertData">
                     <script> property(); </script>
                     <!-- all prperty puts here -->
                 </div>
          
            
        </div>
        <div class="footer-space">
            <%@include file="footer.jsp"%>
        </div> 
        <script>
            //this jquery function remove the header special activity and add the dark background...
                 headerBackgroundDark();
        </script>
    </body>
</html>
