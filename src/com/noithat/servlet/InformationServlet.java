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
import com.noithat.object.Infomation;
import com.noithat.object.Log;

/**
 * Servlet implementation class InformationServlet
 */
public class InformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InformationServlet() {
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
		Infomation info = new Infomation();
		MysqlConnection con = new MysqlConnection();
		JsonArray arr = new JsonArray();
		int id;
		switch(type){
			case 1://create
				jsonData= request.getParameter("jsonData");
				info = g.fromJson(jsonData, Infomation.class);
				status = con.insert(info);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 2://update
				jsonData = request.getParameter("jsonData");
				info = g.fromJson(jsonData, Infomation.class);
				status = con.update(Infomation.class, info, info.getId());
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 3://delete
				id = Integer.parseInt(request.getParameter("infoId"));
				status = con.delete(Infomation.class, id);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 4://get
				id = Integer.parseInt(request.getParameter("infoId"));
				info = con.get(Infomation.class, id);
				String json = g.toJson(info);
				map.put("info", json);
				out.println(map);
				break;
			case 5://select
				int start = Integer.parseInt(request.getParameter("start"));
				int max = Integer.parseInt(request.getParameter("max"));
				List<Infomation> listInfo = con.getListPaging(Infomation.class, start, max);
				for(Infomation inf : listInfo){
					arr.add(g.toJson(inf));
				}
				out.println(arr);
				break;
			default:
		}
		out.println(g.toJson(map));
		out.flush();
	}

}
