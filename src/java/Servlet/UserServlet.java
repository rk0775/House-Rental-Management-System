/*
 
 */
package Servlet;

import HelperClasses.EmailManager;
import HelperClasses.EntityHelper;
import HelperClasses.Helper;
import entities.Bill;
import entities.House;
import entities.Tenants;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@MultipartConfig
public class UserServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        String data ="";
      //  String path = req.getRealPath("files" + File.separator + "images" + File.separator + "UserImg");
        String path = "D:\\TODO\\web\\files\\images\\UserImg";
        HttpSession session = req.getSession();
        String formType = req.getParameter("user");
        int tenantHouseRentLimit=1;
        User loginUser = (User) session.getAttribute("loginUser");
        if (formType.equalsIgnoreCase("full")) {
            data ="            <div class=\"col-12 UserData   \">\n"
                    + "                   \n"
                    + "                    <div class=\"  row my-4\">\n"
                    + "                        <div class=\"col-12  col-md-5  text-center\">\n"
                    + "                            <img class=\"img img-fluid\" src=\"files\\images\\UserImg" + File.separator + loginUser.getPic() + "\" style=\"max-height: 300px;max-width:350px;width:auto\">\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12  col-md-7\">\n"
                    + "                            <h4 class=\"capitalize\"><b>" + loginUser.getName() + "</b></h4>\n"
                    + "                            <p class=\"text-muted\">" + loginUser.getAddress() + "</p>\n"
                    + "                            <div class=\"row my-1\">\n"
                    + "                                <div class=\"col-5\"><b>Phone:</b></div>\n"
                    + "                                <div class=\"col-7 quick-data text-muted\">" + loginUser.getPhone() + "</div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Mobile:</b></div>\n"
                    + "                                    <div class=\"col-7 quick-data text-muted\">" + loginUser.getMobile() + "</div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Email:</b></div>\n"
                    + "                                    <div class=\"col-7 quick-data text-muted\">" + loginUser.getEmail() + "</div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Password:</b></div>\n"
                    + "                                    <div class=\"col-7 quick-data text-muted\">" +HelperClasses.PasswordEncodeDecode.getDecodeString(loginUser.getPassword()) + "</div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Location:</b></div>\n"
                    + "                                    <div class=\"col-7 quick-data text-muted\">Dis " + loginUser.getDistrict() + " , Tal " + loginUser.getTal() + "</div>\n"
                    + "                            </div>\n"
                    + "                            <div class=\"text-center mt-4\">\n"
                    + "                                    <span class=\"pl-1 pr-2 fa my-fa fa-tv\"></span>\n"
                    + "                                    <span class=\"px-2 fa my-fa fa-mobile\"></span>\n"
                    + "                                    <span class=\"px-2 fa my-fa fa-phone\"></span>\n"
                    + "                                    <span class=\"px-2 fa my-fa fa-paper-plane\"></span>\n"
                    + "                                    <span class=\"px-2 fa my-fa fa-tree\"></span>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        \n"
                    + "                    </div>    \n"
                    + "                </div>"
                    + "                    <div class=\"text-center alert-dark agent-bar py-1 col-12\">\n"
                    + "                        <span class=\"text-dark user-btn mx-3 pointer applieHouses \" >Applied House</span>\n"
                    + "                        <span class=\"text-dark user-btn mx-3 pointer UserProperty \">Propertys</span>\n"
                    + "                        <span class=\"text-dark user-btn mx-3 pointer editUser \" >Edit Profile</span>\n"
                    + "                        <span class=\"text-dark user-btn mx-3 pointer deleteUser \">Delete Account</span>\n"
                    + "                        <span class=\"text-muted close user-btn mx-3 pointer SHU \"><i class='SHUI fa fa-arrow-up'></i></span>\n"
                    
                    + "                    </div>\n";
        } else if (formType.equalsIgnoreCase("editUser")) {
            data = ""
                    + "<h5 class='text-muted col-12'><span class='merienda-bold'>EDIT THE USER PROFILE</span> <span onclick='user()' class='text-right close text-right' >X</span></h5> "
                    + "<form class='' id='editUserForm' enctype='multipart/form-data' >"
                    + "<div class=\" row my-2\">\n"
                    + "                    <div class=\"col-12\">\n"
                    + "                        \n"
                    + "                        <div class=\"row my-3\">\n"
                    + "                            <div class=\"col-sm-4  \">\n"
                    + "                                <img class=\"img-fluid mt-5 \" src=\"files\\images\\UserImg\\" + loginUser.getPic() + " \">\n"
                    + "                                <input type='file' class='form-control' name=\"user_pic\">                "
                    + "                            </div>\n"
                    + "                            <div class=\" col-sm-8\">\n"
                    + "                                <input type=\"hidden\" name=\"user\" value=\"saveEditUser\">\n"
                    + "                                <input type=\"hidden\" name=\"aType\" value=\"formEdit\">\n"
                    + "                                <input type=\"hidden\" name=\"oldImgName\" value=\"" + loginUser.getPic() + "\">\n"
                    + "                                <input type=\"hidden\" name=\"Agent_id\" value=\"" + loginUser.getId() + "\">\n"
                    + "                                <input class='form-control my-2' pattern=\"[A-Za-z ]{3,}\" name='user_name'  value='" + loginUser.getName() + "' >\n"
                    + "                                <textarea rows='2' name='user_location' class=\"my-2 form-control text-muted pr-md-5\">" + loginUser.getAddress() + "</textarea>\n" + ""
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>ID:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input disabled  class='form-control' value='" + loginUser.getId() + "' /> </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Phone:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input required class='form-control' pattern=\"[0-9]{10}\" name=\"user_phon\"  value='" + loginUser.getPhone() + "'> </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Mobile:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input class='form-control'  name=\"user_mobile\" value='" + loginUser.getMobile() + "'> </div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Email:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input name='user_email' type='email' required class='form-control' value='" + loginUser.getEmail() + "'></div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Password:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input name='user_password' type='text' required class='form-control' pattern=\"[0-9a-zA-z#@$%^&*]{5,}\" value='" +HelperClasses.PasswordEncodeDecode.getDecodeString(loginUser.getPassword())+ "'></div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>District:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input name='district' type='text' required class='form-control' value='" + loginUser.getDistrict() + "'></div>\n"
                    + "                                </div>\n"
                    + "                                <div class=\"row my-1\">\n"
                    + "                                    <div class=\"col-5\"><b>Tal:</b></div>\n"
                    + "                                    <div class=\"col-6 quick-data text-muted\"><input name='tal' type='text' required class='form-control' value='" + loginUser.getTal() + "'></div>\n"
                    + "                                </div>\n"
                    + "                              \n"
                    + "                               \n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        \n"
                    + "                    </div>\n"
                    + "                 "
                    + "                   \n"
                    + "                </div> "
                    + "<div class='text-center'>"
                    + " <button type='submit' class='btn btn-warning'>Save Changes</button> "
                    + "</div> "
                    + "</form>";
        } else if (formType.equalsIgnoreCase("saveEditUser")) {
            Random rand = new Random();
            int number = rand.nextInt();
            String aName = req.getParameter("user_name");
            String aEmail = req.getParameter("user_email");
            String aAddr = (String) req.getParameter("user_location");
            String aPhone = req.getParameter("user_phon");
            String aMobile = req.getParameter("user_mobile");
            String password = req.getParameter("user_password");
            String district = req.getParameter("district");
            String tal = req.getParameter("tal");
            //Encrypt the password
            String encPass=HelperClasses.PasswordEncodeDecode.getEncodedString(password);
            //atype confirem the form is new or old (add or update)
            Part part = req.getPart("user_pic");
            String picName = "user-" + number + ".jpg";

            loginUser.setName(aName.trim());
            loginUser.setAddress(aAddr.trim());
            loginUser.setEmail(aEmail.trim());
            loginUser.setMobile(aMobile.trim());
            loginUser.setPhone(aPhone.trim());
            loginUser.setDistrict(district.trim());
            loginUser.setPassword(encPass);
            loginUser.setTal(tal.trim());

            if (part.getSize() != 0) {
                //agent New Image is selected
                loginUser.setPic(picName);//so change the image name in database
                //delete the old image in to folder
                File file = new File(path + File.separator + req.getParameter("oldImgName"));
                file.delete();
            }
            //UpadateData take the one list to update
            List<User> uList = new ArrayList<User>();
            uList.add(loginUser);

            if (EntityHelper.updateData(uList)) {
                data = "0";
            } else {
                data = "-1";
            }
            if (part.getSize() != 0) {
                String SavePath = path + File.separator + picName;
                Helper.saveTheImageToFolder(SavePath, part);
            }
        } else if (formType.equalsIgnoreCase("deleteUser")) {

            File file = new File(path + File.separator + loginUser.getPic());
            file.delete();
            List<User> lList = new ArrayList<User>();
            lList.add(loginUser);
            EntityHelper.deleteData(lList);
            session.removeAttribute("loginUser");
            session.setAttribute("logoutAction", "deleteAccount");
        } else if (formType.equalsIgnoreCase("Apply")){
            
            String age=req.getParameter("age");
            Calendar cal=Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            try{
            String arr[]=age.split("-");
            System.out.println("Year : "+year+" User Year : "+arr[2]);
          
            System.out.println("Date is : "+age);
            int conditionYear=year-10;//condition for the User age limit
            if( !(Integer.parseInt(arr[2])<=conditionYear) ){
              System.out.println("Invalid date");
                out.print("Invalid Date");
                return;
            }
            Date birthDate=new Date();
            birthDate.setDate(Integer.parseInt(arr[0]));
            birthDate.setMonth(Integer.parseInt(arr[1])-1);
            birthDate.setYear((Integer.parseInt(arr[2]))-1900);
            
            String reason=req.getParameter("reason");
            String gender=req.getParameter("gender");
            String type=req.getParameter("type");
            int month=Integer.parseInt(req.getParameter("months"));
            List<House> house = EntityHelper.getByQuery("from House where id=" + Integer.parseInt(req.getParameter("aId")));
            
            if (loginUser == null) {
            out.print("null");
            session.setAttribute("logoutAction", "invalid");//this sesion for wrong attempt of userPage
            return;
            }
            List<Tenants> tenant=EntityHelper.getByQuery("from Tenants  where states='done' and tid="+loginUser.getId());
            if(tenant.size()>=tenantHouseRentLimit){
            data="Alredy exists";
            } else if(loginUser.getId()== house.get(0).getHouseOwner().getOid()){
            data="wrong user";
            }else if(age.equals("") || type.equalsIgnoreCase("null") || gender.equalsIgnoreCase("null")){
            data="invlid info";
            }else{
            Tenants t = new Tenants();
            t.setName(loginUser.getName());
            t.setBirthDate(birthDate);
            t.setReason(reason);
            t.setTenancyTime(month);
            t.setGender(gender);
            t.setType(type);
            t.setHouse(house.get(0));
            t.setTid(loginUser.getId());

            Date startDate=new Date();
            t.setStartdate(startDate);
            t.setStates("Apply");
            
            //for sent the email on accont(owner account); 
            List<User> user=EntityHelper.getByQuery("from User where id="+house.get(0).getHouseOwner().getOid());
            String to=user.get(0).getEmail();
            String from="rkpro2021@gmail.com";
            String subject="Tenant Found";
            String message="<b>Dear</b> "+user.get(0).getName()+", <br> On your property "+house.get(0).getName()+" apply by "+loginUser.getName()+" as a new Tenant . please check it your account and Confirm tenant !!";
            EmailManager.sentEmail(from, to, subject, message,false,null);
            
            house.get(0).setTenants(t);
            EntityHelper.updateData(house);
            data = "done";
            }
            }catch(Exception e){
                e.printStackTrace();
                data="Incorect";
            }
        }else if(formType.equalsIgnoreCase("tenatsDetailes")){
            List<User> userList=EntityHelper.getByQuery("from User where id="+Integer.parseInt(req.getParameter("userId")));
            List<Tenants> tenantList=EntityHelper.getByQuery("from Tenants where id="+Integer.parseInt(req.getParameter("TenantId")));
            User user=userList.get(0);
            Tenants tenant=tenantList.get(0);
            House house=tenant.getHouse();
            List<User> ownerUser=EntityHelper.getByQuery("from User where id="+house.getHouseOwner().getOid());
          
            data+="<div class='hide2 OwnerInfo'>"
                    + "<div class='row alert-success my-4 mx-2'>"
                   
                    + "     <div class='col-12 mt-2 col-sm-7'>"
                    + "             <div class='row'>"
                    + "                  <div class='col-4 pt-3'>"
                    + "                     <img class='img-fluid ownerImg' alt='files/images/UserImg/"+ ownerUser.get(0).getPic() +"' src='files/images/UserImg/"+ownerUser.get(0).getPic()+"'>"
                    + "                  </div>"
                    + "                  <div class='col-8'>"
                    + "                      <h4 class='mb-0 pb-0'>"+ownerUser.get(0).getName()+"</h4>"
                    + "                      <small class='mt-0 pt-0 text-primary'>self property</small>"
                    + "                      <h6 class='text-muted'>Owner Id : "+ownerUser.get(0).getId()+"</h6>"
                    + "                      <small class='text-justify text-muted '>"+ownerUser.get(0).getAddress()+"</small>"
                    + "                  </div>"
                    + "             </div>"
                    + "     </div>" 
                    + "     <div class='col-12 mt-4 col-sm-5'>"
                    + "           <div class='row'>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-dark'><b>Phone : </b></span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-primary'>"+ownerUser.get(0).getPhone()+"</span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-dark'><b>Email : </b></span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-primary'>"+ownerUser.get(0).getEmail()+"</span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-dark'><b>House Name : </b></span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-primary'>"+house.getName()+"</span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-dark'><b>House ID : </b></span>"
                    + "               </div>"
                    + "               <div class='col-6'>"
                    + "                     <span class='text-primary'>"+house.getId()+"</span>"
                    + "               </div>"
                    + "           </div>"
                    + "     </div>" 
                    + "</div>"
                    + "</div>"
                    + "<div style='height:3px; width:76%' class='bg-success'></div>"
                    + "<div class=\"mt-5 row\">\n" +
"                <div class=\"text-center col-12 col-sm-5\">\n" +
"                    <img class=\"img-fluid\" src=\"files/images/UserImg/"+user.getPic()+"\" style=\"max-height: 300px;max-width: auto\">\n" +
"                    <p class=\"text-muted mt-5 p-2 text-justify\"> Lorem ipsum dolor sit, amet consectetur adipisicing elit. Eos tempore praesentium neque voluptatem voluptate modi? Labore provident ex voluptatum deserunt.\n" +
"                   Labore provident ex voluptatum deserunt.\n" +
"                    </p>\n" +
"                    <div class=\"text-muted text-right\"><small>-"+user.getName()+"</small></div>\n" +
"                </div>\n" +
"                \n" +
"                <div class=\"col-12 col-sm-7 pl-lg-5\">\n" +
"                    <h4 class=\"text-muted pb-0 mb-0\">"+user.getName()+"</h4>\n" +
"                    <small class=\"text-primary pt-0\">"+user.getDistrict()+" , "+user.getTal()+"</small>\n" +
"                    <div class=\"row mt-3\">\n" +
"                        <div class=\"col-8 col-md-3\">Birth Date : </div>\n";
            
                         SimpleDateFormat sdf=new SimpleDateFormat("E dd-MM-yyyy");
                         String birthDate=sdf.format(tenant.getBirthDate());
                         data+=
"                        <div class=\"col-6 col-md-4 text-muted\">"+birthDate+"</div>\n" +
"                        <div class=\"col-6 col-md-2\">Type : </div>\n" +
"                        <div class=\"col-6 col-md-3 text-muted\">"+tenant.getType()+"</div>\n" +
"                        <div class=\"col-6 col-md-3\">Gender : </div>\n" +
"                        <div class=\"col-6 col-md-4 text-muted\">"+tenant.getGender()+"</div>\n" +
"                        <div class=\"col-6 col-md-2\">Month : </div>\n" +
"                        <div class=\"col-6 col-md-3 text-muted\">"+tenant.getTenancyTime()+" Month</div>\n" +
"                        \n" +
"                        <p class=\"text-muted my-3 col-12\">"+user.getAddress()+"</p>\n" +
"                        \n" +
"                        <h5 class=\"mt-3 col-12 text-primary merienda text-right\">Contact</h5>\n" +
"                        <div class=\"col-6 mt-1\">Mobile : </div>\n" +
"                        <div class=\"col-6  text-right text-muted\">- "+user.getMobile()+"</div>\n" +
"                        <div class=\"col-6 mt-1\">Phone :</div>\n" +
"                        <div class=\"col-6  text-right text-muted\">+91 "+user.getPhone()+"</div>\n" +
"                        <div class=\"col-6 mt-1\">Email :</div>\n" +
"                        <div class=\"col-6  text-right text-muted\" >"+user.getEmail()+"</div>\n" +
"                        \n" +
"                    </div>\n" +
"                    \n"+
                         
"                     <div class=\"col-12 mt-5 text-right\">\n";
                     
               if(ownerUser.get(0).getId() == loginUser.getId()) {
                    if(tenant.getStates().equalsIgnoreCase("Apply") ){
                       data+="<span class=\"p-2 bg-success text-light  allowToTenant\" data-id='"+tenant.getId()+"'>Allow</span>\n";
                       data+="<span class=\"p-2 bg-dark text-light notAllowToTenant \"data-id='"+tenant.getId()+"' data-states='dismiss'  >Not Allow</span>\n" ;
                    }else if(tenant.getStates().equalsIgnoreCase("done")){
                        data+="<span class=\"p-2 bg-dark text-light notAllowToTenant \"data-id='"+tenant.getId()+"' data-states='privious' >Cancel Agriment</span>\n" ;
                    }
                       
               }
                       data+="<span class=\"p-2 bg-primary text-light ShoHideOwnerInfo \" >Show Owner</span>\n" ;
                       if(loginUser.getType().equalsIgnoreCase("admin") && !tenant.isCheckByAdmin() && (tenant.getStates().equalsIgnoreCase("done")) ){
                            data+="<span class=\"p-2 bg-secondary text-light \" onclick='CheckByAdmin("+tenant.getId()+")' >Is Check</span>\n" ;
                           
                       }
                    data+="  </div>\n" +
"                </div>\n" +       
"            </div>";
 
                         
                        data+="<div class=' mt-4 mb-5 pb-4'>"
                                + "<h5 class='text-muted'> Tenant Old Home Record </h5>"
                               + "<div class='mt-3' id='accordion'>";
                               List<Tenants> tList=EntityHelper.getByQuery("from Tenants where states='privious' and tid="+Integer.parseInt(req.getParameter("userId")));
                               int i=0;
                               for(Tenants t:tList){
                                   i++;
                                   List<User> uList=EntityHelper.getByQuery("from User where id="+t.getHouse().getHouseOwner().getOid());
                                   data+= "  <h3>"+i+" Home Details</h3>"
                               + "  <div class='row'>"
                                           /*   + "<div class='col-12 col-sm-2'>"
                                           + "<img class='img-fluid' src='files/images/HouseImg/"+t.getHouse().getPhotos().get(0).getName() +"'>"
                                           + "</div>"*/
                                           + "<div class='col-12 col-sm-8'>"
                                           +     "<div class='row'><div class='text-dark col-sm-5'>Home name : </div><div class='col-sm-7 text-muted'>"+ t.getHouse().getName()+"</div></div>"
                                           +     "<div class='row'><div class='text-dark col-sm-5'>Owner name : </div><div class='col-sm-7 text-muted'>"+ t.getHouse().getHouseOwner().getName()+"</div></div>"
                                           +     "<div class='row'><div class='text-dark col-sm-5'>Contact : </div><div class='col-sm-7 text-muted'>"+  uList.get(0).getPhone()+"</div></div>"
                                           +     "<div class='row'><div class='text-dark col-sm-5'>Email : </div><div class='col-sm-7 text-muted'>"+  uList.get(0).getEmail()+"</div></div>"
                                          
                                           + "</div>"
                                           + "<div class='col-12 col-sm-4'></div>"
                               + "  </div>"; 
                               }
                               data+= "</div>"
                                + "</div>"
                               + "<script>$('#accordion').accordion();</script>";  
                         
                         
            
        }else if(formType.equalsIgnoreCase("AllowToTenant")){
            List<Tenants> tenantList=EntityHelper.getByQuery("from Tenants where states='Apply' and id="+Integer.parseInt(req.getParameter("tId")));
            Tenants tenant=tenantList.get(0);
            int id=tenant.getTid();
            Calendar cal = Calendar.getInstance();
           // String date = "" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            tenant.setStates("done");
            Date startDate=new Date();
            tenant.setStartdate(startDate);
            
          
            Date endDate= Date.from((LocalDate.now().plusMonths(tenant.getTenancyTime())).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
           
            //String endDate = "" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            tenant.setEndDate(endDate);
         
            
            //bill
           
          
            List<Bill> bills=new ArrayList<Bill>();
            for(int i=0;i<tenant.getTenancyTime();i++){
         
            Bill bill=new Bill();
            Date sDate= Date.from((LocalDate.now().plusMonths(i)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            bill.setStartDate(sDate);
            Date eDate= Date.from((LocalDate.now().plusMonths(i+1)).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
            bill.setEndDate(eDate);
            bill.setId("Bill_"+Helper.getRandomeNumber(9999));
            
            bill.setStates("unpaid");
            bill.setSubmitedDate(null);
            bill.setHouseId(tenant.getHouse().getId());
            bill.setTenant(tenant);
            bill.setAmount(tenant.getHouse().getRent());
            bills.add(bill);
            }
            
            tenant.setBills(bills);
            EntityHelper.updateData(tenantList);
           
            
            List<Tenants> tList=EntityHelper.getByQuery("from Tenants where states='done' and tid="+id);
            //System.out.println(id+" Size Of done "+tList.size());
            if(tList.size()>=tenantHouseRentLimit){
                List<Tenants> tenantList2=EntityHelper.getByQuery("from Tenants where states='Apply' and tid="+id);
                for(Tenants t:tenantList2){
                House house=t.getHouse() ;
                house.setTenants(null);
                List<House> list=new ArrayList<House>();
                list.add(house);
                t.setStates("dismiss");
                EntityHelper.updateData(list);
                
                }
                EntityHelper.updateData(tenantList2);
            }
            
            data="done";
        }else if(formType.equalsIgnoreCase("notAllowToTenant")){
            List<Tenants> tenantList=EntityHelper.getByQuery("from Tenants where id="+Integer.parseInt(req.getParameter("tId")));
            House house=tenantList.get(0).getHouse();
            house.setTenants(null);
            List<House> list=new ArrayList<House>();
            list.add(house);
            tenantList.get(0).setStates(req.getParameter("tStates"));
            EntityHelper.updateData(list);
            EntityHelper.updateData(tenantList);
            data="done";
        }else if(formType.equalsIgnoreCase("TenantHome")){
            //user applied houses
            List<Tenants> tenantList=EntityHelper.getByQuery("from Tenants where tid="+loginUser.getId());
            data+="<h4 class='col-12'><span class='merienda-bold'>User Applied Propertys</span><span onclick='user()' class='close'>X</span></h4>";
            for(Tenants tenant:tenantList){
                House house=tenant.getHouse();
                    data += 
                            "<div class=\" row my-4 "+tenant.getId()+"\">\n"
                            + "                    <div class=\"col-12  \">\n"
                            + "                        \n"
                            + "                        <div class=\"row my-3  \">\n"
                            + "                            <div class=\"d-none d-sm-inline col-sm-4  \">\n"
                            + "                                <img class=\"img-fluid px-3\" src=\"files\\images\\HouseImg\\" + house.getPhotos().get(0).getName() + " \" style=\"max-height:300px ;width:350px; \">\n"
                            + "                            </div>\n"
                            + "                            <div class=\" col-sm-8\">\n"
                            + "                                <h5 class='capitalize'><b>" + house.getName() + "</b></h5>\n"
                            + "                                <p class=\"text-muted pr-md-5 text-justify \">" + house.getDiscription() + "</p>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>House ID:</b></div>\n"
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + house.getId() + "</div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>Owner name:</b></div>\n"
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + house.getHouseOwner().getName() + "</div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>Start Date</b></div>\n"
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + tenant.getStartdate() + "</div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>End Date</b></div>\n"
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + tenant.getEndDate() + "</div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>Owner Responce</b></div>\n";
                                                                       if(tenant.getStates().equalsIgnoreCase("Apply")){                         
                                                                            data+="<div class='col-6 quick-data text-right text-tomato'>Please wait for owner responce</div>";
                                                                       }else if(tenant.getStates().equalsIgnoreCase("dismiss")){
                                                                            data+="<div class='col-6 quick-data text-right text-danger'>Process Cancel </div>";
                                                                       }else if(tenant.getStates().equalsIgnoreCase("done")){
                                                                             data+="<div class='col-6 quick-data text-right text-success'>Done</div>";                                                                  
                                                                       }else if(tenant.getStates().equalsIgnoreCase("privious")){
                                                                             data+="<div class='col-6 quick-data text-right text-dark'>Privious Home</div>";                                                                  
                                                                          
                                                                       }
                         
                                                            data+= "</div>"
                                                       + "</div>\n"
                            + "                     </div>\n";
                                                                            
                    data += "</div>\n"
                                    + " <div class=\"text-center alert-secondary agent-bar  col-12\">\n";
                    
                            if(tenant.getStates().equalsIgnoreCase("dismiss")){
                                data += "<span class=\"text-muted mx-3 pointer removeTenant \" data-id='"+tenant.getId()+"' >Remove</span>\n";
                            }else if(tenant.getStates().equalsIgnoreCase("Apply")){
                                data += "<span class=\"text-muted mx-3 pointer notAllowToTenant \" data-states='dismiss' data-id='"+tenant.getId()+"' >Cancel</span>\n";
                            }else if(tenant.getStates().equalsIgnoreCase("done")){
                                data += "<span class=\"text-muted mx-3 pointer  \" ><a  class='a text-muted paymentBtn' data-tenant='"+tenant.getId()+"' data-house='"+house.getId()+"' > Payment </a></span>\n";
                                data += "<span class=\"text-muted mx-3 pointer  \" ><a class='a text-muted' href='singleProperty.jsp?id=" + house.getId() + "' >View</a></span>\n";
                            }else if(tenant.getStates().equalsIgnoreCase("privious")){
                                data += "<span class=\"text-muted mx-3 pointer  \" ><a class='a text-muted' href='singleProperty.jsp?id=" + house.getId() + "' >Apply Again</a></span>\n";
                               
                            }                    
                    data += "</div>\n"
                            + "</div>"
                            + "</div>\n";
                
            }
            
        }else if(formType.equalsIgnoreCase("RemoveTenantAppliedHouse")){
            List<Tenants> tenant=EntityHelper.getByQuery("from Tenants where states='dismiss' and id="+Integer.parseInt(req.getParameter("tenantId")));
            EntityHelper.deleteData(tenant);
            data="done";
        }else if(formType.equalsIgnoreCase("loginOrNoLoginCheck")){
            if(loginUser==null){
                data=null;
                session.setAttribute("logoutAction","propertyApply");
            }
        }/*else if(formType.equalsIgnoreCase("CancelTanantHouse")){
        List<Tenants> tenant=EntityHelper.getByQuery("from Tenants where states='done' and id="+Integer.parseInt(req.getParameter("Tid")));
        tenant.get(0).setStates("privious");
        EntityHelper.updateData(tenant);
        data="done";
        }*/
        out.print(data);
    }//methode body
}//class body
