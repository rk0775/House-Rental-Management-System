/*
 this servlet manage the HouseData(admin)
 */
package adminServlet;

import HelperClasses.EntityHelper;
import HelperClasses.Helper;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import entities.House;
import entities.HouseOwner;
import entities.HousePhotos;
import entities.Payment;
import entities.Tenants;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.json.JSONObject;

@MultipartConfig
public class HouseServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, NullPointerException, ServletException {
       // String path ="D:\\document\\softwares\\netbeans\\netbeansProject\\HouseRentProject\\web\\files\\images\\HouseImg";
        String path="D:\\TODO\\web\\files\\images\\HouseImg";
        System.out.println("Path ; "+path);
        PrintWriter out = res.getWriter();
        String formType = req.getParameter("house");
        System.out.println("FormType : " + formType);
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String data = "";
        int limit = 4, pageNo = 1;

        if (formType.equalsIgnoreCase("full")) {
            //property(house) page
            data = " <div class=\"row mt-3\">\n"
                    + "                    <div class=\"col-11 AddProprtyNew col-sm-6\">\n"
                    + "                         <h4 class=\"text-muted pointer \" id=\"addNewProperty\"><b>Add New Property</b></h4>\n"
                    + "                    </div>"
                    + "                     <div class='col-11  hide2 DHT  col-sm-6'><h4 class='text-muted'><b>Our Propertys</b></h4></div>                                "
                    + "                    <div class=\"col-11 col-sm-6\">\n"
                    + "                            <input type='text' class='form-control HouseSearch'  placeholder='Search The Property...'>\n"
                    + "                               <div class='list-group SearchArea my-1'></div> "
                    + "                    </div>\n"
                    + "                </div>";
            int offset = 0;
            List<HouseOwner> ownerList;
            if(req.getParameter("OwnerHouse")!=null){//user related houses
               ownerList=EntityHelper.getByQuery("from HouseOwner where oid="+loginUser.getId());
            }else
            if (req.getParameter("searchHouseId") == null) {//all data fetch
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
                offset = (pageNo - 1) * limit;
                ownerList = EntityHelper.getDataForpagination("from HouseOwner", offset, limit);
            } else {//only serach data fetct
                ownerList = EntityHelper.getByQuery("from HouseOwner where id=" + Integer.parseInt(req.getParameter("searchHouseId")));
            }
             if(ownerList.size()==0){
                data+="<div class='col-12 text-center text-muted my-5 py-3'><h5 class=''>No property added by you!</h5></div>";
                out.println(data);
                return;
            }
            for (HouseOwner owner : ownerList) {
                List<House> houseList = owner.getHouse();
                for (House house : houseList) {
                     Tenants tenant=house.getTenants();
                    data += ""
                            + "<div class=\" row my-4\">\n"
                            + "                    <div class=\"col-12\">\n"
                            + "                        \n"
                            + "                        <div class=\"row my-3\">\n"
                            + "                            <div class=\"d-none d-sm-inline col-sm-4  \">\n"
                            + "                                <img class=\"img-fluid px-2 \" src=\"files\\images\\HouseImg\\" + house.getPhotos().get(0).getName() + " \" style=\"max-height:320px ;width:360px; \">\n"
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
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + owner.getName() + "</div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>District:</b></div>\n"
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + house.getDistrict() + "</div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"row my-1\">\n"
                            + "                                    <div class=\"col-5\"><b>Date</b></div>\n"
                            + "                                    <div class=\"col-6 quick-data text-muted\">" + house.getDate() + "</div>\n"
                            + "                                </div>\n";
                                                               if(tenant!=null){
                                                                   
                                                                    data += "<div class=\"row my-1\">\n"
        + "                                                                 <div class=\"col-5\"><b>Tenant</b></div>\n"
        + "                                                                     <div class=\"col-6 quick-data text-muted\">" +tenant.getName() + "</div>\n"
        + "                                                                 </div>\n";
                                                                if(tenant.getStates().equalsIgnoreCase("Apply")){
                                                                    data+="<div class=\"row my-1\">\n"
        + "                                                                     <div class=\"col-5\"><b></b></div>\n"
        + "                                                                     <div class=\"col-6 quick-data text-muted text-danger \">New Tenant</div>\n"
        + "                                                                 </div>\n";
                                                                           
                                                                    }
                                                               }
                            
                            
                         
                            data+="                            </div>\n"
                            + "                        </div>\n"
                            + "                        \n"
                            + " <div class=\"text-center alert-secondary agent-bar  col-12\">\n";
                   
                    if (owner.getOid() == loginUser.getId()) {
                        if(req.getParameter("OwnerHouse")!=null){
                             data += "<span class=\"text-muted mx-3 pointer  \" onclick='HouseEdit(" + owner.getId() + ",\"UP\")' >Edit</span>\n";
                           
                        }else{
                            data += "<span class=\"text-muted mx-3 pointer  \" onclick='HouseEdit(" + owner.getId() + ",\"none\")' >Edit</span>\n";
                        }
                        data += "<span class=\"text-muted mx-3 pointer  \" onclick='HouseDelete(" + owner.getId() + ")' >Remove</span>\n";
                        data += "<span class=\"text-muted mx-3 pointer  \" ><a class='a text-muted' href='singleProperty.jsp?id=" + house.getId() + "' >View</a></span>\n";
                        
                    }else{
                        data += "<span class=\"text-muted mx-3 pointer  \" ><a class='a text-muted' href='singleProperty.jsp?id=" + house.getId() + "' >View</a></span>\n";   
                    }
                    if(tenant!=null){
                        if(tenant.getStates().equalsIgnoreCase("Apply") || tenant.getStates().equalsIgnoreCase("done")){
                            if(req.getParameter("OwnerHouse")!=null){
                                data += "<span class=\"text-muted mx-3 pointer  \" ><a class='a text-muted' id='tenant' data-x='UP' data-owner='"+owner.getOid()+"' data-h='" +tenant.getTid()+ "' data-t='" +tenant.getId()+ "' >Tenant</a></span>\n";
                            }else{
                                data += "<span class=\"text-muted mx-3 pointer  \" ><a class='a text-muted' id='tenant' data-x='none' data-owner='"+owner.getOid()+"' data-h='" +tenant.getTid()+ "' data-t='" +tenant.getId()+ "' >Tenant</a></span>\n";
                            }
                        }
                    }
                    data += "</div>\n"
                            + "                       </div>"
                            + "                  </div>\n";
                }
            }
            //for pagination
            if (ownerList.size() >= limit || offset != 0) {
                data += "<ul class='pagination justify-content-end px-4'>";
                int n = EntityHelper.getByQuery("from House").size() / limit;
                System.out.println("as :  " + n);
                if (pageNo != 1) {
                    data += "<li onclick=\" house(housePageNo='" + (pageNo - 1) + "')\" class=\"page-item\" ><a class=\"page-link\">&laquo;</a></li>\n";
                }

                for (int i = 1; i <= n + 1; i++) {
                    if (i == pageNo) {
                        data += "<li class=\"page-item active\" onclick=\" house(housePageNo='" + i + "') \" ><a class=\"page-link\">" + i + "</a></li>\n";
                    } else {
                        data += "<li class=\"page-item \" onclick=\" house(housePageNo='" + i + "') \" ><a class=\"page-link\">" + i + "</a></li>\n";
                    }
                }
                if (pageNo != n + 1) {
                    data += "<li onclick=\" house(housePageNo='" + (pageNo + 1) + "')\" class=\"page-item\" ><a class=\"page-link\">&raquo;</a></li>\n";
                }

                data += "</ul>";

            }

        }else if(formType.equalsIgnoreCase("payment")) {
            
            int HousePayment=2;
            try{
                  RazorpayClient rz=new RazorpayClient("rzp_test","Mc");
                  JSONObject ob=new JSONObject();
                  ob.put("amount",HousePayment*100);
                  ob.put("currency","INR");
                  ob.put("receipt","TXN_"+Helper.getRandomeNumber(9999));
                  //create a order
                  Order order=rz.Orders.create(ob);
                  System.out.println(order);
                  data=order.toString();
                  
            }catch(Exception E){
                data="error";
                E.printStackTrace();
            }
          
            
        } else if (formType.equalsIgnoreCase("addProperty")) { //property(house) form is here 
            
     
       
            data = " <div class=\"card card-body bordered my-3\">\n"
                    + "                    <h4 class=\"text-muted \"><span class='merienda-bold'>Fill The Home Form</span> <span class=\"text-right close Xbtn \" onclick='house()' >X</span>  </h4>\n"
                    + "                <form  class=\"\" id=\"addNewPropertyForm\" enctype=\"multipart/form-data\">\n"
                    + "                    <div class=\"row my-3\">\n"
                    + "                        <div class=\" col-12 col-sm-7 col-md-8\">\n"
                    + "                         <input type='hidden' name='house' value='propertyDataForm'> "
                    + "                         <input type='hidden' name='houseId' id='houseId' value='"+Helper.getRandomeNumber(9999)+"'> "
                    + "                         <input type='hidden' name='user'  id='userType' value='"+loginUser.getType()+"'> "
                    + "                        <input name='House_name' required type=\"text\" placeholder=\"House Name \" class=\" House_name_id form-control\"/>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-5 col-md-4\">\n"
                    + "                    <select  name='Rent_type' class=\"form-control  \">\n"
                    + "                        <option value='rent'>For Rent</option>\n"
                    + "                        <option value='sale'>For Sale</option>        \n"
                    + "                    </select>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                    \n"
                    + "                    <div class=\"row my-3\">\n"
                   + "                        <div class=\" col-12 col-sm-7 col-md-8\">\n"
                    + "                            \n"
                    + "                            <div class=\"dropdown\">\n"
                    + "                                <button type=\"button\" class=\"form-control text-left \" data-toggle=\"dropdown\">Amenities</button>\n"
                    + "                                <div  class=\"dropdown-menu form-control\">\n"
                    + "                                    <div class=\"row p-3\">\n"
                    + "                                     <input type='hidden' class='ani' name='animetie'>"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Balcony\"><span class=\"px-2\">Balcony</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Outdoor kitchen\"><span class=\"px-2\">Outdoor Kitchen</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Cable TV\"><span class=\"px-2\">Cable TV</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Deck\"><span class=\"px-2\">Deck</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Tennis Courts\"><span class=\"px-2\">Tennis Courts</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Internet\"><span class=\"px-2\">Internet</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Parking\"><span class=\"px-2\">Parking</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Sun Room\"><span class=\"px-2\">Sun Room</span></div>\n"
                    + "                                        <div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Concrete Flooring\"><span class=\"px-2\">Concrete Flooring</span></div>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "                            </div>\n"
                    + "                            \n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-5 col-md-4\">\n"
                    + "                            <input required pattern=\"[0-9]{1,}\"  name='House_price' class=\"form-control \" placeholder=\"Open House Price\">\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "                    \n"
                  
                    + "                    \n"
                    + "                    <input name='House_pic' type=\"file\" class=\"form-control\" multiple='multiple' />\n"
                    + "                    <textarea name='Property_location' class=\"form-control my-3\" rows=\"2\" required placeholder=\"Enter Detailed Home Address\"></textarea>\n"
                    + "                    \n"
                    + "                    <div class=\"row\">\n"
                    + "                        <div class=\"col-12 col-sm-3\">\n"
                    + "                            <select name='Beds' class=\"form-control\">\n"
                    + "                                <option value='0'>Bedrooms In Home</option>\n"
                    + "                                <option value='1'>1 Beds</option>\n"
                    + "                                <option value='2'>2 Beds</option>\n"
                    + "                                <option value='3'>3 Beds</option>\n"
                    + "                                <option value='>3'>More than 3 Beds</option>\n"
                    + "                            </select>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-3\">\n"
                    + "                            <select name='Bath' class=\"form-control\">\n"
                    + "                                <option value='0'>Bathrooms In Home</option>\n"
                    + "                                <option value='1'>1 Bath</option>\n"
                    + "                                <option value='2'>2 Bath</option>\n"
                    + "                                <option value='3'>3 Bath</option>\n"
                    + "                                <option value='>3'>More than 3 Bath</option>\n"
                    + "                            </select>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-3\">\n"
                    + "                            <select name='Garage' class=\"form-control\">\n"
                    + "                                <option value='0'>Garage In Home</option>\n"
                    + "                                <option value='1'>1 Garage</option>\n"
                    + "                                <option value='2'>2 Garage</option>\n"
                    + "                                <option value='3'>3 Garage</option>\n"
                    + "                                <option value='>3'>More than 3 Garage</option>\n"
                    + "                            </select>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-3\">"
                    + "                                <select name='district' class=\"district form-control\">\n"
                    + "                                <option value='0'>District</option>\n"
                    + "                                <option value='nashik'>Nashik</option>\n"
                    + "                                <option value='pune'>Pune</option>\n"
                    + "                                <option value='goa'>Goa</option>\n"
                    + "                                <option value='delhi'>Delhi</option>\n"
                    + "                                <option value='hedrabad'>Hedrabad</option>\n"
                    + "                            </select>\n" + "  "
                    + " </div>\n"
                    + "                    </div>\n"
                    + "                    \n"
                    + "                    <textarea required name='Property_discription' rows=\"4\" class=\"form-control my-3\" placeholder=\"Property Discription\"></textarea>\n"
                    + "                    <div class=\"text-right\">\n"
                    + "                        <button type=\"reset\" class=\"btn btn-warning btn-sm\">Reset</button>\n"
                    + "                        <button type=\"submit\"  class=\"btn btn-primary btn-sm\">Submit & Pay</button>\n"
                    + "                    </div>\n"
                    + "                </form>\n"
                    + "               </div>";
        }else if(formType.equalsIgnoreCase("savePaymentInfoToDatabase")){
            String receipt=req.getParameter("receipt");
            String type=req.getParameter("type");
            //System.out.println("receipt : "+receipt+" house no : "+houseNo+" type : "+type+" amount :"+amountString);
            int houseId=Integer.parseInt(req.getParameter("houseNo"));
            float amount=Float.parseFloat(req.getParameter("amount"));
            Payment payment=new Payment(receipt,houseId,type,amount/100,new Date(),loginUser);
            List<Payment> paymentList=new ArrayList<Payment>();
            paymentList.add(payment);
            EntityHelper.saveNewData(paymentList);
            
        } else if (formType.equalsIgnoreCase("propertyDataForm")){
            try{
            
            int Id=Integer.parseInt(req.getParameter("houseId"));
            String hName = (String) req.getParameter("House_name");
            String rentType = (String) req.getParameter("Rent_type");
           // String oName = (String) req.getParameter("Owner_name");
            
            String hPrice = (String) req.getParameter("House_price");
            String aminities = (String) req.getParameter("animetie");
           // String agentId = (String) req.getParameter("Agent_id");
            String bedRoom = (String) req.getParameter("Beds");
            String bathRoom = (String) req.getParameter("Bath");
            String garage = (String) req.getParameter("Garage");
            String district = (String) req.getParameter("district");
            String hDiscription = (String) req.getParameter("Property_discription");
            String hLocation = (String) req.getParameter("Property_location");
            if (district.equals("0")) {
                System.out.print("-1");
                return;
            }
            System.out.println("I am tyr to add the property !!!!!!!!!!!!!!!!!! ");

            File file = new File(path);
                        System.out.println("I am tyr to add the property !!!!!!!!!!!!!!!!!! ");

            if (!file.exists()) {
                file.mkdir();
            }

            Calendar cal = Calendar.getInstance();
            String date = "" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
            List<Part> part = (List) req.getParts();

            HouseOwner owner = new HouseOwner();
            owner.setOid(loginUser.getId());
            owner.setName(loginUser.getName());

            List<House> houseList = new ArrayList<House>();
            House house = new House();
            house.setId(Id);
            house.setName(hName.trim());
            house.setAddress(hLocation.trim());
            house.setRentType(rentType.trim());
            house.setRent(Integer.parseInt(hPrice));
            house.setBathNo(bathRoom);
            house.setBedNo(bedRoom);
            house.setGarageNo(garage);
            house.setDistrict(district.trim());
            house.setDiscription(hDiscription.trim());
            house.setAminities(aminities.trim());
            house.setHouseType("home");
            house.setDate(date);
            house.setHouseOwner(owner);

            //for the saving photo in folder
            List<HousePhotos> photos = new ArrayList<HousePhotos>();
            
            for (Part p : part) {
                if (p.getSize() > 500) {
                   
                    
                    String name = "House-" +Helper.getRandomeNumber(999999) + ".jpg";

                    HousePhotos photosObj = new HousePhotos();
                    photosObj.setHouse(house);
                    photosObj.setName(name);
                    photos.add(photosObj);
                    //save photo to folder
                    Helper.saveTheImageToFolder(path + File.separator + name, p);
                }
            }
            
            System.out.println("fetch : 2");
            house.setPhotos(photos);
            houseList.add(house);
            owner.setHouse(houseList);
          //  EntityHelper.saveNewData(houseList);
                        System.out.println("House : 3");

            List<HouseOwner> oList = new ArrayList<HouseOwner>();
            
            oList.add(owner);
            System.out.println("saved data : "+oList.toString());
            System.out.println("fetch : 4");
            System.out.println("saved data : "+oList);
            EntityHelper.saveNewData(oList);
            System.out.println("fetch : 5");
            data = "0";
            }catch(Exception e){
                System.out.println("!!!!!!!!! Error happen here######### : "+e.getMessage());
                
            }
             

        } else if (formType.equalsIgnoreCase("search")) {
            String key = req.getParameter("searchKey");
            List<House> result = EntityHelper.getByQuery("from House where name like '%" + key.trim() + "%' or id='" + key + "' ");
            System.out.println("Query worked");
            if (result.size() != 0) {
                int topFiveSearch = 0;
                for (House house : result) {
                    HouseOwner owner = house.getHouseOwner();
                    data += "<div class='px-lg-5 t list-group-item gray house-search pointer' data-house='" + owner.getId() + "' >" + house.getName() + "</div>";
                    topFiveSearch++;
                    if (topFiveSearch == 5) {
                        break;
                    }
                }
            } else {
                data += "<a class='text-muted px-4 py-2 gray'><b>Sorry !</b>result not found...</a>";
            }
        } else if (formType.equalsIgnoreCase("edit")) {
            int id = Integer.parseInt(req.getParameter("EditHouseId"));
            List<HouseOwner> ownerl = EntityHelper.getByQuery("from HouseOwner where id=" + id);
            House house = ownerl.get(0).getHouse().get(0);
            String array[] = house.getAminities().split(",");
            data += "<div class=\"container mt-3 mb-5\">\n"
                    + "           <form class='EditHouseForm' enctype=\"multipart/form-data\">\n"
                    + "               <div class=\"row\">\n"
                    + "                    <div class=\"col-12 col-md-5 mt-md-4\">\n"
                    + "                <input type='hidden' name='CoverImg' value='" + house.getPhotos().get(0).getId() + "'>                                     "
                    + "                        <img class=\"img-thumbnail\" src=\"files\\images\\HouseImg" + File.separator + house.getPhotos().get(0).getName() + "\" alt=\"" + path + File.separator + house.getPhotos().get(0).getName() + "\" style=\"max-height: 600px;width:auto\">\n"
                    + "                        <input name='image' class=\"ml-2 mt-2\" type=\"file\">\n"
                    + "                    </div>\n"
                    + "                    <div class=\"col-12 col-md-7 my-md-2 my-4\">\n"
                    + "                          <input type='hidden' name='house' value='editHouse' >"
                    + "                          <input type='hidden' name='houseId' value='" + house.getId() + "' >"
                    + "                        <input class=\"form-control my-input\"  name='House_name'  value=\"" + house.getName() + "\"  placeholder=\"Enter the house name\"/>\n"
                    + "                        <input class=\"form-control  mt-3 my-input\" name='Owner_name' value=\"" + ownerl.get(0).getName() + "\"  placeholder=\"House owner name\"/>\n"
                    + "                        <div class=\"row my-3\">\n"
                    + "                            <div class=\"col-12 col-sm-6 \">\n"
                    + "                                <input class=\"my-input  form-control\" name='Area' value=\" area \" placeholder=\"Area Of House\">\n"
                    + "                            </div>\n"
                    + "                            \n"
                    + "                            <div class=\"col-6\">\n"
                    + "                                <input class=\"form-control my-input\" name='House_price' value=\"" + house.getRent() + "\" placeholder=\"House Price\">\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                        \n"
                    + "                        <div class=\"dropdown\">\n"
                    + "                                <button type=\"button\" class=\"my-input form-control text-left \" data-toggle=\"dropdown\">Amenities</button>\n"
                    + "                                <div  class=\"dropdown-menu form-control\">\n"
                    + "                                    <div class=\"row p-3\">\n"
                    + "                                       <input type='hidden' class='ani2' name='animetie'>\n";
            boolean flag = false;
            flag = Arrays.asList(array).contains("Balcony");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Balcony\"><span class=\"px-2\">Balcony</span></div>\n";
            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Balcony\"><span class=\"px-2\">Balcony</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Outdoor kitchen");
            if (flag) {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Outdoor kitchen\"><span class=\"px-2\">Out Kitchen</span></div>\n";

            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Outdoor kitchen\"><span class=\"px-2\">Outdoor Kitchen</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Cable TV");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Cable TV\"><span class=\"px-2\">Cable TV</span></div>\n";

            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Cable TV\"><span class=\"px-2\">Cable TV</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Deck");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Deck\"><span class=\"px-2\">Deck</span></div>\n";

            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Deck\"><span class=\"px-2\">Deck</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Tennis Courts");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Tennis Courts\"><span class=\"px-2\">Tennis Courts</span></div>\n";
            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Tennis Courts\"><span class=\"px-2\">Tennis Courts</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Internet");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Internet\"><span class=\"px-2\">Internet</span></div>\n";
            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Internet\"><span class=\"px-2\">Internet</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Parking");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Parking\"><span class=\"px-2\">Parking</span></div>\n";
            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Parking\"><span class=\"px-2\">Parking</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Sun Room");
            if (flag) {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Sun Room\"><span class=\"px-2\">Sun Room</span></div>\n";
            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Sun Room\"><span class=\"px-2\">Sun Room</span></div>\n";
            }
            flag = Arrays.asList(array).contains("Concrete Flooring");
            if (flag) {
                data += "<div  class=\"col-6 col-md-4\"> <input type=\"checkbox\"  checked  class=\"animeties\" value=\"Concrete Flooring\"><span class=\"px-2\">C. Flooring</span></div>\n";
            } else {
                data += "<div class=\"col-6 col-md-4\"> <input type=\"checkbox\" class=\"animeties\" value=\"Concrete Flooring\"><span class=\"px-2\">C. Flooring</span></div>\n";
            }

            data += "</div>\n"
                    + "                                </div>\n"
                    + "                        </div>\n"
                    + "                        \n"
                    + "                        <div class=\"row\">\n"
                    + "                       \n"
                    + "                        <div class=\"col-12 col-sm-6 mt-3\">\n"
                    + "                            <input type=\"button\" class=\"form-control my-input editHousePhotos\" value=\"Photos\">\n"
                    + "                        </div>\n"
                    + "                      <div class=\"col-6 mt-3\">\n"
                    + "                                <select name='Rent_type' class=\"form-control my-input \">                      \n"
                    + "                                    <option value='" + house.getRentType() + "'>" + house.getRentType() + "</option>\n"
                    + "                                    <option value='rent'>For Rent</option>\n"
                    + "                                    <option value='sale'>For Sale</option> \n"
                    + "                                </select>\n"
                    + "                            </div>\n"
                    + "                        <div class=\"col-12 col-sm-6 mt-3\">\n"
                    + "                            <select name='Beds' class=\"form-control my-input\">\n"
                    + "                                <option value='" + house.getBedNo() + "'>" + house.getBedNo() + " Beds</option>\n"
                    + "                                <option value='1'>1 Beds</option>\n"
                    + "                                <option value='2'>2 Beds</option>\n"
                    + "                                <option value='3'>3 Beds</option>\n"
                    + "                                <option value='>3'>More than 3 Beds</option>\n"
                    + "                            </select>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-6 mt-3\">\n"
                    + "                            <select name='Bath' class=\"form-control my-input\">\n"
                    + "                                <option value='" + house.getBathNo() + "'>" + house.getBathNo() + " Bath</option>\n"
                    + "                                <option value='1'>1 Bath</option>\n"
                    + "                                <option value='2'>2 Bath</option>\n"
                    + "                                <option value='3'>3 Bath</option>\n"
                    + "                                <option value='>3'>More than 3 Bath</option>\n"
                    + "                            </select>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-6 mt-3\" >\n"
                    + "                            <select name='Garage' class=\"form-control my-input\">\n"
                    + "                                <option value='" + house.getGarageNo() + "'>" + house.getGarageNo() + " Garage</option>\n"
                    + "                                <option value='1'>1 Garage</option>\n"
                    + "                                <option value='2'>2 Garage</option>\n"
                    + "                                <option value='3'>3 Garage</option>\n"
                    + "                                <option value='>3'>More than 3 Garage</option>\n"
                    + "                            </select>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"col-12 col-sm-6 mt-3\">\n"
                    + "                                <select name='district' class=\"district form-control my-input\">\n"
                    + "                                <option value='" + house.getDistrict() + "'>" + house.getDistrict() + "</option>\n"
                    + "                                <option value='nashik'>Nashik</option>\n"
                    + "                                <option value='pune'>Pune</option>\n"
                    + "                                <option value='goa'>Goa</option>\n"
                    + "                                <option value='delhi'>Delhi</option>\n"
                    + "                                <option value='hedrabad'>Hedrabad</option>\n"
                    + "                            </select>\n"
                    + "                       </div>\n"
                    + "                    </div>\n"
                    + "                        \n"
                    + "                    <textarea name='Property_location' class=\"form-control my-3 my-input\" rows=\"3\" required placeholder=\"Enter Detailed Home Address\">" + house.getAddress() + "</textarea>\n"
                    + "                        \n"
                    + "                    </div>\n"
                    + "                   <div class=\"col-12 mt-0\">\n"
                    + "                    <textarea required name='Property_discription' rows=\"4\" class=\" form-control my-3\" placeholder=\"Property Discription\">" + house.getDiscription() + "</textarea>\n"
                    + "                   </div>\n"
                    + "                </div>\n"
                    + "               <div class=\"text-right\">\n"
                    + "                        <button type=\"reset\" class=\"btn btn-warning btn-sm\">Reset</button>\n"
                    + "                        <button type=\"submit\"  class=\"btn btn-primary btn-sm\">Save</button>\n"
                    + "                    </div>\n"
                    + "           </form>\n"
                    + "       </div>\n"
                    + "       <div class=\"editPhotos p-md-3 p-1\">\n"
                    + "           <h5><span class='text-muted merienda-bold'>Property Photos</span><span class=\"close editHousePhotos \">X</span></h5>\n"
                    + "           <div class=\"row my-4\">\n";
            int n = 1;
            for (HousePhotos photo : house.getPhotos()) {
                if (n != 1) {
                    data += "<div class=\" img-body col-12 col-sm-6 mt-2\">\n"
                            + "                   <img class=\" card-img img-fluid\" src=\"files\\images\\HouseImg" + File.separator + photo.getName() + "\">\n"
                            + "                   <div class=\"card-img-overlay\">\n"
                            + "                       <div class=\" " + n + " paction bg-success\">\n"
                            + "                           <span class=\"px-2\" onclick=\"changePhoto('" + photo.getId() + "')\"><i class='fa fa-recycle text-light'></i></span>\n"
                            + "                            <span class=\"px-2 DeleteSinglePhoto \" data-id='" + photo.getId() + "'  ><i class='fa fa-trash text-light'></i></span>\n"
                            + "                       </div>\n"
                            + "                   </div>\n"
                            + "                 \n"
                            + "               </div>\n";
                }
                n++;
            }
            data += "</div>\n"
                    + "                   <hr>"
                    + "            <div class='mul-p'>\n"
                    + "           <form class='NewPhotos' enctype='multipart/form-data'>\n"
                    + "               <div class='row my-4'>"
                    + "                    <div class='col-12 col-md-8 offset-md-4 text-right'>"
                    + "                       <input type='hidden' name='id' value='" + house.getId() + "' >"
                    + "                       <input type='hidden' name='house' value='newPhotos' >"
                    + "                       <input class='form-control' name='newPhotos' type='file' multiple>\n"
                    + "                       <button type='submit' class='mt-4 mx-2 btn btn-sm btn-primary'>Save Photos</button>\n"
                    + "                    </div>"
                    + "               </div>\n"
                    + "           </form>\n"
                    + "           </div>\n"
                    + "              \n"
                    + "           <div class=\"singleP\">\n"
                    + "                <form class='singlePhotoActionForm' enctype=\"multipart/form-data\">\n"
                    + "                    <input name='pid' type='hidden' class=\"changePhotoId\">\n"
                    + "                    <input name='house' type='hidden' value='singlePhoto' >\n"
                    + "                    <input type=\"file\" name='file' >\n"
                    + "                    <button class='btn btn-sm btn-primary'>Change Photo</button>\n"
                    + "                </form>\n"
                    + "           </div>\n"
                    + "         \n"
                    + "          \n"
                    + "       </div>";
        } else if (formType.equalsIgnoreCase("singlePhoto")) {
            List<HousePhotos> photos = EntityHelper.getByQuery("from HousePhotos where  id=" + Integer.parseInt(req.getParameter("pid")));
            Part part = req.getPart("file");
            if (part.getSize() > 500) {
                File file = new File(path + File.separator + photos.get(0).getName());
                file.delete();
                
                String name = "House-" + Helper.getRandomeNumber(99999) + ".jpg";
                Helper.saveTheImageToFolder(path + File.separator + name, part);
                photos.get(0).setName(name);
                EntityHelper.updateData(photos);
                data += "0";
            } else {
                data += "-1";
            }
        } else if (formType.equalsIgnoreCase("editHouse")) {
            int id = Integer.parseInt(req.getParameter("houseId"));
            List<House> houseList = EntityHelper.getByQuery("from House where id=" + id);
            House house = houseList.get(0);
            house.setName(req.getParameter("House_name"));
            house.setRentType(req.getParameter("Rent_type"));
            
            house.setRent(Integer.parseInt(req.getParameter("House_price")));
            house.setAminities(req.getParameter("animetie"));
            
            house.setBedNo(req.getParameter("Beds"));
            house.setBathNo(req.getParameter("Bath"));
            house.setGarageNo(req.getParameter("Garage"));
            house.setDistrict(req.getParameter("district"));
            house.setDiscription(req.getParameter("Property_discription"));
            house.setAddress(req.getParameter("Property_location"));
            int ImgId = Integer.parseInt(req.getParameter("CoverImg"));
            EntityHelper.updateData(houseList);
            data = "0";
            Part part = req.getPart("image");

            if (part.getSize() > 500) {
                data = "1";
                List<HousePhotos> photos = EntityHelper.getByQuery("from HousePhotos where id=" + ImgId);
                System.out.println("from HousePhotos where id=" + ImgId);
                File file = new File(path + File.separator + photos.get(0).getName());
                file.delete();
               
                String name = "House-" + Helper.getRandomeNumber(99999) + ".jpg";
                Helper.saveTheImageToFolder(path + File.separator + name, part);
                photos.get(0).setName(name);
                EntityHelper.updateData(photos);
                data = "0";
            }
        } else if (formType.equalsIgnoreCase("delete")) {
            List<HouseOwner> owner = EntityHelper.getByQuery("from HouseOwner where id=" + Integer.parseInt(req.getParameter("deleteHouseId")));
            EntityHelper.deleteData(owner);
            data += "0";
        } else if (formType.equalsIgnoreCase("deletePhoto")) {
            List<HousePhotos> photos = EntityHelper.getByQuery("from HousePhotos where id=" + Integer.parseInt(req.getParameter("deletePhotoId")));
            File file = new File(path + File.separator + photos.get(0).getName());
            file.delete();
            EntityHelper.deleteData(photos);
            data = "0";
        } else if (formType.equalsIgnoreCase("newPhotos")) {
            List<House> houseList = EntityHelper.getByQuery("from House where id=" + Integer.parseInt(req.getParameter("id")));
            List<Part> parts = (List) req.getParts();
            List<HousePhotos> photos = new ArrayList<HousePhotos>();
            for (Part p : parts) {
                if (p.getSize() > 500) {
                   
                    String name = "House-" + Helper.getRandomeNumber(99999) + ".jpg";
                    HousePhotos photosObj = new HousePhotos();
                    photosObj.setHouse(houseList.get(0));
                    photosObj.setName(name);
                    photos.add(photosObj);
                    //save photo to folder
                    Helper.saveTheImageToFolder(path + File.separator + name, p);
                }
            }
            EntityHelper.saveNewPhotos(photos);
            data = "0";
        }
        out.print(data);
    }
}
