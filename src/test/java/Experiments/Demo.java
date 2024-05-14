package Experiments;

import java.util.Date;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date = new Date();
		String currentDate= date.toString().replace(" ", "-").replace(":", "-");
		System.out.println(currentDate);
		

	}

}
