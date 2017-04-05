package vn.edu.tdt.it.dsa;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class WarehouseBook {
	
	protected static class WarehouseNode {
		private ProductRecord record;
		private WarehouseNode left, right;
		private int balance; 
		
		
		public ProductRecord getRecord() {
			return record;
		}
		public void setRecord(ProductRecord record) {
			this.record = record;
		}
		public WarehouseNode getLeft() {
			return left;
		}
		public void setLeft(WarehouseNode left) {
			this.left = left;
		}
		public WarehouseNode getRight() {
			return right;
		}
		public void setRight(WarehouseNode right) {
			this.right = right;
		}
		public int getBalance() {
			return balance;
		}
		public void setBalance(int balance) {
			this.balance = balance;
		}
	}
	
	private WarehouseNode root;
	private int size;
	
	public int getSize(){
		return size;
	}
	
	public WarehouseBook(){
		root = null;
		size = 0;
	}
	
	public WarehouseBook(File file) throws IOException{
		//sinh vien viet ma tai day
	}
	
	public void save(File file){
		//sinh vien viet ma tai day
	}
	
	public void process(File file) throws IOException{
		//sinh vien viet ma tai day
	}
	
	public void process(List<String> events){
		//sinh vien viet ma tai day
	}
	
	@Override
	public String toString(){
		String res = "";
		//sinh vien viet ma tai day
		return res;
	}
	
	public static void main(String[] args){
		//vi du ham main de chay
		try{
			WarehouseBook wb = new WarehouseBook(new File("warehouse.txt"));
			wb.process(new File("events.txt"));
			wb.save(new File("warehouse_new.txt"));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
