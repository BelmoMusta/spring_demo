package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import app.entity.Continent;
@Repository
public class ContinentDAOImpl implements ContinentDAO {
	private static final Logger LOGGER = Logger.getLogger(ContinentDAOImpl.class.getName());
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public Continent getByCode(String code) {
		String hql = "from Continent C where C.code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, code);
		return (Continent) query.uniqueResult();
	}

	@Override
	public Continent addContinent(Continent continent) {
		try (Connection connection = dataSource.getConnection();) {

			try (PreparedStatement preparedStatement = connection
					.prepareStatement("INSERT INTO  continent(code,name) VALUES(?,?);");) {
				preparedStatement.setString(1, continent.getCode());
				preparedStatement.setString(2, continent.getName());
				preparedStatement.executeUpdate();

			}

		} catch (SQLException exception) {
			LOGGER.log(Level.SEVERE, "Exception while accessing the database", exception);
		}
		return continent;
	}

	@Override
	public Continent getContinent(String code) {

		return this.getByCode(code);
	}

	@Override
	public List<Continent> getContinents() {
		return hibernateTemplate.loadAll(Continent.class);
	}

	@Override
	public void deleteContinent(String code) {
		String hql = "DELETE FROM Continent where code = ?1";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, code);
		query.executeUpdate();

	}

	@Override
	public Continent updateContinent(Continent continent, String code) {
		String hql = "UPDATE  Continent SET name = ?1  where code = ?2";
		Query<?> query = this.getSessionFactory().createQuery(hql);
		query.setParameter(1, continent.getName());
		query.executeUpdate();
		return continent;
	}

	private final Session getSessionFactory() {
		return sessionFactory.getCurrentSession();
	}

}
