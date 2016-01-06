package com.ericsson.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.hbase.client.HTableInterface;

import com.ericsson.example.bo.EventsData;
import com.ericsson.example.util.HbaseSampleExample;




/**
 * Servlet implementation class HbaseExampleServlet
 */
public class HbaseExampleServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HbaseExampleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			
			String startKey=null,endKey=null;
			
			startKey="LRM372:201511000000";
			endKey="LRM372:201512000000";
			
			response.getWriter().append("Served at: ").append(request.getContextPath());
			HbaseSampleExample hbaseSampleExample = new HbaseSampleExample();
			HTableInterface hTableInterface = hbaseSampleExample.connectDB();
			response.getWriter().append("\n\nConnected To: ").append(hTableInterface.getName().getNameAsString());
			
			response.getWriter().append("\n\nPriniting Rows between ").append("Start Key :  ").append(startKey).append(" and ").append("End Key : ").append(endKey);
			
			List<EventsData> eventsList = hbaseSampleExample.scan(startKey,endKey);
			
			for(EventsData eventData :  eventsList){
				
				response.getWriter().append("\n\n").append("moid = ").append(eventData.getMoid()).append("\tcountertime = ").append(eventData.getCountertime());
				response.getWriter().append("\tnodename = ").append(eventData.getNodename());
				
			}
			
			response.getWriter().append("\n\nClosing Connection");
			hbaseSampleExample.closeConnection();
			response.getWriter().append("\n\nConnection Closed");

		} finally {
			response.getWriter().flush();
			response.getWriter().close();

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
