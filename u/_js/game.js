var Games = [
	{
		gameId: 'war3rpg',
		gameName: '魔兽争霸RPG',
		maps: [
			{
				mapId: 'war3rpg-z3',
				mapName: '真三国无双'
			},
			{
				mapId: 'war3rpg-dota',
				mapName: 'DotA'
			},
			{
				mapId: 'war3rpg-3corc',
				mapName: '3CORC'
			},
			{
				mapId: 'war3rpg-ch3',
				mapName: '澄海3C'
			},
			{
				mapId: 'war3rpg-coll',
				mapName: 'RPG综合区'
			}
		]
	},
	
	{
		gameId: 'cs1.5',
		gameName: '反恐精英1.5',
		maps: [
			{
				mapId: 'cs1.5-free',
				mapName: '自由区'
			}
		]
	},
	
	{
		gameId: 'war3',
		gameName: '魔兽争霸Ⅲ',
		maps: [
			{
				mapId: 'war3-coll',
				mapName: '自由区'
			}
		]
	},
	
	{
		gameId: 'cs1.6',
		gameName: '反恐精英1.6',
		maps: [
			{
				mapId: 'cs1.6-free',
				mapName: '自由区'
			}
		]
	},
	
	{
		gameId: 'star.craft',
		gameName: '星际争霸',
		maps: [
			{
				mapId: 'star.craft-coll',
				mapName: '综合区'
			}
		]
	}
];

var GamesHelper = {
	showAll: function(ps, ss) {
		var $ps = $(ps);
		var $ss = $(ss);
		
		var ps_oldv = $ps.val();
		var ss_oldv = $ss.val();
		
		$ps.empty();
		$(Games).each(function() {
			$option = $('<option />');
			$option
				.attr('value', this.gameId)
				.text(this.gameName)
				.data('maps', this.maps);
				
			if(this.gameId == ps_oldv)
				$option.attr('selected', 'true');
				
			$ps.append($option);
		});
		
		$ps.change(function() {
			var $select_option = $('option:selected',this);
			if($select_option.length == 0)
				return;
			
			var maps = $select_option.data('maps');
			$ss.empty();
			$(maps).each(function() {
				$option = $('<option />');
				$option
					.attr('value', this.mapId)
					.text(this.mapName);
					
				if(this.mapId == ss_oldv)
					$option.attr('selected', 'true');
				
				$ss.append($option);	
			});
		});
		
		$ps.change();
	},
	
	parse: function(ps, ss) {
		var $ps = $(ps);
		var $ss = $(ss);
		
		$(Games).each(function() {
			if(this.gameId == $.trim($ps.text())) {
				$ps.text(this.gameName);	
				
				$(this.maps).each(function() {
					if(this.mapId == $.trim($ss.text())) {
						$ss.text(this.mapName);
					}
				});
			}	
		});
	}
}