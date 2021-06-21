package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

//urlPatterns: define as URLs que serão utilizadas para acessar a Servlet
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/delete", "/update", "/report" })
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();

	public Controller() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();
		System.out.println(action);

		if (action.equals("/main")) {
			contatos(request, response);

		} else if (action.equals("/insert")) {
			novoContato(request, response);

		} else if (action.equals("/select")) {
			listarContato(request, response);

		} else if (action.equals("/update")) {
			editarContato(request, response);

		} else if (action.equals("/delete")) {
			deletarContato(request, response);

		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);

		} else {
			response.sendRedirect("index.html");
		}
	}

	// Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Criando um objeto que irá receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContatos();

		// Encaminhar a lista ao documento agenda.jsp
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setando as variaveis JavaBeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// invocar o metodo inserirContato passando o objeto contato
		dao.inserirContato(contato);

		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	// editar contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do id do contato que será editado
		String idcon = request.getParameter("idcon");
		// System.out.println(idcon);

		// Setar a variável JavaBeans
		contato.setIdcon(idcon);

		// executar o método selecionarContato
		dao.selecionarContato(contato);

		// setar os atributos do formulário com o conteudo JavaBeans
		request.setAttribute("idcon", contato.getIdcon());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());

		// encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar as variáveis JavaBeans
		contato.setIdcon(request.getParameter("idcon"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));

		// testar
		System.out.println(request.getParameter("idcon"));
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("fone"));
		System.out.println(request.getParameter("email"));

		// executar o método alterarContato
		dao.alterarContato(contato);

		// redirecionar para o docmuento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	// deletar contato
	protected void deletarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recebimento do id do contato que será deletado
		String idcon = request.getParameter("idcon");

		System.out.println(request.getParameter("idcon"));

		contato.setIdcon(idcon);

		// executar o método deletarContato
		dao.deletarContato(contato);

		// redirecionar para o docmuento agenda.jsp (atualizando as alterações)
		response.sendRedirect("main");
	}

	// gerar relatório
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document documento = new Document();
		try {
			
			//tipo de conteúdo
			response.setContentType("apllication/pdf");
			
			//nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			
			//criar documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			//abrir o documento
			documento.open();
			documento.add(new Paragraph("Lista de Contatos:"));
			documento.add(new Paragraph(" "));
			
			//criar tabela
			PdfPTable tabela = new PdfPTable(3);
			
			//cabeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			
			//popupar a tabela com os contatos
			ArrayList<JavaBeans> lista = dao.listarContatos();
			for(int i=0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}
			
			documento.add(tabela);
			
			documento.close();
			
			} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
		
	}
}
