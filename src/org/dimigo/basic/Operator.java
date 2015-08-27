package org.dimigo.basic;

/**
 *  사다리꼴과 평행사변형 넓이 계산 및 비교
 * @author 윤병창
 *2015 3 20
 */
 
 
public class Operator {

	public static void main(String[] args) {
		int a1 = 9, b1 = 10;
		double h1 = 5.8;
		int a2 = 9;
		double h2 = 5.4;
		System.out.println("<< 도형 넓이 비교 >>");
		System.out.println("사다리꼴 넓이 : " + (a1 + b1)*h1/2);
		System.out.println("평행사변형 넓이 : " + a2*h2);
		if(((a1 + b1)*h1/2) > (a2*h2)) {
			System.out.println("사다리꼴이 평행사변형 보다 " + ((a1 + b1)*h1/2 - a2*h2) + " 더 큽니다.");
		}	else if(((a1 + b1)*h1/2) == (a2*h2))	{
			System.out.println("두 도형의 넓이가 같습니다.");
		}
		else {
			System.out.println("평행사변형이 사다리꼴보다 " + (a2*h2 - (a1 + b1)*h1/2) + " 더 큽니다.");
		}

	}

}
