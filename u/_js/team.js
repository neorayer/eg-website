/******************************************
 *	Team 
 ******************************************/
var Team = {
	id: null,
	//解散战队
	disband: function(id, callBack) {
		if(!confirm("您确实要解散战队吗?"))
			return false;
		var url ='../team/team.disband.json.do?id='+id;
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});
		
		return false;
	},
	
	//删除战队
	del: function(id, callBack) {
		if(!confirm("您确实要删除战队吗?"))
			return false;
		var url ='../team/team.del.json.do?id='+id;
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});
		
		return false;
	},
	
	vi_teams: function() {
		$('#index-body-main-box').load('../team/teams.jsp.vi');
	},
	
	pass_apply: function(username, callBack) {
		var url ='../team/teamMember.pass.json.do';
		var params = {
			teamid: Team.id,
			username: username
		};
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});	
	},
	
	refuse_apply: function(username, callBack) {
		var url ='../team/teamMember.refuse.json.do';
		var params = {
			teamid: Team.id,
			username: username
		};
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});	
	},
	
	mod_type: function(teamId, srcType, descType) {
		var url ='../team/teamType.mod.json.do';
		var params = {
			teamId: teamId,
			srcType: srcType,
			descType: descType
		};
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
		   	}
		});	
	}
};

/******************************************
 *	TeamMember 
 ******************************************/

var TeamMember = {
	apply: function(teamId, callBack) {
		if(!Portal.checkSession()) {
			alert('您还没有登录,不能申请加入战队!');
			return false;
		}
		
		if(!confirm("您确定要申请加入该战队吗?"))
			return false;
		
		var url ='../team/teamMember.apply.json.do';
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: {
				teamid: teamId
			},
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				alert("申请成功!请等待队长批准！");
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});
	},
	
	del: function(userName, callBack) {
		if(!confirm("您确定要删除该成员吗?"))
			return false;
			
		var params = {
			teamid: Team.id,
			username: userName
		}
		var url ='../team/teamMember.del.json.do';
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});
		
		return false;
	},
	
	cancel_apply: function(callBack) {
		if(!confirm("您确定要取消申请吗?"))
			return false;
			
		var params = {
			teamid: Team.id
		}	
		var url ='../team/teamMember_apply.cancel.json.do';
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});
		
		return false;
	},
	
	leave_team: function(teamId, callBack) {
		if(!confirm("您确定要退出该战队吗?"))
			return false;
			
		var params = {
			teamid: teamId
		}
		var url ='../team/teamMember.cancelTeam.json.do';
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				if(typeof callBack != 'undefined')
					callBack(data);
		   	}
		});
		
		return false;
	},
	
	transfer: function(teamid, userName) {
		if(!confirm("您确定要将该战队转让给 "+ userName +" 吗?"))
			return false;
		var params = {
			teamid: teamid,
			username: userName
		}
		var url ='../team/header.transfer.json.do';
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: function(data) {
				if(data.res == 'no') {
					alert(data.data);
					return;
				}
				
				alert("转让成功!");
				Team.vi_members();
		   	}
		});
		
		return false;
	},
	
	checkMemberExists: function(teamid, username, callBack) {
		var url ='../team/memberExists.check.json.do?teamid='+teamid + '&username=' + username;
		var params = {
			teamid: teamid,
			username: username
		}
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: url,
			data: params,
			success: callBack
		});
	}
};

