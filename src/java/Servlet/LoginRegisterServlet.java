/*
 * This servlet manage the registration,login and logout task...
 */
package Servlet;

import HelperClasses.EmailManager;
import HelperClasses.EntityHelper;
import HelperClasses.Helper;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class LoginRegisterServlet extends HttpServlet{
    HttpSession httpSession;
    
    @Override
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException{
        res.setContentType("text/html;charset=UTF-8");
        String formType=req.getParameter("form_type");
        httpSession=req.getSession();
        System.out.println(" formtype : "+formType);
      //  String path=req.getRealPath("files"+File.separator+"images"+File.separator+"UserImg");
        String path="D:\\TODO\\web\\files\\images\\UserImg";
        PrintWriter out=res.getWriter();
        if(formType.equalsIgnoreCase("login")){
            //login managment is here
            String email=req.getParameter("l-email");
            String password=req.getParameter("l-password");
            String encPassword=HelperClasses.PasswordEncodeDecode.getEncodedString(password);
            try{
            List<User> u=EntityHelper.getByQuery("From User where email='"+email+"' and password='"+encPassword+"'");
            System.out.println(encPassword);
            if(u.size()==0 ){
                out.print("-1");
            }else{
                for(User user:u){
                
                    httpSession.setAttribute("loginUser",user);
                    out.print(user.getType());
                }
            }
            }catch(Exception e){
                out.print("1");
            }
        }else if(formType.equalsIgnoreCase("register")){
           //register management is here
           String name=req.getParameter("r-name");
           String email=req.getParameter("r-email");
           String phone=req.getParameter("r-phone");
           String mobile=req.getParameter("r-mobile");
           String pass1=req.getParameter("r-pass1");
           String district=req.getParameter("district");
           String tal=req.getParameter("tal");
           String addr=req.getParameter("r-addr");
           Part part=req.getPart("r-pic");
           String encPassword=HelperClasses.PasswordEncodeDecode.getEncodedString(pass1);
           User user;
           if(part.getSize()==0){
           user=new User( name.trim(),email,phone,mobile,encPassword,district.trim(),tal.trim(),addr.trim(),"default.png","normal");
           httpSession.removeAttribute("otp");
           httpSession.removeAttribute("otpEmail");
           }else{
               httpSession.removeAttribute("otp");
               httpSession.removeAttribute("otpEmail");
            Random rand=new Random();
            int number=rand.nextInt();
            String picName="user-"+number+".jpg";
            
            user=new User(name.trim(),email,phone,mobile,encPassword,district.trim(),tal.trim(),addr.trim(),picName,"normal");
            
            File file=new File(path);
            if(!file.exists()){
                file.mkdir();
            }
            Helper.saveTheImageToFolder(path+File.separator+picName, part);
           }
           try{
               List<User> uList=new ArrayList<User>();
               uList.add(user);
            EntityHelper.saveNewData(uList);
           }catch(Exception e){
               e.printStackTrace();
               out.print("-1");
           }
            
        } //if(register) End 
        else if(formType.equalsIgnoreCase("logout")){
            //logout code
            httpSession.removeAttribute("loginUser");
            httpSession.setAttribute("logoutAction","done");
            
        }else if(formType.equalsIgnoreCase("generateOtp")){
            //OTP related code
           String toEmail=req.getParameter("to");
           String otpEmail=(String)httpSession.getAttribute("otpEmail");
           boolean flag=false;
            Timer timer=new Timer();
            TimerTask task=new TimerTask(){
                public void run(){
                        httpSession.removeAttribute("otp");
                        httpSession.removeAttribute("otpEmail");
                }
            };       
            if(!toEmail.equalsIgnoreCase(otpEmail) && httpSession.getAttribute("otpEmail")!=null ){
                System.out.println("Email changed");
                httpSession.removeAttribute("otp");
                httpSession.removeAttribute("otpEmail");
                flag=true;
            }
            if(httpSession.getAttribute("otp")==null){
            Random rand=new Random();
            int otp=rand.nextInt(999999);
            httpSession.setAttribute("otp",otp);
            httpSession.setAttribute("otpEmail",toEmail);
            String to=toEmail;
            String from="rkpro2021@gmail.com";
            String subject="RkRent OTP";
            String message="<html>Dear user ,your OTP is <b>"+otp+"</b> please not share .</html>";
            EmailManager.sentEmail(from, to, subject, message,false,null);
            if(flag)
               out.print(otp+"/removeCounter"); 
                else
            out.print(otp+"/active");
            timer.schedule(task,124000);//otp invalid when 2 min 
            }else{
                out.print(httpSession.getAttribute("otp")+" /oldCounter");
            }
            //httpSession.setMaxInactiveInterval(150);
        }
    }
 
}
