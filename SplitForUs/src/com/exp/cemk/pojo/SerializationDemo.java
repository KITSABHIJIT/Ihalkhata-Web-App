
package com.exp.cemk.pojo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
public class SerializationDemo {
	
public void setData(){
	Items it=new Items();
	it.setItems();
	
	try{
		FileOutputStream fo=new FileOutputStream("item.ser");
		ObjectOutputStream out=new ObjectOutputStream(fo);
		out.writeObject(it);
		out.close();
		fo.close();
		
		
	}
	catch(IOException ex){
		System.err.println(ex);
	}
}
}
