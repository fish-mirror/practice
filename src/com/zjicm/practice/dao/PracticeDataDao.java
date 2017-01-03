package com.zjicm.practice.dao;

import java.util.List;

import com.zjicm.dto.LocationDTO;
import com.zjicm.entity.PracticeData;

public interface PracticeDataDao {

	public void add(PracticeData pd);
	public List<PracticeData> getList(int offset, int length);
	public int count();
	
	public LocationDTO getLoactionData();
}
