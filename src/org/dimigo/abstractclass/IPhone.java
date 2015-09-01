/**
 * 
 */
package org.dimigo.abstractclass;

/**
 *  <pre>
 * org.dimigo.abstractclass
 * |_IPhone
 *
 * 1. 개요 : 
 * 2. 작성일 : 2015. 9. 1. 
 * </pre>
 *
 * @author : 윤병창
 * @version : 1.0
 */
public class IPhone extends SmartPhone{
	public IPhone()	{
		
	}
	
	public IPhone(String model, String company, int price)	{
		super(model, company, price);
	}
	
	public void pay()	{
		System.out.printf("Apple 페이로 결제합니다.");
	}
	
	public void useAirDrop()	{
		System.out.println("AirDrop 기능을 사용합니다.");
	}

}