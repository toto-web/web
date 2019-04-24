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
import com.noithat.object.Account;
import com.noithat.object.Log;

/**
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AccountServlet() {
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
		Account acc = new Account();
		MysqlConnection con = new MysqlConnection();
		JsonArray arr = new JsonArray();
		int id;
		switch(type){
			case 1://create
				jsonData= request.getParameter("jsonData");
				acc = g.fromJson(jsonData, Account.class);
				status = con.insert(acc);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 2://update
				jsonData = request.getParameter("jsonData");
				acc = g.fromJson(jsonData, Account.class);
				status = con.update(Account.class, acc, acc.getUserId());
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 3://delete
				id = Integer.parseInt(request.getParameter("userId"));
				status = con.delete(Account.class, id);
				map.put("status", Boolean.toString(status));
				out.println(map);
				break;
			case 4://get
				id = Integer.parseInt(request.getParameter("userId"));
				acc = con.get(Account.class, id);
				String json = g.toJson(acc);
				map.put("account", json);
				out.println(map);
				break;
			case 5://select
				int start = Integer.parseInt(request.getParameter("start"));
				int max = Integer.parseInt(request.getParameter("max"));
				List<Account> listacc = con.getListPaging(Account.class, start, max);
				for(Account a : listacc){
					arr.add(g.toJson(a));
				}
				out.println(arr);
				break;
			default:
		}
		out.flush();
	}

}
