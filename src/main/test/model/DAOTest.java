package model;
import java.util.List;

import model.DAO;
import model.JavaBeans;

public class DAOTest {
	public static void main(String[] args) {
		DAO dao = new DAO();
		
		List<JavaBeans> list = dao.getContatos();
		list.forEach(jb -> System.out.println(jb.getNome()));
		System.out.println(list.size());
	}
}
