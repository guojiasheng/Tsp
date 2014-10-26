import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SA {

	public static final Double E = 2.7182818284590452354 ;
	public double T;   //初始温度
	public double Speed;   //衰减率
	public int CityNum;
	
	int begin=0;  //起始点
	ArrayList<Integer>solution=new ArrayList<Integer>();  //tsp解
	Util tool=new Util();
	CityPoint []city;
	
	double dis=0;
	public SA(double T,double speed)
	{
		this.T=T;
		this.Speed=speed;
	}
	
	
	/**
	 * 产生初始解
	 * @param begin  起点城市
	 */
	public void Initi()
	{
	
		for(int i=0;i<CityNum;i++)
		{
			solution.add(i);
		}
	
		solution.remove(begin);
		Collections.shuffle(solution);
		
  
	//	solution=tool.GetBest("E://a280.opt.tour");
	}
	
	/**
	 * 更新温度
	 */
	public void UpdateT()
	{
		T=T*Speed;
	}
	
	/**
	 * 通过交换两个点，产生新解
	 * @param old
	 */
	public ArrayList<Integer> ExChangeTwo(ArrayList<Integer>solution)
	{
		ArrayList<Integer>newSolution=new ArrayList<Integer>();
		for(int temp:solution)
			newSolution.add(temp);
	
		Random random=new Random();
		int x=random.nextInt(CityNum-1);
		int y=random.nextInt(CityNum-1);
		while(x==y)
			y=random.nextInt(CityNum-1);
		
		int temp=newSolution.get(x);
		newSolution.set(x, y);
		newSolution.set(y, temp);
		return newSolution;
	}
	
	/**
	 * 通过交换两个点，产生新解
	 * @param old
	 */
	public ArrayList<Integer> SortTwo(ArrayList<Integer>solution)
	{
		ArrayList<Integer>newSolution=new ArrayList<Integer>();
		for(int temp:solution)
			newSolution.add(temp);
	
		Random random=new Random();
		int x=random.nextInt(CityNum-1);
		int y=random.nextInt(CityNum-1);
		while(x==y)
			y=random.nextInt(CityNum-1);
		if(x>y)
		{
			int temp=y;
			y=x;
			x=temp;
		}
		
		List<Integer> temp=new ArrayList<Integer>();
		for(int i=x;i<=y;i++)
		  temp.add(newSolution.get(i));
		Collections.reverse(temp);
		for(int i=0;i<temp.size();i++)
		{
			newSolution.set(x, temp.get(i));
			x++;
		}
		return newSolution;
	}
	
	
	/**
	 * 计算距离
	 * @param solution
	 * @return
	 */
	public double DistanceCost(ArrayList<Integer>solution)
	{
		double instanceFirst = 0;//解的路径代价
		int index=begin;
		for(int temp:solution)
		{
			instanceFirst+=tool.Distance(city[index], city[temp]);
			index=temp;
		}
		return instanceFirst;
				
	}
	
	/**
	 * 迭代
	 */
	public void TspAS()
	{
		while(T>1)
		{
		double E1=DistanceCost(solution);
		dis=E1;
		ArrayList<Integer> newSolution=SortTwo(solution);
		double E2=DistanceCost(newSolution);
		double diff=E1-E2;
		if(diff>0)
		{
			solution=newSolution;
			dis=E2;
		}
		else
		{
	
			 double probability=Math.pow(E,diff/T);
			 Random random=new Random();
			 double x=random.nextDouble();
			 if(probability>random.nextDouble())
			 {
				 solution=newSolution;
				 dis=E2;
			 }
			
		}
		UpdateT();
		}
		
	}
	
	public void Best(String file)
	{
		ArrayList <Integer>list=new ArrayList<Integer>();
		list=tool.GetBest(file);
		list.remove(begin);
		System.out.println(list);
		System.out.println(DistanceCost(list));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Util tool=new Util();
		SA test=new SA(1000,0.9);
		test.city=tool.ReadFile("E://a280.tsp");
		test.CityNum=test.city.length;
		test.Initi();
		test.TspAS();
		test.Best("E://a280.opt.tour");
		
		System.out.println(test.solution);
		System.out.print(test.dis);
	}

}
