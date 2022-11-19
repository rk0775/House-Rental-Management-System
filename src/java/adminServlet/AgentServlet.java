/*
 *All Agent detailes manage this page
 */
package adminServlet;
import HelperClasses.EntityHelper;
import HelperClasses.Helper;
import entities.Agent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class AgentServlet extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        PrintWriter out=res.getWriter();
        String data="";
        int page=1;
        int limit=3;
        String path="D:\\TODO\\web\\files\\images\\AgentImg";
        String formType=req.getParameter("agent");
        //this if condition work for all the data show of agent front page agent
        if(formType.equalsIgnoreCase("full")){
            data=" <div class=\"row mt-3\">\n" +
"                    <div class=\"col-11 col-sm-6\">\n" +
"                    <h4 class=\"text-muted pointer \" id=\"addNewAgent\"><b>Add New Agent</b></h4>\n" +
"                    </div>\n" +
"                    <div class=\"col-11 col-sm-6\">\n" +
"                            <input type='text' class='form-control AgentSearch' placeholder='Search The Agent...'>\n"+                          
"                            <div class='list-group SearchArea my-1'></div>" +
"                    </div>\n"
                    + "<div class='col-12 d-inline'>"
                    + "     <button class='btn btn-sm text-muted' onclick='agent()'>Hired Agents</button>"
                    + "     <button onclick=\"agent(states='fired')\" class='text-muted btn btn-sm firedAgent'>Fired Agents</button>"
                    + "</div>"+
"                </div>";
            
            List<Agent> agent=null; 
            if(req.getParameter("searchAgentId")==null){//main page data get
                page=Integer.parseInt(req.getParameter("aPageNo"));//display the perticular page...
                int offset=(page-1)*limit;//create the start of page
                //this if condition display the all hired agents
                agent=EntityHelper.getDataForpagination("from Agent where states='"+req.getParameter("aStates")+"'",offset,limit);
            }else{//search page data get
                //this els condition display the serach agent by input tag...
                agent=EntityHelper.getByQuery("from Agent where id="+req.getParameter("searchAgentId"));
                
            }
            if(agent.size()==0){
                data+="<h5 class='my-4 text-center text-muted'><b>Sorry !</b> no data found on server...</h5>";
                out.println(data);
                return;
            }
            data+= "<div class='agentArea my-4'>";
              for(Agent a:agent){
                    data += ""
                    + "<div class=\" row my-4\">\n" +
"                    <div class=\"col-12\">\n" +
"                        \n" +
"                        <div class=\"row my-3\">\n" +
"                            <div class=\"d-none d-sm-inline col-sm-4  \">\n" +
"                                <img class=\"img-fluid\" src=\"files\\images\\AgentImg\\"+a.getPic()+" \" style=\"max-height:250px;\">\n" +
"                                <div class=\"card-img-overlay \">\n" +
"                                     <div class=\" agent-img-icon text-light\">\n" +
"                                    <span class=\"pl-1 pr-2 fa fa-tv\"></span>\n" +
"                                    <span class=\"px-2 fa  fa-mobile\"></span>\n" +
"                                    <span class=\"px-2 fa  fa-phone\"></span>\n" +
"                                    <span class=\"px-2 fa  fa-paper-plane\"></span>\n" +
"                                    <span class=\"px-2 fa  fa-tree\"></span>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                            </div>\n" +
"                            <div class=\" col-sm-8\">\n" +
"                                <h5 class='capitalize'><b>"+a.getName()+"</b></h5>\n" +
"                                <p class=\"text-muted pr-md-5\">"+ a.getDiscription() +"</p>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Agent ID:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\">"+ a.getId() +"</div>\n" +
"                                </div>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Phone:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\">"+ a.getPhone() +"</div>\n" +
"                                </div>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Mobile:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\">"+a.getMobile()+"</div>\n" +
"                                </div>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Email:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\">"+a.getEmail()+"</div>\n" +
"                                </div>\n" +
"                              \n" +
"                               \n" +
"                            </div>\n" +
"                        </div>\n" +
"                        \n" +
"                    </div>\n" +
"                    <div class=\"text-center alert-secondary agent-bar  col-12\">\n" +
"                        <span class=\"text-muted mx-3 pointer  \" onclick='agentEdit("+a.getId()+")' >Edit</span>\n" +
"                        <span class=\"text-muted mx-3 pointer\"  >Disabled</span>\n";
                        if(req.getParameter("aStates")!=null){
                            if(req.getParameter("aStates").equalsIgnoreCase("hired"))
                                data+="<span class=\"text-muted mx-3 pointer\" onclick=\"hiredFiredAgentAction('fired',"+a.getId()+")\">Fired</span>\n";
                            else
                                data+="<span class=\"text-muted mx-3 pointer\" onclick=\"hiredFiredAgentAction('hired',"+a.getId()+")\" >Hired </span>\n";                           
                        }else{//when the user search then display the hired and fired options...
                            if(a.getStates().equalsIgnoreCase("hired"))
                                data+="<span class=\"text-muted mx-3 pointer\" onclick=\"hiredFiredAgentAction('fired',"+a.getId()+")\">Fired</span>\n";
                            else
                                data+="<span class=\"text-muted mx-3 pointer\" onclick=\"hiredFiredAgentAction('hired',"+a.getId()+")\" >Hired </span>\n";                      
                        }
                              
                      data+="</div>\n" +
"                   \n" +
"                </div>";
              }
              
              data += "</div>";
                if(req.getParameter("searchAgentId")==null){
                       //inthis conditioncode execute only when the main agent page can aceess not the serch agent page
                       data+= "<ul class='pagination justify-content-end px-4'>";
                       int n=EntityHelper.getByQuery("from Agent where states='"+req.getParameter("aStates")+"'").size()/limit;
                       System.out.println("as :  "+n);
                       if(page!=1)
                       data+="<li onclick=\" agent(states='"+req.getParameter("aStates")+"',pageNo='"+(page-1)+"')\" class=\"page-item\" ><a class=\"page-link\">&laquo;</a></li>\n";

                       for(int i=1;i<=n+1;i++){
                           if(i==page)
                                data+="<li class=\"page-item active\" onclick=\" agent(states='"+req.getParameter("aStates")+"',pageNo='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                           else
                                data+="<li class=\"page-item \" onclick=\" agent(states='"+req.getParameter("aStates")+"',pageNo='"+i+"') \" ><a class=\"page-link\">"+i+"</a></li>\n";
                       }
                       if(page!=n+1)
                       data+="<li onclick=\" agent(states='"+req.getParameter("aStates")+"',pageNo='"+(page+1)+"')\" class=\"page-item\" ><a class=\"page-link\">&raquo;</a></li>\n";

                       data+= "  "
                       + "</ul>";
                }
        }else 
            //this if condition work for agent form show
            if(formType.equalsIgnoreCase("newAgentForm")){
            data="  <div class=\"my-3 card card-body bordered\">\n" +
"                    <span class=\"close text-right\" onclick=\"agent()\" >X</span>\n" +
"                    <h4 class=\"text-muted\">Fill The Agent Form</h4>\n" +
"                    <form class=\"\" id=\"addNewAgentForm\" enctype=\"multipart/form-data\">\n" +
"                        <input type=\"hidden\" name=\"agent\" value=\"formData\">\n" +
"                        <input type=\"hidden\" name=\"aType\" value=\"formNew\">\n" +
"                        <input class=\"form-control my-2\" type=\"text\" required name=\"Agent_name\" placeholder=\"Enter Full Name*\">\n" +
"                        <input class=\"form-control my-2\" type=\"email\" required name=\"Agent_email\" placeholder=\"Enter Email*\">\n" +
"                        <textarea name='Agent_location' rows=\"2\" class=\"form-control my-3\" placeholder=\"Detailed Address\"></textarea>\n" +
"                        <input class=\"form-control my-2\" type=\"text\" required name=\"Agent_phon\" placeholder=\"Phone Number*\">\n" +
"                        <input class=\"form-control my-2\" type=\"text\" required name=\"Agent_mobile\" placeholder=\"Mobile Number*\">\n" +
"                        <label class=\" mt-2\">Select Agent Pic</label>\n" +
"                        <input class=\"form-control my-1\" type=\"file\" required name=\"Agent_pic\">\n" +
"                        <textarea name='Agent_discription' rows=\"3\" class=\"form-control my-3\" placeholder=\"Agent Discription\"></textarea>\n" +
"                        <div class=\"text-right\">\n" +
"                            <button type=\"reset\" class=\"btn btn-warning btn-sm\">Reset</button>\n" +
"                            <button type=\"submit\" class=\"btn btn-primary btn-sm\">Submit</button>\n" +
"                        </div>\n" +
"                            \n" +
"                        \n" +
"                    </form>\n" +
"                </div>\n" +
"                \n" +
"                ";
        }else 
        //this if condition save the agent detailed to the database 
        if(formType.equalsIgnoreCase("formData")){
            Random rand=new Random();
            int number=rand.nextInt();
            String aName=req.getParameter("Agent_name");
            String aEmail=req.getParameter("Agent_email");
            String aAddr=(String)req.getParameter("Agent_location");
            String aPhone=req.getParameter("Agent_phon");
            String aMobile=req.getParameter("Agent_mobile");
            String aDiscription=(String)req.getParameter("Agent_discription");
            //atype confirem the form is new or old (add or update)
            String aType=(String)req.getParameter("aType");
            Part part=req.getPart("Agent_pic");
            String picName="Agent-"+number+".jpg";
             
            if(aType.equalsIgnoreCase("formNew")){ 
                //new agent are added to database
                Agent agent=new Agent(aName.trim(),aPhone.trim(),aMobile.trim(),aEmail.trim(),aDiscription.trim(),aAddr.trim(),picName,"hired");
                List<Agent> aList=new ArrayList<Agent>();
                aList.add(agent);
                EntityHelper.saveNewData(aList);
                    File file=new File(path);
                    if(!file.exists()){
                       file.mkdir();
                    }
                data="0";
            }else if(aType.equalsIgnoreCase("formEdit")){ 
                //old agent was update the data
                //get the id to edit user and get hibernate class set the changes
                List<Agent> agent=EntityHelper.getByQuery("from Agent where id="+Integer.parseInt(req.getParameter("Agent_id")));
                //here set the changes(i know query get the only one data)
                agent.get(0).setName(aName.trim());
                agent.get(0).setAddress(aAddr.trim());
                agent.get(0).setEmail(aEmail.trim());
                agent.get(0).setDiscription(aDiscription.trim());
                agent.get(0).setMobile(aMobile.trim());
                agent.get(0).setPhone(aPhone.trim());
                
                if(part.getSize()!=0){
                    //agent New Image is selected
                    agent.get(0).setPic(picName);//so change the image name in database
                    //delete the old image in to folder
                    File file=new File(path+File.separator+req.getParameter("oldImgName"));
                    file.delete();
                }
                //update the changes using hibernate
               if(EntityHelper.updateData(agent)){
                   data="0";
               }else{
                   data="-1";
               }
            } //form edit if condition close
            
            //when file is save to folder then he is selected
            if(part.getSize() !=0){
                String SavePath=path+File.separator+picName;
                Helper.saveTheImageToFolder(SavePath,part);
            }
           
        }else 
           //this if conditon show the edit data         
            if(formType.equalsIgnoreCase("editAgentForm")){
                
                List<Agent> aList=EntityHelper.getByQuery("from Agent where id="+Integer.parseInt(req.getParameter("agentId")));
                Agent a=aList.get(0);
                data="<h5 class='text-muted p-3 '><b>EDIT THE AGENT PROFILE</b><span onclick='agent()' class='close'>X</span></h5> "
                        + "<form class='' id='editAgentForm' enctype='multipart/form-data' >"                       
                        + "<div class=\" row my-4\">\n" +
"                    <div class=\"col-12\">\n" +
"                        \n" +
"                        <div class=\"row my-3\">\n" +
"                            <div class=\"col-sm-4  \">\n" +
"                                <img class=\"img-fluid mt-5 \" src=\"files\\images\\AgentImg\\"+a.getPic()+" \">\n" +
"                                <input type='file' class='form-control' name=\"Agent_pic\">                "+
"                            </div>\n" +
"                            <div class=\" col-sm-8\">\n" +
                        
"                                <input type=\"hidden\" name=\"agent\" value=\"formData\">\n" +
"                                <input type=\"hidden\" name=\"aType\" value=\"formEdit\">\n" +
"                                <input type=\"hidden\" name=\"oldImgName\" value=\""+a.getPic()+"\">\n" +
"                                <input type=\"hidden\" name=\"Agent_id\" value=\""+a.getId()+"\">\n" +
                        
"                                <input class='form-control my-2' name='Agent_name'  value='"+a.getName()+"' >\n" +
"                                <textarea rows='2' name='Agent_location' class=\"my-2 form-control text-muted pr-md-5\">"+ a.getAddress() +"</textarea>\n" +
"                                <textarea rows='4' class=\"my-2 form-control text-muted pr-md-5\"   name='Agent_discription'>"+ a.getDiscription() +"</textarea>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>ID:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\"><input disabled  class='form-control' value='"+ a.getId() +"' /> </div>\n" +
"                                </div>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Phone:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\"><input class='form-control' name=\"Agent_phon\"  value='"+ a.getPhone() +"'> </div>\n" +
"                                </div>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Mobile:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\"><input class='form-control' name=\"Agent_mobile\" value='"+a.getMobile()+"'> </div>\n" +
"                                </div>\n" +
"                                <div class=\"row my-1\">\n" +
"                                    <div class=\"col-5\"><b>Email:</b></div>\n" +
"                                    <div class=\"col-6 quick-data text-muted\"><input name='Agent_email' type='email' required class='form-control' value='"+a.getEmail()+"'></div>\n" +
"                                </div>\n" +
"                              \n" +
"                               \n" +
"                            </div>\n" +
"                        </div>\n" +
"                        \n" +
"                    </div>\n" +
"                 " +
"                   \n" +
"                </div> "
                        + "<div class='text-center'>"
                        +   " <button type='submit' class='btn btn-warning'>Save Changes</button> "
                        + "</div> "
                        + "</form>";
            }else
                //
            if(formType.equalsIgnoreCase("hiredFiredAgentAction")){
                List<Agent> agent=EntityHelper.getByQuery("from Agent where id="+Integer.parseInt(req.getParameter("firedID")));
                agent.get(0).setStates(req.getParameter("aStates"));
                EntityHelper.updateData(agent);
            }else
                //
            if(formType.equalsIgnoreCase("search")){
                String key=req.getParameter("searchKey");
                System.out.println(key);
                List<Agent> result=EntityHelper.getByQuery("from Agent where name like '%"+key.trim()+"%' or id='"+key+"' ");
                
                if(result.size()!=0){
                    int topFiveSearch=0;
                    for(Agent agent:result){
                        data+="<div class='px-lg-5 t list-group-item gray agent-search pointer' data-agent='"+agent.getId()+"' >"+agent.getName()+"</div>";
                        topFiveSearch++; 
                        if(topFiveSearch==5)
                            break;
                    }
                }else{
                    data+="<a class='text-muted px-4 py-2 gray'><b>Sorry !</b>result not found...</a>";
                }
            }
        out.println(data);
    }
}
