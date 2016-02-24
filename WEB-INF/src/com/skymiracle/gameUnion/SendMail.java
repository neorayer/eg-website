package com.skymiracle.gameUnion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.skymiracle.client.tcpClient.pop3Client.Pop3Client;
import com.skymiracle.client.tcpClient.smtpClient.SmtpClient;
import com.skymiracle.io.Dir;
import com.skymiracle.io.TextFile;
import com.skymiracle.mime.MimeCreater;
import com.skymiracle.skyUpload.SkyUpFile;
import com.skymiracle.skyUpload.SkyUpFiles;
import com.skymiracle.skyUpload.SkyUploadException;
import com.skymiracle.util.UUID;
import com.skymiracle.util.UsernameWithDomain;

public class SendMail {

	private static final int DEFAULT_WAIT_SECONDS = 5;

	private static final String DEFAULT_EMLCONTENT_TMPL = "From:$FROM$\r\n"
			+ "To: $TO$\r\n" + "Subject: 比赛挑战书\r\n"
			+ "$content$\r\n";

	private String host;

	private String from;

	private String to;
	
	private String emlContent;

	SmtpClient smtpClient = new SmtpClient();
	private ArrayList dataLineList = new ArrayList();

	private String getPlainFromHtml(String input) {
		try {
			Pattern pattern = Pattern.compile("<.*?>");
			Matcher m = pattern.matcher(input);
			StringBuffer sb = new StringBuffer();
			while (m.find()) {
				m.appendReplacement(sb, "");
			}
			m.appendTail(sb);
			return sb.toString().replaceAll("&lt;", "<")
					.replaceAll("&gt;", ">").replaceAll("&amp;", "&")
					.replaceAll("&nbsp;", " ").replaceAll("&quot;", "\"")
					.trim();
		} catch (Exception e) {
			return input;
		}
	}
	
	
	public void sendMail(String to, String subject,
			String charset, String priority, String content, String sendername , String fromEmail) throws Exception {
		String[] tos = to.split(",");
		ArrayList rcptList = new ArrayList();
		MimeCreater mcr = new MimeCreater();
		mcr.setFrom(sendername, fromEmail);

		if (!fromEmail.equals(""))
			mcr.setReplyTo(fromEmail);

		for (int i = 0; i < tos.length; i++) {
			String emailAddress = tos[i].trim();
			if (emailAddress.length() == 0)
				continue;
			mcr.addTo(emailAddress, emailAddress);
			rcptList.add(emailAddress);
		}
		mcr.setSubject(subject);
		mcr.setCharset(charset);
		mcr.setPriority(Integer.parseInt(priority));
		mcr.setPlainContent(content);

		String mimeTmpPath = "/tmp/" + "/" + new UUID().toShortString();

		ArrayList dataLineList = null;
		try {
			mcr.saveToFile(mimeTmpPath);
			dataLineList = TextFile.loadLinesList(mimeTmpPath);
			this.smtpClient.setFromEmail(fromEmail);
			this.smtpClient.send(to, dataLineList);
		} catch (Exception e) {
			com.skymiracle.logger.Logger.error("", e);
		}

		new File(mimeTmpPath).delete();
	}

	public static void main(String[] args) throws Exception {
		new SendMail().sendMail("zhuli@skymiracle.com","比赛挑战书","utf-8","3","比赛挑战书content","EG电竞","postmaster@51bisai.com");
	}
}
