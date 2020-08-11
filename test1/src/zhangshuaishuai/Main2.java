package zhangshuaishuai;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
	public static void main(String[] args) {
		List<DataInfo> dataInfos = new ArrayList<DataInfo>();
		dataInfos.add(new DataInfo(1, 2, 10, 15));
		dataInfos.add(new DataInfo(1, 2, 4, 20));
		dataInfos.add(new DataInfo(1, 3, 5, 1));
		
		for(int i=0;i<dataInfos.size();i++) {
			
		}
	}
}

class DataInfo{
	int u;
	int v;
	int a;
	int b;
	int sum;
	public DataInfo(int u, int v, int a, int b) {
		super();
		this.u = u;
		this.v = v;
		this.a = a;
		this.b = b;
		sum = this.u +this.v +this.a + this.b;
	}
}