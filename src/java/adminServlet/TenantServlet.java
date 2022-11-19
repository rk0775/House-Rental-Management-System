/*
 
 */
package adminServlet;

import HelperClasses.EntityHelper;
import entities.Tenants;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TenantServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        PrintWriter out=res.getWriter();
        String data="";
        String formType=req.getParameter("tenant");
        int pageNo=1;
        int limit=5;
        if(formType.equalsIgnoreCase("full")){
            
              data=" <div class=\"row mt-3\">\n" +
"                    <div class=\"col-11 col-sm-6\">\n" +
"                    <h4 class=\"text-muted \" ><b>All Tenants</b></h4>\n" +
"                    </div>\n" +
"                    <div class=\"col-11 col-sm-6\">\n" +
"                            <input type='text' class='form-control TenantSearch' placeholder='Search The User...'/>\n"
                    + "      <div class='list-group SearchArea my-1'></div>" +
"                    </div>\n" +
"                </div>";
            pageNo=Integer.parseInt(req.getParameter("cPageNo"));
            int offset=(pageNo-1)*limit;
            List<Tenants> tenants=EntityHelper.getDataForpagination("From Tenants",offset,limit);
            data+="<h6 class='mt-5 text-muted'>Tenants Detailes...</h6>"
                    + "<div class=\"mt-3 mb-2 \">\n" +
"                    <table class=\"table table-responsive-md table-hover\">\n" +
"                        <thead class=\"gray text-muted\">\n" +
"                            \n" +
"                                <th class=\"t-10\">ID</th>\n" +
"                                <th>Name</th>\n" +
"                                <th>Email</th>     "+
"                                <th>House Name</th>\n" +
"                                <th>Numbers</th>\n" +
"                                <th>States</th>\n" +
"                                <th>Admin Check</th>\n" +
"                            \n" +
"                        </thead>\n" +
"                        <tbody>\n" +
"                            ";
                            for(Tenants tenant:tenants){
                                List<User> userList=EntityHelper.getByQuery("from User where id="+tenant.getTid());
                                User user=userList.get(0);
                                data+="<tr class='  ' id='tenant' data-x='Table' data-h='" +tenant.getTid()+ "' data-t='" +tenant.getId()+ "'  data-tenant='"+tenant.getId()+" '>\n"
                             + " <td class='  ' >"+user.getId()+"</td>\n" +
"                                <td class=' ' >"+user.getName()+"</td>\n" +
"                                <td class=' '>"+user.getEmail()+"</td>\n" +
"                                <td class='  '>"+tenant.getHouse().getName()+"</td>\n" +
"                                <td class=' '>Phone : "+ user.getPhone() +" </td>\n" +
"                                <td class='  '>";
                                if(tenant.getStates().equalsIgnoreCase("done")){
                                data+="<span class='text-success'>Done</span>";
                                }else if(tenant.getStates().equalsIgnoreCase("dismiss")){
                                    data+="<span class='text-danger'>Dismiss</span>";
                                }else{
                                    data+="<span class='text-warning'>Wait</span>";
                                }
                                data+="</td>\n" +
"                                <td class=' '>"+ tenant.isCheckByAdmin() +" </td>"+

                              "\n" +
"                            </tr>";
                            }                  
                       data+=" </tbody>\n" +
"                    </table>\n" +
"                </div>";
                // pagination of client
                data+= "<ul class='pagination justify-content-end mt-4 px-4'>";
                int n=EntityHelper.getByQuery("from Tenants").size()/limit;
                if(pageNo!=1)
                    data+="<li onclick=\" tenants(pageNo='"+(pageNo-1)+"')\" class=\"page-item\" ><a class=\"page-link\">&laquo;</a></li>\n";
                       for(int i=1;i<=n+1;i++){
                           if(i==pageNo)
                                data+="<li class=\"page-item active\" onclick=\" tenants(pageNo='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                           else
                                data+="<li class=\"page-item \" onclick=\" tenants(pageNo='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                       }   
                if(pageNo!=n+1)
                    data+="<li onclick=\" tenants(pageNo='"+(pageNo+1)+"')\" class=\"page-item\" ><a class=\"page-link\">&raquo;</a></li>\n"
                +"</ul>";        
        }else if(formType.equalsIgnoreCase("checkByAdmin")){
            List<Tenants>tenantList=EntityHelper.getByQuery("from Tenants where id="+Integer.parseInt(req.getParameter("tId")));
            tenantList.get(0).setIsCheckByAdmin(true);
            EntityHelper.updateData(tenantList);
            data="done";
        }else if (formType.equalsIgnoreCase("searchTenant")) {
            String key = req.getParameter("searchKey");
            List<Tenants> result = EntityHelper.getByQuery("from Tenants where name like '%" + key.trim() + "%' or id='" + key + "' ");
           
            if (result.size() != 0) {
                int topFiveSearch = 0;
                for (Tenants tenant : result) {
                    data += "<div class='px-lg-5 t list-group-item gray pointer' id='tenant' data-x='Table' data-h='" +tenant.getTid()+ "' data-t='" +tenant.getId()+ "'  data-tenant='"+tenant.getId()+" ' >" + tenant.getName() + "</div>";
                    topFiveSearch++;
                    if (topFiveSearch == 5) {
                        break;
                    }
                }
            } else {
                data += "<a class='text-muted px-4 py-2 gray'><b>Sorry !</b>result not found...</a>";
            }
        }
        out.println(data);
    }//method body
}//class body
