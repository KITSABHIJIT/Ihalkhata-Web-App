package com.exp.cemk.util;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.*;
public class CryptoUtil {
	
	private SecretKey secretKey = null;
	private Cipher desCipher;
	private Map encPassMap = new HashMap();
	private Map decPassMap = new HashMap();
	
	
	
	
	private static CryptoUtil _instance = new CryptoUtil();
	
	public static CryptoUtil getInstance()
	{
		return _instance;
	}
	
	
	private CryptoUtil()
	{
		intialize();
	}
	
	private void intialize()
	{
		encPassMap.put("A", "z26");
	  	  encPassMap.put("B", "2y5");
	  	  encPassMap.put("C", "24x");
	  	  encPassMap.put("D", "2w3");
	  	  encPassMap.put("E", "v22");
	  	  encPassMap.put("F", "2u1");
	  	  encPassMap.put("G", "20t");
	  	  encPassMap.put("H", "1s9");
	  	  encPassMap.put("I", "r18");
	  	  encPassMap.put("J", "1q7");
	  	  encPassMap.put("K", "16p");
	  	  encPassMap.put("L", "1o5");
	  	  encPassMap.put("M", "n14");
	  	  encPassMap.put("N", "1m3");
	  	  encPassMap.put("O", "12l");
	  	  encPassMap.put("P", "1k1");
	  	  encPassMap.put("Q", "j10");
	  	  encPassMap.put("R", "0i9");
	  	  encPassMap.put("S", "08h");
	  	  encPassMap.put("T", "0g7");
	  	  encPassMap.put("U", "f06");
	  	  encPassMap.put("V", "0e5");
	  	  encPassMap.put("W", "04d");
	  	  encPassMap.put("X", "0c3");
	  	  encPassMap.put("Y", "b02");
	  	  encPassMap.put("Z", "0a1");
	  	  
	  	  
	  	  encPassMap.put("a", "26Z");
	  	  encPassMap.put("b", "2Y5");
	  	  encPassMap.put("c", "X24");
	  	  encPassMap.put("d", "2W3");
	  	  encPassMap.put("e", "22V");
	  	  encPassMap.put("f", "2U1");
	  	  encPassMap.put("g", "T2t");
	  	  encPassMap.put("h", "1S9");
	  	  encPassMap.put("i", "18R");
	  	  encPassMap.put("j", "1Q7");
	  	  encPassMap.put("k", "P16");
	  	  encPassMap.put("l", "1O5");
	  	  encPassMap.put("m", "14N");
	  	  encPassMap.put("n", "1M3");
	  	  encPassMap.put("o", "L12");
	  	  encPassMap.put("p", "1K1");
	  	  encPassMap.put("q", "10J");
	  	  encPassMap.put("r", "0I9");
	  	  encPassMap.put("s", "H08");
	  	  encPassMap.put("t", "0G7");
	  	  encPassMap.put("u", "06F");
	  	  encPassMap.put("v", "0E5");
	  	  encPassMap.put("w", "D04");
	  	  encPassMap.put("x", "0C3");
	  	  encPassMap.put("y", "02B");
	  	  encPassMap.put("z", "0A1");
	  	  
	  	  encPassMap.put("0","111");
	  	  encPassMap.put("1","009");
	  	  encPassMap.put("2","008");
	  	  encPassMap.put("3","007");
	  	  encPassMap.put("4","006");
	  	  encPassMap.put("5","005");
	  	  encPassMap.put("6","004");
	  	  encPassMap.put("7","003");
	  	  encPassMap.put("8","002");
	  	  encPassMap.put("9","001");
	  	  encPassMap.put("@","000");
	  	  
	  	  
	  	  
	  	decPassMap.put("z26","A" );
	  	  decPassMap.put("2y5","B" );
	  	  decPassMap.put("24x","C" );
	  	  decPassMap.put("2w3","D" );
	  	  decPassMap.put("v22","E" );
	  	  decPassMap.put("2u1","F" );
	  	  decPassMap.put("20t","G" );
	  	  decPassMap.put("1s9","H" );
	  	  decPassMap.put("r18","I" );
	  	  decPassMap.put("1q7","J" );
	  	  decPassMap.put("16p","K" );
	  	  decPassMap.put("1o5","L" );
	  	  decPassMap.put("n14","M" );
	  	  decPassMap.put("1m3","N" );
	  	  decPassMap.put("12l","O" );
	  	  decPassMap.put("1k1","P" );
	  	  decPassMap.put("j10","Q" );
	  	  decPassMap.put("0i9","R" );
	  	  decPassMap.put("08h","S" );
	  	  decPassMap.put("0g7","T" );
	  	  decPassMap.put("f06","U" );
	  	  decPassMap.put("0e5","V" );
	  	  decPassMap.put("04d","W" );
	  	  decPassMap.put("0c3","X" );
	  	  decPassMap.put("b02","Y" );
	  	  decPassMap.put("0a1","Z" );
	  	  
	  	  
	  	 
	  	  
	  	  
	  	  decPassMap.put("26Z","a" );
	  	  decPassMap.put("2Y5","b" );
	  	  decPassMap.put("X24","c" );
	  	  decPassMap.put("2W3","d" );
	  	  decPassMap.put("22V","e" );
	  	  decPassMap.put("2U1","f" );
	  	  decPassMap.put("T2t","g" );
	  	  decPassMap.put("1S9","h" );
	  	  decPassMap.put("18R","i" );
	  	  decPassMap.put("1Q7","j" );
	  	  decPassMap.put("P16","k" );
	  	  decPassMap.put("1O5","l" );
	  	  decPassMap.put("14N","m" );
	  	  decPassMap.put("1M3","n" );
	  	  decPassMap.put("L12","o" );
	  	  decPassMap.put("1K1","p" );
	  	  decPassMap.put("10J","q" );
	  	  decPassMap.put("0I9","r" );
	  	  decPassMap.put("H08","s" );
	  	  decPassMap.put("0G7","t" );
	  	  decPassMap.put("06F","u" );
	  	  decPassMap.put("0E5","v" );
	  	  decPassMap.put("D04","w" );
	  	  decPassMap.put("0C3","x" );
	  	  decPassMap.put("02B","y" );
	  	  decPassMap.put("0A1","z" );
	  	  
	  	  
	  	  decPassMap.put("009","1");
	  	  decPassMap.put("008","2");
	  	  decPassMap.put("007","3");
	  	  decPassMap.put("006","4");
	  	  decPassMap.put("005","5");
	  	  decPassMap.put("004","6");
	  	  decPassMap.put("003","7");
	  	  decPassMap.put("002","8");
	  	  decPassMap.put("001","9");
	  	  decPassMap.put("111","0");
	  	  
	  	  decPassMap.put("000","@");
	  	  
	  	  
	}
	
	
	public String encrypt(String toEncrypt)
	{
		StringBuilder ret = new StringBuilder();
		
		for(int i= 0;i<toEncrypt.length();i++)
		{
			
			ret.append(encPassMap.get(toEncrypt.substring(i, i+1)));
		}
		
	
		
  	  return ret.toString();
  	  
  	  
		
	}
	
	public String decrypt(String toDecrypt)
	{
		
		StringBuilder ret = new StringBuilder();
		
		for(int i= 0;i<toDecrypt.length()-2;)
		{
			
			//System.out.println(toDecrypt.substring(i, i+3));
			ret.append(decPassMap.get(toDecrypt.substring(i, i+3)));
			i+=3;
		}
		
		
		
  	  return ret.toString();
	
  	 
	
	}
	
	public static void main(String[] str)
	{
		System.out.println(CryptoUtil.getInstance().decrypt("26Z22V22V000003005111"));
		//System.out.println(CryptoUtil.getInstance().encrypt("mumbai"));
	}
	
	
	
}
