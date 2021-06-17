package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?userTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123456";

	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void inserir(JavaBeans javaBeans) {
		String sql = "INSERT INTO contatos (nome, fone, email) VALUES (?,?,?);";
		try {
			var con = this.conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, javaBeans.getNome());
			pst.setString(2, javaBeans.getFone());
			pst.setString(3, javaBeans.getEmail());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {

		}

	}

	public ArrayList<JavaBeans> getContatos() {
		String sql = "SELECT * FROM contatos ORDER BY nome";
		ArrayList<JavaBeans> list = new ArrayList<JavaBeans>();
		try {
			var con = this.conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String fone = rs.getString("fone");
				String email = rs.getString("email");
				list.add(new JavaBeans(id, nome, fone, email));
			}
			return list;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void deletarContato(long id) {
		String sql = "DELETE FROM contatos WHERE id=?";
		try {
			var con = this.conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setLong(1, id);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public JavaBeans getContato(long id) {
		String sql = "SELECT * FROM contatos WHERE ID=?";
		try {
			var con = this.conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setLong(1, id);
			ResultSet rs = pst.executeQuery();
			rs.next();
			var jb = new JavaBeans(rs.getLong("id"), rs.getString("nome"), rs.getString("fone"), rs.getString("email"));
			con.close();
			return jb;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void atualizarContato(JavaBeans jb) {
		String sql = "UPDATE contatos SET nome=?, fone=?, email=? WHERE id=?";
		try {
			var con = this.conectar();
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, jb.getNome());
			pst.setString(2, jb.getFone());
			pst.setString(3, jb.getEmail());
			pst.setLong(4, jb.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
