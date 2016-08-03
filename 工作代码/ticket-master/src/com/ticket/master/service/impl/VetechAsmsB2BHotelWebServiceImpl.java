package com.ticket.master.service.impl;

import com.ticket.master.common.CheckInterface;
import com.ticket.master.service.ParentService;
import com.ticket.master.service.VetechAsmsB2BHotelWebService;

public class VetechAsmsB2BHotelWebServiceImpl implements VetechAsmsB2BHotelWebService{
	ParentService parentService = new ParentServiceImpl();
	public String queryHotel(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"queryHotel").equals("ok")) {
			return yanzhengUser(account, md5, xml,"queryChangeOrder");
		}

		String result = parentService.getHotelService("queryHotel", xml,
				account, md5);
		return result;
	}

	public String getHotelStaticDataXmlURL(String account, String md5,
			String xml) {
		if (!yanzhengUser(account, md5, xml,"getHotelStaticDataXmlURL").equals("ok")) {
			return yanzhengUser(account, md5, xml,"getHotelStaticDataXmlURL");
		}

		String result = parentService.getHotelService("getHotelStaticDataXmlURL", xml,
				account, md5);
		return result;
	}

	public String queryHotelOrderDetail(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"queryHotelOrderDetail").equals("ok")) {
			return yanzhengUser(account, md5, xml,"queryHotelOrderDetail");
		}

		String result = parentService.getHotelService("queryHotelOrderDetail", xml,
				account, md5);
		return result;
	}

	public String queryHotelOrderList(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"queryHotelOrderList").equals("ok")) {
			return yanzhengUser(account, md5, xml,"queryHotelOrderList");
		}

		String result = parentService.getHotelService("queryHotelOrderList", xml,
				account, md5);
		return result;
	}

	public String cancelHotelOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"cancelHotelOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml,"cancelHotelOrder");
		}

		String result = parentService.getHotelService("cancelHotelOrder", xml,
				account, md5);
		return result;
	}

	public String submitHotelOrder(String account, String md5, String xml) {
		if (!yanzhengUser(account, md5, xml,"submitHotelOrder").equals("ok")) {
			return yanzhengUser(account, md5, xml,"submitHotelOrder");
		}

		String result = parentService.getHotelService("submitHotelOrder", xml,
				account, md5);
		return result;
	}
	
	public String yanzhengUser(String account, String md5, String xml,String method) {
		CheckInterface checkInterface = new CheckInterface();
		return checkInterface.checkInterface(account, md5, xml,method);
	}
}
