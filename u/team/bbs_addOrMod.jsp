<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<script language="javascript" >
tinyMCE.init({
	// General options
	mode : "textareas",
	language:"zh",
	theme : "advanced",
	fontSize: "12px",
	plugins : "safari,layer,advhr,advlink,emotions,inlinepopups,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable",
	// Theme options
	theme_advanced_buttons1 : "bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,formatselect,fontselect,fontsizeselect",
	theme_advanced_buttons2 : "forecolor,backcolor,|,bullist,numlist,|,outdent,indent,link,unlink,|,undo,redo",
	//theme_advanced_buttons3 : "",
	theme_advanced_toolbar_location : "top",	
	theme_advanced_toolbar_align:"left",
	theme_advanced_fonts:"宋体=宋体;黑体=黑体;仿宋=仿宋;楷体=楷体;隶书=隶书;幼圆=幼圆;Arial=arial,helvetica,sans-serif;Comic Sans MS=comic sans ms,sans-serif;Courier New=courier new,courier;Tahoma=tahoma,arial,helvetica,sans-serif;Times New Roman=times new roman,times;Verdana=verdana,geneva;Webdings=webdings;Wingdings=wingdings,zapf dingbats",
	convert_fonts_to_spans:true,
	remove_trailing_nbsp:true,
	remove_linebreaks:false,
	width:"100%",
	height: '450px',
	relative_urls:false,
	theme_advanced_resizing : true,

	// Example content CSS (should be your site CSS)
	content_css : "",

	// Drop lists for link/image/media/template dialogs
	template_external_list_url : "lists/template_list.js",
	external_link_list_url : "lists/link_list.js",
	external_image_list_url : "lists/image_list.js",
	media_external_list_url : "lists/media_list.js"

});
</script>

<style type="text/css">
#team-bbs-add-box{
	padding: 10px 20px 10px 20px;
	width: 90%;
}

#team-bbs-add-top-box{
	height: 30px;
	line-height: 30px;
}

#team-bbs-add-top-box #back-link{
	float: right;
	margin-right: 5px;
}

#team-bbs-add-middle-box{
	border-top:1px solid #96CE0D;
	border-bottom:1px solid #96CE0D;
	background:#F7F7F7 repeat-x scroll 0 0;
	overflow:hidden;
}

#team-bbs-add-title-box{
	text-align: left;
	padding: 10px 0px;
}
 
.label{
	color:#333333;
	font-weight:bold;
}

#team-bbs-add-content-box{
	padding: 10px 0px;
}
#team-bbs-add-imgs-box{
	padding: 10px 0px;
}
#team-bbs-add-buttons-box{
	padding: 10px 0px;
} 
#uploadFrame{
	position: absolute;
	left:-1000px;
}
</style>


<div id="team-bbs-add-box">

	<div id="team-bbs-add-top-box">
		<a href="javascript:" onclick="Team_BbsAdd_Page.back();" id="back-link">返回</a>
	</div>
	
	<div id="team-bbs-add-middle-box">
		<form id="team-bbs-add-form" action="../team/bbs.addOrMod.jsp.do" target="uploadFrame" method="post">
			<input type="hidden" name="uuid" value="<c:out value="${bbs.uuid}" />" />
			<input type="hidden" name="teamid" value="<c:out value="${team.id}" />" />
			
			<div id="team-bbs-add-title-box">
				<label class="label">标题:</label>
				<input type='text' id="bbs-title" name='title' maxlength="25" value='<c:out value="${bbs.title}"/>' style="width:85%" />
			</div>
		
			<div id="team-bbs-add-content-box" align="center" class="editor">
				<textarea id="bbs-content" name="content" style="width:95%" rows="10">
					<c:out value="${bbs.content}"/>
				</textarea>
			</div>
			<input id="team-bbs-add-submit-button" type="submit" style="display: none"/>
		</form>
		
		<div  id="team-bbs-add-imgs-box" class="fwrap">
			<label class="label" for="title">图片:</label>
			<iframe id="uploadFrame" name="uploadFrame" src="about:blank" ></iframe>
			<form action="<c:out value='../team/bbs_attach.add.jsp.do?id=${bbs.uuid}'/>"  target="uploadFrame" method="post" enctype="multipart/form-data">
				<input type="file" id="file" name="file" value=""/>&nbsp;
				<input type="submit" value="上传" class="button2"/>
			</form>
		</div>
		
		<div  id="team-bbs-add-buttons-box" align="center">
			<input class="button" type="button" value="保存" onclick="javascript: Team_BbsAdd_Page.add_bbs();"/>
			<input class="button" type="button" value="返回" onclick="javascript: Team_BbsAdd_Page.back();"/>
		</div>
	</div>
</div>
 

<script language="javascript">
function insImg(url) {
	s = "<img src=" + url + " />";
	tinyMCE.execCommand('mceInsertContent',false, s);
}

Team_BbsAdd_Page = {
	back: function() {
		Team_Coll_Page.load('../team/bbses.jsp.vi');
	},
	
	add_bbs: function() {
		$('#team-bbs-add-form').submit();
	}
}	

$(document).ready(function() {
});
	 
</script>

