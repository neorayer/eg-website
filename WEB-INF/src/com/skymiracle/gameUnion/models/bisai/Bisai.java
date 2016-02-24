package com.skymiracle.gameUnion.models.bisai;

import static com.skymiracle.gameUnion.Singletons.BisaiX;
import static com.skymiracle.gameUnion.Singletons.WarZoneX;

import com.skymiracle.gameUnion.models.User;
import com.skymiracle.gameUnion.models.WarZone;
import com.skymiracle.mdo5.Mdo;
import com.skymiracle.mdo5.Mdo_X;
import com.skymiracle.validate.Vf_NotEmpty;

public class Bisai extends Mdo<Bisai> {

	public static String FILE_STORE_ROOTPATH = "/gf/tomcat/webapps/51bisaifiles/";
	//public static String FILE_STORE_ROOTPATH = "D:/applications/workspace/51bisaifiles/";
	
	static public class X extends Mdo_X<Bisai> {

		

	}

	@Title("主键")
	private String id ;

	@Title("创建者")
	@Required
	@Valid(Vf_NotEmpty.class)
	private String owner;

	@Title("比赛名称")
	@Required
	@Valid(Vf_NotEmpty.class)
	private String title;

	@Title("比赛类型")
	@Required
	@Valid(Vf_NotEmpty.class)
	private String type;
	
	public static enum SoloOrRaidTYPE {
		solo,raid
	};
	@Title("单挑还是群殴")
	private SoloOrRaidTYPE soloorraid=SoloOrRaidTYPE.raid;
	
	@Title("下限人数")
	private int atleastmember=5;
	
	@Title("上限人数")
	private int atmostmember=10;

	//@Title("版式")
	//private String pagestyle="121";

	@Title("风格")
	private String clothes="war3_2";
	/*
	@Title("比赛介绍")
	private String bisaiSubject;

	@Title("比赛规则")
	private String bisaiRule;
	*/

	@Title("主办方")
	private String mainOrganizer;

	@Title("协办方")
	private String coOrganizer;

	@Title("赞助")
	private String sponsor;

	@Title("徽标")
	private String logo;

	@Title("头部图片")
	private boolean useHeadImg =false;
	
	@Title("时间")
	private String bisaiTime;

	@Title("状态")
	private BisaiState state=BisaiState.prepare;
	

	@Title("联系方式")
	private String contact;
	
	@Title("赛程驱动方式")
	private ScheduleDrive drive = Bisai.ScheduleDrive.users;
	
	@Title("赛程方式")
	private ScheduleMethod schedulemethod = ScheduleMethod.signup;
	
	public static enum ScheduleMethod{
		signup,report
	}
	@Desc("管理员驱动 | 裁判驱动 | 选手驱动")
	public static enum ScheduleDrive{
		managersolo,referee,users
	}
	
	
	public static enum FrendLinkTYPE {
		onlyPic, onlyName, Both
	};

	private FrendLinkTYPE friendLinkType = FrendLinkTYPE.onlyPic;

	@Desc("未建立 | 报名中 | 报名结束（即开赛）")
	public static enum SignUpFormStat {
		no, built, cancel
	};
	
	@Desc("准备|进行|结束") 
	public static enum BisaiState{
		prepare,progress,end
	};
	
	@Title("报名表状态")
	private SignUpFormStat signUpFormStat = SignUpFormStat.no;

	@Title("创建日期")
	@Auto(Auto.Type.CreateDate)
	private String createDate;
	
	@Title("创建时间")
	@Auto(Auto.Type.CreateDateTime)
	private String createDateTime;
	
	@Title("战区")
	private String warZoneId;
	
	@Title("是否显示")
	private boolean viewed  =false ;
	
	public boolean getViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public Bisai() {
		super(BisaiX);
	}

	public Bisai(String id) {
		this();
		this.id = id;
	}

	@Override
	public String[] keyNames() {
		return new String[] { "id" };
	}

	@Override
	public String table() {
		return "tb_bisai";
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getWarZoneId() {
		return warZoneId;
	}

	public void setWarZoneId(String warZoneId) {
		this.warZoneId = warZoneId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SoloOrRaidTYPE getSoloorraid() {
		return soloorraid;
	}

	public void setSoloorraid(SoloOrRaidTYPE soloorraid) {
		this.soloorraid = soloorraid;
	}

	public int getAtleastmember() {
		return atleastmember;
	}

	public void setAtleastmember(int atleastmember) {
		this.atleastmember = atleastmember;
	}

	public int getAtmostmember() {
		return atmostmember;
	}

	public void setAtmostmember(int atmostmember) {
		this.atmostmember = atmostmember;
	}

	public String getClothes() {
		return clothes;
	}

	public void setClothes(String clothes) {
		this.clothes = clothes;
	}

	public String getMainOrganizer() {
		return mainOrganizer;
	}

	public void setMainOrganizer(String mainOrganizer) {
		this.mainOrganizer = mainOrganizer;
	}

	public String getCoOrganizer() {
		return coOrganizer;
	}

	public void setCoOrganizer(String coOrganizer) {
		this.coOrganizer = coOrganizer;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isUseHeadImg() {
		return useHeadImg;
	}

	public void setUseHeadImg(boolean useHeadImg) {
		this.useHeadImg = useHeadImg;
	}

	public String getBisaiTime() {
		return bisaiTime;
	}

	public void setBisaiTime(String bisaiTime) {
		this.bisaiTime = bisaiTime;
	}

	public BisaiState getState() {
		return state;
	}

	public void setState(BisaiState state) {
		this.state = state;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public ScheduleDrive getDrive() {
		return drive;
	}

	public void setDrive(ScheduleDrive drive) {
		this.drive = drive;
	}

	public ScheduleMethod getSchedulemethod() {
		return schedulemethod;
	}

	public void setSchedulemethod(ScheduleMethod schedulemethod) {
		this.schedulemethod = schedulemethod;
	}

	public FrendLinkTYPE getFriendLinkType() {
		return friendLinkType;
	}

	public void setFriendLinkType(FrendLinkTYPE friendLinkType) {
		this.friendLinkType = friendLinkType;
	}

	public SignUpFormStat getSignUpFormStat() {
		return signUpFormStat;
	}

	public void setSignUpFormStat(SignUpFormStat signUpFormStat) {
		this.signUpFormStat = signUpFormStat;
	}
	public boolean getIsPrepare(){
		if(BisaiState.prepare.equals(state)){
			return true;
		}
		return false;
	}
	public boolean getIsProgress(){
		if(BisaiState.progress.equals(state)){
			return true;
		}
		return false;
	}
	public boolean getIsEnd(){
		if(BisaiState.end.equals(state)){
			return true;
		}
		return false;
	}
	public boolean getSolo(){
		return SoloOrRaidTYPE.solo.equals(soloorraid);
	}
	public boolean getRaid(){
		return SoloOrRaidTYPE.raid.equals(soloorraid);
	}
	
	public WarZone getWarZone () {
		return WarZoneX.getItemsMap().get(warZoneId);
	}
	public User getOwnerUser(){
		User user=null;
		try {
			user=new User(owner).load();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
}
