package com.kjc.jc.Service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.kjc.jc.Dao.BDao;

public class ReplyService implements JCCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		 BDao dao = new BDao();
		 String bName = request.getParameter("bName");
		 String bTitle = request.getParameter("bTitle");
		 String bContent = request.getParameter("bContent");
		 String bGroup = request.getParameter("bGroup");
		 String bStep = request.getParameter("bStep");
		 String bIndent = request.getParameter("bIndent");

		 
		 dao.reply(bName, bTitle, bContent, bGroup, bStep, bIndent);
		
	}

}
