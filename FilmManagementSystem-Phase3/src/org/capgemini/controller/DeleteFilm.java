package org.capgemini.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flp.service.FilmServiceImpl;

/**
 * Servlet implementation class DeleteFilm
 */
public class DeleteFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		int id=Integer.parseInt(request.getParameter("id"));
		FilmServiceImpl filmServImpl=new FilmServiceImpl();
		int count=filmServImpl.removeFilm(id);
		
		
		out.println("<html>"
				+ "<body>");
		//out.println(count);
		if(count>0)
		{
		request.getRequestDispatcher("DeleteFilm2").forward(request, response);
		}
		out.println(
				 "</body>"
				+ "</html>");
		
		
	}

}
