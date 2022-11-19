<%-- 
    Document   : scriptManager
    Created on : Dec 16, 2020, 3:04:55 PM
    Author     : Rohit
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <script>
         //  alert("i am active");
           
           function generateInvoicePDF(){
               alert("invoise downloading ....");
               const invoice=document.getElementById("downloadInvoice");
                  html2pdf().from(invoice).save();
               alert("save pdf");
           }
                $(document).ready(function(){
                   $(".search").click(function(){
                       $(".search-back").show();
                       $(".searchBar").slideDown("slow");
                   });
                   $(".closeSearch").click(function(){
                       $(".searchBar").slideUp("slow");
                       $(".search-back").fadeOut("slow");
                   }); 
                   
                   //fro calendar
                   $(function(){
                       $(".calendar").datepicker({
                           changeMonth:true,
                           changeYear:true,
                           dateFormat:'dd-mm-yy',
                       });
                      
                   });
               
                   //for check box
                   $(function(){
                       $("input").checkboxradio();
                   });
                });
                
                $(window).scroll(function(){
                    if($(this).scrollTop()>80){
                        //scroll down
                       $(".main-nav").addClass("sidebar");
                       
                       $(".main-nav").addClass("bg-dark");
                    }else{
                        //scroll up
                       $(".main-nav").removeClass("sidebar");
                        $(".main-nav").removeClass("bg-dark");
                       

                    }
                });
                
                 function removeLoader(){       
                       $(".loader").fadeOut("slow"); 
                    }
                 function removeAdminLoader(){
                       alert("hellow");
                       $(".admin-loader").fadeOut("slow"); 
                    }
             
              $(document).ready(function(){
       
  
         $(".fa-arrow-left").click(function(){
            $(".admin-sidebar").toggleClass("col-1 col-0 col-sm-2 col-sm-1");
            $(".admin-workSpace").toggleClass("col-11 col-12 col-sm-10 col-sm-11");
            $(".arrow-icon").toggleClass("fa-arrow-left fa-arrow-right");
            $(".tag-name").toggleClass("d-none");
            $(".tag-pic").toggleClass(" fa-1x fa-x");
            $(".admin-text").toggleClass("text-left text-center");
        });
        $(".full-screen").click(function (){
            $(".admin-workSpace").addClass("px-auto");
            $(".tag-name").removeClass("d-none");
            $(".tag-pic").removeClass("fa-2x");
            $(".admin-text").removeClass("text-center");
            $(".admin-text").addClass("text-left");
            $(".admin-sidebar").addClass("d-none");
            $(".admin-workSpace").removeClass("col-11 col-12 col-sm-10 col-sm-11");
            $(".admin-sidebar").removeClass("col-1 col-0 col-sm-2 col-sm-1");
            $(".arrow-icon").removeClass("fa-arrow-left fa-arrow-right");
            $(".admin-workSpace").addClass("col-12");
            $(".w-o").toggleClass("d-none");
            
        });
        $(".reset-screen").click(function(){
            $(".admin-workSpace").removeClass("px-auto");
            $(".w-o").toggleClass("d-none");
            $(".admin-workSpace").removeClass("col-12");
            $(".admin-sidebar").toggleClass("d-none");
            $(".admin-sidebar").addClass("col-1 col-sm-2");
            $(".admin-workSpace").addClass("col-11 col-sm-10 ");
            $(".arrow-icon").addClass("fa-arrow-left");
        });
         })
            
      
       
    $(document).ready(function(){
      //user passeword hide and show jquery function
       $(".hide").click(function(){
           
           $(this).toggleClass("fa-eye-slash fa-eye");
           var input=$("#hideShowPassword");
           if(input.attr("type")=="password")
           {
               input.attr("type","text");
           }else{
               input.attr("type","password");
           }
           
       }); 
       
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_name",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter the full name.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_email",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter the email well formate.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_password1",function(e){
           $(".register_message").html("<span class='text-tomato'>password at least five digit.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_phone",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter the correct number.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_password2",function(e){
           $(".register_message").html("<span class='text-tomato'>Confirm your password again.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_district",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter your district.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_tal",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter your taluka.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_addr",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter your complate address.</span>");
        });
       //this code for register input tag for correct informatin message
        $(document).on("focus",".register_mobile",function(e){
           $(".register_message").html("<span class='text-tomato'>Enter your mobile number.</span>");
        });
        
        
        
          $(document).on("click",".editHousePhotos",function(){
                   $(".editPhotos").fadeToggle("slow");
          })
          
    
          $(document).on("click",".SHU",function(){
                   $(".UserData").slideToggle("slow");
                   $(".SHUI").toggleClass("fa-arrow-down fa-arrow-up");
          })
          $(document).on("click",".ShoHideOwnerInfo",function(){
                   $(".OwnerInfo").slideToggle("slow");          
          })
          
    
   });//document . ready function end
   
    //this function remove the header adintional functinality and add the dark background...
       function headerBackgroundDark(){
            $(".headerBackground").addClass("bg-dark");
            $(".headerBackground").removeClass("main-nav");
        }
        
        //owl acaruseol managements of index banner 
          $(".owl-carousel").owlCarousel({
                        loop:true,
                        autoplay:false,
                        lazyLoad:true,
                        responsive:{
                            0:{
                                items:1

                            },
                            600:{
                              items:3
                           
                            },
                            1000:{
                                items:3
                            }
                           
                        }
                    });
                    
                                            
                 // Back to top button
                    $(window).scroll(function() {
                      if ($(this).scrollTop() > 100) {
                        $('.back-to-top').fadeIn('slow');
                      } else {
                        $('.back-to-top').fadeOut('slow');
                      }
                    });
                    $(document).ready(function(){
                         $('.back-to-top').click(function(){
                         $("html,body").animate({scrollTop:0},1000);
                         return false;
                     
                    });
                    })
                   

                  
                  //admin serach bar hide when focus out to input serach  
            $(document).on("focusout",".ClientSearch",function(){
                $(".SearchArea").fadeOut("slow");
            })
            
           
          function changePhoto(id){
              $(".mul-p").hide();
              $(".singleP").show();
              $(".changePhotoId").val(id);
          }    
    </script>
            
            
    </body>
</html>
