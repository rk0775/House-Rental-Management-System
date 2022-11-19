<%-- 
    Document   : Resister
    Created on : Jan 1, 2021, 1:58:38 PM
    Author     : Rohit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register page</title>
        
    </head>
  
    <body onload="removeLoader()">
       
        <%@include file="header.jsp" %>
        <div class="container-fluid">
        <div class="row my-5 px-md-4 px-sm-2">
            <div class="d-none d-sm-inline col-12 col-sm-2 col-md-4">
                <img class="img-fluid  pt-md-5 mt-md-3" src="files/images/webImg/homeVector.jpg" alt="Logo not Found">
                <div class="text-center text-muted">
                    <h5><b>RKRENT.com</b></h5>
                    <small class="text-muted mtr-5">You Can Trust Our Site complately</small>
                </div>
            </div>
            <div class="bordered  py-3 col-12 col-sm-10 col-md-8 ">
                <h5 class="text-uppercase text-primary form-titl"><b>Please Fill Register Form</b></h5>
                <form class="px-3 registerForm" id="loginRegisterForm" enctype="multipart/form-data">
                    <input type="hidden" value="register" name="form_type">
                    <input name="r-name"  required class="register_name mt-3 form-control my-input" pattern="[A-Za-z ]{3,}" type="text" placeholder="Enter Full Name*">
                    <input name="r-email" required type="email" class="register_email mt-3 my-input form-control" placeholder="Email@gmail.com">
                    <div class="row mt-3">
                        <div class="col-6">
                            <input name="r-phone" required class="register_phone my-input form-control" pattern="[0-9]{10}" placeholder="Phone Number..">
                        </div>
                        <div class="col-6">
                             <input name="r-mobile" class="my-input form-control register_mobile" pattern="[0-9]{10}" placeholder="Mobile Number..">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-6">
                            <input name="r-pass1" required type="password" pattern="[0-9a-zA-z#@$%^&*]{5,}" class="register_password1 my-input form-control" placeholder="Password">
                        </div>
                        <div class="col-6">
                            <div class="input-group">
                            <input name="r-pass2" required id="hideShowPassword" type="password" class="register_password2 my-input form-control" placeholder="Confirm Password">
                            <span  class="pt-2 text-muted input-group-prepend hide fa fa-eye-slash"></span>
                            </div>
                        </div>
                    </div>
                     <div class="row mt-3">
                        <div class="col-6">
                            <input name="district" pattern="[A-Za-z ]{3,}" required class="my-input form-control register_district" placeholder="District">
                        </div>
                        <div class="col-6">
                             <input name="tal" pattern="[A-Za-z ]{3,}" required class="my-input form-control register_tal" placeholder="Tal">
                        </div>
                    </div>
                    <input name="r-pic" type="file" class="mt-3 form-control my-input" />
                    <textArea name="r-addr" required rows="2" class="mt-3 my-input form-control register_addr" placeholder="Enter The Detailed Address"></textarea>
                    <input type="checkbox" class="mt-3 register_check"><small class="pl-3 text-muted">Accept terms and conditions</small>
                    <br>
                    <small class="text-danger register_message">.</small>
                    <div class="text-right">
                        <button type="reset" class="btn btn-warning btn-sm">Reset</button>
                        <button type="submit" class="btn btn-primary btn-sm resisterSubmit">Submit</button>
                    </div>
                </form>
            </div>
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
