import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Util {

	/**
	 * 读取文件
	 * @param path
	 * @return
	 */
	public static CityPoint[] ReadFile(String path) {
		CityPoint[] point = null;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(path));
			String tempString;
			for (int i = 0; i < 5; i++) {
				reader.readLine();
				if (i == 2) {
					tempString = reader.readLine();
					String DIMENSION = tempString.substring(
							tempString.length() - 3, tempString.length());
					point = new CityPoint[Integer.valueOf(DIMENSION)]; // 城市个数
				}
			}

			int index = 0;
			// 读取坐标
			while ((tempString = reader.readLine()) != null) {
				if (index == point.length)
					break;
				String ar[] = tempString.split(" ");
				int x = 0, tempx = 0, tempy = 0;
				for (int i = 0; i < ar.length; i++) {
					if (!ar[i].equals("")) {
						if (x == 1) {
							tempx = Integer.valueOf(ar[i]);
						} else if (x == 2) {
							tempy = Integer.valueOf(ar[i]);
						}
						x++;
					}
				}
				CityPoint pointTemp = new CityPoint(tempx, tempy);
				point[index] = pointTemp;
				index++;
				// pointTemp.x=

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return point;
	}

	
	public ArrayList<Integer>GetBest(String file)
	{
		ArrayList<Integer>best=new ArrayList<Integer>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
				String	tempString= "";
				 reader.readLine();
				 reader.readLine();
				 reader.readLine();
				 reader.readLine();
			int index = 0;
			// 读取坐标
			while ((tempString = reader.readLine()) != null) {
				if (tempString.equals("-1"))
					break;
				best.add(Integer.valueOf(tempString)-1);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return best;
	}
	
	/**
	 * 计算两个城市的距离
	 * @param begin
	 * @param end
	 * @return
	 */
	public double Distance(CityPoint begin,CityPoint end)
	{
		double y=Math.abs(begin.y-end.y);
		double x=Math.abs(begin.x-end.x);
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String FilePath = "E://a280.tsp";
		Util.ReadFile(FilePath);

	}

}
