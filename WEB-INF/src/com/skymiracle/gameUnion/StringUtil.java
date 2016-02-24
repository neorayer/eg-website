package com.skymiracle.gameUnion;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

import com.skymiracle.logger.Logger;

public class StringUtil {
	public static String HTMLStringSub(String longString,int sublength,String lastAppend){
    	if(longString==null){
    		return "";
    	}else if(longString.length()<=sublength){
    		return longString;
    	}else if(longString.length()>sublength){
    		longString=longString.substring(0,sublength)+lastAppend;
    		return longString;
    	}else{
    		return "&nbsp;";
    	}
    }
	
	public static String creatSelect(Map selectMap, String selectName, String selectedKey, boolean isNoSelect) {
		return creatSelect(selectMap, selectName, selectedKey, isNoSelect, "");
	}

	public static String creatSelect(Map selectMap, String selectName, String selectedKey, boolean isNoSelect, String extStr) {
		if (extStr == null)
			extStr = "";
		String str = "<select name='" + selectName + "' " + extStr + ">";
		if (isNoSelect)
			str += "<option value='-1'>-未选择-</option>";
		String sed = "";
		if (selectMap != null) {
			Set keyset = selectMap.keySet();
			for (Iterator iter = keyset.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String value = (String) selectMap.get(key);
				sed = "";
				if (selectedKey != null && selectedKey.equals(key))
					sed = " selected ";
				str += "<option value='" + key + "' " + sed + ">" + value + "</option>";
			}
		}
		str += "</select>";
		return str;
	}
	
	public static List<String> getVoteString(String voteString){
		List<String> list=new ArrayList<String>();
		if(voteString==null||"".equals(voteString)||voteString.trim().equals("<br>")){
			return list;
		}
		if(voteString.indexOf("<br>")==-1){
			list.add(voteString);
			return list;
		}else{
			try{
			 while(voteString.indexOf("<br>")!=-1){
			   String subedString=voteString.substring(0,voteString.indexOf("<br>"));
			    if(subedString!=null&&!"".equals(subedString.trim())){
			    list.add(subedString);
			 }
			voteString = voteString.substring(voteString.indexOf("<br>")+4);
			}
			if(voteString!=null&&!"".equals(voteString.trim())){
				list.add(voteString);
			 }
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	public static String getVoteString(List<String> list){
		StringBuffer sb=new StringBuffer();
		for(String string:list){
			sb.append(string+"<br>");
		}
		return sb.toString();
	}
	public static String getRate(long totalQty,long sum){
	      float a=totalQty;
		  float b=sum;
		  if(b==0){
			  return "0";
		  }else{
		  float result=(float)(a*100/b);
		  String sValue=Float.toString(result);
		  String re=fourOutFiveIn(sValue);
		  return re;
		 }
	   }
	private static String fourOutFiveIn(String s){
		  String result=null;
		  char tempChar=s.charAt(s.indexOf(".")+1);
		  int tempInt=Character.getNumericValue(tempChar);
		  if(tempInt<=4){
			  result=s.substring(0, s.indexOf("."));
		  }else{
			  int iValue=Integer.valueOf(s.substring(0,s.indexOf("."))).intValue()+1;
			  result=Integer.toString(iValue);
		  }
		  return result;
	  }
	public static String getUrl(HttpServletRequest request){
		String ret="http://";
		ret=ret+request.getLocalAddr()+":"+request.getLocalPort()+request.getContextPath();
		//Logger.debug("url="+ret);
		//Logger.debug("contextPath="+request.getContextPath());
		return ret;
	}
	public static List<String> formatedRelatedDic(List<String> list){
		List<String> ret=new ArrayList<String>();
		if(list!=null&&list.size()>0){
			for(String string:list){
				if(string!=null&&!"".equals(string)){
					String noformatedString=string.trim();
					StringTokenizer token=new StringTokenizer(noformatedString," ");
					while(token.hasMoreElements()){
						ret.add(token.nextToken());
					}
				}
			}
		}
		return ret;
	}
	public static String getWinrate(int win,int lose){
		String strRate="";
		if(win==0){
       	  strRate="0%";
        }
		if(win!=0&&lose==0){
       	   strRate="100%";
       	}
        if(win!=0&&lose!=0){
       	  double rate=Integer.valueOf(win).doubleValue()/(win+lose);
          double dd=(double) (Math.round(rate*100)/100.0);
           NumberFormat nf=NumberFormat.getIntegerInstance();
           strRate=nf.format(dd*100)+"%";
        }
       return strRate;    
	}
	public static String getTopArticlePicUrl(String content){
		Pattern p = Pattern.compile("<img src=\"([^\"]*)");
		Matcher m = p.matcher(content);
		if(m.find()){
			Logger.debug("picUrl=="+m.group(1));
			return m.group(1);	
		}else{
			return "";
		}
	}
	public static String removeNbsp(String content){
		return content.replaceAll("&nbsp;", "");
	}
	public static String extractText(String inputHtml) throws Exception{
		  StringBuffer text = new StringBuffer();
		  Parser parser = Parser.createParser(inputHtml,"utf-8");
		  //遍历所有的节点
		  NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter(){
			   public boolean accept(Node node) {
			    return true;
			   }
		  	}
		  );
		  Node node = nodes.elementAt(0);
		  text.append(new String(node.toPlainTextString().getBytes("utf-8")));
		  return text.toString();
	}
	public static void main(String[] args) throws Exception {
		//getTopArticlePicUrl("1=="+"");
		//String text = extractText("<td>点击<b><a href=index.jsp>这里</a></b>回到首页</td>");
		//  System.out.println(text);
	}
}
