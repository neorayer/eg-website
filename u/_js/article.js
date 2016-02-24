
var Article = {
	
	add:function(){
		window.location='../article/article_addormod.jsplayout.vi';
	},
	back:function(){
		window.location='../article/articles.jsplayout.vi';
	},
	mod:function(id){
		window.location='../article/article_addormod.jsplayout.vi?act=mod&id='+id; 
	},
	enter:function(id){
		window.location='../article/article.jsplayout.vi?id='+id; 
	},
	del:function(id){
		if(confirm("确定删除吗?")){
			jQuery.post("../article/article.del.json.do", {id:id}, function() {
				Article.back();
			}, 'json');
		}
		
	}
}
 