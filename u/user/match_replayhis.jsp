<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<div class="replayhis-body">
	<div class="replay-top-title">参赛录像</div>
	<table width="100%" cellspacing="0" cellpadding="0" class="replay-his-table">
		<thead>
			<tr>
				<td width="20%" align="center">比赛</td>
				<td width="" align="center">replay</td>
				<td width="15%" align="center">时间</td>
				<td width="10%" align="center">下载</td>
				<td width="10%" align="center">下载量</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var='replayhis' items='${replayhistorys}'>
			<tr>
				<td>
					<div class="replayhis-bisai-box" title="<c:out value='${replayhis.replay.bisai.title}'/>">
						<nobr>
						<c:out value='${replayhis.replay.bisai.title}'/>
						</nobr>
					</div>
				</td>
				<td>
					<div class="replayhis-replay-name-box"><NOBR><a href="javascript:Replay.data_export('<c:out value="${replayhis.replayId}"/>','<c:out value="${replayhis.id}"/>')"><c:out value='${replayhis.replay.name}'/></a></NOBR></div></td>
				<td align="center"><c:out value='${replayhis.createDate}'/></td>
				<td align="center"><a href="javascript:Replay.data_export('<c:out value="${replayhis.replayId}"/>','<c:out value="${replayhis.id}"/>')">下载</a></td>
				<td align="center"><c:out value='${replayhis.downtimes}'/>次</td>
			</tr>
			</c:forEach>
			
		</tbody>
		<tr>
				<td colspan="5">
					<div class="replay-his-pagebar">
					<c:out value='${pageBar}' escapeXml="false"/>
					</div>
				</td>
			</tr>
	</table>
</div>

<script type="text/javascript">
	var Replay = {
		data_export:function (id,hisid) {
			window.location = '../user/downloadReplay.down.do?id='+id+'&hisid='+hisid;
		}
	}
	$(document).ready(function(){
    	$(".replay-his-pagebar a").click(function(){
    		  var url = $(this).attr('href');
    		  $('.certificate-album').load("../user/match_replayhis.jsp.vi"+url);
			return false;
    	});
    })
</script>