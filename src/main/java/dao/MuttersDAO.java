package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Mutter;


public class MuttersDAO {
	private final String JDBC_url = "jdbc:mysql://localhost:3306/dokotsubu?charaxterEncoding=UTF-8&serverTimezone=JST";
	private final String DB_user = "root";
	private final String DB_pass = "";
		
//	全ての情報をカラムごとにリストに格納
	public List<Mutter> findAll(){
		List<Mutter>mutterList = new ArrayList<>();
//		JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
//		データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_url, DB_user, DB_pass)){
//			select文の準備(IDを大きい順に並べる
			String sql = "select * from MUTTERS order by ID desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);
//			結果表を取得
			ResultSet rs = pStmt.executeQuery();
//			//SELECT文の結果をArrayListに格納
			while(rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id, userName, text);
				mutterList.add(mutter);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null ;
		}
		return mutterList;
	}
	
//	JDBCドライバを読み込む
	public boolean create(Mutter mutter) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
//		データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_url, DB_user, DB_pass)){
//			inset文の準備
			String sql = "insert into MUTTERS(NAME, TEXT) value(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
//			insert文中の「?」に使用する値を設定
			pStmt.setString(1, mutter.getUserName());
			pStmt.setString(2, mutter.getText());
			
//			insert文を実行(resultには追加された行数が代入される)
			int result = pStmt.executeUpdate();
			if(result != 1) {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false ;
		}
		return true;
	}
}
