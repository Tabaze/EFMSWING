package com.efm.crjj.ismo.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import com.efm.crjj.ismo.model.Employe;
public class EmployeModel extends AbstractTableModel{

	
	String[] cols= {"Code",
			"Nom et Prenom", "Departement", "Salaire"
		};
	List<Employe> Employes = new ArrayList<Employe>();

	public EmployeModel(List<Employe> Employes) {
		super();
		this.Employes = Employes;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return Employes.size();

	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cols.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employe p = Employes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getNom();
		case 1:
			return p.getNom();
		case 2:
			return p.getDepartement();
		case 3:
			return p.getSalaire();
		}
		return "";
	}


}
