package com.atayun.hgs.wuliu.utils.sql;

public interface CarInformationSQL {

	// 获取所有的车源
	public String getAllCarInformationSQL = "select * from tb_cari  where cari_flag in (0,3)";
	// 条件查询获取所有的车源
	public String getAllCarListInformationSQL = "select * from carListView "
			+ "where cari_flag in (0,3) and cari_start like ? and cari_end like ? order by updatetime desc";
	// 根据出发点查询所有车源
	public String getAllCarStartListInformationSQL = "select * from carListView  "
			+ "where cari_flag in (0,3) and cari_start like ? order by updatetime desc";
	// 根据目的地查询所有车源
	public String getAllCarEndListInformationSQL = "select * from carListView "
			+ "where cari_flag in (0,3) and cari_end like ? order by updatetime desc";
	// 查询获取所有的车源
	public String getAllCarListsInformationSQL = "select * from carListView where cari_flag in (0,3) order by updatetime desc";
	// 根据ID查询详细车辆信息
	// 根据userId查询车主信息
	public String getCarOwnwerSQL = "select * from tb_caro where user_id=?";
	// 根据userId查询车辆信息
	public String getCarInfoSQL = "select * from tb_cari where user_id=?";
	//根据userId查询车主、车辆信息（carListView）
	public String getCarInformationSQL = "select * from carListView where user_id=?";
	// 插入车主信息
	public String insertCarOwnerSQL = "insert into tb_caro value(?,?,?,?,?,?,sysdate())";
	// 插入车辆信息
	public String insertCarInfoSQL = "insert into tb_cari(cart_id,user_id,cari_lpnum,cari_length,cari_width,cari_height,cari_volume,cari_vunit,cari_load,cari_lunit,cari_dlicurl,cari_dlicflag,cari_picurl,cari_picflag,cari_flag,updatetime) value(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate())";
	// 插入车辆行车路线信息
	public String insertCarRouteSQL = "insert into tb_carr(cari_id,carr_start,carr_end,updatetime) value(?,?,?,sysdate())";
	// 根据userId查询车主信息
	public String getOwnerSQL = "select * from tb_caro a where a.user_id=?";
	// 根据userId查询车辆信息（除自身以外）
	public String checkCarInformationSQL = "select count(*) from tb_cari where cari_lpnum=? and user_id not in(?)";
	// 修改路线车辆信息
	public String updateCarRouteInfoSQL = "update tb_cari set cari_start=?,cari_end=?,cari_olength=?,cari_owidth=?,cari_oheight=?,cari_oload=?, updatetime=sysdate() "
			+ "where user_id=?";
	// 修改车辆信息
	public String updateCarInfoSQL = "update tb_cari set cart_id=?,cari_lpnum=?,cari_length=?,cari_width=?,cari_height=?,"
			+ "cari_volume=?,cari_vunit=?,cari_rvolumn=?,cari_load=?,cari_lunit=?,cari_rload=?,cari_dlicurl=?,cari_dlicflag=?,cari_picurl=?,cari_picflag=?,cari_flag=?,updatetime=sysdate() "
			+ "where user_id=?";
	// 用户使用用户ID 查找用户
	public String checkUserIdSQL = "select * from tb_user where USER_ID=?";
	public String updateCarOwnerSQL = "update tb_caro set INFOFLAG=? where USER_ID=?";
	public String updateOwnerSQL = "update tb_caro set CARO_DLICPICURL=?,CARO_DLICPICFLAG=?,CARO_CPNAME=?,InfoFlag=?,Caro_Mobile=?,updatetime=sysdate()"+
									"where user_id=?";
	
	//初始化车辆，黄威杰修改
	public String initCarInfoMationSQL="insert into tb_cari(CART_ID,USER_ID,CARI_LPNUM,CARI_LOAD,CARI_DLICFLAG,CARI_PICURL,CARI_FLAG,CARI_ROUTENUM,UPDATETIME) " +
															"values(14,?,'湘A12345',0,0,'picture.jpg',0,0,sysdate())";


	// 修改车主信息
	public String applyUpdateOwnerSQL = "update tb_caro set CARO_DLICPICURL=?,CARO_DLICPICFLAG=0,CARO_CPNAME=?,InfoFlag=?,Caro_Mobile=?,updatetime=sysdate()"
			+ "where user_id=?";
	// 修改车辆信息
	public String applyUpdateCarInfoSQL = "update tb_cari set cart_id=?,cari_lpnum=?,cari_length=?,cari_width=?,cari_height=?,"
			+ "cari_volume=?,cari_vunit=?,cari_load=?,cari_lunit=?,cari_dlicurl=?,cari_dlicflag=0,cari_picurl=?,cari_picflag=0,cari_flag=?,updatetime=sysdate() "
			+ "where user_id=?";

	public String getUserId = "select * from tb_user where user_mobile=?";

	public String publicOwnerSQL = "update tb_caro set Caro_Mobile=?,updatetime=sysdate()"
			+ "where user_id=?";
	public String publicCarInfoSQL = "update tb_cari set cari_start=?,cari_scity=?,cari_sstreet=?,cari_end=?,cari_ecity=?,cari_estreet=?,cari_lng=?,cari_lat=?,cari_elng=?,cari_elat=?,cari_olength=?,cari_owidth=?,cari_oheight=?,cari_oload=?,cari_routeNum=?,updatetime=sysdate()"
			+ " where user_id=?";
	//统计车主行车路线条数
	public String countRouteSQL="select count(distinct a.carr_start,a.carr_end) from tb_carr a,tb_carr b " +
			" where a.carr_start<>b.carr_start and a.carr_end<>b.carr_end " +
			" and a.cari_id=? and(a.carr_start<>'' or a.carr_end<>'') ";
	//添加车辆路线信息
	public String addCarRouteSQL="insert into tb_carr(cari_id,carr_start,carr_end,updatetime) values(?,?,?,sysdate())";
	public String getCariIdSQL="select * from tb_cari where user_id=?";
	
	//查询车主行车路线记录
	public String getRouteSQL="select distinct a.carr_start,a.carr_end from tb_carr a,tb_carr b" +
			" where a.carr_start<>b.carr_start and a.carr_end<>b.carr_end and a.cari_id=? " +
			" and(a.carr_start<>'' or a.carr_end<>'') order by a.updatetime desc";
}
