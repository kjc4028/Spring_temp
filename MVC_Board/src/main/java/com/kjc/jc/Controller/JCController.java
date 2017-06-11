package com.kjc.jc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kjc.jc.Dao.BDao;
import com.kjc.jc.Dao.MDao;
import com.kjc.jc.Dto.MDto;
import com.kjc.jc.MemService.JoinService;
import com.kjc.jc.MemService.LoginService;
import com.kjc.jc.MemService.memberListService;
import com.kjc.jc.Service.BContentCommand;
import com.kjc.jc.Service.BWriteCommand;
import com.kjc.jc.Service.DeleteService;
import com.kjc.jc.Service.JCCommand;
import com.kjc.jc.Service.ListService;
import com.kjc.jc.Service.ModifyService;
import com.kjc.jc.Service.ReplyService;
import com.kjc.jc.Service.ReplyView;
import com.kjc.jc.util.Constant;

@Controller
public class JCController {
	
	private JdbcTemplate template;
	
	JCCommand command;

	@Autowired
	public void setTemplate(JdbcTemplate template){
		this.template = template;
		Constant.template = this.template;

	}

	@RequestMapping("/list")
	public String list(Model model){
		command = new ListService();
		command.execute(model);

		return "/list";

	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("write_view()");

		return "write_view";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest request, Model model) {
		System.out.println("write()");

		model.addAttribute("request", request);
		command = new BWriteCommand();
		command.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/content_view")
	public String contentView(HttpServletRequest request,Model model){
		System.out.println("contentView()");
		model.addAttribute("request", request);
		command = new BContentCommand();
		command.execute(model);
		return "content_view";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,Model model){
		System.out.println("delete()");
		model.addAttribute("request",request);
		command = new DeleteService();
		command.execute(model);
		return "redirect:list";
	}
	
	@RequestMapping("/modify")
	public String modify(HttpServletRequest request,Model model){
		System.out.println("modify()");
		model.addAttribute("request", request);
		command = new ModifyService();
		command.execute(model);
		return "redirect:list";

	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request,Model model){
		System.out.println("reply_view()");
		model.addAttribute("request", request);
		command = new ReplyView();
		command.execute(model);
		return "reply_view";

	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest request,Model model){
		System.out.println("reply()");
		model.addAttribute("request", request);
		command = new ReplyService();
		command.execute(model);
		return "redirect:list";
	}
	
	
	//-----이하 회원 관련--------
	@RequestMapping("/joinForm")
	public String joinForm(){
		System.out.println("joinForm()");
		return "joinForm";

	}
	
	@RequestMapping("/join")
	public String join(HttpServletRequest request,Model model){
		System.out.println("join()");
		model.addAttribute("request", request);
		command = new JoinService();
		command.execute(model);
		return "redirect:memberList";

	}
	
	@RequestMapping("/memberList")
	public String memberList(HttpServletRequest request,Model model){
		System.out.println("memberList()");
		model.addAttribute("request", request);
		command = new memberListService();
		command.execute(model);
		return "memberListview";

	}
	
	@RequestMapping("/memberListview")
	public String memberListview(){
		System.out.println("memberListview()");
		return "memberListview";

	}
	
	@RequestMapping("/loginForm")
	public String loginForm(){
		System.out.println("loginForm()");
		return "loginForm";

	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,Model model,HttpSession session){
		System.out.println("login()");
		String result = "";
		model.addAttribute("request", request);
		
		LoginService aa = new LoginService();
		String qq = aa.execute(model);
		if(qq == "1"){
			result = "loginSuccess";
			System.out.println(request.getParameter("id"));
			session.setAttribute("userId", request.getParameter("id"));
		}
		if(qq == "0"){
			result = "loginFail";
		}
	
		return result;

	}
	
	
	
}
