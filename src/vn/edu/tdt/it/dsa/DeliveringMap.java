package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;

public class DeliveringMap {

	public DeliveringMap(File file) throws IOException{
		//sinh vien viet ma o day
	}
	
	public int calculate(int level, boolean rushHour){
		int res = 0;
		//sinh vien viet cma o day 
		return res;
	}
	
	public static void main (String[] args){
		try{
			DeliveringMap map = new DeliveringMap(new File("map.txt"));
			System.out.println(map.calculate(3, false));
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
