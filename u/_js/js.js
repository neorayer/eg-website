
// 跟"修改"UI相关的JS函数
var EditorX = {
	isEditing: false,
	isEditFileing: false,
	//增强文本型数据
	enhanceText: function(selector, props) {
		$(selector)
			.click(function() {
				//如果正在编辑状态，则不响应其他的mousemove
				if (EditorX.isEditing)
					return;
					
				//隐藏数据，显示editor
				$(this).hide().next().fadeIn('fast');
				$(this).hide().next().focus();
			})
			.mouseover(function() {
				//如果正在编辑状态，则不响应其他的mousemove
				if (EditorX.isEditing)
					return;
					
				//隐藏数据，显示editor
				$(this).hide().next().fadeIn('fast');
				$(this).next().css("cursor", "pointer");
			});
		$(selector).next()
			.focus(function() {
				EditorX.isEditing = true;
				$(this).data('isFocus',true);

				//保留原数据
				$(this).data('oldVal', $(this).val());
			})
			.blur(function() {
				EditorX.isEditing = false;
				//设置失去焦点
				$(this).data('isFocus', false);
				
				//取得一些变量
				var field = $(this).attr("name");
				var val = $(this).val();
				var oldVal = $(this).data('oldVal');
				
				//设置数据：输入框 => prev
				$(this).prev().html(val);
				
				//如果变量发生变化，则提交
				if (oldVal != val) {
					if (props.onChanged) {
						props.onChanged(field, val);
					}
				}
					
				//隐藏输入框，显示数据
				$(this).hide().prev().show();
			})
			.keypress(function(e) {
				//回车也疯狂
				if (13 == e.which)
					$(this).blur();
			})
			.mouseout(function() {
				//没取得焦点，不响应mouseout
				if ($(this).data('isFocus'))
					return;

				//隐藏输入框，显示数据
				$(this).hide().prev().show();
			});
			
		return;
	},
	
	//增强文件型数据
	enhanceFile: function(selector, props) {
		$(selector)
			//mouseover事件
			.click(function() {
				if (EditorX.isEditFileing){
					$(selector).next().fadeOut();
					EditorX.isEditFileing=false;
				}else{
					$(selector).not($(this)).next().fadeOut();
					//显示"上传"
					$(this).next().fadeIn();
					$(this).next().validate({
						submitHandler: function(form) {
								jQuery(form).ajaxSubmit(function(data){
									EditorX.isEditFileing=false;
								});
							}
						});
					EditorX.isEditFileing=true;
				}
			})
			 
		$inputFile = $(selector).next();
		 
	}
}

// 弹出窗口函数

var PopupWinX = {
	/**
	 * props {
	 * 	className
	 *	closeEvent
	 *	buttons:[
	 *		{title:'', event: functino() {}}	
	 *	]
	 * }
	 */
	create: function(dom, props) {
		var $dom = $(dom);
		var props = props || {};
		
		var $winPrototype = $("#WIN_Manage");
		var $win = $winPrototype.clone();
		$win.appendTo($winPrototype.parent());
		
		if (props.className)
			$win.removeClass(props.className).addClass(props.className);
			
		if(props.closeEvent)
			$win.data("closeEvent", props.closeEvent);
		
		/**	
		if(props.buttons) {
			var $buttons = $('<div style="text-align: center;margin-top: 20px;"/>');
			$(props.buttons).each(function() {
				if(!this.title || !this.event)
					return;
					
				var $button = $('<button class="button" style="margin-right: 5px;">' + this.title + '</button>');
				$button.click(this.event);
				$buttons.append($button);
			});
			$dom.append($buttons);
		}	
		*/
			
		//把dom放入窗口
		var body = $win.find('.WIN_Body');
		$dom.appendTo(body.html('')).show();

		//$win.show();
		PopupWinX.centerIt($win);
		
		//隐藏选择框
		$("select").hide();
		
		return $win;
	},
	
	centerIt: function($win) {
		$win.css({
			'position': 'absolute',
			'z-index': '999',
			'left': ($(window).width() - parseInt($win.width())) / 2,
			'top': ($(window).height() - parseInt($win.height())) / 2
		});
	},
	
	close: function(el) {
		//显示选择框
		$("select").show();
		$win = $(el).parents(".WIN");
		if($win.data('closeEvent'))	
			$win.data('closeEvent')();
		$win.fadeOut(function() {$(this).remove()});
	}
}



$.widget("ui.imgCutter", {
	_init: function() {
		var opt = this.options;
		var self = this;
		var $shader = $(this.element).children("table");
		$shader.find("td")
			.css("background-color", "gray");
		$shader.find("td:eq(2)")
			.css("background-color", "transparent")
			.css("border", "1px solid black");

		self.viewerL = $shader.find("td:eq(1)").width();
		self.viewerT = $shader.find("td:eq(0)").height();
		self.viewerW = $shader.find("td:eq(2)").width();
		self.viewerH = $shader.find("td:eq(2)").height();

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
		var $img = this.$img = $(document.createElement("img"))
			.css("position", "absolute")
			.css("left", 0)
			.css("top", 0)
			.prependTo(this.element)
			.attr("src", opt.img)
		;
		
		opt.imgOriWidth = $img.width();
		opt.imgOriHeight = $img.height();
		$img[0].onload = function(){
			opt.imgOriWidth = $img.width();
			opt.imgOriHeight = $img.height();
		};
		
		/*
		var eW = $(this.element).width();
		var eH = $(this.element).height();
		var imgW = $img.width();
		var imgH = $img.height();
		var imgL = (eW - imgW ) / 2;
		imgL = imgL < 0 ? 0: imgL;
		var imgT = (eH - imgH ) / 2;
		imgT = imgT < 0 ? 0: imgT;
		$img
			.css("left", imgL + "px")
			.css("top", imgT + "px");
		*/
			
		
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
					self.cut();
			})
		;
		
		var $slider = this.$slider = $(document.createElement("div"))
			.insertAfter(this.element)
			.html('<a style="left: 50%;" class="ui-slider-handle ui-state-default ui-corner-all" href="#"></a>')
			.attr("class", "ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all")
			.css("width", $shader.width())
			.css("margin", "10px 0 0 0")
			.slider({
				range: "min",
				min: 1,
				max: 200,
				slide: function(event, ui) {
					var w = parseInt(self.options.imgOriWidth * ui.value / 100);
					$img
						.attr("width", w)
					;
				}
			})
		;
		
		$slider.slider("value", 100);
	},
	
	cut: function() {
		var l = this.viewerL - parseInt(this.$img.css("left"));
		var t = this.viewerT - parseInt(this.$img.css("top"));
		var persent = this.$slider.slider("value");
		this.options.cut(persent, l, t, this.viewerW, this.viewerH );
	}
});
