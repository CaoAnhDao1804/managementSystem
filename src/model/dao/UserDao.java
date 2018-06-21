package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import libralies.ConnectDBLibrary;
import model.bean.Classes;
import model.bean.Results;
import model.bean.ScheduleOfTrainee;
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
	
	public String getFullName(int user_id){
		String fullname ="";

		try {
			conn = ConnectDBLibrary.getConnection();
			System.out.println("Connect 2!");
			
			String sql = "Select * from users where user_id = ?";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			while (rs.next()){
				fullname = rs.getString(1);
				return fullname;
			}
		}
		 catch (Exception e) {
		}
			 
			return fullname;
			
		
	}
	public ArrayList<Results> getResultOfTrainee (int user_id){
		ArrayList<Results> listResult = new ArrayList<Results>();
		try {
			conn = ConnectDBLibrary.getConnection();
			System.out.println("Connect 2!");
			
			String sql = "select results.class_id , classes.name , results.status from results , classes where results.class_id = classes.class_id and user_id= ? ";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				Results results = new Results();
				results.setClassId(rs.getInt(1));
				results.setClassName(rs.getString(2));
				results.setStatus(rs.getInt(3));
				
				listResult.add(results);		
			}		
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listResult;
	}
		
	public ArrayList<ScheduleOfTrainee> getClassesOfTrainee( int user_id ){
		
		ArrayList<ScheduleOfTrainee> listClasses = new ArrayList<ScheduleOfTrainee>();
		try {
			conn = ConnectDBLibrary.getConnection();
			System.out.println("Connect 2!");
			
			String sql = "SELECT classes.class_id, classes.name, trainer_id ,time_of_date, date_of_week , count_lesson, room_id FROM learning  INNER JOIN classes on learning.class_id = classes.class_id INNER JOIN users ON users.user_id = learning.user_id WHERE learning.user_id = ?";
			System.out.println(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				ScheduleOfTrainee scheduleOfTrainee = new ScheduleOfTrainee();
				scheduleOfTrainee.setClassid(rs.getInt(1));
				scheduleOfTrainee.setNameClass(rs.getString(2));
				int idTrainer = rs.getInt(3);
				String fullnameTrainer ="";

				
				try {
					String sql1 = "Select fullname from users where user_id = ?";
					System.out.println(sql1);
					PreparedStatement pst1 = conn.prepareStatement(sql1);
					pst1.setInt(1, idTrainer);
					ResultSet rs1 = pst1.executeQuery();
					if (rs1.next()) {
						fullnameTrainer = rs1.getString(1);
						scheduleOfTrainee.setNameTrainer(fullnameTrainer);
					}
						
					
				} catch (Exception e) {
					scheduleOfTrainee.setNameTrainer(String.valueOf(idTrainer));
				}
				int idRoom = rs.getInt(7);
				String nameRoom ="";
				scheduleOfTrainee.setNameRoom(String.valueOf(idRoom));

				try {
					String sql2 = "Select name from rooms where room_id = ?";
					System.out.println(sql2);
					PreparedStatement pst2 = conn.prepareStatement(sql2);
					pst2.setInt(1, idRoom);
					ResultSet rs2 = pst2.executeQuery();
					
					if (rs2.next()) {
						nameRoom = rs2.getString(1);
						scheduleOfTrainee.setNameRoom(nameRoom);
					}
						
					
				} catch (Exception e) {
					scheduleOfTrainee.setNameRoom(String.valueOf(idRoom));
				}
				
				
				scheduleOfTrainee.setTimeofday(rs.getString(4));
				scheduleOfTrainee.setCountLession(rs.getInt(6));
				
				
				String str = rs.getString(5);
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
				scheduleOfTrainee.setDateofweek(s);
				
				listClasses.add(scheduleOfTrainee);				
			}
			System.out.println(listClasses.size());
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return listClasses;
	}

}
