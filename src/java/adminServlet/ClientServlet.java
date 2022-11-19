/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminServlet;

import HelperClasses.EntityHelper;
/*import com.sun.security.ntlm.Client;
import entities.Agent;*/
import entities.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        PrintWriter out=res.getWriter();
        String data="";
        int pageNo=1;
        int limit=5;
        String formType=req.getParameter("client");
        if(formType.equalsIgnoreCase("full")){
            data=" <div class=\"row mt-3\">\n" +
"                    <div class=\"col-11 col-sm-6\">\n" +
"                    <h4 class=\"text-muted \" ><b>All Client Library</b></h4>\n" +
"                    </div>\n" +
"                    <div class=\"col-11 col-sm-6\">\n" +
"                            <input type='text' class='form-control ClientSearch' placeholder='Search The User...'/>\n"
                    + "      <div class='list-group SearchArea my-1'></div>" +
"                    </div>\n" +
"                </div>";
            pageNo=Integer.parseInt(req.getParameter("cPageNo"));
            int offset=(pageNo-1)*limit;
            List<User> users=EntityHelper.getDataForpagination("From User",offset,limit);
            data+="<h6 class='mt-5 text-muted'>User Detailes...</h6>"
                    + "<div class=\"mt-3 mb-2 \">\n" +
"                    <table class=\"table table-responsive-md table-hover\">\n" +
"                        <thead class=\"gray text-muted\">\n" +
"                            \n" +
"                                <th class=\"t-10\">ID</th>\n" +
"                                <th>Name</th>\n" +
"                                <th>Photo</th>\n" +
"                                <th>Email</th>\n" +
"                                <th>Numbers</th>\n" +
"                                <th>Location</th>\n" +
"                            \n" +
"                        </thead>\n" +
"                        <tbody>\n" +
"                            ";
                            for(User user:users){
                              data+="<tr class=' clientRowClick' data-client='"+user.getId()+"'>\n"
                             + " <td class=' pt-4 ' >"+user.getId()+"</td>\n" +
"                                <td class='pt-4 ' >"+user.getName()+"</td>\n" +
"                                <td class=' pt-2 '><img class='m-0 img-fluid userPic pointer'  src=\"files\\images\\UserImg\\"+user.getPic()+" \"  style='max-width:50px; max-height:50px' > </td>\n" +
"                                <td class=' pt-4 '>"+user.getEmail()+"</td>\n" +
"                                <td class=' pt-2'>Phone : "+ user.getPhone() +"<br>Mobile : "+user.getMobile()+" </td>\n" +
"                                <td class=' pt-2'><span>Dis : "+user.getDistrict()+"<br>Tal : "+user.getTal()+"</span></td>\n" +
"                              \n" +
"                            </tr>\n";
                            }                  
                       data+=" </tbody>\n" +
"                    </table>\n" +
"                </div>";
                // pagination of client
                data+= "<ul class='pagination justify-content-end mt-4 px-4'>";
                int n=EntityHelper.getByQuery("from User").size()/limit;
                System.out.println("as :  "+n);
                if(pageNo!=1)
                    data+="<li onclick=\" client(pageNo='"+(pageNo-1)+"')\" class=\"page-item\" ><a class=\"page-link\">&laquo;</a></li>\n";
                       for(int i=1;i<=n+1;i++){
                           if(i==pageNo)
                                data+="<li class=\"page-item active\" onclick=\" client(pageNo='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                           else
                                data+="<li class=\"page-item \" onclick=\" client(pageNo='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                       }   
                if(pageNo!=n+1)
                    data+="<li onclick=\" client(pageNo='"+(pageNo+1)+"')\" class=\"page-item\" ><a class=\"page-link\">&raquo;</a></li>\n"
                +"</ul>";
                               
                               
        }else if(formType.equalsIgnoreCase("searchClient")){
             String key=req.getParameter("searchKey");
                int topFiveSearch=0;
                List<User> result=EntityHelper.getByQuery("from User where name like '%"+key+"%' or id='"+key+"' ");
                if(result.size()!=0){
                    for(User user:result){
                        data+="<div class='px-lg-5 t list-group-item client-search gray pointer' data-client='"+ user.getId()+"' >"+user.getName()+"</div>";
                        topFiveSearch++; 
                        if(topFiveSearch==5)
                            break;
                    }
                }else{
                    data+="<a class='text-muted  px-4 py-2 gray'><b>Sorry !</b> result not found...</a>";
                }
        }else if(formType.equalsIgnoreCase("displaySearchClient")){
            List<User> user=EntityHelper.getByQuery("From User where id="+req.getParameter("searchClientId"));
            for(User u:user){
                  data=" <div class='card my-5 '><h3 class=\"text-muted bold my-2 px-4 \"> Search User <span onclick='client()' class='close'>X</span></h3>\n" +
"                <div class=\"col-12 my-2 \">\n" +
"                   \n" +
"                    <div class=\"row my-4\">\n" +
"                        <div class=\"col-12  col-md-5  text-center\">\n" +
"                            <img class=\"img img-fluid\" src=\"files\\images\\UserImg"+ File.separator+u.getPic() +"\" style=\"max-height: 300px;max-width:350px;width:auto\">\n" +
"                        </div>\n" +
"                        <div class=\"col-12  col-md-7\">\n" +
"                            <h4 class=\"capitalize\"><b>"+ u.getName() +"</b></h4>\n" +
"                            <p class=\"text-muted\">"+ u.getAddress() +"</p>\n" +
"                            <div class=\"row my-1\">\n" +
"                                <div class=\"col-5\"><b>Phone:</b></div>\n" +
"                                <div class=\"col-7 quick-data text-muted\">"+u.getPhone() +"</div>\n" +
"                            </div>\n" +
"                            <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Mobile:</b></div>\n" +
"                                    <div class=\"col-7 quick-data text-muted\">"+u.getMobile() +"</div>\n" +
"                            </div>\n" +
"                            <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Email:</b></div>\n" +
"                                    <div class=\"col-7 quick-data text-muted\">"+u.getEmail() +"</div>\n" +
"                            </div>\n" +
"                            <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Password:</b></div>\n" +
"                                    <div class=\"col-7 quick-data text-muted\">"+ u.getPassword() +"</div>\n" +
"                            </div>\n" +
"                            <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Location:</b></div>\n" +
"                                    <div class=\"col-7 quick-data text-muted\">Dis "+u.getDistrict() +" , Tal "+ u.getTal() +"</div>\n" +
"                            </div>\n" +
"                            <div class=\"text-center mt-4\">\n" +
"                                    <span class=\"pl-1 pr-2 fa my-fa fa-tv\"></span>\n" +
"                                    <span class=\"px-2 fa my-fa fa-mobile\"></span>\n" +
"                                    <span class=\"px-2 fa my-fa fa-phone\"></span>\n" +
"                                    <span class=\"px-2 fa my-fa fa-paper-plane\"></span>\n" +
"                                    <span class=\"px-2 fa my-fa fa-tree\"></span>\n" +
"                            </div>\n" +
"                        </div>\n" +
"                        \n" +
"                    </div>    \n" +
"                </div>"+
"             </div>";
            }
        }
        out.print(data);
    }
}
