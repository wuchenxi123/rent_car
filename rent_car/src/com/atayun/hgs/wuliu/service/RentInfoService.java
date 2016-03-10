package com.atayun.hgs.wuliu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.RentInfoDao;
import com.atayun.hgs.wuliu.po.RentInfo;
import com.atayun.hgs.wuliu.po.User;



@Transactional
@Service
public class RentInfoService {
	/** spring 注入方式 将userDao注入*/
	@Autowired
	private RentInfoDao rentinfodao;
	
	
//	租车界面
//	全部
public int CreateRentInfo(RentInfo rentinfo){
		
		int rentid=rentinfodao.creatRentInfo(rentinfo.getRentPlace(), rentinfo.getRentDays(), rentinfo.getRentTakeTime(),
				rentinfo.getRentCtype(), rentinfo.getRentLpnum(),rentinfo.getUserId());
		
		return rentid;
	}
//部分
public int CreatePartRentInfo(RentInfo rentinfo){
		
		int rentid=rentinfodao.insertRentInfo(rentinfo.getRentPlace(), rentinfo.getRentDays(), rentinfo.getRentTakeTime());
		
		return rentid;
	}
	
	public int improveRentInfo(RentInfo rentinfo){
        int rentid=rentinfodao.improveRentInfo(rentinfo.getRentId(), rentinfo.getRentLpnum(), rentinfo.getRentCtype());
		
		return rentid;
	}
	
	public User getDriverInfo(int i){
		User driver=new User();
		driver=rentinfodao.getDriverInfo(i);
		return driver;
	}
}
