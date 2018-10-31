package com.caps.dev.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.caps.dev.beans.Movie;

/**
 * Hello world!
 *
 */
public class JDBCApp 
{
    public static void main( String[] args )
    {
       System.out.println("Enter Movie Details");
       System.out.println("-------------------");
       Movie movie = new Movie();
       Scanner in = new Scanner(System.in);
       
       System.out.println("Enter movie id: ");
       movie.setMovieId(Integer.parseInt(in.nextLine()));
       
       System.out.println("Enter movie name: ");
       movie.setMovieName(in.nextLine());
       
       System.out.println("Enter movie ratings: ");
       movie.setRatings(Double.parseDouble(in.nextLine()));
       
       System.out.println("Enter movie summary: ");
       movie.setSummmary(in.nextLine());
       
       save(movie);
    }
    
    public static void save(Movie movie) {
    	Connection con = null;
		PreparedStatement pstmt = null;

		try {
			/*
			 * 1. Load the Driver
			 */
			Class.forName("com.mysql.jdbc.Driver");

			/*
			 * 2. Get the DB Connection via Driver
			 */
			
			String dbUrl="jdbc:mysql://localhost:3306/capsv4_db";

			//3rd version of getConnnecton()
			con = DriverManager.getConnection(dbUrl,"root","root"); //1st version of getConnection


			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "insert into movies_info values(?,?,?,?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, movie.getMovieId());
			pstmt.setString(2,movie.getMovieName());
			pstmt.setDouble(3, movie.getRatings());
			pstmt.setString(4, movie.getSummmary());
			
			int count = pstmt.executeUpdate();
			
			/*
			 * 4. Process the results
			 */

			if(count > 0) {
				System.out.println("Movie Saved in your favourite list");
			}else {
				System.out.println("Failed!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }
}