package com.mmdata.util;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

public class GrubUtil {
	
	public static void main(String[] args) {
		try {
			String url = "http://esf.fang.com/house/c61-kw%b2%fd%c6%bd/";
			System.out.println(grubWeb(url, "gbk"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String grubWeb(String strUrl,String chart) throws Exception{
		
        BufferedInputStream bufferedInputStream = null;
		URL url = new URL(strUrl);
		System.out.println("²É¼¯£º" + strUrl);
		try {
			bufferedInputStream = new BufferedInputStream(new GZIPInputStream(
					url.openStream()));
		} catch (java.util.zip.ZipException e) {
			bufferedInputStream = new BufferedInputStream(url.openStream());
		}catch(IOException e){
			try{
				bufferedInputStream = new BufferedInputStream(url.openStream());
			}catch(FileNotFoundException e1){
				
			}
			
		}
		if(bufferedInputStream==null){
			return "";
		}
		
		byte[] bytes = new byte[1024];
		int readCnt = 0;
		StringBuilder sBuilder = new StringBuilder("");
		while ((readCnt = bufferedInputStream.read(bytes, 0, 1024)) != -1) {
			sBuilder.append(new String(bytes, 0, readCnt, Charset.forName(chart)));
		}

		bufferedInputStream.close();
		return sBuilder.toString();
}

}
