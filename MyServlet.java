package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loading success!");
           
            String url = "jdbc:mysql://localhost/countries" +
                    "?characterEncoding=utf8";
            String name = "root";
            String password = "1234";
            Connection con = null;
            try {
                 con =  (Connection) DriverManager.getConnection(url, name, password);
                System.out.println("Connected.");
                Statement st = (Statement) con.createStatement();
    			String query = "select * from country";
    			ResultSet rs = st.executeQuery(query);
    			response.setContentType("text/html;charset=utf-8");
    			PrintWriter out = response.getWriter();
                String n;
    			while (rs.next()) {
    				n = rs.getString("name");
    				out.println(n + "<br>");
    			}
    			out.close();
    			con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
	}
}
