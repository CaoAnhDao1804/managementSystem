package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import libralies.ConnectDBLibrary;
import model.bean.Classes;
import model.bean.User;

public class UserDao {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<User> getItems(){
		return null;
	}
	
	public User getItemById(int id) {
		return null;
	}
	
	public User getItemByUserNameAndPassword(String username, String password) {
		return null;
	}
	
	public int addItem(User user) {
		return 0;
	}
	
	public int editItem(User user) {
		return 0;
	}
	
	public int deleteItem(int id) {
		return 0;
	}
	public ArrayList<User> getTraineeOfClass(int class_id){
		ArrayList<User> listUser = new ArrayList<User>();
		try {
			conn = ConnectDBLibrary.getConnection();
			System.out.println("Connect 2!");
			
			String sql = "SELECT fullname ,phone FROM learning  INNER JOIN users on learning.user_id = users.user_id  WHERE  learning.class_id = ? ";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, class_id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setFullname(rs.getString(1));
				user.setPhone(rs.getString(2));
				
				listUser.add(user);			
			}
			System.out.println(listUser.size());
			for (User u1 : listUser){
				System.out.println("Cac hoc sinh lay duoc: ");
              	System.out.print(u1.getFullname());
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listUser;
	}
	
	public ArrayList<Classes> getClassesOfTrainee( int user_id ){
		
		ArrayList<Classes> listClasses = new ArrayList<Classes>();
		try {
			conn = ConnectDBLibrary.getConnection();
			System.out.println("Connect 2!");
			
			String sql = "SELECT classes.class_id ,classes.name, trainer_id , classes.created_at, time_of_date, date_of_week , count_lesson, room_id FROM learning  INNER JOIN classes on learning.class_id = classes.class_id INNER JOIN users ON users.user_id = learning.user_id WHERE learning.user_id = ?";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Classes classes= new Classes();
				classes.setClassId(rs.getInt(1));
				System.out.println(rs.getInt(1));
				
				classes.setName(rs.getString(2));
				System.out.println(rs.getString(2));
				
				classes.setTrainerId(rs.getInt(3));
				System.out.println(rs.getInt(3));
				classes.setCreateAt(rs.getDate(4));
				System.out.println(rs.getDate(4));
				classes.setTimeOfDate(rs.getString(5));
				String str = rs.getString(6);
				String s =""; 
          	 	String arr[] =str.split(",");
              	 for(int i=0; i<arr.length;i++){
              		 switch(arr[i]){
              		 case "2":
              		 {
              			 s+="Mon";
              			 break;
              		 }
              		 case "3":
              		 {
              			 s+="Tue";
              			break;
              		 }
              		 case "4":
              		 {
              			 s+="Wed";
              			break;
              		 }
              		case "5":
              		 {
              			 s+="Thu";
              			break;
              		 }
              		case "6":
              		 {
              			 s+="Fri";
              			break;
              		 }
              		case "7":
              		 {
              			 s+="Sat";
              			break;
              		 }
              		case "8":
              		 {
              			 s+="Sun";
              			break;
              		 }
              	    default:
              	    {
              	        
              	    }
              		 }
              		 if (i< (arr.length-1)) {
              			 s+=",";
              		 }
              		 else {
              			 s+=".";
              		 }
          	 	}
				classes.setDateOfWeek(s);
				classes.setCountLession(rs.getInt(7));
				classes.setRoomId(rs.getInt(8));
				
				listClasses.add(classes);				
			}
			System.out.println(listClasses.size());
			for (Classes l1 : listClasses){
				System.out.println("Cac lop lay duoc: ");
              	System.out.print(l1.getClassId());
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listClasses;
	}

}
