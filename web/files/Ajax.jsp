<%-- 
    Document   : Ajax
    Created on : Dec 26, 2020, 11:44:10 AM
    Author     : Rohit
--%>

<%@page import="entities.Agent"%>
<%@page import="HelperClasses.EntityHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      
        <script>
         var loader="<div class=\"admin-loader\">\n" +
"                     <img class=\"pl-3\" src=\"files/images/webImg/ajax-loader.gif\"> <br>\n" +
"                     <small class=\"d-none d-sm-block text-light\">Please wait...</small>\n" +
"                </div> \n" +
"            </div>";

     function dashBoard(){
         $(".admin-workSpace").html(loader);
         $.ajax({
             url:"DashbordServlet",
             type:"POST",
            data:{add:"add"},
             success:function(data){
                   $(".admin-workSpace").append(data);
                    $(".admin-loader").fadeOut("slow");
             }
         });
     }
     
     //houseServlet management
    function house(housePageNo="1"){
         $(".admin-workSpace").html(loader);
           $.ajax({
            url:"HouseServlet",
            type:"POST",
            data:{house:"full",pageNo:housePageNo},
            success:function(data){
                $(".admin-workSpace").append(data);
                $(".admin-loader").fadeOut("slow");
            }
           
        });  
   }
     
     $(document).on("click","#addNewProperty",function(){
          $.ajax({
            url:"HouseServlet",
            type:"POST",
            data:{house:"addProperty"},
            success:function(data){
                $(".userDatabody").html(data);
                $(".Xbtn").attr("onclick","user()");
                $(".admin-workSpace").html(data);
            }
           
        });  
     });
     
     $(document).on("submit","#addNewPropertyForm",function(e){
        e.preventDefault();
          var a=[];
                $(".animeties").each(function(){
                   if($(this).is(":checked")){
                       a.push($(this).val());
                    }
                 });
                 var aa=a.toString();
                 $(".ani").val(aa);
                 var dataform=new FormData(this);
         var userType=$("#userType").val();
         var houseId=$("#houseId").val();
         if(userType=="admin"){
             //admin user can not pay the money for add the house
                     $.ajax({
                           url:"HouseServlet",
                           type:"POST",
                           data:dataform,
                           contentType:false,
                           processData:false,
                           success:function(data){
                           if(data==0){
                                swal.fire({
                                    icon:"success",
                                    title:"Property Added",
                                    text:"Property data save to database",
                                });
                              // $(".House_name_id").val(""); 
                                $("#addNewPropertyForm").trigger("reset");
                                
                            }
                           }

                    });
             
         }else if(userType=="normal"){
             //normal user can pay the money for add house
             
                            //payment getway code
               $.ajax({
                  url:"HouseServlet",
                  data:{house:"payment"},
                //  contentType:'application/json',
                  type:'POST',
                 // dataType:"json",
                  success:function(res){
                      if(res=="error"){
                          alert("Connection Problem! please check connection");
                      }
                      console.log("Success : "+res);
                      var response=JSON.parse(res);
                      var pay=response;
                      if(response.status=="created"){
                          let option={
                              key:"rzp_test_2uyw",
                              amount:response.amount,
                              currency:'INR',
                              name:'House Charge',
                              description:'rkRent',
                              image:'files/images/webImg/homeVector.jpg',
                              order_id:res.id,
                              handler: function (response){
                                  
                                   alert( pay.amount/100+" rs Payment successfull ");
                                   //save the payment detailes on database
                                    $.ajax({
                                        url:"HouseServlet",
                                        type:"POST",
                                        data:{house:"savePaymentInfoToDatabase",receipt:pay.receipt,houseNo:houseId,type:"house charge",amount:pay.amount},
                                        success:function(data){
                                            
                                        }
                                    });

                                       //if payment sucesfull
                                       $.ajax({
                                           url:"HouseServlet",
                                           type:"POST",
                                           data:dataform,
                                           contentType:false,
                                           processData:false,
                                           success:function(data){
                                               if(data==0){
                                                   swal.fire({
                                                       icon:"success",
                                                       title:"Property Added",
                                                       text:"Property data save to database",
                                                   });
                                               }else if(data=="-1"){
                                                    swal.fire({
                                                       toast:true,
                                                       position:"top-end",
                                                       text:"Please select the property district.",
                                                       showConfirmButton:false,
                                                       timer:"5000",
                                                   });
                                                   $(".district").addClass("text-danger");
                                               }
                                           }

                                       });  //save the house detailes/

                               },
                               prefill: {
                                   name: "",
                                   email: "",
                                   contact:""
                               },
                               notes: {
                                   address:"Razorpay Corporate Office"
                               },
                               theme: {
                                   color: "#3399cc"
                               }
                          }

                          let rzp=new Razorpay(option);
                          rzp.on('payment.failed', function (response){
                              alert("Paymen failed");
                                   alert(response.error.code);
                                   alert(response.error.description);
                                   alert(response.error.source);
                                   alert(response.error.step);
                                   alert(response.error.reason);
                                   alert(response.error.metadata.order_id);
                                   alert(response.error.metadata.payment_id);
                           });
                          rzp.open();
                      }
                  },
                  error:function(error){
                      alert("Please check the net Conection!!");
                  }


               });
             
             
         }//normal user if condition end
     });
     
     $(document).on("submit",".EditHouseForm",function(e){
        e.preventDefault();
        var a=[];
        $(".animeties").each(function(){
            if($(this).is(":checked")){
                a.push($(this).val());
            }
        });
        var aa=a.toString();
        $(".ani2").val(aa);
          var dataform=new FormData(this);
        $.ajax({
            url:"HouseServlet",
            type:"POST",
            data:dataform,
            contentType:false,
            processData:false,
            success:function(data){
             alert(data);
             if(data=="0"){
                 swal.fire({
                     icon:"success",
                     title:"Property was Upadated successfully...",
                     timer:"3000",
                 })
             }else{
                  swal.fire({
                     icon:"error",
                     title:"Somthimg else",
                     timer:"3000",
                 })
             }
                  
            }
           
        });  
        
     });
     
       $(document).on("keyup",".HouseSearch",function(){
          var key=$(this).val();
          if(key.length==0){
              
            $(".SearchArea").fadeOut("slow");  
          }else{
                $.ajax({
                    url:"HouseServlet",
                    type:"Post",
                    data:{house:"search",searchKey:key},
                    success:function(data){
                        $(".SearchArea").fadeIn("slow");
                        $(".SearchArea").html(data);   
                    }
                })
            }
        });
         $(document).on("click",".house-search",function(){
           var d=$(this).data("house");
            sK= $(".HouseSearch").val();
             $(".admin-workSpace").html(loader);
            $.ajax({
                    url:"HouseServlet",
                    type:"Post",
                    data:{house:"full",searchHouseId:d},
                    success:function(data){
                        $(".admin-workSpace").html(data);
                        $(".userDatabody").html(data);
                        $(".admin-loader").fadeOut("slow"); 
                        var input=$(".AgentSearch");
                        input.attr("value"," "+sK );
                    }
                });

       });
        
        function HouseEdit(id,x){//user click on house edit btn
          //  alert(x);
        $(".admin-workSpace").html(loader);
             $.ajax({
                    url:"HouseServlet",
                    type:"Post",
                    data:{house:"edit",EditHouseId:id},
                    success:function(data){
                        if(x=="UP"){
                            $(".admin-workSpace").html("<h4 class='mt-3 col-12'><span class='merienda-bold text-muted'>Edit The Property</span><span class='close UserProperty'  >X</span></h4>");                            
                        }else{
                            $(".admin-workSpace").html("<h4 class='mt-3 col-12'><span class='merienda-bold text-muted'>Edit The Property</span><span class='close' onclick='house()' >X</span></h4>");
                        }
                        $(".admin-workSpace").append(data);
                        $(".userDatabody").html("<h4 class='col-12'><span class='merienda-bold text-muted'>Edit The Property</span><span class='close UserProperty '>X</span></h4>");
                        $(".userDatabody").append(data);
                        $(".admin-loader").fadeOut("slow"); 
                    }
                });
        }
           
        function HouseDelete(id){//user click on house edit btn
            swal.fire({
            title:"Confimation",
            text:"Are you sure to remove this property",
            showCancelButton:true,
            cancelButtonText:"No",
            confirmButtonText:"Yes! DO IT.",
        }).then((result)=>{
            if(result.value){
            $.ajax({
                    url:"HouseServlet",
                    type:"Post",
                    data:{house:"delete",deleteHouseId:id},
                    success:function(data){
                        house();
                       swal.fire({ 
                           icon:"success",
                           title:"Property Removed Successfully...",
                           timer:"1000",
                           showConfirmButton:false,
                       })
                     
                    }
                });
            }
        })
        }
        $(document).on("submit",".singlePhotoActionForm",function(e){
            e.preventDefault();
          var dataform=new FormData(this);
            $.ajax({
                    url:"HouseServlet",
                    type:"Post",
                    data:dataform,
                    contentType:false,
                    processData:false,
                    success:function(data){
                        if(data=="-1"){
                            swal.fire({
                                title:"Please Select the Image...",
                            })
                        }else if(data=="0"){
                             swal.fire({
                                title:"Image was Changed successfuly",
                            })
                        }
                       
                    }
                });
           
        });
        $(document).on("submit",".NewPhotos",function(e){
            e.preventDefault();
          var dataform=new FormData(this);
            $.ajax({
                    url:"HouseServlet",
                    type:"Post",
                    data:dataform,
                    contentType:false,
                    processData:false,
                    success:function(data){
                       if(data=="0"){
                             house();
                             swal.fire({
                                title:"new image added successfully...",
                            })
                        }
                       
                    }
                });
           
        });
        $(document).on("click",".DeleteSinglePhoto",function(){
            var id=$(this).data("id");
            swal.fire({
            title:"Confimation",
            text:"Are you sure to remove this photo",
            showCancelButton:true,
            cancelButtonText:"No",
            confirmButtonText:"Yes! DO IT.",
            }).then((result)=>{
                if(result.value){
                    $.ajax({
                        url:"HouseServlet",
                        type:"Post",
                        data:{house:"deletePhoto",deletePhotoId:id},
                        success:function(data){
                            swal.fire({ 
                                icon:"success",
                                title:"Property photo Removed Successfully...",
                                text:"Your changes save in database please refresh for see the changes ",
                                timer:"2000",
                                showConfirmButton:false,
                            })
                        } 
                    })
                 }
            })
        })
     //agentServlet Management
     
     function agent(states='hired',pageNo='1'){
         $(".admin-workSpace").html(loader);
         $.ajax({
             url:"AgentServlet",
             type:"POST",
             data:{agent:"full",aStates:states,aPageNo:pageNo},
             success:function(data){
                  $(".admin-workSpace").append(data);
                  $(".admin-loader").fadeOut("slow");
             }
                
         });
       
     }
      $(document).on("click","#addNewAgent",function(){
          $.ajax({
            url:"AgentServlet",
            type:"POST",
            data:{agent:"newAgentForm"},
            success:function(data){
              
                $(".admin-workSpace").html(data);
            }
           
        });  
     });
      $(document).on("submit","#addNewAgentForm",function(e){
        e.preventDefault();
        var dataform=new FormData(this);
        $.ajax({
            url:"AgentServlet",
            type:"POST",
            data:dataform,
            contentType:false,
            processData:false,
            success:function(data){
                if(data==0){
                    swal.fire({
                       icon:"success",
                       title:"Successfull Saved",
                       html:"<smal>Agent Data Was SuccessFully Added To Database.</small>",
                    });
                    $("#addNewAgentForm").trigger("reset");
                }
            }
           
        });  
        
     });
     
      $(document).on("submit","#editAgentForm",function(e){
        e.preventDefault();
        var dataform=new FormData(this);
        $.ajax({
            url:"AgentServlet",
            type:"POST",
            data:dataform,
            contentType:false,
            processData:false,
            success:function(data){
               if(data==0){
                    swal.fire({
                       icon:"success",
                       title:"Succefully Updated",
                       html:"<smal>Agent Data Was Updated on yhe server.</small>",
                    }); 
                }else if(data==-1){
                     swal.fire({
                       icon:"error",
                       title:"Error",
                       html:"<smal>Somthing went wrong ...</small>",
                    });
                }
            }
           
        });  
        
     });
     
    function agentEdit(id){
        $.ajax({
             url:"AgentServlet",
             type:"POST",
             data:{agent:"editAgentForm",agentId:id},
             success:function(data){
                  $(".admin-workSpace").html(data);
             }
         })
    }
 
    
    function hiredFiredAgentAction(action,id){
         swal.fire({
            title:"Confimation",
            text:"Are you sure to "+action+" this agent",
            showCancelButton:true,
            cancelButtonText:"No",
            confirmButtonText:"Yes! DO IT.",
        }).then((result)=>{
            if(result.value){
                $.ajax({
                url:"AgentServlet",
                type:"POST",
                data:{agent:"hiredFiredAgentAction",firedID:id,aStates:action},
                success:function(data){
                    swal.fire({
                        title:"Work Done !",
                        text:"Agent was "+action+" ...",
                        showCancelButton:false,
                    });
                    agent();
                }
             }); 
            }
        })
    }
         //login form validation
        $(document).on("submit",".loginForm",function(e){
           e.preventDefault();
           $(".logout_message").text("");
           var email= $(".login_Email").val();
           var password=$(".login_password").val();
           if(email==""){
               $(".login_message").text("please fill login email..");
           }else if(password==""){
                $(".login_message").text("please fill login password..");
           }else{
              $(".login_message").text("please wait...");
             $.ajax({
                url:"LoginRegisterServlet",
                type:"POST",
                data:$("#loginRegisterForm").serialize(),
                success:function(data){
                    if(data=="-1"){//for incorect information
                        $(".login_message").text("Incorect Information");
                    }else if(data=="normal"){//for login successfull
                        $(".login_message").text("");
                        window.location.replace("userPage.jsp");
                    }else  if(data=="admin"){
                         $(".login_message").text("");
                        window.location.replace("adminPage.jsp");
                    }else if(data=="1"){//for server problem
                        $(".login_message").html("<b>Opps</b> server failed...");
                    }
                }
            })
           }
           
        });
       
        //registration form validation
         $(document).on("submit",".registerForm",function(e){
             e.preventDefault();
           
             
             var p1=$(".register_password1").val();
             var p2=$(".register_password2").val();
             $(".register_message").removeClass("text-success");
             $(".register_message").addClass("text-danger");
             if( p1!=p2 ){
                $(".register_message").text("passwords not match please correct it...");

             }else if(! $(".register_check").is(":checked")){
                 $(".register_message").text("Please check the Term and Conditions ...");

             }else{
                  
                  var autoGenerateOtp;
                  var counterType;
                $.ajax({
                url:"LoginRegisterServlet",
                type:"POST",
                data:{form_type:"generateOtp",to:$(".register_email").val()},
                success:function(data){
                       console.log("registry data : "+data);
                       var dataArray=data.split("/");
                       autoGenerateOtp=dataArray[0].toString();
                       counterType=dataArray[1];
                       
                        if( counterType=="active"){ 
                            var timer2 = "2:00";
                            var interval = setInterval(function() {
                            var timer = timer2.split(':');
                            //by parsing integer, I avoid all extra string processing
                            var minutes = parseInt(timer[0], 10);
                            var seconds = parseInt(timer[1], 10);
                            --seconds;
                            minutes = (seconds < 0) ? --minutes : minutes;
                            if (minutes < 0) clearInterval(interval);
                            if(minutes<0){alert("OTP has time out")};
                            seconds = (seconds < 0) ? 59 : seconds;
                            seconds = (seconds < 10) ? '0' + seconds : seconds;
                            //minutes = (minutes < 10) ?  minutes : minutes;
                            $('.countdown').html('</small>Remaning Time </small><span class="text-danger">'+minutes + ':' + seconds+'</span>');
                            timer2 = minutes + ':' + seconds;
                          }, 1000);
                        }
                   }
                });
  
                 $(".register_message").html("please wait...");
                 swal.fire({
                     title:"Email Verification",
                     showCloseButton:true,
                     confirmButtonText:"Final Submit",
                     html:"<div><p class='text-left text-muted'>OTP sent your email account enter here !!</p>\n\
                                </div><div class='text-right mt-2'><input class=' form-control p-3 OTP' placeholder='------' /><small class='countdown text-dark'>Enter the Otp Please</small></div>",
                 }).then((result)=>{
                     if(result.value){
                         var userOtp=$(".OTP").val().toString();
                         console.log("OTP : "+autoGenerateOtp.trim());
                       //  alert(autoGenerateOtp+" "+userOtp);
                          if(autoGenerateOtp.trim()==userOtp.trim()){
                                    var dataform=new FormData(this);
                                    $.ajax({
                                   url:"LoginRegisterServlet",
                                   type:"POST",
                                   data:dataform,
                                   contentType:false,
                                   processData:false,
                                   success:function(data){
                                           if(data=="-1"){
                                               $(".register_message").html("<b>Opps</b> Sorry server failed...");
                                           }else{
                                               $(".register_message").toggleClass("text-danger text-success");
                                               $(".register_message").html("You are <b>successfully Registerd </b> Thanks!");
                                               $(".registerForm").trigger("reset");
                                           }
                                      }
                                   })
                         }else{
                          swal.fire({
                            title:"invalid OTP",
                            text:'please enter the valid OTP  !!'
                          });
                         }
                     }
                 })
            
            }
             
    });
        //logout
        $(document).ready(function(){
            $(".logout").click(function(){
                 swal.fire({
                    text:"Are You Sure To Logout",
                    showCancelButton:true,
                    cancelButtonText:"No",
                    confirmButtonText:"Yes! Logout",
                }).then((result)=>{
                    if(result.value){
                        $.ajax({
                            url:"LoginRegisterServlet",
                            type:"POST",
                            data:{form_type:"logout"},
                            success:function(data){
                              window.location.replace("Login.jsp");
                            }
                        }); 
                    }
                });//swal end
            });//click function end
        });//document ready end
       //userServlet manage
       function user(){
           $.ajax({
               url:"UserServlet",
               type:"POST",
               data:{user:"full"},
               success:function(data){
                   $(".userDatabody").html(data);
               }
           })
       }
       $(document).on("click",".editUser",function(){
           $.ajax({
               url:"UserServlet",
               type:"POST",
               data:{user:"editUser"},
               success:function(data){
                $(".userDatabody").html(data);
               }
           })
       });
       $(document).on("click",".deleteUser",function(){
            swal.fire({
                    text:"Are You Sure To Delete Account Permantaly",
                    showCancelButton:true,
                    cancelButtonText:"No",
                    confirmButtonText:"Yes! Sure",
                }).then((result)=>{
                    if(result.value){
                         $.ajax({
                            url:"UserServlet",
                            type:"POST",
                            data:{user:"deleteUser"},
                            success:function(data){
                                window.location.replace("Login.jsp");
                            }
                        });
                    }
                });//swal end
           
           
           
         
       });
       $(document).on("submit","#editUserForm",function(e){
           e.preventDefault();
               var dataform=new FormData(this);
                 $.ajax({
                url:"UserServlet",
                type:"POST",
                data:dataform,
                contentType:false,
                processData:false,
                success:function(data){
                     swal.fire({
                         icon:"success",
                         title:"success",
                         text:"Successfully updated...",
                     })
                     user();
                   }
                
                })
       })
      
       
       
       
       
       //admin client servlet management
       function client(pageNo='1'){
            $(".admin-workSpace").html(loader);
           $.ajax({
               url:"ClientServlet",
               type:"Post",
               data:{client:"full",cPageNo:pageNo},
               success:function(data){
                   $(".admin-workSpace").append(data);
                   $(".admin-loader").fadeOut("slow");
               }
           })
       }
       
       function tenants(pageNo='1'){
            $(".admin-workSpace").html(loader);
           $.ajax({
               url:"TenantServlet",
               type:"Post",
               data:{tenant:"full",cPageNo:pageNo},
               success:function(data){
                   $(".admin-workSpace").append(data);
                   $(".admin-loader").fadeOut("slow");
               }
           })
       }
       
       
       $(document).on("keyup",".AgentSearch",function(){
          var key=$(this).val();
          if(key.length==0){
              
            $(".SearchArea").fadeOut("slow");  
          }else{
                $.ajax({
                    url:"AgentServlet",
                    type:"Post",
                    data:{agent:"search",searchKey:key},
                    success:function(data){
                        $(".SearchArea").fadeIn("slow");
                        $(".SearchArea").html(data);   
                    }
                })
            }
        });
       $(document).on("click",".agent-search",function(){
           var d=$(this).data("agent");
            sK= $(".AgentSearch").val();
             $(".admin-workSpace").html(loader);
            $.ajax({
                    url:"AgentServlet",
                    type:"Post",
                    data:{agent:"full",searchAgentId:d},
                    success:function(data){
                        $(".admin-workSpace").html(data);
                        $(".admin-loader").fadeOut("slow"); 
                        var input=$(".AgentSearch");
                        input.attr("value"," "+sK );
                    }
                });

       });
       
          $(document).on("keyup",".ClientSearch",function(){
          var key=$(this).val();
          if(key.length==0){
              
            $(".SearchArea").fadeOut("slow");  
          }else{
                $.ajax({
                    url:"ClientServlet",
                    type:"Post",
                    data:{client:"searchClient",searchKey:key},
                    success:function(data){
                        $(".SearchArea").fadeIn("slow");
                        $(".SearchArea").html(data);   
                    }
                })
            }
        });
        
          $(document).on("keyup",".TenantSearch",function(){
          var key=$(this).val();
          if(key.length==0){
              
            $(".SearchArea").fadeOut("slow");  
          }else{
                $.ajax({
                    url:"TenantServlet",
                    type:"Post",
                    data:{tenant:"searchTenant",searchKey:key},
                    success:function(data){
                        $(".SearchArea").fadeIn("slow");
                        $(".SearchArea").html(data);   
                    }
                })
            }
        });
        
       $(document).on("click",".client-search , .clientRowClick",function(){
           var d=$(this).data("client");
            sK= $(".ClientSearch").val();
            $.ajax({
                    url:"ClientServlet",
                    type:"Post",
                    data:{client:"displaySearchClient",searchClientId:d},
                     success:function(data){
                        $(".admin-workSpace").html(data);
                        $(".admin-loader").fadeOut("slow"); 
                        var input=$(".ClientSearch");
                        input.attr("value"," "+sK );
                    }
                });
        });
        //all property servlet management
        function property(pno="1"){     
            var sKey=$(".AllPropertySearchKey").val();
             $(".AllPropertData").html("<div class=' col-12 text-center text-muted  my-5'><h6 class='merienda'>Please wait...</h6> </div>")
             $("html,body").animate({scrollTop:0},500);
              
        $.ajax({
                url:"AllPropertyServlet",
                type:"Post",
                data:{Allproperty:"full",pageNo:pno,key:sKey},
                success:function(data){
                    $(".AllPropertData").html(data);
                }
            })
        }
         
   
        
        //Appley the property
    $(document).ready(function(){
       $(".applyPropertyBtn").click(function(){
             $.ajax({
               url:"UserServlet",
               type:"Post",
               data:{user:"loginOrNoLoginCheck"},
               success:function(data){
                   if(data=="null"){
                     window.location.replace("Login.jsp");   
                   }
               }
           })
        })
        
       $(".tApplyBtn").click(function(e){
          
           e.preventDefault();
           $(".message").show();
           $.ajax({
               url:"UserServlet",
               type:"Post",
               data:$("#TenantApplyForm").serialize(),
               success:function(data){
                  $(".message").hide();
                  if(data=="null"){
                      window.location.replace("Login.jsp");
                  }else if(data=="Incorect"){
                       swal.fire({
                           icon:"error",
                          title:"Invalid",
                          text:"please fill the correct information.",
                      })
                  }else if(data=="Alredy exists"){
                       swal.fire({
                          title:"Invalid",
                          text:"Already exit one property for rent",
                      })
                  }else if(data=="wrong user"){
                       swal.fire({
                          icon:"error",
                          text:"property owner not appied self property",
                          timer:"10000"
                      })
                  }else if(data=="Invalid Date"){
                      $(".age-info").html("<small class=' text-danger'>invalid date</small>");
                      swal.fire({
                          icon:"warning",
                          text:"The tenant age must be gereter than 10 Years old Or Fill Carrect birth date",
                          timer:"10000"
                      })
                  }else if(data=="invlid info"){
                       swal.fire({
                          icon:"warning",
                          text:"Please fill the valid age , type and gender",
                          timer:"10000"
                      })
                  }else if(data=="done"){
                      $(".applyPropertyBtn").hide();
                      swal.fire({
                          icon:"success",
                          text:"Successfully applied to this property..",
                          timer:"10000"
                      })
                      $(".applyPropertyBtnInfo").fadeIn("slow");
                      $(".tApplyBtn").fadeOut("slow");
                      $(".modal-footer").fadeIn("slow");
                  }
               }
           })
           
       })
    })
       
       //add property by user
      

   
   /*    $(document).on("click","#addNewPropertyByUser",function(){
          $.ajax({
            url:"HouseServlet",
            type:"POST",
            data:{house:"addProperty"},
            success:function(data){
                $(".userDatabody").html(data);
                $(".Xbtn").attr("onclick","user()");

            }
        });  
     });
    
    
      $(document).on("click",".CancelTanantHouse",function(){
          alert("b");
          id =$(this).data("id");
          $.ajax({
              url:"UserServlet",
              type:"POST",
              data:{user:"CancelTanantHouse",Tid:id},
              success:function(data){
                  alert(data);
              }
              
          })
      })
    */
        $(document).on("click","#tenant",function(){
             hid=$(this).data("h");
             tid=$(this).data("t");            
             x=$(this).data("x");
            // alert(x);
                $.ajax({
                    url:"UserServlet",
                    type:"Post",
                    data:{user:"tenatsDetailes",userId:hid,TenantId:tid},
                    success:function(data){
                        if(x=="dash"){
                            $(".admin-workSpace").html("<h4 class='col-12 mt-2 mb-4'><span class='text-muted merienda-bold'>Detailes</span><span class='close' onclick='dashBoard()' >X</span></h4>");                            
                        }else if(x=="UP"){
                            $(".admin-workSpace").html("<h4 class='col-12 mt-2 mb-4'><span class='text-muted merienda-bold'>Detailes</span><span class='close UserProperty ' >X</span></h4>");
                        }else if(x=="Table"){
                            $(".admin-workSpace").html("<h4 class='col-12 mt-2 mb-4'><span class='text-muted merienda-bold'>Detailes</span><span class='close ' onclick='tenants()' >X</span></h4>"); 
                        }else{
                            $(".admin-workSpace").html("<h4 class='col-12 mt-2 mb-4'><span class='text-muted merienda-bold'>Detailes</span><span class='close U ' onclick='house()' >X</span></h4>");
                        }
                         $("html,body").animate({scrollTop:10},100);
                        $(".admin-workSpace").append(data);
                        
                        $(".userDatabody").html("<h4 class='col-12 mt-2 mb-4'><span class='text-muted merienda-bold'>Detailes</span><span class='close UserProperty ' >X</span></h4>");
                        $(".userDatabody").append(data);
                      
                       
                    }
                });
        })
        
     
        
       /*  $(document).on("click",".UserProperty",function(){
            $.ajax({
                    url:"UserServlet",
                    type:"POST",
                    data:{user:"OwnerHouse"},
                    success:function(data){
                    $(".userDatabody").html(data);
                    $(".admin-workSpace").html(data);
                    }
            });
       })*/
         $(document).on("click",".UserProperty",function(){
            $.ajax({
                    url:"HouseServlet",
                    type:"POST",
                    data:{house:"full", OwnerHouse:"OwnerHouse"},
                    success:function(data){
                    $(".admin-workSpace").html(data);
                     $(".AddProprtyNew").hide();
                     $(".DHT").show();
                    $(".userDatabody").html(data);
                    }
            });
       })
       
       $(document).on("click",".allowToTenant",function(){
           var id=$(this).data("id");
            swal.fire({
                    text:"Are you shure to not allow this tenant",
                    showCancelButton:true,
                    cancelButtonText:"No",
                    confirmButtonText:"Yes! Sure",
                }).then((result)=>{
                    if(result.value){
                        $.ajax({
                                url:"UserServlet",
                                type:"POST",
                                data:{user:"AllowToTenant",tId:id},
                                success:function(data){
                                    if(data=="done"){
                                        $(".allowToTenant").hide();
                                        swal.fire({
                                            title:"Permistion Granted",
                                            text:"This tenant is allow to move",
                                         })
                                    }
                                }
                            });
                    }
                });//swal end    
       })
       
       $(document).on("click",".applieHouses",function(){
           $.ajax({
                    url:"UserServlet",
                    type:"POST",
                    data:{user:"TenantHome"},
                    success:function(data){
                        $(".userDatabody").html(data);
                    }
            });
       })
       $(document).on("click",".notAllowToTenant",function(){
           var id=$(this).data("id");
           var states=$(this).data("states");
           
             swal.fire({
                    text:"Are you sure to Cancel this request",
                    showCancelButton:true,
                    cancelButtonText:"No",
                    confirmButtonText:"Yes! Sure",
                }).then((result)=>{
                    if(result.value){
                        $.ajax({
                                 url:"UserServlet",
                                 type:"POST",
                                 data:{user:"notAllowToTenant",tId:id,tStates:states},
                                 success:function(data){
                                     if(data=="done"){
                                         swal.fire({
                                             text:"Request Cancel Succussfully..",
                                         })
                                     }
                                 }
                         });
                    }
                });//swal end
           
         
       })
       
       $(document).on("click",".removeTenant",function(){
           var id=$(this).data("id");
             $.ajax({
                        url:"UserServlet",
                        type:"POST",
                        data:{user:"RemoveTenantAppliedHouse",tenantId:id},
                        success:function(data){
                            if(data=="done"){
                                swal.fire({
                                    toast:true,
                                    title:"Property Removed",
                                    position:"top-end",
                                    timer:"5000",
                                    })
                             }
                             $("."+id).fadeOut("slow");
                        }
                         });
       })
      
      function CheckByAdmin(id){
         // alert(id);
           $.ajax({
                    url:"TenantServlet",
                    type:"POST",
                    data:{tenant:"checkByAdmin",tId:id},
                    success:function(data){
                       // alert(data);
                        if(data=="done"){
                          swal.fire({
                            icon:"success",
                            title:"Tenants Checked",
                            text:"Tenants is Successfully checked",
                          })   
                        }
                    }
            });
      }
      
      
      
      //payment by tenant to the owner 
      $(document).on("click",".paymentBtn",function(){
        var tenantId=$(this).data("tenant");
        var houseId=$(this).data("house");
        $.ajax({
            url:"PaymentServlet",
            type:"POST",
            data:{payment:'table',tenant:tenantId,house:houseId},
            success:function(data){
                $(".userDatabody").html("<div class='col-12 text-right applieHouses'>X</div>");
                $(".userDatabody").append(data);
                
            },
            error:function(error){
                alert("ERROR : "+error);
            }
        })
         
      });
      
    
      function ShowInvoiceBtn(id){
           $.ajax({
            url:"PaymentServlet",
            type:"POST",
            data:{payment:'invoice',billId:id},
            success:function(data){
               
                $(".userDatabody").html(data);
                
            },
            error:function(error){
                alert("ERROR : "+error);
            }
        })
      }
      
      $(document).on("click",".rentPayBtn",function(){
          var Id=$(this).data("id");
          var Price=$(this).data("amount");
          
          $.ajax({
              url:"PaymentServlet",
              type:'POST',
              data:{payment:"razorPayIntegration",amount:Price,billId:Id},
              success:function(res){
                  if(res=="error"){
                      alert("Please Check the net connection !!");
                  }
                  var response=JSON.parse(res);
                     if(response.status=="created"){
                          let option={
                              key:"rzp_test",
                              amount:response.amount,
                              currency:'INR',
                              name:'House Rent',
                              description:'RkRent.com',
                              image:'files/images/webImg/homeVector.jpg',
                              order_id:res.id,
                              
                              handler: function (response){
                                   //alert("  ");
                                   //save the payment detailes on database
                                    $.ajax({
                                        url:"PaymentServlet",
                                        type:"POST",
                                        data:{payment:"successfullPayment",billId:Id},
                                        success:function(data){
                                            swal.fire({
                                                icon:"success",
                                                title:'Payment Successfull',
                                                text:"your payment has successfull thanks for response ",
                                            })
                                              ShowInvoiceBtn(data);
                                        }
                                    });

                               },
                               prefill: {
                                   name: "",
                                   email: "",
                                   contact:""
                               },
                               notes: {
                                   address:"Razorpay Corporate Office"
                               },
                               theme: {
                                   color: "#3399cc"
                               }
                          }

                          let rzp=new Razorpay(option);
                          rzp.on('payment.failed', function (response){
                              alert("Paymen failed");
                                   alert(response.error.code);
                                   alert(response.error.description);
                                   alert(response.error.source);
                                   alert(response.error.step);
                                   alert(response.error.reason);
                                   alert(response.error.metadata.order_id);
                                   alert(response.error.metadata.payment_id);
                           });
                          rzp.open();
                      }
              },
              error:function(error){
                  alert("Error : "+error);
              }
              
          })
          
      })
      
        </script>
      
    </body>
</html>
