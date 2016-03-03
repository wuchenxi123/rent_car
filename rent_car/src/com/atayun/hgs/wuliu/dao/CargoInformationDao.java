package com.atayun.hgs.wuliu.dao;
import java.util.ArrayList;
import java.util.List;

import com.atayun.hgs.wuliu.po.CargoAllViewPo;
import com.atayun.hgs.wuliu.po.CargoInformation;
import com.atayun.hgs.wuliu.po.CargoListView;
import com.atayun.hgs.wuliu.po.User;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
public interface CargoInformationDao {
	
	//查询全部货源信息
	public ArrayList<CargoAllViewPo> getAllCargoInformations();
	
	//获取除了第一条最佳匹配外的其他匹配，全部信息
	public ArrayList<CargoListView> getOrtherMatchALL(Integer userId);
	
	//点击查询货物的详情
	//public ArrayList<CargoListView> getMatchDetail(Integer mareId);
	//发布货源
	public int publishCargoInfo(CargoInformation cargoInformation,int userId);
	
	//获取第一条最佳匹配,全部信息，假设是组合
	public ArrayList<CargoListView> getBestMatchAllC(Integer userId);
	
	//获取第一条最佳匹配,全部信息，假设单个single
	public ArrayList<CargoListView> getBestMatchAllS(Integer userId);
	
	//检查用户的信息时候完善
	public List getUserFlag(Integer userId);
	
	//删除货源
	public boolean deleteCargoInfo(Integer cargoInfoId);
	
	//修改货源信息，在未被下订单前
	public boolean modifyCargoInfo(CargoInformation cargoInformation);
	
	//查看我的发布货源
	public ArrayList<CargoAllViewPo> getMyCargoInfoByID(Integer userId);
	
	//根据cainId修改最后的议价
	public boolean modifylastprice(Integer cainId,float cargoRPrice);
	
	//根据出发地和目的地进行模糊查询
	public ArrayList<CargoAllViewPo> getAllCargoInfoByStartAndEnd(String start,String end);
}
