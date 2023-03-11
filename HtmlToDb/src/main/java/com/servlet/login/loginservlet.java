package com.servlet.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")

public class loginservlet extends HttpServlet {
	//create query
	private static final String INSERT_QUERY ="INSERT INTO LOGIN(USER_NAME,PASS) VALUES(?,?)";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get printwriter
		PrintWriter pw = res.getWriter();
		//content type
		res.setContentType("text/html");
		//read the form vallues
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//System.out.println("Name: "+username);
		//System.out.println("Password: "+password);
		
		//load the jdbc driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//create connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///login","root","Neeraj@2001");
				
				PreparedStatement ps = con.prepareStatement(INSERT_QUERY);){
			//SET VALUES
			ps.setString(1,user_name);
			ps.setString(1,pass);
			
			//execute query
			int count = ps.executeUpdate();
			if(count==0)
			{
				pw.println("record not stored in database...");
			}
			else
			{
				pw.println("record successfully stored in database...");
			}
		}
		catch(SQLException se)
		{
			pw.println(se.getMessage());
			se.printStackTrace();
		}
		catch(Exception e)
		{
			pw.println(e.getMessage());
			e.printStackTrace();
		}
		//close stream
		pw.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
