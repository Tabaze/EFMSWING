package com.efm.crjj.ismo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.efm.crjj.ismo.model.Employe;
import com.efm.crjj.ismo.utils.HibernateUtils;

public class DaoEmploye implements IDao<Employe> {

	@Override
	public List<Employe> getAll() {
		Session session = HibernateUtils.getSessionfactory().getCurrentSession();
		Transaction t = session.beginTransaction();

		List<Employe> Employes = session.createQuery("from Employe").getResultList();

		t.commit();
		session.close();

		return Employes;
	}

	@Override
	public Employe getOne(int id) {
		Session session = HibernateUtils.getSessionfactory().getCurrentSession();
		Transaction t = session.beginTransaction();

		Employe Appartement = session.get(Employe.class, id);

		t.commit();
		session.close();

		return Appartement;
	}

	@Override
	public boolean save(Employe obj) {
		try {
			Session session = HibernateUtils.getSessionfactory().getCurrentSession();
			Transaction t = session.beginTransaction();

			Object o = session.save(obj);

			System.out.println(o);

			t.commit();
			session.close();

			if (o == null)
				return false;
			else
				return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Employe obj) {
		try {
			Session session = HibernateUtils.getSessionfactory().getCurrentSession();
			Transaction t = session.beginTransaction();

			session.delete(obj);

			t.commit();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
