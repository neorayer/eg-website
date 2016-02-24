<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" %>

<style>
<!--
	table.listTable{
		border:1px solid gray;
	}
   table.listTable td{
     text-align: center;
     border:1px solid gray;
   }
   p {
      padding-left:70px;
   }
-->
</style>

<div id="allvisitor-page">
	<div id="allvisitor-page-title">
		<div class="floatLeft">
			<strong>积分等级规则</strong>
		</div>
		<div class="floatRight">
			<a href="javascript:history.back(-1)">返回>></a>
		</div>
	</div>
	



<table width="734" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="708" height="8" align="center">
	    <h1></h1>
    </td>

  </tr>
  <tr>
    	<td valign="top">
       	    <p >EG平台严格按照bn积分等级划分规则,各个等级需要的积分条件如下表</p>
       	    <p >表一：各级经验值表</p>
       	   
       	    <table border=1 align=center width=80% class="listTable" cellpadding="0" cellspacing="0">
       	        <tr>
       	            <td>级别</td>
       	            <td>基础经验</td>
       	            <td>升级经验</td>
       	            <td>经验值系数</td>
       	        </tr>
       	        <tr>
       	            <td>1</td>
       	            <td>0</td>
       	            <td>100</td>
       	            <td>0.10</td>
       	        </tr>
       	        <tr>
       	            <td>2</td>
       	            <td>100</td>
       	            <td>100</td>
       	            <td>0.11</td>
       	        </tr>
       	        <tr>
       	            <td>3</td>
       	            <td>200</td>
       	            <td>200</td>
       	            <td>0.11</td>
       	        </tr>
       	        <tr>
       	            <td>4</td>
       	            <td>400</td>
       	            <td>200</td>
       	            <td>0.25</td>
       	        </tr>
       	        <tr>
       	            <td>5</td>
       	            <td>600</td>
       	            <td>300</td>
       	            <td>0.25</td>
       	        </tr>
       	        <tr>
       	            <td>6</td>
       	            <td>900</td>
       	            <td>300</td>
       	            <td>0.43</td>
       	        </tr>
       	        <tr>
       	            <td>7</td>
       	            <td>1200</td>
       	            <td>400</td>
       	            <td>0.43</td>
       	        </tr>
       	        <tr>
       	            <td>8</td>
       	            <td>1600</td>
       	            <td>400</td>
       	            <td>0.67</td>
       	        </tr>
       	        <tr>
       	            <td>9</td>
       	            <td>2000</td>
       	            <td>500</td>
       	            <td>0.67</td>
       	        </tr>
       	        <%for(int i=10;i<=49;i++){%>
       	        <tr>
       	            <td><%=i%></td>
       	            <td><%=2500+(i-10)*500%></td>
       	            <td>500</td>
       	            <td>1.00</td>
       	        </tr>
       	        <%}%>
       	        <tr>
       	            <td>50</td>
       	            <td>22500</td>
       	            <td>--</td>
       	            <td>1.00</td>
       	        </tr>
       	    </table>
       	    <p>表二：不同等级之间输赢导致的经验值变化</p>
            <p>如果输者的表一对应的经验值系数为LF</p>
            <p>那么高等级赢，经验值加（级别差对应的表二经验）,高等级输经验值减（级别差对应的表二经验*LF）</p>
            <p>如果低等级赢，经验值加（级别差对应的表二经验）,低等级输经验值减（级别差对应的表二经验*LF）</p>
            <p>同等级输赢经验值加减100，</p>     
            <table border=1 border=1 align=center width=80% class="listTable" cellpadding="0" cellspacing="0">
                <tr>
                    <td>级别差</td>
       	            <td>级别高的赢</td>
       	            <td>级别高的输</td>
       	            <td>级别低的赢</td>
       	            <td>级别低的输</td>
                </tr>
                <tr>
                    <td>0</td>
       	            <td>100</td>
       	            <td>100</td>
       	            <td>100</td>
       	            <td>100</td>
                </tr>
                <tr>
                    <td>1</td>
       	            <td>95</td>
       	            <td>105</td>
       	            <td>115</td>
       	            <td>85</td>
                </tr>
                <tr>
                    <td>2</td>
       	            <td>90</td>
       	            <td>110</td>
       	            <td>130</td>
       	            <td>70</td>
                </tr>
                <tr>
                    <td>3</td>
       	            <td>85</td>
       	            <td>115</td>
       	            <td>145</td>
       	            <td>55</td>
                </tr>
                <tr>
                    <td>4</td>
       	            <td>80</td>
       	            <td>120</td>
       	            <td>155</td>
       	            <td>45</td>
                </tr>
                <tr>
                    <td>5</td>
       	            <td>75</td>
       	            <td>125</td>
       	            <td>165</td>
       	            <td>35</td>
                </tr>
                <tr>
                    <td>6</td>
       	            <td>70</td>
       	            <td>130</td>
       	            <td>175</td>
       	            <td>25</td>
                </tr>
            </table>      	    
            <p>战队等级:
            <p>只有战队和战队之间比赛，才能计入战队积分，如果有其他不属于战队的人加入该场比赛，则该场比赛结果
            <p>无法计入战队积分，但可以计入个人战队积分，战队获得的积分由出场选手的平均等级决定，同样，如果平
            <p>均等级相差7级或者7级以上，则比赛双方无法获得分数。
    	</td>
  </tr>

</table>
</div>