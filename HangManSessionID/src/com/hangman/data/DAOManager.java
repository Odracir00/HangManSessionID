package com.hangman.data;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.hangman.elements.Answer;
import com.hangman.elements.Country;
import com.hangman.elements.Game;


public class DAOManager {

	private static SessionFactory sessionFactory;

	public DAOManager() {
		sessionFactory = createSessionFactory();
	}

	public static void main(String[] args) {
		try {
			sessionFactory = createSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		DAOManager daoManager = new DAOManager();

		/* Add few game records to the database */
		Integer gameID1 = daoManager.addGame(new Game (21, new Country("France")));
		Integer gameID2 = daoManager.addGame(new Game (22, new Country("Estonia")));
		Integer gameID3 = daoManager.addGame(new Game (23, new Country("Slovakia")));

		/* List down all the employees */
		daoManager.listGames();

		/* Update employee's records */
	//	daoManager.updateEmployee(gameID1, "abc");

		/* Delete an employee from the database */
		daoManager.deleteGame(gameID2);

		// /* List down new list of the employees */
		daoManager.listGames();
	}

	   /* Method to CREATE an game in the database */
	   public Integer addGame(Game game){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      Integer gameID = null;
	      try{
	         tx = session.beginTransaction();	
	         
	         
	         System.out.println(game.getId());
	         System.out.println(game.getKey());
	         System.out.println(game.getState());
	         System.out.println(game.getAnswer().getName());
	         System.out.println(game.getHint());
	         System.out.println(game.getTriedLetters());
	         
	         
	         gameID = (Integer) session.save(game); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      return gameID;
	   }
	   
	   
	   /* Method to  READ all game */
	   public List<Game> listGames( ){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      List<Game> games = new LinkedList<>();
	      try{
	         tx = session.beginTransaction();
	         games = session.createQuery("FROM com.hangman.elements.Game").list(); 
//	         for (Iterator<Game> iterator = games.iterator(); iterator.hasNext();){
//	            Game game = (Game) iterator.next(); 
//	            System.out.println("Id:" + game.getId()); 
//	            System.out.println("key:" + game.getKey()); 
//                System.out.println("Answer:" + game.getAnswer().getName());
//	            System.out.println("STATE: " + game.getState()); 
//	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      
	      return games;
	   }
	   
//	   /* Method to UPDATE salary for an employee */
//	   public void updateEmployee(Integer EmployeeID, String triedLetters ){
//	      Session session = sessionFactory.openSession();
//	      Transaction tx = null;
//	      Integer gameID = null;
//	      try{
//	         tx = session.beginTransaction();
//	         Game employee = (Game) session.get(Game.class, EmployeeID); 
//	         employee.setTriedLetters(triedLetters); ;
//	         session.update(employee); 
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	   }
	   
	   
	   /* Method to DELETE a game from the records */
	   public void deleteGame(Integer gameID){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         Game game = (Game)session.get(Game.class, gameID); 
	         session.delete(game); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	   }
	
	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		// configuration.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sFactory = configuration.buildSessionFactory(serviceRegistry);
		return sFactory;
	}
	     
}
