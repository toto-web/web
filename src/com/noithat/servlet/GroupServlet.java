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
import com.noithat.object.Group;
import com.noithat.object.Log;

/**
 * Servlet implementation class GroupServlet
 */
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet() {
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
		Group group = new Group();
		JsonArray arr = new JsonArray();
		MysqlConnection con = new MysqlConnection();
		int id;
		switch(type){
			case 1://create
				jsonData= request.getParameter("jsonData");
				group = g.fromJson(jsonData, Group.class);
				status = con.insert(group);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 2://update
				jsonData = request.getParameter("jsonData");
				group = g.fromJson(jsonData, Group.class);
				status = con.update(Group.class, group, group.getGroupId());
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 3://delete
				id = Integer.parseInt(request.getParameter("groupId"));
				status = con.delete(Group.class, id);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 4://get
				id = Integer.parseInt(request.getParameter("groupId"));
				group = con.get(Group.class, id);
				String json = g.toJson(group);
				map.put("group", json);
				out.println(map);
				break;
			case 5://select
				int start = Integer.parseInt(request.getParameter("start"));
				int max = Integer.parseInt(request.getParameter("max"));
				List<Group> listGroup = con.getListPaging(Group.class, start, max);
				for(Group gr : listGroup){
					arr.add(g.toJson(gr));
				}
				out.println(arr);
				break;
			default:
		}
		out.flush();
	}

}
