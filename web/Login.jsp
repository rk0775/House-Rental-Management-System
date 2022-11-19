<%-- 
    Document   : Login
    Created on : Jan 2, 2021, 11:12:18 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body onload="removeLoader()">
     <%@include file="header.jsp" %>
     
     <div class="container">
         <div class="row my-5">
             <div class="col-md-5">
                  <img class="img-fluid" src="files/images/webImg/homeVector.jpg" alt="Logo not Found">
                <div class="text-center text-muted">
                    <h5><b>RKRENT.com</b></h5>
                    <small class="text-muted mtr-5">You Can Trust Our Site complately</small>
                </div>
             </div>
             <div class="col-12 col-md-6 mt-md-4">
                 <h5 class="text-uppercase text-primary"><b>Login Here Please</b></h5>
                 <form class="px-lg-3 loginForm" id="loginRegisterForm">
                     <input type="hidden" value="login" name="form_type">
                     <input name="l-email" pattern="[A-Za-z0-9._%+-]+@gmail.com" value="" class="login_Email form-control my-input mt-3" placeholder="Email@gmail.com"/>
                     <input required name="l-password" type="password" value="" class="form-control my-input mt-3 mb-2 login_password" placeholder="Password"/>
                     <small class="text-danger  login_message"></small>
                     <%//this java code for proper logout message
                     String flag=(String)session.getAttribute("logoutAction");
                     //if user has logout then flag is not null
                     if(flag!=null){    
                        if(flag.equalsIgnoreCase("done") ){%>
                                <small class="text-success logout_message">Successfully Logout</small>   
                            <%
                            }else if(flag.equalsIgnoreCase("invalid")){%>
                                <small class="text-tomato logout_message">Please login first</small>   
                            <%    
                            }else if(flag.equalsIgnoreCase("deleteAccount")){%>
                                <small class="text-success logout_message">Your account was deleted...</small>   
                            <%    
                            }
                            else if(flag.equalsIgnoreCase("propertyApply")){%>
                                <small class="text-danger logout_message">After the apply to property please login first</small>   
                            <%    
                            }
                          //successfully logout message print then work done of if loop and session Atribute logout then remove it for next proper messages...

                            session.removeAttribute("logoutAction");
                     }
                     %>
                     <div class="text-right mt-4">
                         <button class="btn btn-primary" type="submit">Login</button><br>
                         <a href="Resister.jsp" class="text-muted pointer"> Register Here</a>
                     </div>
                 </form>
             </div>
         </div>
     </div>
     
     <div class="footer-space">
         <%@include file="footer.jsp"  %>
     </div>
     
     <script>
          //this jquery function remove the header special activity and add the dark background...
            headerBackgroundDark();
     </script>
    </body>
</html>
