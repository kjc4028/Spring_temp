package com.kjc.jc.MemService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kjc.jc.Dao.MDao;

public class LoginService  {

	
	public String execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			MDao dao = new MDao();
			String a = dao.login(id, password);
			 
			 return a;
			
	}

}
