/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.infonal.controller;

/**
 *
 * @author fidanras
 */
import com.infonal.dao.user.UserDao;
import com.infonal.model.RequestResult;
import com.infonal.model.user.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;  
  
@Controller  
public class UserController {  
    private static final String JSONText = "JSONText";

        @Autowired
        private UserDao userDao;
        
	@RequestMapping("/user/create")
	public ModelAndView add(HttpServletRequest request,
		HttpServletResponse response){
            RequestResult result = new RequestResult();
            
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String telNo = request.getParameter("telNo");
            String captchaParam = request.getParameter("captcha");
            
            HttpSession session = request.getSession();
            
            String captchaValue = (String) session.getAttribute("captcha");
            
            if(captchaParam.equals(captchaValue)){
                User user = new User(name,surname,telNo);
                userDao.createUser(user);
                
                result.setSuccessfull(true);
                result.addMessage("success", "Sonuc basarili.");
            } else {
                result.setSuccessfull(false);
                result.addMessage("wrongCaptcha", "Captcha Dogrulanamadi. Tekrar Deneyiniz.");
            }
            
            ModelAndView modelAndView = new ModelAndView("ajax/AjaxResult");
            
            modelAndView.addObject(JSONText, result.toJSONString());
            
            return modelAndView;
 
	}
 	@RequestMapping("/user/update")
	public ModelAndView update(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
            RequestResult result = new RequestResult();
            
            String userid = request.getParameter("userid");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String telNo = request.getParameter("telNo");
            
            userDao.updateUser(userid, name, surname, telNo);

            result.setSuccessfull(true);
            result.addMessage("success", "Sonuc basarili.");
                
            ModelAndView modelAndView = new ModelAndView("ajax/AjaxResult");
            
            modelAndView.addObject(JSONText, result.toJSONString());
            
            return modelAndView;
	}
 
	@RequestMapping("/user/delete")
	public ModelAndView delete(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
            RequestResult result = new RequestResult();
            String userid = request.getParameter("userid");
            
            userDao.deleteUserById(userid);
            
            result.setSuccessfull(true);
           result.addMessage("success", "Sonuc basarili.");
                
            ModelAndView modelAndView = new ModelAndView("ajax/AjaxResult");
            
            modelAndView.addObject(JSONText, result.toJSONString());
            return modelAndView;
	}
 
	@RequestMapping("/user/list")
	public ModelAndView list(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
            
            RequestResult result = new RequestResult();
            
            List <User> list = userDao.findAllUsers();
            
            result.setResultList(list);
            
            result.setSuccessfull(true);
            
            //result.addMessage("ERROR", "Mongo DB Calismior");
            
            ModelAndView modelAndView = new ModelAndView("ajax/AjaxResult");
            
            modelAndView.addObject(JSONText, result.toJSONString());
            
            return modelAndView;
 
	}
        
        @RequestMapping("/UserPage")
	public ModelAndView redirectToUserPage() throws Exception { 
		return new ModelAndView("user/UserPage");
 
	}
}  