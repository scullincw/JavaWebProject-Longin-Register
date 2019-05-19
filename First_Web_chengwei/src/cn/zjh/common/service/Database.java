package cn.zjh.common.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

	// 通过编译后的文件相对路径来获取users.txt数据文件
	private String userPath = Database.class.getClassLoader().getResource("../../data/users.txt").getPath();
	

//	打开存储数据的文件并在文件末尾追加用户数据
	public void addAccount(UserInfo userInfo) {
		System.out.println(userPath);

		FileWriter outWriter = null;
		try {
			outWriter = new FileWriter(userPath, true);
		} catch (IOException e) {
			System.out.println("File can't be found or creats errors!");
		}

		BufferedWriter outStream = new BufferedWriter(outWriter);
		try {
//			System.out.println("写入数据：account:" + userInfo.getAccount() + "  password:" + userInfo.getPassword()
//			+ "  tel:" + userInfo.getTel()+ "  email:" + userInfo.getEmail());
			outStream.newLine();
			outStream.write(userInfo.getAccount() + "," + userInfo.getPassword() + "," + userInfo.getTel() + ","
					+ userInfo.getEmail());
//			System.out.println("写入！");
//			关闭顺序，先关闭过滤流再关闭节点流！！！！！
			outStream.close();
			outWriter.close();
//			System.out.println("写入成功！");
		} catch (IOException e) {
			System.out.println("\n write IO Errors.");
		}

	}

//	while循环将文件中的用户数据读取到userInfos中
	public ArrayList<UserInfo> getAccounts() {
		ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
		FileReader inReader = null;

		try {
			inReader = new FileReader(userPath);
		} catch (IOException e) {
			System.out.println("File can't be found or File creates error.");
		}

		BufferedReader inStream = new BufferedReader(inReader);
		String str;
		try {
			while((str = inStream.readLine()) != null) {
//				System.out.println(str);
				UserInfo userInfo = new UserInfo();
				String[] strs = str.split(",");
				userInfo.setAccount(strs[0]);
				userInfo.setPassword(strs[1]);
				userInfo.setTel(strs[2]);
				userInfo.setEmail(strs[3]);
				
				userInfos.add(userInfo);
//				System.out.println("已获取数据：account:" + userInfo.getAccount() + "  password:" + userInfo.getPassword()
//				+ "  tel:" + userInfo.getTel()+ "  email:" + userInfo.getEmail());
			}
			
			inStream.close();
			inReader.close();
		} catch (IOException e) {
			System.out.println("\n read IO Errors.");
		}
		
		return userInfos;
	}

}
