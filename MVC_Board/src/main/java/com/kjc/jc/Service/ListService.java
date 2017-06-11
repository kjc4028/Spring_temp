package com.kjc.jc.Service;


import java.util.ArrayList;

import org.springframework.ui.Model;

import com.kjc.jc.Dao.BDao;
import com.kjc.jc.Dto.BDto;

public class ListService implements JCCommand {

	@Override
	public void execute(Model model) {
		// TODO Auto-generated method stub
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
		
		
	}

}
