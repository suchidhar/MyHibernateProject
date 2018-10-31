package com.caps.dev.jdbc;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.caps.dev.beans.Person;
import com.caps.dev.dao.PersonDB;
import com.mysql.jdbc.Driver;

public class PersonJDBCImpl implements PersonDB {

	public Person login(int pid, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Person p=null;

		try {
			/*
			 * 1. Load the Driver
			 */
			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			/*
			 * 2. Get the DB Connection via Driver
			 */
			String dbUrl = "jdbc:mysql://localhost:3306/capsV4_db";

			// 3rd Way to get a DB Connection
			String filePath = "D:/Geminites/J2EE/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);

			con = DriverManager.getConnection(dbUrl, prop);

			System.out.println("Connected...");

			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from person_info where p_id=? and p_password=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			/*
			 * 4. Process the results
			 */

			if (rs.next()) { // if for single row query
				int p_id=rs.getInt("p_id");
				String pname=rs.getString("p_name");
				String pass=rs.getString("p_password");
				int age=rs.getInt("p_age");
				String email=rs.getString("p_email");
				String address=rs.getString("p_address");
				
				p=new Person();
				
				p.setId(p_id);
				p.setName(pname);
				p.setPassword(pass);
				p.setAge(age);
				p.setEmail(email);
				p.setAddress(address);
				
				System.out.println(p.getId());
				System.out.println(p.getName());
				System.out.println(p.getPassword());
				System.out.println(p.getAge());
				System.out.println(p.getEmail());
				System.out.println(p.getAddress());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return p;
	}

	public boolean createProfile(Person p) {

		Connection con = null;
		PreparedStatement pstmt = null;
		boolean b = false;

		try {
			// 1. Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded...");

			// 2. Get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/capsV4_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			System.out.println("Connected...");

			// 3. Issue the Sql query
			String sql = "insert into person_info values(?,?,?,?,?,?)";
			System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			
			p.getId();
			System.out.println(p.getId());
			
			pstmt.setInt(1, p.getId());
			pstmt.setString(2, p.getName());
			pstmt.setString(3, p.getPassword());
			pstmt.setInt(4, p.getAge());
			pstmt.setString(5, p.getEmail());
			pstmt.setString(6, p.getAddress());

			int count = pstmt.executeUpdate();

			// 4. Process the result
			if (count > 0) {
				System.out.println("Profile Created Successfully!");
				b = true;
			} else {
				System.out.println("Profile Creation Failed!");
				b = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return b;

	}

	public Person search(int pid) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		Person p=null; 

		try {
			// 1. Load the Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded ... ");

			// 2. Get the DB connection via Driver
			String dbUrl = "jdbc:mysql://localhost:3306/capsV4_db?user=root&password=root";
			con = DriverManager.getConnection(dbUrl);
			System.out.println("Connected ... ");

			// 3. Issue the Sql query
			String sql = "select * from person_info where p_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pid);

			rs = pstmt.executeQuery();

			/*
			 * 4. Process the results
			 */

			if (rs.next()) {
				int p_id=rs.getInt("p_id");
				String pname=rs.getString("p_name");
				String pass=rs.getString("p_password");
				int age=rs.getInt("p_age");
				String email=rs.getString("p_email");
				String address=rs.getString("p_address");
				
				p=new Person();
				
				p.setId(p_id);
				p.setName(pname);
				p.setPassword(pass);
				p.setAge(age);
				p.setEmail(email);
				p.setAddress(address);
				
				System.out.println(p.getId());
				System.out.println(p.getName());
				System.out.println(p.getPassword());
				System.out.println(p.getAge());
				System.out.println(p.getEmail());
				System.out.println(p.getAddress());

			} else {
				System.out.println("No Data is Present");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return p;

	}

	public boolean delete(int pid, String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Person p=null;
		boolean b=false;

		try {
			// 1. Load the Driver

			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			// 2. Get the DB connection via Driver

			String dbUrl = "jdbc:mysql://localhost:3306/capsV4_db";

			// 3rd Way to get a DB Connection
			String filePath = "D:/Geminites/J2EE/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);

			con = DriverManager.getConnection(dbUrl, prop);

			System.out.println("Connected...");

			// 3. Issue the Sql query

			String sql = "delete from person_info where p_id=? and p_password=?";

			int count = 0;
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, pid);
			pstmt.setString(2, password);

			count = pstmt.executeUpdate();

			// 4. Process the result

			if (count > 0) {
				System.out.println("\nProfile Deleted...");
				b=true;
			} else {
				System.out.println("\nProfile Deletion Failed !!! ");
				b=false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
		return b;

	}

	public boolean update(int id, String password) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter new password : ");
		String newP1 = in.nextLine();
		System.out.println("Re-enter new password : ");
		String newP2 = in.nextLine();
		boolean b=false;

		if (!newP1.equals(newP2))
			throw new com.caps.dev.exception.NewPasswordMismatchException();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. Load the Driver

			java.sql.Driver driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			System.out.println("Driver Loaded...");

			// 2. Get the DB connection via Driver

			/*
			 * String dbUrl =
			 * "jdbc:mysql://localhost:3306/capsV3_db?user=root&password=root"; 
			 * con = DriverManager.getConnection(dbUrl);
			 */

			String dbUrl = "jdbc:mysql://localhost:3306/capsV4_db";

			// 3rd Way to get a DB Connection
			String filePath = "D:/Geminites/J2EE/db.properties";
			FileReader reader = new FileReader(filePath);
			Properties prop = new Properties();
			prop.load(reader);

			con = DriverManager.getConnection(dbUrl, prop);

			System.out.println("Connected...");

			// 3. Issue the Sql query

			String sql = "update person_info set p_password=? where p_id=? and p_password=?";

			int count = 0;
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, newP1);
			pstmt.setInt(2, id);
			pstmt.setString(3, password);

			count = pstmt.executeUpdate();

			// 4. Process the result

			if (count > 0) {
				System.out.println("\nPassword Updated...");
				b=true;
			} else {
				System.out.println("\nPassword Updation Failed");
				b=false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			/*
			 * 5. Close all the JDBC Objects
			 */

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}

		}
		return b;


	}

}
