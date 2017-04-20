package com.mmdata.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class parseTxt {
	
		public static void main(String[] args) {
			URL resource = parseTxt.class.getResource("/file.txt");
			String path = resource.getPath();
			System.out.println("resource = "+path);
			try {
				String readFile = readFile(path);
				String[] split = readFile.split(";");
				for(int i=0;i<split.length;i++){
					System.out.println("split["+i+"] = "+split[i]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
	public static String readFile(String filePath) throws IOException{
		StringBuffer sb=  null;
		try {
		String encoding = "gbk";
		 sb = new StringBuffer();
		File file = new File(filePath);
		if(file.isFile() && file.exists()){
			
				InputStreamReader reader = new InputStreamReader(new FileInputStream(file),encoding);
				BufferedReader bufferedReader = new BufferedReader(reader);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					sb.append(lineTxt).append(";");
					System.out.println("line = "+lineTxt);
					
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
		System.out.println("sb = "+sb.toString());
		return sb.toString();
	}
}
