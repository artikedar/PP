package com.flp.fms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.flp.fms.domain.Actor;

public class ActorDaoImplForList implements IActorDao{
	
	public Connection getConnection(){
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/filmdb", "root", "Pass1234");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public Set<Actor> getActors() {
		Set<Actor> actors=new HashSet<>();
		Connection con=getConnection();
		String sql="SELECT * FROM actors";
		
		try {
			PreparedStatement pst=con.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				Actor actor=new Actor();
				
				actor.setActor_Id(rs.getInt(1));
				actor.setFirst_Name(rs.getString(2));
				actor.setLast_Name(rs.getString(3));
				
				actors.add(actor);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		/*actors.add(new Actor(101, "Shahrukh", "Khan", 0));
		actors.add(new Actor(102, "Amir", "Khan", 0));
		actors.add(new Actor(103, "Salman", "Khan", 0));
		actors.add(new Actor(104, "SaifAli", "Khan", 0));
		actors.add(new Actor(105, "Irrfan", "Khan", 0));
		*/
		return actors;
	}
	

}
