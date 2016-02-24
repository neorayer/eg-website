var Album = {
	realbums:function(){
		window.location = '../album/albums.jsplayout.vi';
	},
	realbumavaor:function(){
		window.location = '../album/album_avator.jsplayout.vi';
	},
	rephotos:function(id){
		window.location = '../album/photos.jsplayout.vi?id='+id;
	},
	create:function(){
		window.location = '../album/album_create.jsplayout.vi';
	},
	modPhotoDesc: function() {
		$("#page-modphoto-link").hide();
		$("#page-modphoto-form").fadeIn();
	},
	cancelPhotoDesc: function() {
		$("#page-modphoto-link").fadeIn();
		$("#page-modphoto-form").hide();
	},
	modalbum:function(id){
		window.location = '../album/album_mod.jsplayout.vi?id='+id;
	},
	setavator:function(id) {
	 
		var params = {
			id : id
		}
		var url = '../album/photo.setavator.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					alert('头像设置成功');
					Album.realbumavaor();
				} 
			}
		});
	},
	setcover:function(id,albumid) {
	 
		var params = {
			id : id
		}
		var url = '../album/photo.setcover.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					alert('封面设置成功');
					Album.rephotos(albumid);
				} 
			}
		});
	},
	delalbum: function(id) {
		if (!confirm("您确定要删除吗?"))
			return false;

		var params = {
			id : id
		}
		var url = '../album/album.del.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					Album.realbums();
				} 
			}
		});
	},
	delphoto: function(id,albumid) {
		if (!confirm("您确定要删除吗?"))
			return false;

		var params = {
			id : id
		}
		var url = '../album/photo.del.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					Album.rephotos(albumid);
				} 
			}
		});
	},
	delavator: function(id) {
		if (!confirm("您确定要删除头像吗?"))
			return false;

		var params = {
			id : id
		}
		var url = '../album/photo.delavator.json.do';
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : url,
			data : params,
			success : function(data) {
				if (data.res == 'no') {
					alert(data.data);
					return;
				}else{
					Album.realbumavaor();
				} 
			}
		});
	}
}
 