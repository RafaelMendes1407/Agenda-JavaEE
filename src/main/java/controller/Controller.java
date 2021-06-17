package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/delete", "/editar" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();

	public Controller() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/delete")) {
			deletarContato(request, response);
		} else if (action.equals("/editar")) {
			editarContato(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

	protected void contatos(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ArrayList<JavaBeans> lista = dao.getContatos();
		req.setAttribute("contatos", lista);
		RequestDispatcher rd = req.getRequestDispatcher("agenda.jsp");
		rd.forward(req, res);
	}

	protected void novoContato(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String nome = req.getParameter("nome");
		String fone = req.getParameter("fone");
		String email = req.getParameter("email");
		JavaBeans jb = new JavaBeans(nome, fone, email);
		dao.inserir(jb);
		res.sendRedirect("main");
	}
	
	protected void listarContato(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		long id = Long.parseLong(req.getParameter("id"));
		var contato = dao.getContato(id);
		req.setAttribute("contato", contato);
		RequestDispatcher rd = req.getRequestDispatcher("editar.jsp");
		rd.forward(req, res);
	}
	
	protected void deletarContato(HttpServletRequest req, HttpServletResponse res) throws IOException {
		long id = Long.parseLong(req.getParameter("id"));
		dao.deletarContato(id);
		res.sendRedirect("main");
	}
	
	protected void editarContato(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String fone = req.getParameter("fone");
		String email = req.getParameter("email");
		JavaBeans jb = new JavaBeans(id, nome, fone, email);
		dao.atualizarContato(jb);
		res.sendRedirect("main");
	}

}
