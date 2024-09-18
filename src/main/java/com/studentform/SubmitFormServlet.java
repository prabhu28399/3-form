package com.studentform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/submitForm")
public class SubmitFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // Connect to the database and insert data
        Connection conn = DatabaseConnection.getConnection();
        try {
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                response.getWriter().println("Data inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred while inserting data.");
        }
    }
}
