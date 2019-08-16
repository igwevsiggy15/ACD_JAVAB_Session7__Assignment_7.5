package Assignment7_5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Files{
	private volatile StringBuilder vow;
	private volatile StringBuilder cons;
	private volatile StringBuilder dig;
	
	public Files() {
		vow = new StringBuilder();
		cons = new StringBuilder();
		dig = new StringBuilder();
	}
	
	synchronized StringBuilder getVow() {
		return this.vow;
	}
	
	synchronized StringBuilder getCons() {
		return this.cons;
	}
	
	synchronized StringBuilder getDig() {
		return this.dig;
	}
}

class Creator extends Thread{
	String toCreate;
	File f;
	
	
	public Creator(String toCreate, String fileName) {
		this.toCreate = toCreate;
		this.f = new File(fileName);
	}
	
	@Override
	public void run() {
		BufferedWriter bw = null;
		try {
			System.out.println("Waiting 10 seconds...");
			Thread.sleep(10000);
			System.out.println("Creating file...");
			if (!f.exists()) {
				f.createNewFile();
			}
				bw = new BufferedWriter(new FileWriter(f));
				bw.write(toCreate);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class Writer extends Thread{
	String toCreate;
	File f;
	
	
	public Writer(String toCreate, String fileName) {
		this.toCreate = toCreate;
		this.f = new File(fileName);
	}
	
	@Override
	public void run() {
		BufferedWriter bw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}
				bw = new BufferedWriter(new FileWriter(f));
				bw.write(toCreate);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class Reader extends Thread {
	File f;
	Files files;
	public Reader(Files files, String name) {
		f = new File(name);
		this.files = files;
	}
	
	@Override
	public void run() {
		Scanner sc = null;
		try {
			if (f.exists()) {
				sc = new Scanner(f);
			}
			sc.useDelimiter("");
			while (sc.hasNext()) {
				String s = sc.next();
				if ("kaluiggy".indexOf(s) > -1) {
					files.getVow().append(s);
				} else if ("1508002473".indexOf(s) > -1) {
					files.getDig().append(s);
				} else if (Character.isLetter(s.charAt(0))) {
					files.getCons().append(s);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

public class LetterPrinter {
	public static void main(String[] args) {
		String createFile = "my text.txt";
		String consFile = "consonants.txt";
		String vowFile = "vowel.txt";
		String digFile = "digits.txt";
		String toProcess = "Hello my name is Igwe iggy15@gmail.com15.";
		Files myFiles = new Files(); 
		Thread t1;
		Thread t2;
		Thread t3;
		
		t1 = new Creator(toProcess, createFile);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Reading file...");
		t1 = new Reader(myFiles, createFile);
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		t1 = new Writer(myFiles.getCons().toString(), consFile);
		t2 = new Writer(myFiles.getDig().toString(), digFile);
		t3 = new Writer(myFiles.getVow().toString(), vowFile);
		
		System.out.println("Sorting characters...");
		t1.start();
		t2.start();
		t3.start();
	}

}