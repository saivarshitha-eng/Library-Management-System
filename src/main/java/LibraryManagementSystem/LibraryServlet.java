package LibraryManagementSystem;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	
	    private static final long serialVersionUID = 1L;

	   

    private LibraryService service = new LibraryService();
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        try {
            List<Book> books = service.getAllBooks();

            PrintWriter out = response.getWriter();

            out.println("<h2>All Books</h2>");

            for (Book book : books) {
                out.println("Book Name: " + book.getBookName() + "<br>");
                out.println("Author: " + book.getAuthor() + "<br>");
                out.println("Book Type: " + book.getBookType() + "<br>");
                out.println("Price: " + book.getPrice() + "<br><hr>");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {

                Book book = new Book();

                book.setBookName(request.getParameter("bookName"));
                book.setAuthor(request.getParameter("author"));
                book.setBookType(request.getParameter("bookType"));
                book.setPrice(Double.parseDouble(request.getParameter("price")));

                service.addBook(book);

                response.getWriter().println("Book added successfully!");
            }
            else if ("update".equals(action)) {
                Book book = new Book();

                book.setId(Integer.parseInt(request.getParameter("bookId")));
                book.setBookName(request.getParameter("bookName"));
                book.setAuthor(request.getParameter("author"));
                book.setBookType(request.getParameter("bookType"));
                book.setPrice(Double.parseDouble(request.getParameter("price")));

                service.updateBook(book);

                response.getWriter().println("Book updated successfully!");
            }

            else if ("delete".equals(action)) {

                int bookId = Integer.parseInt(request.getParameter("bookId"));

                service.deleteBook(bookId);

                response.getWriter().println("Book deleted successfully!");
            }

            else {
                response.getWriter().println("Invalid action");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
