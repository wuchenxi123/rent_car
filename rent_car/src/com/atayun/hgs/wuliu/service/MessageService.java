package com.atayun.hgs.wuliu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

import com.atayun.hgs.wuliu.dao.MessageDao;
import com.atayun.hgs.wuliu.po.MessageCar;
import com.atayun.hgs.wuliu.po.MessageCargo;

@Transactional
@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	private static final String appKey ="ed999eecca30d7cf7cea6b3b";
	private static final String masterSecret = "8f630c8434b93ffa319fba8a";
	private static JPushClient jpush = null;
	/**
	 * 根据货主的ID查询我的消息
	 * @param cagoId 货主的ID
	 * @return
	 */
	public ArrayList<MessageCargo> getMyMessagesCargo(Integer cagoId) {
		return messageDao.getMyMessagesCargo(cagoId);
	}
	
	/**
	 * 根据车主的ID查询我的消息
	 * @param caroId
	 * @return
	 */
	public ArrayList<MessageCar> getMyMessagesCar(Integer caroId) {
		
		return messageDao.getMyMessagesCar(caroId);
	}
	
	public boolean jPushMessage(String title,String content,String alias){
		boolean flag = true;
		jpush = new JPushClient(masterSecret, appKey);
		//title所发信息的标题，content所发信息的内容，alias
		try {
			Map<String,String> map = new HashMap<String,String>();
			jpush.sendAndroidNotificationWithAlias(title,content, map,alias);
		} catch (APIConnectionException e) {
			flag = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("APIConnectionException01 is "+e);
		} catch (APIRequestException e) {
			flag = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("APIConnectionException02 is "+e);
		}
		return flag;
	}
}
