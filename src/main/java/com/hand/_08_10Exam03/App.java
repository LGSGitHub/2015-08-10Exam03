package com.hand._08_10Exam03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		new OutputAsXMLJSON().start();
	}
}

class OutputAsXMLJSON extends Thread {
	@Override
	public void run() {
		try {

			URL url = new URL("http://hq.sinajs.cn/list=sz300170");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);

			String line;

			StringBuilder builder = new StringBuilder();
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				builder.append(line);// 把结果一条一条地添加到SringBuilder中
			}

			//System.out.println(builder.toString());
			
			File file = new File("exam03.xml");
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			bw.write(builder.toString());
			
			bw.close();
			osw.close();
			fos.close();
			
			File file2 = new File("exam03.json");
			FileOutputStream fos2 = new FileOutputStream(file2);
			OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "UTF-8");
			BufferedWriter bw2 = new BufferedWriter(osw2);
			
			bw2.write(builder.toString());
			
			System.out.println("写入完成");
			
			bw2.close();
			osw2.close();
			fos2.close();

			br.close();
			isr.close();
			is.close();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
