package com.kjc.jc.MemService;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.kjc.jc.Dao.MDao;
import com.kjc.jc.Dto.MDto;
import com.kjc.jc.Service.JCCommand;

public class memberListService implements JCCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub

		MDao dao = new MDao();
		ArrayList<MDto> dtos = dao.memberList();
		model.addAttribute("memberlist", dtos);
	}

}
