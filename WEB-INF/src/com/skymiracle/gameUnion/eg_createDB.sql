drop database db_bisai;

CREATE DATABASE db_bisai CHARACTER SET utf8 COLLATE utf8_general_ci;
use db_wpx2;
CREATE TABLE tb_gameteam (id  varchar(255) ,teamname  varchar(255) ,shortname  varchar(255) ,creator  varchar(255) ,gameid  varchar(255) ,hostmaptype  varchar(255) ,url  varchar(255) ,qq  varchar(255) ,memo  varchar(255) ,point bigint ,membercount bigint ,createdatetime  varchar(255) ,opened int ,primary key(id));
CREATE TABLE tb_teamPost (teamid  varchar(255) ,level bigint ,postname  varchar(255) ,primary key(teamid,level));


CREATE TABLE tb_bisai (id  varchar(255) ,owner  varchar(255) ,title  varchar(255) ,type  varchar(255) ,mainorganizer  varchar(255) ,coorganizer  varchar(255) ,sponsor  varchar(255) ,logo  varchar(255) ,bisaitime  varchar(255) ,state  varchar(255) ,contact  varchar(255) ,friendlinktype  varchar(255) ,signupformstat  varchar(255) ,primary key(id));
CREATE TABLE tb_signupuser (bisaiid  varchar(100) ,teamname  varchar(255) ,state  varchar(255) ,username  varchar(100) ,qq  varchar(255) ,realname  varchar(255) ,gender  varchar(255) ,birthyear bigint ,birthmonth bigint ,birthday bigint ,province  varchar(255) ,email  varchar(255) ,mobile  varchar(255) ,vocation  varchar(255) ,college  varchar(255) ,primary key(bisaiid,username));

CREATE TABLE tb_gameuser (username  varchar(255) ,password  varchar(255) ,email  varchar(255) ,question  varchar(255) ,answer  varchar(255) ,sex  varchar(255) ,nickname  varchar(255) ,realname  varchar(255) ,province  varchar(255) ,signature  varchar(255) ,telphone  varchar(255) ,phone  varchar(255) ,idcard  varchar(255) ,accountcoins bigint ,account bigint ,level bigint ,usertype bigint ,curlevelrate bigint ,ingame int ,point bigint ,rank bigint ,regdatetime  varchar(255) ,vipaddr bigint ,ispracticer int ,qq  varchar(255) ,userrole  varchar(255) ,birthday  varchar(255) ,theater  varchar(255) ,bbscount bigint ,bbsessencepost bigint ,bbsbadessencepost bigint ,bbssilverpost bigint ,bbspoint bigint ,bbscharm bigint ,bbsmoney bigint ,bbstimecost bigint ,bbsprestige bigint ,bbscontribute bigint ,bbsstatus bigint ,bbssignpicuuid  varchar(255) ,bbssignpicname  varchar(255) ,bbsusesign int ,power bigint ,honor  varchar(255) ,realip  varchar(255) ,iplocation  varchar(255) ,createdate  varchar(255) ,createdatetime  varchar(255) ,slotaddr bigint ,logintime bigint ,lastheartbeat bigint ,sessionkey  varchar(255) ,roomid  varchar(255) ,primary key(username))
alter table tb_gameuser add column privacy varchar(255);
alter table tb_gameuser add column profession varchar(255);
alter table tb_gameuser add column birthday varchar(255);
alter table tb_gameuser add column warzone varchar(255);

CREATE TABLE tb_teamMember (teamid  varchar(100) ,username  varchar(100) ,level bigint ,isheader int ,passed int ,primary key(teamid,username));
#alter table tb_teamMember add column isheader int;
#alter table tb_teamMember add column ismanager int;
#alter table tb_teamMember add column post varchar(100);
#alter table tb_teamMember add column ismainforce int;

CREATE TABLE tb_egmessage (id  varchar(255) ,sender  varchar(255) ,receiver  varchar(255) ,message  varchar(255) ,messagetype  varchar(255) ,createdatetime  varchar(255) ,createdate  varchar(255) ,primary key(id));

CREATE TABLE tb_egmessage (id  varchar(255) ,sender  varchar(255) ,receiver  varchar(255) ,subject  varchar(255) ,content longtext ,readed int ,msgtype  varchar(255) ,createdatetime  varchar(255) ,createdate  varchar(255) ,status int ,primary key(id));
CREATE TABLE tb_gameHallUserFriend (username  varchar(255) ,friendname  varchar(255) ,alias  varchar(255) ,state  varchar(255) ,subject  varchar(255) ,passeddate  varchar(255) ,passeddatetime  varchar(255) ,createdate  varchar(255) ,createdatetime  varchar(255) ,primary key(username,friendname));
CREATE TABLE tb_userfriend (username  varchar(255) ,friendname  varchar(255) ,privacy  varchar(255) ,friendreqtip  varchar(255) ,primary key(username));


alter table tb_bisai add column customselect int; 
alter table tb_bisai add column sn int; 
alter table tb_bisai add column remarks varchar(255);


CREATE TABLE tb_user_eg_history (id  varchar(255) ,username  varchar(255) ,matchtypeid  varchar(255) ,createdatetime  varchar(255) ,content  varchar(255) ,primary key(id));

CREATE TABLE tb_article (id  varchar(64) ,title  varchar(64) ,content longtext ,author  varchar(64) ,rootid  varchar(64) ,iscollected int ,type  varchar(255) ,newstype  varchar(255) ,level  varchar(255) ,goodvote bigint ,badvote bigint ,readcount bigint ,bookmarkcount bigint ,videocode longtext ,primary key(id));
CREATE TABLE tb_userPro_info (username  varchar(64) ,matchtypeid  varchar(64) ,grade  varchar(64) ,creditval  varchar(64) ,certiid  varchar(64) ,primary key(username));

alter table tb_gameuser add column warzoneid varchar(255);

//5.31
CREATE TABLE tb_user_msg (id  varchar(64) ,sender  varchar(64) ,receiver  varchar(64) ,subject  varchar(128) ,content longtext ,readed int ,msgtype  varchar(64) ,createdatetime  varchar(64) ,createdate  varchar(64) ,status int ,primary key(id));
CREATE TABLE tb_usercaller (username  varchar(64) ,callername  varchar(64) ,time  varchar(64) ,primary key(username,callername));
//6.2
CREATE TABLE tb_friend_msg (id  varchar(64) ,sender  varchar(64) ,receiver  varchar(64) ,subject  varchar(128) ,content longtext ,readed int ,msgtype  varchar(64) ,createdatetime  varchar(64) ,createdate  varchar(64) ,status int ,primary key(id));
//6.10
CREATE TABLE tb_duel (id  varchar(64) ,gametype  varchar(64) ,platform  varchar(64) ,hall  varchar(64) ,contact  varchar(64) ,content  varchar(64) ,createdatetime  varchar(64) ,primary key(id))

