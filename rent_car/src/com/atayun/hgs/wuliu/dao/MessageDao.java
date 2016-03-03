package com.atayun.hgs.wuliu.dao;

import java.util.ArrayList;

import com.atayun.hgs.wuliu.po.MessageCar;
import com.atayun.hgs.wuliu.po.MessageCargo;

public interface MessageDao {

	//货主查看我的消息,根据自己的货主ID进行查询
	public ArrayList<MessageCargo> getMyMessagesCargo(Integer cagoId);
	
	//车主查看我的消息,根据自己的车主ID进行查询
	public ArrayList<MessageCar> getMyMessagesCar(Integer caroId);
}
