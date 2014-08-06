package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import JDBC.Bean.City;

public class DAOImpl {
	
	public static void main(String[] args) {
		ArrayList<City> list =new ArrayList<City>();
		Connection con=null;
		PreparedStatement pre =null;
		ResultSet set = null;
		try {
			
			//加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//获取链接
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:MYORCL", "scott", "tiger");
			//
			String sql = "select * from t_city";
			pre= con.prepareStatement(sql);
			set = pre.executeQuery();
			while(set.next()){
				City city = new City();
				city.setId(set.getInt("pk_id"));
				city.setName(set.getString("c_name"));
				list.add(city);
			}
			for(City c:list){
				System.out.println(c.getId()+"="+c.getName());
			}
			//System.out.println(i);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
				try {
					if(pre!=null)pre.close();
					if(con!=null) con.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
