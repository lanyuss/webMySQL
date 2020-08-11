package zhangshuaishuai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line1 = scanner.next();
		String line2 = scanner.next();
		String line3 = scanner.next();
		int n = Integer.parseInt(line1);
		int m = Integer.parseInt(line2);
		int k = Integer.parseInt(line3);
		List<Integer> sum = new ArrayList<Integer>();
		int a = 0;
		int b = 0;
		int count = n>m?m:n;
		for(int i=0;i<count;i++) {
			b = m - k/(n-a);
			if (b<=0) b = 0;
			sum.add(a+b);
			a+=1;
		}
		int min = sum.get(0);
		
		Iterator<Integer> iterator = sum.iterator();
		int tmp = 0;
		while(iterator.hasNext()) {
			tmp = iterator.next();
			if(min > tmp) {
				min = tmp;
			}
		}
		System.out.println(min);
		scanner.close();
	}
}
