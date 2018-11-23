package beans;

import java.util.List;


public class AdminBean {
	private ConnectionBean connectionBean;
	
	public AdminBean() {
		connectionBean = new ConnectionBean();
	}
	
	public String checkAdmin(String username, String password) {
		String query = ("SELECT * FROM admin WHERE "
				+ "adminUsername = '" + username + "' AND "
				+ "adminPassword = '" + password + "'");
		List<List<String>> adminCredsList = null;
		String uname = null, psswd = null;
		
		try {
			adminCredsList = connectionBean.executeBeanQuery(query);
			for(int i = 0; i < adminCredsList.size(); i++) {
				uname = adminCredsList.get(i).get(0);
				psswd = adminCredsList.get(i).get(1);
			}
			
			if(uname != null && psswd != null) {
				return "success";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
}
