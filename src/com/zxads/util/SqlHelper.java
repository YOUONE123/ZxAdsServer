package com.zxads.util;

import static com.zxads.util.ZxConstants.LOCATION_DECK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlHelper {
	
	private static SqlHelper _Instance;
	
	Connection connection = null;
	
	private static final String DB_PATH = "jdbc:sqlite:./zexcards.db";
	
	public static SqlHelper getInstance()
	{
		if(_Instance == null)
		{

			try {
				SqlHelper._Instance = new SqlHelper();
			} catch (SQLException | ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		return _Instance;
	}
	
	private SqlHelper() throws SQLException, ClassNotFoundException
	{
        connection = DriverManager.getConnection(DB_PATH);
        Class.forName("org.sqlite.JDBC");
	}
	
	public Connection getConnection()
	{
		return this.connection;
	}
	
	public ResultSet getCardResultSet(int cid)
	{

	    try {

		    Statement statement = connection.createStatement();
	        String sql = "select * from datas INNER JOIN texts ON datas.cid = texts.cid WHERE datas.cid = " + ((Integer)cid).toString();
	        
	        ResultSet rs = statement.executeQuery(sql);
	        rs.next();
	        return rs;
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } 
	    return null;
	}

}
