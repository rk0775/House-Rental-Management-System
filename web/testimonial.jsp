<%-- 
    Document   : testimonial
    Created on : Dec 19, 2020, 1:17:01 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <div class="container">
                 <h4 class="brand-name text-primary text-uppercase">Testimonials</h4>

             <div class="owl-carousel owl-theme">
                 
                 <div class="row">
                     <div class="col-md-6 text-center">
                         <img class="t-img img-fluid" src="files/images/webImg/testimonial-1.jpg">
                     </div>
                     <div class="col-md-6">
                         <div class="text-center text-success"><i class=" fa fa-comment fa-3x"></i></div>
                         <p class="t-comment text-muted">Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Curabitur non nulla .  </p>
                         <img class=" t-img2 img-fluid" src="files/images/webImg/mini-testimonial-1.jpg">
                         <h5 class="t-name">Albert & Erica</h5>
                     </div>
                 </div>
                 
                 <div class="row">
                     <div class="col-md-6 text-center">
                         <img class="t-img img-fluid" src="files/images/webImg/testimonial-2.jpg">
                     </div>
                     <div class="col-md-6">
                         <div class="text-center text-success"><i class=" fa fa-comment fa-3x"></i></div>
                         <p class="t-comment text-muted">Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Proin eget tortor risus. Curabitur non nulla .  </p>
                         <img class=" t-img2 img-fluid" src="files/images/webImg/mini-testimonial-2.jpg">
                         <h5 class="t-name">Palbo & Emma</h5>
                     </div>
                 </div>
                 
             </div>
             </div>
             
               <script>
                    $(".owl-carousel").owlCarousel({
                        loop:true,
                        autoplay:true,
                        margin:15,
                        responsive:{
                            0:{
                                items:1

                            }
                           
                        }
                    })
                </script>
    </body>
</html>
