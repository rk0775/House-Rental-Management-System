<%-- 
    Document   : demo
    Created on : Jan 21, 2021, 6:55:15 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body onload="removeLoader()">
       <%@include file="header.jsp" %>
       <div class="container my-5">
           <form>
               <div class="row">
                    <div class="col-12 col-md-5 mt-md-4">
                        <img class="img-thumbnail" src="files/images/webImg/property-1.jpg" alt="img not found..." style="max-height: 600px;width:auto">
                        <input class="ml-2 mt-2" type="file">
                    </div>
                    <div class="col-12 col-md-7 my-md-2 my-4">
                        <input class="form-control my-input" value="House Name"  placeholder="Enter the house name"/>
                        <input class="form-control mt-3 my-input" value="Owner Name"  placeholder="House owner name"/>
                        <div class="row my-3">
                            <div class="col-12 col-sm-6 ">
                                <input class="my-input form-control" value="1023" placeholder="Agent id">
                            </div>
                            
                            <div class="col-6">
                                <input class="form-control my-input" value="House Price" placeholder="House Price">
                            </div>
                        </div>
                        
                        <div class="dropdown">
                                <button type="button" class="my-input form-control text-left " data-toggle="dropdown">Amenities</button>
                                <div  class="dropdown-menu form-control">
                                    <div class="row p-3">
                                       <input type='hidden' class='ani' name='animetie'>                 
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Balcony"><span class="px-2">Balcony</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Outdoor kitchen"><span class="px-2">Outdoor Kitchen</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Cable TV"><span class="px-2">Cable TV</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Deck"><span class="px-2">Deck</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Tennis Courts"><span class="px-2">Tennis Courts</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Internet"><span class="px-2">Internet</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Parking"><span class="px-2">Parking</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Sun Room"><span class="px-2">Sun Room</span></div>
                                        <div class="col-6 col-md-4"> <input type="checkbox" class="animeties" value="Concrete Flooring"><span class="px-2">C. Flooring</span></div>
                                    </div>
                                </div>
                        </div>
                        
                        <div class="row">
                       
                        <div class="col-12 col-sm-6 mt-3">
                            <input type="button" class="form-control my-input editHousePhotos" value="Photos">
                        </div>
                      <div class="col-6 mt-3">
                                <select  name='Rent_type' class="form-control my-input ">                      
                                    <option value='rent'>For Rent</option>
                                    <option value='sale'>For Sale</option> 
                                </select>
                            </div>
                        <div class="col-12 col-sm-6 mt-3">
                            <select name='Beds' class="form-control my-input">
                                <option value='0'>Bedrooms In Home</option>
                                <option value='1'>1 Beds</option>
                                <option value='2'>2 Beds</option>
                                <option value='3'>3 Beds</option>
                                <option value='>3'>More than 3 Beds</option>
                            </select>
                        </div>
                        <div class="col-12 col-sm-6 mt-3">
                            <select name='Bath' class="form-control my-input">
                                <option value='0'>Bathrooms In Home</option>
                                <option value='1'>1 Bath</option>
                                <option value='2'>2 Bath</option>
                                <option value='3'>3 Bath</option>
                                <option value='>3'>More than 3 Bath</option>
                            </select>
                        </div>
                        <div class="col-12 col-sm-6 mt-3" >
                            <select name='Garage' class="form-control my-input">
                                <option value='0'>Garage In Home</option>
                                <option value='1'>1 Garage</option>
                                <option value='2'>2 Garage</option>
                                <option value='3'>3 Garage</option>
                                <option value='>3'>More than 3 Garage</option>
                            </select>
                        </div>
                        <div class="col-12 col-sm-6 mt-3">
                                <select name='district' class="district form-control my-input">
                                <option value='0'>District</option>
                                <option value='nashik'>Nashik</option>
                                <option value='pune'>Pune</option>
                                <option value='goa'>Goa</option>
                                <option value='delhi'>Delhi</option>
                                <option value='hedrabad'>Hedrabad</option>
                            </select>
                       </div>
                    </div>
                        
                    <textarea name='Property_location' class="form-control my-3 my-input" rows="3" required placeholder="Enter Detailed Home Address"></textarea>
                        
                    </div>
                   <div class="col-12 mt-0">
                    <textarea required name='Property_discription' rows="4" class=" form-control my-3" placeholder="Property Discription"></textarea>
                   </div>
                </div>
           </form>
       </div>
       <div class="editPhotos p-md-3 p-1">
           <span class="close editHousePhotos">X</span>
           <div class="row my-4">
               <div class="col-12 col-sm-6 mt-2">
                   <img class="card-img img-fluid" src="files/images/webImg/slide-1.jpg">
                   <div class="card-img-overlay">
                       <div class="paction alert-dark">
                           <span class="px-2" onclick="changePhoto('first photo')">Change photo</span>
                            <span class="px-2">Delete photo</span>
                       </div>
                   </div>
                 
               </div>
               <div class="col-12 col-sm-6 mt-2">
                   <img class="card-img img-fluid" src="files/images/webImg/slide-2.jpg">
                     <div class="card-img-overlay">
                       <div class="paction alert-dark">
                           <span class="px-2" onclick="changePhoto('second photo')">Change photo</span>
                            <span class="px-2">Delete photo</span>
                       </div>
                   </div>
               </div>
               <div class=" col-12 col-sm-6 mt-2">
                   <img class="card-img img-fluid" src="files/images/webImg/slide-1.jpg">
                     <div class="card-img-overlay">
                       <div class="paction alert-dark">
                           <span class="px-2" onclick="changePhoto('third photo')">Change photo</span>
                            <span class="px-2">Delete photo</span>
                       </div>
                   </div>
               </div>
               <div class="col-12 col-sm-6 mt-2">
                   <img class="card-img img-fluid" src="files/images/webImg/slide-2.jpg">
                     <div class="card-img-overlay">
                       <div class="paction alert-dark">
                           <span class="px-2" onclick="changePhoto('forth photo')">Change photo</span>
                            <span class="px-2">Delete photo</span>
                       </div>
                   </div>
               </div>
           </div>
           <div class='mul-p'>
                <form>
               <input class='form-control' type='file'>
               <button class='btn btn-sm btn-primary'>Save Photos</button>
           </form>
           </div>
              
           <div class="singleP">
                <form class='singlePhotoActionForm'>
                    <input class="changePhotoId">
                    <input type="file">
                    <button class='btn btn-sm btn-primary'>Change Photo</button>
                </form>
           </div>
         
          
       </div>
        <script>
        //this jquery function remove the header special activity and add the dark background...
        headerBackgroundDark();
        </script>
    </body>
</html>
