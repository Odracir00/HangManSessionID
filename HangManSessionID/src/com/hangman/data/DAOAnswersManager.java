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

import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.Stoppable;

import com.hangman.elements.Answer;

public class DAOAnswersManager {

	// code duplication with the other DAO. ok by now
	private static SessionFactory sessionFactory;

	public DAOAnswersManager() {
		sessionFactory = createSessionFactory();
	}

	public static void main(String[] args) {
		try {
			sessionFactory = createSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		DAOAnswersManager daoManager = new DAOAnswersManager();

		/* Add few game records to the database */
//		Integer gameID1 = daoManager.addGame(new Game (21, new Answer(AnswerType.COUNTRY, "France"), "_ _ _ _ _ _" ));
//		Integer gameID2 = daoManager.addGame(new Game (22, new Answer(AnswerType.COUNTRY, "Estonia"), "_ _ _ _ _ _ _" ));
//		Integer gameID3 = daoManager.addGame(new Game (23, new Answer(AnswerType.COUNTRY, "Slovakia"), "_ _ _ _ _ _ _ _" ));

		/* List down all the employees */
		List<Answer> list = daoManager.listAnswers();
		System.out.println(list.size());
		for(Answer ans : list ){
			System.out.println(ans.getValue());
			
		}

//		/* Update employee's records */
//	//	daoManager.updateEmployee(gameID1, "abc");
//
//		/* Delete an employee from the database */
//		daoManager.deleteGame(gameID2);
//
//		// /* List down new list of the employees */
//		daoManager.listGames();
	}

//	   /* Method to CREATE an game in the database */
//	   public Integer addGame(Game game){
//	      Session session = sessionFactory.openSession();
//	      Transaction tx = null;
//	      Integer gameID = null;
//	      try{
//	         tx = session.beginTransaction();	
//	         
//	         
//	         System.out.println(game.getId());
//	         System.out.println(game.getKey());
//	         System.out.println(game.getState());
//	         System.out.println(game.getAnswer().getValue());
//	         System.out.println(game.getHint());
//	         System.out.println(game.getTriedLetters());
//	         
//	         
//	         gameID = (Integer) session.save(game); 
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	      return gameID;
//	   }
//	   
	   
	   /* Method to  READ all game */
	   public List<Answer> listAnswers(){
	      Session session = sessionFactory.openSession();
	      Transaction tx = null;
	      List<Answer> answers = new LinkedList<>();
	      try{
	         tx = session.beginTransaction();
	         answers = session.createQuery("FROM Answer ans WHERE ans.type = 'COUNTRY'").list(); // country hardcoded by now
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
				if (session.isOpen()) {
					System.out.println("It is open. 1");
				}
			session.close();
			if (session.isOpen()) {
				System.out.println("It is open. 2");
			}
			stopConnectionProvider(); // hack -> forcing to close the session
			if (session.isOpen()) {
				System.out.println("It is open. 3");
			}
		}
	      
	      return answers;
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
//	   
//	   
//	   /* Method to DELETE a game from the records */
//	   public void deleteGame(Integer gameID){
//	      Session session = sessionFactory.openSession();
//	      Transaction tx = null;
//	      try{
//	         tx = session.beginTransaction();
//	         Game game = (Game)session.get(Game.class, gameID); 
//	         session.delete(game); 
//	         tx.commit();
//	      }catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      }finally {
//	         session.close(); 
//	      }
//	   }
//	
	public static SessionFactory createSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		// configuration.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sFactory = configuration.buildSessionFactory(serviceRegistry);
		return sFactory;
	}

	// hack to close the session
	public static void stopConnectionProvider() {
		final SessionFactoryImplementor sessionFactoryImplementor = (SessionFactoryImplementor) sessionFactory;
		ConnectionProvider connectionProvider = sessionFactoryImplementor.getConnectionProvider();
		if (Stoppable.class.isInstance(connectionProvider)) {
			((Stoppable) connectionProvider).stop();
		}
	}
}
