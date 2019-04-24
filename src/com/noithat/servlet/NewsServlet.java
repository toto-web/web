package com.noithat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.noithat.databases.MysqlConnection;
import com.noithat.object.Log;
import com.noithat.object.News;

/**
 * Servlet implementation class NewsServlet
 */
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		HashMap<String, String> map = new HashMap<String, String>();
		response.setContentType("text/html;charset=UTF-8");
		int type = Integer.parseInt(request.getParameter("type"));
		String jsonData = "";
		boolean status;
		Gson g = new Gson();
		News news = new News();
		MysqlConnection con = new MysqlConnection();
		JsonArray arr = new JsonArray();
		int id;
		switch(type){
			case 1://create
				jsonData= request.getParameter("jsonData");
				news = g.fromJson(jsonData, News.class);
				status = con.insert(news);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 2://update
				jsonData = request.getParameter("jsonData");
				news = g.fromJson(jsonData, News.class);
				status = con.update(News.class, news, news.getNewsId());
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 3://delete
				id = Integer.parseInt(request.getParameter("newsId"));
				status = con.delete(News.class, id);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 4://get
				id = Integer.parseInt(request.getParameter("newsId"));
				news = con.get(News.class, id);
				String json = g.toJson(news);
				map.put("news", json);
				out.println(map);
				break;
			case 5://select
				int start = Integer.parseInt(request.getParameter("start"));
				int max = Integer.parseInt(request.getParameter("max"));
				List<News> listNews= con.getListPaging(News.class, start, max);
				for(News n : listNews){
					arr.add(g.toJson(n));
				}
				out.println(arr);
				break;
			default:
		}
		out.flush();
	}

}
