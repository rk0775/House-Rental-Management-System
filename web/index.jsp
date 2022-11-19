<%-- 
    Document   : index
    Created on : Dec 9, 2020, 9:45:02 AM
    Author     : Rohit
--%>
<%@page import="entities.House"%>
<%@page import="HelperClasses.Helper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>RKRENT.com</title>
    </head>
    <body onload="removeLoader()">
       
           <%--add the small navbar to index page --%> 
             <nav class="navbar navbar-dark top-nav m-0 p-0">
                <small class="d-none d-sm-block text-light msg"> hello User . please login for beter exprience </small>
                 <nav class="navbar navbar-dark  top-nav m-0 p-1 ml-auto">
                     <div class="d-flex social">
                         <i class="mx-1 fa fa-paper-plane"></i>
                         <i class="mx-1 fa fa-tv"></i>
                         <i class="mx-1 fa fa-phone"></i>
                         <i class="mx-1 fa fa-home"></i>
                     </div>

                </nav>
             </nav>
    <!-- ------------------------------------------------------------------------------------------------------ -->         
           <%-- incllude the header page --%> 
           <%@include file="header.jsp" %>
    <!-- ------------------------------------------------------------------------------------------------------ -->         
           <%-- create the banner of index page  --%>
  
            <div class="background">
               <%--background.jsp include web starting disign in that page present owl carousel--%>
                <%@include file="background.jsp"%>
            </div>
    <!-- ------------------------------------------------------------------------------------------------------ -->         
            <%-- create the service block   --%>
            <div class=" pt-5" style="margin-top:-65px;">
                    <div class="container">

                    <h4 class="brand-name text-primary mt-5">OUR SERVICES</h4>
                    <div class="owl-carousel owl-theme row">
                        <div class=" p-4">
                          <img src="files/images/webImg/data2.png" class="img-s"><h3 class="img-text">Life Style</h3>
                            <h6 class="text-muted">Sed porttitor lectus nibh. Cras ultricies ligula sed magna dictum porta. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. </h6>
                            <button class="btn">Learn more ></button>
                        </div>

                        <div class=" p-4">
                          <img src="files/images/webImg/shell.png" class="img-s"><h3 class="img-text ml-3">Shell</h3>
                            <h6 class="mt-1 text-muted">Sed porttitor lectus nibh. Cras ultricies ligula sed magna dictum porta. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. </h6>
                            <button class="btn">Learn more ></button>
                        </div>

                        <div class=" p-4">
                          <img src="files/images/webImg/loan.png" class="img-s"><h3 class="img-text">Loans</h3>
                            <h6 class="text-muted">Sed porttitor lectus nibh. Cras ultricies ligula sed magna dictum porta. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. </h6>
                            <button class="btn">Learn more ></button>
                        </div>


                    </div>
                </div>

            </div>
        <!-- ------------------------------------------------------------------------------------------------------ -->         
               <hr>
              <%-- property page is here --%>
                <div class="container my-2">
                   <h4 class='brand-name text-primary'>OUR PROPERTIES <span class="sub-head text-muted"><a class="a text-muted" href="AllProperty.jsp">All Property</a></span></h4>

                    <div class="owl-carousel owl-theme">
                            <%
                                List<House> houseList=EntityHelper.getByQuery("from House");
                                for(House house:houseList){
                            %>
                                <div class=' card home-card'>
                                    <div class='card-body m-0 p-0'>
                                        <img class=" card-img p-0 " src="files/images/HouseImg/<%= house.getPhotos().get(0).getName() %>" >
                                           <div class="bg card-img-overlay">
                                               <div class="home-content">
                                                     <h2 class="home-name text-light"><%= house.getName() %></h2>
                                               <div class="text-center btn-border"><%= house.getRentType() %> | $<%= house.getRent() %></div>
                                               <h6 class='mt-3 text-light'><a href="singleProperty.jsp?id=<%= house.getId() %>" class="a text-light">View All Detailes</a><i class="mx-2 text-light fa fa-angle-right"></i></h6>

                                               <div class="px-0 py-2 img-side bg-success row" style="width:117%">
                                                  <div class=" col-3 m-0">
                                                       <h6>Area</h6>
                                                       <small class="text-light">340sq</small>
                                                   </div>
                                                   <div class="col-2 m-0 p-0 ">
                                                       <h6>Beds</h6>
                                                       <small class="text-light"><%= house.getBedNo() %></small>
                                                   </div>
                                                   <div class="col-3 ">
                                                        <h6>Baths</h6>
                                                       <small class="text-light"><%= house.getBathNo() %></small>
                                                   </div>
                                                   <div class="col-4">
                                                        <h6>Garages</h6>
                                                       <small class="text-light"><%= house.getGarageNo() %></small>
                                                   </div>

                                               </div>
                                               </div>
                                           </div>
                                    </div>
                                </div> 
                        <%}%>
                    </div>                    
                </div>
                    
                <script>
                    $(".owl-carousel").owlCarousel({
                        loop:false,
                        autoplay:true,
                        margin:15,
                        responsive:{
                            0:{
                                items:1

                            },
                            600:{
                              items:2
                           
                            },
                            1000:{
                                items:3
                            }
                           
                        }
                    })
                </script>
              <%-- our team --%>
              <hr>
        <!-- ------------------------------------------------------------------------------------------------------ -->         
            <%-- display the agents block  --%>
            <div class="container my-3">
                <h3 class="brand-name text-primary">OUR AGENTS</h3>
                <div class="p-0 owl-carousel owl-theme">
                    
                    <%
                        String path=request.getRealPath("files"+File.separator+"images"+File.separator+"AgentImg");
                        List<Agent> agentList=EntityHelper.getByQuery("from Agent where states='hired' ");
                        
                        for(Agent agent:agentList){
                            %>
                                <div class="team ">
                                  <div class="our-team">
                                      <img class="img img-fluid" src="files\images\AgentImg<%= File.separator+agent.getPic() %>">
                                      <div class="agent-detail text-light ">
                                      <h5><%= agent.getName() %></h5>
                                      <small class="text-muted my-3"><%= Helper.shortDisProvider(agent.getDiscription()) %></small>
                                      <small ><br><b>Phone</b>&nbsp;: +<%= agent.getPhone() %><br>
                                          <b>Email</b>&nbsp;: <%= agent.getEmail() %></small>
                                      </div>
                                  </div>
                                </div>
                            <%
                        }
                    %>
                </div>    
              </div>
                <script>
                    $(".owl-carousel").owlCarousel({
                        loop:true,
                        autoplay:true,
                        margin:15,
                        responsive:{
                            0:{
                                items:2

                            },
                            600:{
                              items:3  
                            },
                            1000:{
                                items:4
                            }
                           
                        }
                    })
                </script>      
        <!-- ------------------------------------------------------------------------------------------------------ -->         
            <hr>
             <%--include the testimonial page--%>
             <%@include file="testimonial.jsp"%>
             <hr class="mt-5">
            <%--include the footer page--%>
            <%@include file="footer.jsp"%>
    </body>
</html>