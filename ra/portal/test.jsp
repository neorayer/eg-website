<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<style type="text/css">
#img-cutter {
	border:3px solid black;
}

#cutter-t-shader,
#cutter-l-shader,
#cutter-r-shader,
#cutter-b-shader
{
	background-color: white;
}

#cutter-view-box {
	border:1px solid black;
}
</style>

<div>
	<h2>hall Add</h2>
	<div id="img-cutter" >
		<table cellpadding="0" cellspacing="0">
			<tr  id="cutter-t-shader">
				<td colspan="3" height="100"><br />&nbsp;</td>
			</tr>
			<tr >
				<td id="cutter-l-shader" width="100" ><br />&nbsp;</td>
				<td id="cutter-view-box" width="100" height="100"><br />&nbsp;</td>
				<td id="cutter-r-shader" width="100"><br />&nbsp;</td>
			</tr>
			<tr id="cutter-b-shader">
				<td colspan="3" height="100"><br />&nbsp;</td>
			</tr>
		</table>
	</div>

</div>

<script type="text/javascript">
$.widget("ui.imgCutter", {
	_init: function() {
		var opt = this.options;
		var self = this;
		var $shader = $(this.element).children("table");
		self.viewerL = $shader.find("td:eq(1)").width();
		self.viewerT = $shader.find("td:eq(1)").height();
		self.viewerW = $shader.find("td:eq(2)").width();
		self.viewerH = $shader.find("td:eq(2)").height();


		opt.img = "sample.jpg";
		
		$shader
			.css("position", "relative")
			.css("opacity", "0.5")
		;

		$(this.element)
			.css("position", "relative")
			.css("width", $shader.width())
			.css("height",  $shader.height())
			.css("overflow", "hidden")
		;
		
		//create img
		var $img = $(document.createElement("img"))
			.css("position", "absolute")
			.css("left", 0)
			.css("top", 0)
			.prependTo(this.element)
			.attr("src", opt.img)
		;
		
		opt.imgOriWidth = $img.width();
		opt.imgOriHeight = $img.height();
		
		
		//create dragger
		var $dragger = $(document.createElement("div"))
			.css("position", "absolute")
			.css("left", -2000)
			.css("top", -2000)
			.css("cursor", "move")
			.appendTo(this.element)
			.css("width", 4000)
			.css("height", 4000)
			.draggable({
				cursor: 'move',
				drag: function(event, ui) {
					$img
						.css("left", parseInt($(this).css("left")) + 2000)
						.css("top", parseInt($(this).css("top")) + 2000)
					;
				}
			})
			.dblclick( function() {
				if (!self.options.cut)
					return;
				var l = self.viewerL - parseInt($img.css("left"));
				var t = self.viewerT - parseInt($img.css("top"));
				var persent = $slider.slider("option", "value");
				self.options.cut(persent, l, t);
			})
		;
		
		var $slider = $(document.createElement("div"))
			.insertAfter(this.element)
			.html('<a style="left: 50%;" class="ui-slider-handle ui-state-default ui-corner-all" href="#"></a>')
			.attr("class", "ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all")
			.css("width", $shader.width())
			.css("margin", "10px 0 0 0")
			.slider({
				min: 1,
				max: 200,
				value: 100,
				slide: function(event, ui) {
					var w = parseInt(self.options.imgOriWidth * ui.value / 100);
					$img
						.attr("width", w)
					;
				}
			})
			
		;
		
		
			
	}
});

$("#img-cutter").imgCutter({
	cut: function(persent, left, top) {
		alert(persent +"," + left +"," + top);
	}
});
</script>

