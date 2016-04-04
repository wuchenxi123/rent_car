package com.atayun.hgs.wuliu.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atayun.hgs.wuliu.dao.CarInfoDao;
import com.atayun.hgs.wuliu.dao.OrderInfoDao;
import com.atayun.hgs.wuliu.dao.ReturnInfoDao;
import com.atayun.hgs.wuliu.po.CarInfo;
import com.atayun.hgs.wuliu.po.RentOrderView;
import com.atayun.hgs.wuliu.po.ReturnInfo;

@Transactional(rollbackFor = Exception.class)
@Service
public class ReturnInfoService {
	@Autowired
	private ReturnInfoDao returninfoDao;
	@Autowired
	private OrderInfoDao orderInfoDao;
	@Autowired
	private CarInfoDao carInfoDao;
	
	public ReturnInfo getReturnInfo(int returnid){
		return returninfoDao.selectReturnInfo(returnid);
	}
	
	//添加还车信息 设置还车方式   事务
	public int putReturnInfo(int userid,String returnPlace ,Date reTime,int returnType){
		int state=0;
		int returnid=returninfoDao.insertReturnInfo(userid, returnPlace, reTime);
		if((returnid!=-1)&&returnType==1){  //如果公司地点还车 修改状态
			boolean flag=returninfoDao.changeReturnType(returnid, returnType);
			if(flag){
				state=returnid;   //成功返回returnid
			}
		}
     if((returnid!=-1)&&returnType==0){
			state=returnid;
		}
		return state;
	}
	
	//事务  订单和车辆状态都修改
	public int  changeOrderAndCarState(int orderid){
		int state=0; //未修改
		 boolean flag= orderInfoDao.changeOrderStatus(3, orderid);//改为订单结束状态 3
	       if(flag){
	    	   state=1; //订单状态修改成功
	    	   RentOrderView rentorder=orderInfoDao.getOrderDetalilByID(orderid);
	    	   CarInfo car=carInfoDao.getCarBybandAndLpnum(rentorder.getRentCtype(), rentorder.getRentLpnum());
               boolean flag2=  carInfoDao.setCarBusy(car.getCarId(),0);//车辆改为未租用
	    	 if(flag2){
	    		 state=2;//订单和车辆状态都修改成功
	    	 }
       }
	       return state;
	}
	
	
}
