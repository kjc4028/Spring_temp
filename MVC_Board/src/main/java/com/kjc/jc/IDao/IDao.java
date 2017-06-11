package com.kjc.jc.IDao;

import java.util.ArrayList;

import com.kjc.jc.Dto.BDto;

public interface IDao {

	public ArrayList<BDto> listDao();
	public void writeDao(String mWrite, String mContent);
	
}
