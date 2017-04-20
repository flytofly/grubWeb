package com.mmdata.commonUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllHostSplitTo {
	
	
	public static void main(String[] args) throws IOException {
		/*String filePath = "E:/feedback/all-shost.txt";
		String[] hangye = {"dianping.com","meituan.com","diditaxi.com.cn","10101111.com","ctrip.com","qunar.com","zhihu.com","163.com","jd.com","tmall.com","cmbchina.com","icbc.com.cn","eastmoney.com","10jqka.com.cn","gw.com.cn","xueqiu.com","u51.com","autohome.com.cn","yiche.com","babytree.com","mia.com"};
		//String[] hangye = {"dianping.com"};
		for(int i=0;i<hangye.length;i++){
			AllHostSplitTo.judgeFile(filePath,hangye[i]);
		}*/
		String topDomain = AllHostSplitTo.getTopDomain("http://push.mobile.meituan.com");
		System.out.println(topDomain);
	}
	
	
	public static String getTopDomain(String url) {
		if(url == null){
			return "";
		}
		String host = "";
		try {
			host = new URL(url).getHost().toLowerCase();// 此处获取值转换为小写
		} catch (MalformedURLException e) {
			return "";
		}
		Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.edu\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.tel|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
		Matcher matcher = pattern.matcher(host);
		while (matcher.find()) {
			return matcher.group();
		}
		return "";
	}
	
	
	
    public static void judgeFile(String filePath,String demo) throws IOException{
		
		try {

			String encoding = "utf-8";
		StringBuffer demoStr = new StringBuffer();
		
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			    
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					String[] split = lineTxt.split("\\|");
					String host = split[2];
					String topDomain = AllHostSplitTo.getTopDomain("http://"+host);
					if(topDomain.equals(demo)){
						demoStr.append(lineTxt).append("\n");
					}
					
				}
				String substring = demo.substring(0, demo.indexOf("."));
				AllHostSplitTo.output(demoStr.toString(), "E:/feedback/data/"+substring+".txt");
				reader.close();
			
			}else {
				System.out.println("系统找不到指定文件");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}
    
    
    
    public  static void output(String str, String fileName)
	{
		File outFile = new File(fileName);

		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter( new FileOutputStream(outFile, true), "utf-8") );
			pw.println(str);
			pw.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	

}
