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
import com.noithat.object.Categories;
import com.noithat.object.Log;
import com.noithat.object.Categories;
import com.noithat.object.Categories;

/**
 * Servlet implementation class CategoriesServlet
 */
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() {
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
		Categories cate = new Categories();
		MysqlConnection con = new MysqlConnection();
		JsonArray arr = new JsonArray();
		int id;
		switch(type){
			case 1://create
				jsonData= request.getParameter("jsonData");
				cate = g.fromJson(jsonData, Categories.class);
				status = con.insert(cate);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 2://update
				jsonData = request.getParameter("jsonData");
				cate = g.fromJson(jsonData, Categories.class);
				status = con.update(Categories.class, cate, cate.getCategoriesId());
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 3://delete
				id = Integer.parseInt(request.getParameter("categoriesId"));
				status = con.delete(Categories.class, id);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 4://get
				id = Integer.parseInt(request.getParameter("categoriesId"));
				cate = con.get(Categories.class, id);
				String json = g.toJson(cate);
				map.put("categories", json);
				out.println(map);
				break;
			case 5://select
				int start = Integer.parseInt(request.getParameter("start"));
				int max = Integer.parseInt(request.getParameter("max"));
				List<Categories> listCate = con.getListPaging(Categories.class, start, max);
				for(Categories c : listCate){
					arr.add(g.toJson(c));
				}
				out.println(arr);
				break;
			default:
		}
		out.flush();
	}

}
