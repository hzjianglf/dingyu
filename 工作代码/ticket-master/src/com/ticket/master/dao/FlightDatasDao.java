package com.ticket.master.dao;

import com.ticket.master.entity.CabinData;
import com.ticket.master.entity.FlightData;
import com.ticket.master.entity.Res;
import com.ticket.master.entity.User;

public interface FlightDatasDao{
	public void saveFlightDatas(FlightData fd);
	public Res findById(int resId);
	public void saveCabinData(CabinData cd);
}
