package com.connexions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.connexions.entity.City;
import com.connexions.entity.State;
import com.connexions.persistence.HibernateUtil;

/**
 * Starter app
 *
 */
public class App 
{
    public static void main(String[] args )
    {
        System.out.println("Hibernate - Cascade - NativeSQL - HQL");
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
        	
        	session.beginTransaction();
        	
        	State state = new State();
        	state.setState_name("PUNJAB");
        	state.setState_code("PB");
        	state.setCreated_by("admin");
        	state.setCreated_date(new Date());
        	System.out.println("Persisting state :: " + state.toString());
        	session.saveOrUpdate(state);
        	
        	City city = new City();
        	city.setCity_name("LUDHIANA");
        	city.setCity_code("LH");
        	city.setCreated_by("admin");
        	city.setCreated_date(new Date());
        	city.setState(state);        	
        	
        	state.getCityList().add(city);
        	
        	System.out.println("Persisting city :: " + city.toString());
        	session.saveOrUpdate(city);
        	
        	String hql = "FROM State s where s.state_code = :stateCode";
        	Query query = session.createQuery(hql).setParameter("stateCode", args[0]);
        	List results = query.list();
        	System.out.println("Results :: " + results.size());
        	
        	State state2 = (State) results.get(0);
        	City city2 = new City();
        	city2.setCity_name("AMRITSAR");
        	city2.setCity_code("AM");
        	city2.setCreated_by("admin");
        	city2.setCreated_date(new Date());
        	city2.setState(state2);        	
        	
        	state.getCityList().add(city2);
        	
        	System.out.println("Persisting city :: " + city2.toString());
        	session.saveOrUpdate(city2);
        	session.getTransaction().commit();
        	
        	//Begin new session to fetch records
        	session.beginTransaction();
        	String sql1 = "SELECT * FROM STATE";
            SQLQuery sqlQuery1 = session.createSQLQuery(sql1);
            sqlQuery1.addEntity(State.class);
            List<State> stateResultList = new ArrayList<State>(sqlQuery1.list());
            System.out.println("Total states enrolled : " + stateResultList.size());
            Map<Integer,State> stateMap = new HashMap<Integer,State>();
            for (State stateObject : stateResultList){
            	System.out.println(stateObject.toString());
            	stateMap.put(stateObject.getState_id(), stateObject);
            }
            
            String sql2 = "FROM City c join fetch c.state s";
            Query sqlQuery2 = session.createQuery(sql2);
            List<City> cityResultList = new ArrayList<City>(sqlQuery2.getResultList());
            for (City cityObject : cityResultList){
            	System.out.println(cityObject.toString());
            }
            
            Map<Integer,List<City>> stateCityMap = new HashMap<Integer,List<City>>();
            for (City fetchedCity : cityResultList){
            	List<City> stateCityList = stateCityMap.get(fetchedCity.getState_id());
            	if(stateCityMap.get(fetchedCity.getState_id()) == null){
            		stateCityList = new ArrayList<City>();
            	}
            	stateCityList.add(fetchedCity);
            	stateCityMap.put(fetchedCity.getState_id(), stateCityList);
//               System.out.print("  State Name: " + st.getState_name() + "(" + st.getState_code() + ")"); 
            }
            
            for(Integer fetchedStateKey : stateCityMap.keySet()){
            	State fetchedState = stateMap.get(fetchedStateKey);
            	List<City> stateCityList = stateCityMap.get(fetchedStateKey);
            	System.out.println("State Name: " + fetchedState.getState_name() + "(" + fetchedState.getState_code() + ") ");
            	if(stateCityList != null){
	            	for(City fetchedCity : stateCityList){
	            		System.out.println(fetchedCity.toString());
	            	}
            	}
            }
        	session.getTransaction().commit();
        }catch (Exception e) {
        	if (session.getTransaction()!=null) session.getTransaction().rollback();
        	e.printStackTrace(); 
        } finally {
        	session.close();
        }
    }
}
