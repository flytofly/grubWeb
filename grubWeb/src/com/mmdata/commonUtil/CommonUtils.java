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
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
	
	
	public static void main(String[] args) throws IOException {
		String filePath_allHost = "E:/feedback/all-shost.txt";
		String feedback_resu = "E:/feedback/feedbak_0330.txt";
		String friendRecommandPath = "E:/feedback/friendRecommand_1.txt";
		String duanxinPath = "E:/feedback/duanxin_1.txt";
		Map<String, String> readFileMap = CommonUtils.readFile(filePath_allHost);
		CommonUtils.judgeFile(feedback_resu, readFileMap,friendRecommandPath,duanxinPath);
		
	}
	
	
	
	
    public static void judgeFile(String filePath,Map<String,String> map,String friendRecommandPath,String duanxinPath) throws IOException{
		
		StringBuffer sb=  null;
		try {
		String encoding = "utf-8";
		 sb = new StringBuffer();
		 StringBuffer friendRecommand = new StringBuffer();
		 StringBuffer duanxin = new StringBuffer();
		 StringBuffer friendRecommand_chongzhi = new StringBuffer();
		 StringBuffer friendRecommand_licai = new StringBuffer();
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			    
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					String[] split = lineTxt.split("\t");
					String phone = split[1];
					String chongzhi_time = split[4];
					String licai_time = split[6];
					String string = map.get(phone);
					if(!chongzhi_time.equals("0")){
						if(string != null && string != ""){
							duanxin.append(lineTxt).append("\n");
						}else{
							if(!licai_time.equals("0")){
								friendRecommand_licai.append(lineTxt).append("\n");
							}else{
								friendRecommand_chongzhi.append(lineTxt).append("\n");
							}
							friendRecommand.append(lineTxt).append("\n");
						}
					}
				}
				CommonUtils.output(duanxin.toString(),duanxinPath);
				CommonUtils.output(friendRecommand.toString(),friendRecommandPath);
				CommonUtils.output(friendRecommand_licai.toString(),"E:/feedback/friendRecommand_licai1.txt");
				CommonUtils.output(friendRecommand_chongzhi.toString(),"E:/feedback/friendRecommand_chongzhi1.txt");
				
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
	
public static Map<String,String>  readFile(String filePath) throws IOException{
		
		Map<String,String> map = null;
		try {
		String encoding = "utf-8";
		File file = new File(filePath);
		map = new HashMap<String, String>();
		
		if(file.isFile() && file.exists()){
			
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					String[] split = lineTxt.split("\\|");
					map.put(split[0], split[2]);
				}
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
		return map;
	}
}
