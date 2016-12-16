package org.cs.basic.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * 验证码方法
 * @author Mr.Cheng
 *
 */
public class Captcha {
	private int width;
	private int height;
	private int num;
	private String code;
	
	private static final Random ran=new Random();
	private static Captcha captcha;
	private Captcha(){
		code="0123456789";
		num=4;
	}
	public static Captcha getInstance(){
		if(captcha==null) captcha=new Captcha();
		return captcha;
	}
	public void set(int width,int height,int num,String code){
		this.width=width;
		this.height=height;
		this.num=num;
		this.code=code;
	}
	public void set(int width,int height){
		this.width=width;
		this.height=height;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String generateCheckCode(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<num;i++){
			sb.append(code.charAt(ran.nextInt(code.length())));
		}
		return sb.toString();
	}
	/**
	 * 验证码
	 * @param checkcode
	 * @return
	 */
	public BufferedImage generateCheckImage(String checkcode){
		BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphic=img.createGraphics();
		graphic.setColor(Color.WHITE);
		graphic.fillRect(0, 0, width, height);
		graphic.setColor(Color.black);
		graphic.drawRect(0, 0, width-1, height-1);
		Font font=new Font("宋体",Font.BOLD+Font.ITALIC,(int)(height*0.8));
		graphic.setFont(font);
		for(int i=0;i<num;i++){
			graphic.setColor(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
			graphic.drawString(String.valueOf(checkcode.charAt(i)),i*(width/num)+4, (int)(height*0.8));
		}
		for(int i=0;i<5;i++){
			graphic.setColor(new Color(ran.nextInt(255),ran.nextInt(255),ran.nextInt(255)));
			graphic.drawLine(0,ran.nextInt(height) ,width, ran.nextInt(height));
		}
		return img;
	}
}


















