package country.dao;

import country.model.Continent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@Transactional
public class ContinentDAOImpl implements ContinentDAO {
	@Autowired
	private DataSource dataSource;
	@Autowired
	static
    SessionFactory  sessionFactory;
	
	private static final Logger LOGGER = Logger.getLogger(ContinentDAOImpl.class.getName());
	@Override
	public Continent getByCode(String continentCode) {
		Continent continent = null;

		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM continent where code = ?;");
			preparedStatement.setString(1, continentCode);
			ResultSet Setresult = preparedStatement.executeQuery();
			
			if (Setresult.next()) {
				continent = new Continent();
				Integer id = Setresult.getInt(1);
				String name = Setresult.getString(2);
				String code = Setresult.getString(3);
			
				
				continent.setId(id);
				continent.setName(name);
				continent.setCode(code);
				
			}
		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return continent;
	}

	@Override
	public Continent getByName(String name) {
		   
			String sql="from Continent C where C.name =:name";
			Session session = sessionFactory.getCurrentSession();
			Query query=session.createQuery(sql);
			query.setParameter("name", name);
			return (Continent) query.uniqueResult();
	}

	@Override
	public boolean exist(String code) {
		 Session session = sessionFactory.getCurrentSession();
         Query query = session.createQuery("from Continent c where c.code = :code");
         query.setParameter("code",code);
         return query.list().size() > 0;
	}
}
