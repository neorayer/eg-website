<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<style type="text/css">
#play-page-bisai-egrecord-body {
	clear: both;
}


</style>


<div id="play-page-bisai-egrecord-body">
	<table width="100%" cellspacing="0" cellpadding="0" id="player-egreplay-list-box">
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
			<c:forEach var='replayhis' items='${replayhis}'>
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
		<!-- 
		<tr>
				<td colspan="5">
					<div class="replay-his-pagebar">
					<c:out value='${pageBar}' escapeXml="false"/>
					</div>
				</td>
			</tr>
			 -->
	</table>
</div>

<DIV id="SOR_EXCEPTION" style="color:red"><c:out value="${REASON}" /></DIV>




<div style="clear: both;"></div>

<script type="text/javascript">

;$(document).ready(function() {

});  
</script>
