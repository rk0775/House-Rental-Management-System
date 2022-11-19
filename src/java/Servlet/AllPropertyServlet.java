/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import HelperClasses.EntityHelper;
import entities.House;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AllPropertyServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        HttpSession session=req.getSession();
          PrintWriter out=res.getWriter();
        String data="";
        String formType=req.getParameter("Allproperty");
        
        
        
        if(req.getParameter("shouse")!=null){
            String key=req.getParameter("shouse");
            if(!key.equalsIgnoreCase("all")){
                session.setAttribute("sHouse",key);
            }else{
                session.removeAttribute("sHouse");
            }
            if(key.equalsIgnoreCase("")){
                 session.removeAttribute("sHouse");
            }
         
            String type=req.getParameter("type");
            if(!type.equalsIgnoreCase("all")){
                session.setAttribute("sType",type);
            }else{
                session.removeAttribute("sType");
            }
            
            String city=req.getParameter("city");
            if(!city.equalsIgnoreCase("all")){
                session.setAttribute("sCity",city);
            }else{
                session.removeAttribute("sCity");
            }
            
            String bed=req.getParameter("bed");
            if(!bed.equalsIgnoreCase("all")){
                                System.out.println("bed room session created");
                session.setAttribute("sBed",bed);
            }else{
                                System.out.println("bed room session destroyed");
                session.removeAttribute("sBed");
            }
            
            String garage=req.getParameter("garage");
            if(!garage.equalsIgnoreCase("all")){
                System.out.println("garage room session created");
                session.setAttribute("sGarage", garage);
            }else{
                                System.out.println("garege room session destroyed");
                session.removeAttribute("sGarage");
            }
            
            String bath=req.getParameter("bath");
            if(!bath.equalsIgnoreCase("all")){
                System.out.println("bath room session created");
                session.setAttribute("sBath", bath);
            }else{
                                System.out.println("bath room session destroyed");
                session.removeAttribute("sBath");
            }
            
            String price=req.getParameter("price");
            if(!price.equalsIgnoreCase("all")){
                session.setAttribute("sPrice", price);
            }else{
                session.removeAttribute("sPrice");
            }
            
            res.sendRedirect("AllProperty.jsp");
            return;
        }else
        
        
      
        if(formType!=null){
            int pageNo=Integer.parseInt(req.getParameter("pageNo"));
            String searchKey=req.getParameter("key");
            int limit=6;
            String Query="from House where Tenants_id=NULL";
            
            
            

            if(searchKey.equalsIgnoreCase("Rent")){
                Query+=" and rentType='"+searchKey+"'";
            }else if(searchKey.equalsIgnoreCase("Sale")){
                Query+=" and rentType='"+searchKey+"'";
            }else if(searchKey.equalsIgnoreCase("New to old")){
                Query=" from House";
            }
            if(session.getAttribute("sHouse")!=null){
                Query+=" and name like '%"+session.getAttribute("sHouse") +"%'";
            }if(session.getAttribute("sType")!=null){
               Query+=" and rentType='"+session.getAttribute("sType") +"'";
            }
            if(session.getAttribute("sCity")!=null){
               Query+=" and district='"+session.getAttribute("sCity") +"'";
            }
            if(session.getAttribute("sBed")!=null){
               Query+=" and bedNo='"+session.getAttribute("sBed")+"'";
            }
            if(session.getAttribute("sGarage")!=null){
               Query+=" and garageNo='"+session.getAttribute("sGarage")+"'";
            }
             if(session.getAttribute("sBath")!=null){
               Query+=" and bathNo='"+session.getAttribute("sBath")+"'";
            }
            if(session.getAttribute("sPrice")!=null){
                
               Query+=" and rent<="+session.getAttribute("sPrice");
            }
            System.out.println("Query is : "+Query);
            
            int offset=(pageNo-1)*limit;
            List<House> houseList=EntityHelper.getDataForpagination(Query,offset,limit);
            if(houseList.size()==0){
                data+="<div class=' col-12 text-center text-muted  my-5'><h6 class='merienda'>Sorry no result found !</h6> </div>";
            }
            for(House house:houseList){
               data+=" <div class=\"mt-5 col-12 col-sm-6 col-md-4\">\n" +
"                            \n" +
"                      <div class=' card home-card'>\n" +
"                            <div class='card-body m-0 p-0'>\n" +
"                                <img class=\" card-img p-0 \" src='files/images/HouseImg/"+ house.getPhotos().get(0).getName() +"'>\n" +
"                                   <div class=\"bg card-img-overlay \">\n" +
"                                       <div class=\"home-content \">\n" +
"                                             <h3 class=\"home-name text-light \">"+ house.getName() +"</h3>\n" +
"                                       <div class=\"text-center btn-border\">"+house.getRentType()+" | $"+house.getRent()+"</div>\n" +
"                                       <h6 class='mt-3 text-light'><a href=\"singleProperty.jsp?id="+house.getId()+"\" class=\"a text-light\">View All Detailes</a><i class=\"mx-2 text-light fa fa-angle-right\"></i></h6>\n" +
"                                       \n" +
"                                       <div class=\"px-2 py-2 img-side bg-success row\" style=\"width:117%\" >\n" +

"                                           <div class=\"col-2 m-0 p-0 \">\n" +
"                                               <h6>Beds</h6>\n" +
"                                               <small class=\"text-light\">"+house.getBedNo()+"</small>\n" +
"                                           </div>\n" +
"                                           <div class=\"col-3 \">\n" +
"                                                <h6>Baths</h6>\n" +
"                                               <small class=\"text-light\">"+house.getBathNo()+"</small>\n" +
"                                           </div>\n" +
"                                           <div class=\"col-4\">\n" +
"                                                <h6>Garages</h6>\n" +
"                                               <small class=\"text-light\">"+house.getGarageNo()+"</small>\n" +
"                                           </div>\n" +
"                                           <div class=\" col-3 m-0\">\n" +
"                                               <h6> Area</h6>\n" +
"                                               <small class=\"text-light\">340sq</small>\n" +
"                                           </div>\n" +
"                                       </div>\n" +
"                                       </div>\n" +
"                                   </div>\n" +
"                            </div>\n" +
"                        </div> \n" +
"                     \n" +
"                     </div>";
                  
                       
            }//for each lop end 
            if(houseList.size()>=limit || offset!=0){
            data+= "<div class='col-12 mt-5'>"
                    + "<ul class='pagination justify-content-end px-4'>";
                       int n= EntityHelper.getByQuery("from House").size()/limit;
                       if(pageNo!=1)
                       data+="<li onclick=\" property(pno='"+(pageNo-1)+"') \" class=\"page-item\" ><a class=\"page-link\">&laquo;</a></li>\n";

                       for(int i=1;i<=n+1;i++){
                           if(i==pageNo)
                                data+="<li class=\"page-item active \" onclick=\" property(pno='"+i+"') \" ><a class=\"page-link pactive\">"+i+"</a></li>\n";
                           else
                                data+="<li class=\"page-item \" onclick=\" property(pno='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                       }
                       if(pageNo!=n+1)
                       data+="<li onclick=\" property(pno='"+(pageNo+1)+"') \" class=\"page-item\" ><a class=\"page-link\">&raquo;</a></li>\n";

                       data+="</ul>"
                               + "</div>";
            }
        }
       out.println(data);
    }//doPost method end
    
}//class end
