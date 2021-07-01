package com.dell.webapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dell.webapp.connection.DBConnection;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/add-product-st")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AddProduct() {}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add navigation
		request.getRequestDispatcher("index.html").include(request, response);
		
		request.getRequestDispatcher("add-product.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// add navigation
		request.getRequestDispatcher("index.html").include(request, response);
		
		String name= request.getParameter("name");
		String price= request.getParameter("price");
		String description= request.getParameter("description");
		
		try {
			// 1. get connection
			Connection conn = DBConnection.getConnection();
			
			// 2. create callable statement
			
			CallableStatement cstm = conn.prepareCall("{call add_product(?,?,?)}");			
			
			// 3. execute query
			cstm.setString(1, name);
			cstm.setDouble(2, Double.parseDouble(price));
			cstm.setString(3, description);
			
			int noOfRowsAffected = cstm.executeUpdate();
			
			if(noOfRowsAffected>=0) {
				out.println("<h1 style='color:green'> Product is created successfully !</h1>");
			} else {
				out.println("<h1 style='color:red'> Product creation failed !</h1>");
			}
			
		} catch (Exception e) {
			out.println("<h1 style='color:red'> Product creation failed !</h1>");
			out.println(e);
		}
	}

}
