/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import HelperClasses.EntityHelper;
import HelperClasses.Helper;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import entities.Bill;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Admin
 */
public class PaymentServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMM,yyyy");
        PrintWriter out=res.getWriter();
        String data="";
        String type=req.getParameter("payment");
        if(type.equalsIgnoreCase("table")){
            int tenant=Integer.parseInt(req.getParameter("tenant"));
            int house=Integer.parseInt(req.getParameter("house"));
            List<Bill> bills=EntityHelper.getByQuery("from Bill where tenant_Tenants_id="+tenant+" and House_id="+house);
        
            data=" \n" +
"            <div class=\"container\">\n"
                    + "<h4 class='text-muted my-2'>BILL TABLE </h4>" +
"                <div class=\"\">\n" +
"                    <table class=\"table borderd table-hover\">\n" +
"                        <tr class=\"alert-secondary\">\n" +
"                            <td>Bill No</td>\n" +
"                            <td>Date</td>\n" +
"                            <td>Amount</td>\n" +
"                            <td>States</td>\n" +
"                            <td>Action</td>\n" +
"                        </tr>\n";
              for(Bill bill:bills){  
                  data+="<tr class=\"\">\n" +
"                            <td>"+bill.getId()+"</td>\n" +
"                            <td>"+sdf.format(bill.getStartDate())+"<span class='text-muted px-md-2'> To </span>"+sdf.format(bill.getEndDate())+"</td>\n" +
"                            <td>"+bill.getAmount()+"</td>\n";
                   if(bill.getStates().equalsIgnoreCase("paid")){     
                    data+="   <td class='text-success'><b>Paid</b></td>\n";
                   }else if(bill.getStates().equalsIgnoreCase("unpaid")){
                    data+="   <td class='text-danger'><b>Unpaid</b></td>\n";
                   }
                   data+=""+ 
"                            <td><button onclick='ShowInvoiceBtn(\"" +bill.getId()+"\")' class=\" btn-sm btn-primary\" >View Bill</button></td>\n" +
"                        </tr>\n";
              }
                    data+=""+
"                    \n" +
"                        \n" +
"                    </table>\n" +
"                </div>\n" +
"            </div>";
        }else if(type.equalsIgnoreCase("invoice")){
            String id=req.getParameter("billId");
          
            
            List<Bill> bills=EntityHelper.getByQuery("from Bill where id='"+id+"'");
           Bill bill=bills.get(0);
           
           
            data=""
                    + "<div class=\"container\">\n" +
"            \n" +
"            " +
"            <div class=\"pr-lg-5 pl-lg-5 pr-md-3 pl-md-3 mt-3 mb-5 \" >\n"
                    + "<div class=\"pr-md-5 text-right\">\n" +
"                           <i onclick=\"generateInvoicePDF()\" class=\"fa-muted fa fa-download\"></i>\n"
                    + "     <i class='px-1 paymentBtn fa fa-arrow-circle-left' data-tenant='"+bill.getTenant().getId()+"'  data-house='"+bill.getHouseId()+"'></i>" +
"                    </div>" +
"            <div class=\"mx-lg-4  card py-0 my-3 \" id=\"downloadInvoice\">\n" +
"                <div class=\"bg-light pt-2\">\n" +
"                    <h3 class=\"text-dark pl-5 invoice\" ><img class=\"img-fluid\" src=\"files/images/webImg/homeVector.jpg\" style=\"max-height: 65px;width:auto\"><span class=\"pl-3\"><b>INVOICE</b></span></h3>\n" +
"                </div>\n" +
"                <div class=\"card-body alert-light mb-5 px-5 mx-4\">\n" +
"                    <div class=\"row\">\n" +
"                    <div class=\"col-12 col-sm-6\">\n" +
"                        <h5>"+bill.getTenant().getHouse().getName()+"</h5>\n" +
"                        <h6 class=\"mt-3 mb-0 pb-0\"><span class=\"text-muted\">Produce Date :</span>"+sdf.format(bill.getStartDate())+"</h6>\n" +
"                        <h6 class=\"mt-0 pt-0\"><span class=\"text-muted\">Invoice No :</span>"+bill.getId()+"</h6>\n" +
"                    </div>\n" +
"                    <div class=\"col-12 col-sm-6 text-right invoice-taxt-mobile\">\n" +
"                        <p class=\"m-0 p-0 text-muted\">"+bill.getTenant().getName()+"</p>\n" +
"                        <p class=\"m-0 p-0 text-muted\">"+bill.getTenant().getGender()+"</p>\n" +
"                        <p class=\"m-0 p-0 text-muted\">Town City</p>\n" +
"                        <p class=\"m-0 p-0 text-muted\">States : ";
                            if(bill.getStates().equalsIgnoreCase("unpaid")){
                                data+="<span class=\"text-danger\">Unpaid</span>";
                            }else if(bill.getStates().equalsIgnoreCase("Paid")){
                                data+="<span class=\"text-success\">Paid</span>";                                
                            }
                            
                        data+="</p>\n" +
"                    </div>\n" +
"                    </div>\n" +
"                    \n" +
"                    <table class=\" table mt-5 mb-0 pb-0 \">\n" +
"                        <t-head>\n" +
"                            <tr class=\"\" style=\"border-bottom:1px solid #009900 ;\">\n" +
"                                <td class=\"text-muted text-left invoice-text\">Description</td>\n" +
"                                <td class=\"text-muted text-right invoice-text\">Rate</td>\n" +
"                                <td class=\"text-muted text-right invoice-text\">Time</td>\n" +
"                                <td class=\"text-muted text-right invoice-text\">Price</td>\n" +
"                            </tr>\n" +
"                        </t-head>\n" +
"                    </table>\n" +
"                    <table class=\"table mt-0 pt-0 \">\n" +
"                        <t-body>\n" +
"                            <tr class=\"mt-3 \">\n" +
"                                <td class=\"text-left text-dark invoice-text\"><b>H. Rent</b></td>\n" +
"                                <td class=\"text-right text-dark invoice-text\"><b>&#x20B9; "+bill.getAmount()+"</b></td>\n" +
"                                <td class=\"text-right text-dark invoice-text\"><b>1 <small>month</small></b></td>\n" +
"                                <td class=\"text-success text-right invoice-text\"><b>&#x20B9;"+bill.getAmount()+"</b></td>\n" +
"                            </tr>\n" +
"                            <tr class=\"mt-3 \">\n" +
"                                <td class=\"text-left text-dark invoice-text\"><b>L. Charge</b></td>\n" +
"                                <td class=\"text-right text-dark invoice-text\"><b>&#x20B9; 0.0</b></td>\n" +
"                                <td class=\"text-right text-dark invoice-text\"><b>0</b></td>\n" +
"                                <td class=\"text-success text-right invoice-text\"><b>&#x20B9; 0.0</b></td>\n" +
"                            </tr>\n" +
"                           \n";
                        if(bill.getStates().equalsIgnoreCase("unpaid")){
                            data+=
"                            <tr class=\"text-right\">\n" +
"                                <td colspan=\"3\">Total Price </td>\n" +
"                                <td ><h5 class=\"text-danger\"> <b>"+bill.getAmount()+"</b></h5></td>\n" +
"                            </tr>\n";
                        }
                              data+=
"                           \n" +
"                         \n" +
"                        </t-body>\n" +
"                    </table>\n";
                        if(bill.getStates().equalsIgnoreCase("unpaid")){
                            data+=
"                     <div class=\"text-right\">\n" +
"                         <button class=\"btn btn-success px-5 rentPayBtn \" data-id='"+bill.getId()+"' data-amount='"+bill.getAmount()+"' >PAY</button>\n" +
"                     </div>\n";
                        }
                data+="</div>\n";
                        if(bill.getStates().equalsIgnoreCase("paid")){
                            data+=
"                <div class=\"card-footer mt-5 alert-secondary px-5 \">\n" +
"                    <table class=\"table mt-4 px-5\">\n" +
"                       <t-head>\n" +
"                           <tr class=\"\" style=\"border-bottom:1px solid #999999 ;\">\n" +
"                                <td class=\"text-muted text-left\">Bank Info</td>\n" +
"                                <td class=\"text-muted text-right \">Due By</td>\n" +
"                                <td class=\"text-muted text-right\">Total Due</td>\n" +
"                            </tr>\n" +
"                        </t-head>\n" +
"                    </table>\n" +
"                    <table class=\"table\">\n" +
"                       <t-head>\n" +
"                           <tr class=\"\" style=\"border-bottom:1px solid #009900 ;\">\n" +
"                               <td class=\"text-muted text-left\">\n" +
"                                   <span class=\"text-muted invoice-text\">Account No : </span><span>123456789009</span><br>\n" +
"                                   <span class=\"text-muted invoice-text\">Sort Code : </span><span>12345</span>\n" +
"                               </td>\n" +
"                               <td class=\"text-dark text-right \"><h5 ><b>"+sdf.format(bill.getSubmitedDate())+"</b></h5></td>\n" +
"                               <td class=\"text-right text-tomato\"><h5><b>&#8377;  "+bill.getAmount()+"</b></h5></td>\n" +
"                            </tr>\n" +
"                        </t-head>\n" +
"                    </table>\n" +
"                    <div class=\"row pb-5\">\n" +
"                    <div class=\"col-6\" ><i class=\"fa fa-heart text-danger\"> </i><b> Thank You!</b></div>\n" +
"                    <div class=\"col-6 text-right text-muted\" ><small>rkpro2021@gmail.com | 8806723456 | RkRent.com</small></div>\n" +
"                    </div>\n" +
"                </div>\n";
        }
                data+=
"                    \n" +
"            </div>\n" +
"            </div>\n" +
"            \n" +
"        </div>";
            
        }else if(type.equalsIgnoreCase("razorPayIntegration")){
            try {
                float amount=Float.parseFloat(req.getParameter("amount"));
                String billid=req.getParameter("billId");
                RazorpayClient rzp=new RazorpayClient("rzp_test_2U","Mc");
                JSONObject ob=new JSONObject();
                ob.put("amount",amount*100);
                ob.put("currency","INR");
                ob.put("receipt",billid);
                  //create a order
                  Order order=rzp.Orders.create(ob);
                  data=order.toString();
                
            } catch (Exception ex) {
                data="error";
                ex.printStackTrace();
            }
        }else if(type.equalsIgnoreCase("successfullPayment")){
            
            String billid=req.getParameter("billId");
            List<Bill> bills=EntityHelper.getByQuery("from Bill where id='"+billid+"'");
            System.out.println("!!!!!!!!!!!!!!!!!!!! "+billid+" %%%%%%%%%%%%%");
            bills.get(0).setSubmitedDate(new Date());
            bills.get(0).setStates("paid");
            EntityHelper.updateData(bills);
            data=""+bills.get(0).getId();
        }
        
        out.print(data);
    }
    
}
