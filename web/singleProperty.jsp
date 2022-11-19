<%-- 
    Document   : singleProperty
    Created on : Dec 22, 2020, 12:50:06 PM
    Author     : Admin
--%>

<%@page import="entities.HouseOwner"%>
<%@page import="entities.HousePhotos"%>
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
     
        <%@include file="header.jsp"%>
       
        <div class="container PropertyManager"><%--the full container padding 5% both side--%>
            <%
                List<House> houseList=EntityHelper.getByQuery("from House where id="+request.getParameter("id"));
               System.out.println("House : "+houseList.size()  );
                for(House house:houseList){
            %>
        <div class="container row my-5 right-border">
            <div class="col-md-6">
                <h3 class="merienda-bold "><%= house.getName() %></h3>
                <h6 class="text-muted"><%= house.getDistrict() %></h6>
            </div>
            <div class="col-md-6 home-address">
                <h6><a href="index.jsp " class="a text-dark">Home</a> <span class="mx-2 text-muted">/</span> <a href="AllProperty.jsp" class="a text-dark">Properties</a><span class="mx-2 text-muted">/</span><a class="text-muted"><%= house.getName() %></a></h6>
            </div>
           
        </div>
            
            <%-- here we create the image galary --%>
            <div class="owl-carousel owl-theme">
                <%int i=1;
                    for(HousePhotos photo:house.getPhotos()){
                       if (i!=1)
                       {  
                       
                %>
                <div class="">
                    <img style="max-height: 370px ; min-height: 300px" class="img-fluid" src="files/images/HouseImg/<%= photo.getName() %>">
                </div>
                <%}
                       i++;
                    }
                %>
            </div>
            
            <div class="row mt-4 ">
                <%--here price of rent--%>
                <div class="col-md-5 p-3 text-center"><h1 class="merienda-bold text-success">$ <%= house.getRent() %></h1></div>
                <%--property discription--%>
                <div class="col-md-7">
                    <h2 class="merienda">Property Description</h2>
                    <p class="text-muted mt-3 text-justify">
                        <%= house.getDiscription() %>
                    </p>
                    <p class="text-muted text-justify"> 
                       <%= house.getAddress() %>
                   </p>
                </div>
                <%--Quick aummary--%>
                <div class="col-md-5 quick-summary p-lg-5 p-md-3 p-sm-1">
                    <h2 class="my-3 merienda">Quick Summary</h2>
                    <div class="row my-1">
                        <div class="col-7"><b>Property ID:</b></div>
                        <div class="col-5 quick-data text-muted"><%= house.getId() %></div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>Location:</b></div>
                        <div class="col-5 quick-data text-muted"><%= house.getDistrict() %></div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>Property Type:</b></div>
                        <div class="col-5 quick-data text-muted">House</div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>States:</b></div>
                        <div class="col-5 quick-data text-muted"><%= house.getRentType() %></div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>Area:</b></div>
                        <div class="col-5 quick-data text-muted">340m2</div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>Beds:</b></div>
                        <div class="col-5 quick-data text-muted"><%= house.getBedNo() %></div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>Bath:</b></div>
                        <div class="col-5 quick-data text-muted"><%= house.getBathNo() %></div>
                    </div>
                    <div class="row my-1">
                        <div class="col-7"><b>Garage:</b></div>
                        <div class="col-5 quick-data text-muted"><%= house.getGarageNo() %></div>
                    </div>
                   
                </div>
                    
                <div class="col-md-7">
                    <h2 class="mt-lg-5 mt-3 merienda">Amenities</h2>
                    <div class="row">
                        <%
                            String array[]=house.getAminities().split(",");
                          for(String amn:array)
                            {
                        %>
                        <div class="col-6 col-md-6 col-lg-4 p-1"> <span class="text-muted">- <%= amn %></span></div>
                        <%
                            }
                        %>
                    </div>
                    <%
                          
                          if(house.getTenants()==null ){%>
                                <div class="mt-5  text-muted text-right"><span data-toggle="modal" data-target="#tmodal" class=" pointer applyPropertyBtn bordered p-2 bg-success text-light">Apply This Property</span></div>  
                               <div class="mt-5  text-muted text-right"><span class="singleP  pointer bordered p-2 bg-tomato text-light applyPropertyBtnInfo">Property booked</span></div>  

                          <%}else{%>
                               <div class="mt-5  text-muted text-right"><span class="pointer bordered p-2 bg-tomato text-light">Property booked</span></div>  
                          <%}
                    %>
                                <!--div class="mt-5  text-muted text-right"><span onclick="ApplyProperty(<%-- house.getId()--%>)" class="pointer applyPropertyBtn bordered p-2 bg-success text-light">Apply This Property</span></div-->  
                    <div class="modal fade" id="tmodal">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header pb-1 bg-success text-light col-12">
                                    <h5 class="col-12"><span class="mirienda">Please Fill the Little Info</span><span data-dismiss="modal" class="close text-light">&times;</span></h5>
                                </div>
                                <div class="modal-body">
                                    <form  id="TenantApplyForm">
                                        <input hidden class="form-control" value="<%= house.getId() %>" name="aId">
                                        <input hidden class="form-control" value="Apply" name="user">
                                        <small class="mt-3 text-muted">Birth Date :</small>
                                        <input class="calendar form-control" type="text" name="age" placeholder="dd-mm-yyyy">
                                        <div class="age-info text-right"></div>
                                        <small class="mt-3 text-muted">Tenancy Time : </small>
                                         <select class="mt-2 form-control" name="months">
                                            <option value="1">One Month</option>
                                            <option value="2">Two Months</option>
                                            <option value="3">Three Months</option>
                                            <option value="4">Four Months</option>
                                        </select>
                                        <select class="mt-2 form-control" name="type">
                                            <option class="text-muted" value="null">Select Tenant Type</option>
                                            <option value="student">Student</option>
                                            <option value="bachlor">Bachlor</option>
                                            <option value="friend">Friends</option>
                                            <option value="bachlor">Family</option>
                                        </select>
                                        
                                       
                                        <select class="mt-2 form-control" name="gender">
                                            <option class="text-danger" value="null">Select Gender</option>
                                            <option value="male">Male</option>
                                            <option value="female">Female</option>
                                            <option value="Group">Group of Persons</option>
                                        </select>
                                        <small class="text-muted">Reason For Leaving : </small>
                                        <textarea name="reason" class="form-control"></textarea>
                                        <div class="text-right mt-3">
                                            <button type="reset" class="tcloseBtn btn btn-sm btn-secondary" data-dismiss="modal">Close</button>
                                            <button type="submit" class="tApplyBtn btn btn-primary btn-sm">Submit</button><br>
                                            <small class="message text-danger" Style="display: none"> Please Waite <i class="fa fa-spinner fa-spin" aria-hidden="true"></i></small>
                                        </div>
                                      
                                    </form>
                                </div>
                                          <div class="modal-footer m-0 pt-0 singleP">
                                            <% if(session.getAttribute("loginUser")!=null){ %>
                                            <small class="text-justify text-dark"><%= user.getName() %> you are apply to <%= house.getName() %> <b>successfully</b> . please wait for owner response for next action . </small>
                                            <%}%>
                                  
                                            
                                        </div>
                                            <div style='height:3px; width:100%' class='bg-success'></div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
                <%}%>
                <div class='contact-agent'>
                     <h2 class="merienda-bold">Contact Agent</h2>
                <div class="row my-4">
                  
                    <%--there are two part first one is agent and second is the contact us form--%>
                    <%
                        HouseOwner owner=houseList.get(0).getHouseOwner();
                        List<User> userList=EntityHelper.getByQuery("from User where id="+owner.getOid());
                        User u=userList.get(0);
                        
                    %>
                    <div class="col-md-12 col-lg-8">
                        
                        <div class="row my-4">
                            <div class=" col-md-6  ">
                                <img class="img-fluid" src="files/images/UserImg/<%=  u.getPic() %>">
                            </div>
                            <div class=" col-md-6">
                                <h4><b><%= u.getName() %></b></h4>
                                <p class="text-muted"><%= u.getAddress() %></p>
                                <div class="row my-1">
                                    <div class="col-5"><b>Phone:</b></div>
                                    <div class="col-7 quick-data text-muted">+91 <%= u.getPhone() %></div>
                                </div>
                                <div class="row my-1">
                                    <div class="col-5"><b>Mobile:</b></div>
                                    <div class="col-7 quick-data text-muted"><%= u.getMobile() %></div>
                                </div>
                                <div class="row my-1">
                                    <div class="col-5"><b>Email:</b></div>
                                    <div class="col-7 quick-data text-muted"><%= u.getEmail() %></div>
                                </div>
                                <div class="row my-1">
                                    <div class="col-5"><b>Skype:</b></div>
                                    <div class="col-7 quick-data text-muted">Anabella.ge</div>
                                </div>
                                <div class="row my-2">
                                    <% if(u.getType().equalsIgnoreCase("normal")  ){%>
                                        <div class="col-12 text-right text-muted"><small>Sertified Owner</small></div>
                                    <%}else{%>
                                        <div class="col-12 text-right text-muted"><small>Profetional Agent</small></div>                                        
                                    <%}%>
                                </div>
                                <div class="text-center mt-4">
                                    <span class="pl-1 pr-2 fa my-fa fa-tv"></span>
                                    <span class="px-2 fa my-fa fa-mobile"></span>
                                    <span class="px-2 fa my-fa fa-phone"></span>
                                    <span class="px-2 fa my-fa fa-paper-plane"></span>
                                    <span class="px-2 fa my-fa fa-tree"></span>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    <%--second part contact us--%>
                    <div class="col-md-12 col-lg-4">
                        <form class="was-validated mt-4">
                            <input type="text" class="form-control my-3 p-3" placeholder="Name *">
                            <label class="invalid-feedback">Fill valid name</label>
                            
                            <input type="email" class="form-control my-3 p-3" placeholder="Email *">
                            <label class="invalid-feedback">Fill valid Email</label>
                            
                            <textarea rows='5' class="form-control my-3 p-3" placeholder="Comment *"></textarea>
                            <button type='submit' class='btn btn-dark my-3 py-2 px-3'>Sent Message</button>
                        </form>
                    </div>
                </div>
                </div>
                    
            
            
        </div>
        <div class='mt-5 pt-5 pb-2 gray'>
            <%@include file="footer.jsp" %>
        </div>
                
                
    <script>
        //this jquery function remove the header special activity and add the dark background...
        headerBackgroundDark();
           <%--owl carousel disription is script--%> 
              
                    $(".owl-carousel").owlCarousel({
                        loop:true,
                        autoplay:true,
                        margin:15,
                        responsive:{
                            0:{
                                items:1

                            }
                        }
                    });
          
    </script>
     
    </body>
</html>
