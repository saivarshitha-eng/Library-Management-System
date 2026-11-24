package LibraryManagementSystem;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    public void addBook(Book book) throws Exception {
        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO books (bname, author, btype, price) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, book.getBookName());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getBookType());
        ps.setDouble(4, book.getPrice());

        ps.executeUpdate();
        System.out.println("Book added successfully!");
    }
    public List<Book> getAllBooks() throws Exception {

        List<Book> books = new ArrayList<>();

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM books";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Book book = new Book();

            book.setBookId(rs.getInt("id"));
            book.setBookName(rs.getString("bname"));
            book.setAuthor(rs.getString("author"));
            book.setBookType(rs.getString("btype"));
            book.setPrice(rs.getDouble("price"));

            books.add(book);
        }

        return books;
    }
    public void updateBook(Book book) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE books SET bname=?, author=?, btype=?, price=? WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, book.getBookName());
        ps.setString(2, book.getAuthor());
        ps.setString(3, book.getBookType());
        ps.setDouble(4, book.getPrice());
        ps.setInt(5, book.getId());

        ps.executeUpdate();

        System.out.println("Book updated successfully!");
    }
    public void deleteBook(int bookId) throws Exception {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM books WHERE id=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, bookId);

        ps.executeUpdate();

        System.out.println("Book deleted successfully!");
    }
}




