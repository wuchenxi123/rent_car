select * from tb_user;
select * from tb_car;
select * from view_order;
select * from tb_order;
select * from view_rentorder;
select * from tb_rentinfo;
select * from tb_returninfo;

alter table tb_car alter column car_takeType set default '0';

create view view_orderDetail as
(select 
   o.order_id,o.createDate,o.user_id ,o.order_price,
   u.user_relname drivername,u.user_mobile driverMobile,u.user_idcard driverIDcard,u.user_license driverLicense,
   c.car_band,c.car_color,c.car_lpnum,c.car_pictUrl,c.car_rentPri,c.car_takeType,c.insure_price,c.scsm_price,
   r.rent_place,r.rent_takeTime,r.rent_days,
   t.return_place,t.return_reTime,t.returnType
 from tb_order o,tb_rentinfo  r,tb_returninfo  t,tb_car c ,tb_user u 
  where 
     r.rent_id=o.rent_id and
     t.return_id=o.return_id AND
       r.rent_lpnum=car_lpnum AND  
        r.user_id=u.user_id );
        
        
        select car_lpnum,car_purTime,car_band,car_color,car_price,user_name from tb_car,tb_user " +
			"where tb_car.user_id=tb_user.user_id
        select * from tb_car where car_band='奥迪' and car_lpnum='湘ALW342' and sp_flag=1
        update tb_user set user_mobile=18734917692 WHERE user_id=24;
        
        update tb_rentinfo set user_id=17 WHERE rent_id=3;
        select * from tb_car where car_lpnum='湘AQW989' and sp_flag=1
        select * from view_orderDetail where order_id=106
        select * from tb_car;
        select * from tb_order;
select * from view_rentorder;
select * from tb_rentinfo;
select * from tb_returninfo;
        delete from tb_order where order_id=129;
		delete from tb_rentinfo where rent_id=129;
		delete from tb_returninfo where return_id=91;
		update tb_car set car_flag=0 WHERE car_id=10;