package model.bo;

import java.util.ArrayList;

import model.bean.Classes;
import model.bean.User;
import model.dao.UserDao;

public class UserBo {
	private UserDao userDao;
	
	public ArrayList<Classes> getClassOfTrainee(int user_id){
		userDao = new UserDao();
		return userDao.getClassesOfTrainee(user_id);
	}
	
	public ArrayList<User> getTraineeOfClass(int class_id){
		userDao = new UserDao();
		return userDao.getTraineeOfClass(class_id);
	}
}
