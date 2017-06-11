package com.kjc.jc.MemService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kjc.jc.Dao.MDao;
import com.kjc.jc.Service.JCCommand;

public class JoinService implements JCCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
Map<String, Object> map = model.asMap();
HttpServletRequest request = (HttpServletRequest)map.get("request");
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	
	MDao dao = new MDao();
	dao.join(id, password, name);
	
	}

}
