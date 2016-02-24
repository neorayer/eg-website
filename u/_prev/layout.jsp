<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	
	%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%//
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
		
		<style type="text/css">
			/** base font rules **/
			/** granular element rules **/
			p,form,fieldset,h1,h2,h3,h4,h5,h6,input,ul,li {
				margin: 0;
				padding: 0;
			}
			
			ul,li {
				list-style-type: none;
			}
			
			input {
				padding-left: 3px;
			}
			
			img,fieldset {
				border: 0
			}
			
			legend {
				display: none
			}
			
			label {
				font-weight: normal;
				cursor: pointer;
				cursor: hand;
				
			}
			
			body {
				margin: 0;
				padding: 0;
			}
			
			/* font */
			body, td, th,input, h3{
				font-size: 12px;
				font-family:  courier new, courier, monospace,宋体;
			}

			div {
				border:1px dashed gray;
			}
			
			/*****************************/
			#p-box {
				width: 1000px;
				margin: 0 auto;
			}
			
			#phead-box {
			}
			#phead-t-box {
				height: 24px;
			}
			
			#phead-m-box {
				height: 128px;
			}
			
			#phead-m-l-box {
				width: 276px;
				height: 128px;
				float: left;
				
				background-color: #e9e9e9;
			}

			#phead-m-c-box {
				width: 484px;
				height: 128px;
				float: left;
				background-color: #cccc66;
			}
			
			#phead-m-r-box {
				width: 240x;
				height: 128px;
				background-color: #a2a235;
			}
			
			#phead-b-box {
				height: 25px;
			}
			
			#phead-menu-list li{
				display: inline;
				float: left;
			}

			#phead-menu-list li a{
				background-color: #787866;
				display: block;
				color: white;
				text-decoration: none;
				height: 22px;
				line-height: 22px;
				padding: 0 14px 0 17px;
				margin: 3px 0 0 5px;
				letter-spacing: 3px;
			}
		</style>
	</head>
	

	<body>
		<div id="p-box">
			<div id="phead-box">
				<div id="phead-t-box" >
				</div>
				<div id="phead-m-box">
					<div id="phead-m-l-box" >
					</div>
					<div id="phead-m-c-box" >
					</div>
					<div id="phead-m-r-box" >
					</div>
				</div>
				<div id="phead-b-box">
					<ul id="phead-menu-list">
						<li>
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">首页</a>
						</li>
					</ul>
				</div>
			</div>
			<div id="pbody-box">
			</div>
			<div id="pfoot-box">
			</div>
		</div>	
	</body>

	<script type="text/javascript">
	</script>
</html>
