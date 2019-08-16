package Assignment7_5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class LogThread1 extends Thread{
	volatile File f;
	String message;
	volatile BufferedWriter br;
	
	public LogThread1(String message, String name) {
		super(name + " LOGGER");
		this.message = message;
		f = new File("log.txt");
	}
	
	public void LogThread(String message2, String name) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			br = new BufferedWriter(new FileWriter(f, true));
			if (!f.exists()) {
				f.createNewFile();
			}
			System.out.println(this.getName() + ": writing to log.txt.");
			br.write(message + "\n");
			br.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}