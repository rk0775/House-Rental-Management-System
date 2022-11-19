<%-- 
    Document   : newjsp
    Created on : Feb 22, 2021, 7:16:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body Style="background: tomato" onload="removeLoader()">
        <%@include file="header.jsp" %>
        <div class="container">
           
            <div class="pr-lg-5 pl-lg-5 pr-md-3 pl-md-3 mt-3 mb-5 " >
            <div class="mx-lg-4  card py-0 my-3 " id="downloadInvoice">
                <div class="bg-light pt-2">
                    <h3 class="text-dark pl-5 invoice" ><img class="img-fluid" src="files/images/webImg/homeVector.jpg" style="max-height: 65px;width:auto"><span class="pl-3"><b>INVOICE</b></span></h3>
                </div>
                <div class="card-body alert-light mb-5 px-5 mx-4">
                    <div class="row">
                    <div class="col-12 col-sm-6">
                        <h5>Client Name</h5>
                        <h6 class="mt-3 mb-0 pb-0"><span class="text-muted">Produce Date :</span> 16 may,21</h6>
                        <h6 class="mt-0 pt-0"><span class="text-muted">Invoice No :</span> 123456</h6>
                    </div>
                    <div class="col-12 col-sm-6 text-right invoice-taxt-mobile">
                        <p class="m-0 p-0 text-muted">Your Name</p>
                        <p class="m-0 p-0 text-muted">Your Address</p>
                        <p class="m-0 p-0 text-muted">Town City</p>
                        <p class="m-0 p-0 text-muted">States : <span class="text-danger">Unpaid</span></p>
                    </div>
                    </div>
                    
                    <table class=" table mt-5 mb-0 pb-0 ">
                        <t-head>
                            <tr class="" style="border-bottom:1px solid #009900 ;">
                                <td class="text-muted text-left invoice-text">Description</td>
                                <td class="text-muted text-right invoice-text">Rate</td>
                                <td class="text-muted text-right invoice-text">Hour</td>
                                <td class="text-muted text-right invoice-text">Price</td>
                            </tr>
                        </t-head>
                    </table>
                    <table class="table mt-0 pt-0 ">
                        <t-body>
                            <tr class="mt-3 ">
                                <td class="text-left text-dark invoice-text"><b>Front End</b></td>
                                <td class="text-right text-dark invoice-text"><b>&#x20B9; 1000</b></td>
                                <td class="text-right text-dark invoice-text"><b>12</b></td>
                                <td class="text-success text-right invoice-text"><b>&#x20B9; 12000</b></td>
                            </tr>
                            <tr class="mt-3 ">
                                <td class="text-left text-dark invoice-text"><b>Front End</b></td>
                                <td class="text-right text-dark invoice-text"><b>&#x20B9; 1000</b></td>
                                <td class="text-right text-dark invoice-text"><b>12</b></td>
                                <td class="text-success text-right invoice-text"><b>&#x20B9; 12000</b></td>
                            </tr>
                           
                            <!--tr class="text-right">
                                <td colspan="3">Total Price </td>
                                <td ><h5 class="text-danger"> <b>12000</b></h5></td>
                            </tr-->
                           
                         
                        </t-body>
                    </table>
                     <!--div class="text-right">
                         <button class="btn btn-success px-5">PAY</button>
                     </div-->
                </div>
                
                <div class="card-footer mt-5 alert-secondary px-5 ">
                    <table class="table mt-4 px-5">
                       <t-head>
                           <tr class="" style="border-bottom:1px solid #999999 ;">
                                <td class="text-muted text-left">Bank Info</td>
                                <td class="text-muted text-right ">Due By</td>
                                <td class="text-muted text-right">Total Due</td>
                            </tr>
                        </t-head>
                    </table>
                    <table class="table">
                       <t-head>
                           <tr class="" style="border-bottom:1px solid #009900 ;">
                               <td class="text-muted text-left">
                                   <span class="text-muted invoice-text">Account No : </span><span>123456789009</span><br>
                                   <span class="text-muted invoice-text">Sort Code : </span><span>12345</span>
                               </td>
                               <td class="text-dark text-right "><h5 ><b>16 may,21</b></h5></td>
                               <td class="text-right text-tomato"><h5><b>&#8377; 24000</b></h5></td>
                            </tr>
                        </t-head>
                    </table>
                    <div class="row pb-5">
                    <div class="col-6" ><i class="fa fa-heart text-danger"> </i><b> Thank You!</b></div>
                    <div class="col-6 text-right text-muted" ><small>rkpro2021@gmail.com | 8806723456 | RkRent.com</small></div>
                    </div>
                </div>
                    
            </div>
            </div>
            
        </div>
        <script>
               headerBackgroundDark();
        </script>
    </body>
</html>
