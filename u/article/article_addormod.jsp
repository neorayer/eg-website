<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<script type="text/javascript" src="../../_js/tiny_mce/tiny_mce.js"></script>
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

</style>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>战斗日记--新增</strong>
		</div>
		<div class="floatRight">
			<a href="javascript:history.back(-1)"/>返回>></a>
		</div>
	</div>
	
	
	<div id="page-photos-body-box">
 
		<form id='AddArtForm' action="../article/article.addormod.jsp.do" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="act" value="<c:out value="${act}" />" />
			<input type="hidden" name="id" value="<c:out value="${article.id}" />" />
			
			<div id="page-article-mod-title-box">
				<label class="label">  标题: </label>
				<input type='text' name='title' id="addart-title" maxlength="30" value='<c:out value="${article.title}"/>' style="width:85%" />
				<p class="time">
					时间: (<c:out value='${systime}'/>)
				</p>
			</div>
			
			<div id="page-article-mod-editor-box" align="center" class="editor">
				<textarea name="content" style="width:95%" rows="10">
					<c:out value="${article.content}"/>
				</textarea>
			</div>
		</form>
		
		<div  id="page-article-mod-foot-box">
			<label class="label" for="title">图片:</label>
			<iframe id="uploadFrame" name="uploadFrame" src="about:blank" ></iframe>
			<form class="picForm" action="<c:out value='../article/articleattach.add.jsp.do?newsid=${article.id}'/>"  target="uploadFrame" method="post" enctype="multipart/form-data" id="picform" name="picform">
				<input type="file" id="file" name="file" value=""/>&nbsp;<input type="submit" value="上传" class="button2"/>
			</form>
		</div>
		
		<div  id="page-article-mod-buttons-box" align="center">
			<input type="button" class="button" id="addormod-buttom" value="保存" onclick="javascript:subForm();"/>
			<input type="button" class="button" value="返回" onclick="Article.back();"/>
		</div>
		
	</div>
</div>
 
<script language="javascript">
	function insImg(url) {
		s = "<img src=" + url + " />";
		tinyMCE.execCommand('mceInsertContent',false, s);
	}
	function subForm(){
		if(checknews()){
			document.forms.AddArtForm.submit();
		}
	}
	function checknews(){
		var title=document.getElementById('addart-title').value;
		if(title==''){
			alert("文章标题不能为空");
			return false;
		}
		return true;
	}
	
	 
</script>

