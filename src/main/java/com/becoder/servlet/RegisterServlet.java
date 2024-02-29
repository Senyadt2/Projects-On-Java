package com.becoder.servlet;

import com.becoder.conn.HibernateUtil;
import com.becoder.doa.EmpDao;
import com.becoder.entity.Emp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name =  req.getParameter("name");
       String email = req.getParameter("email");
        String salary =  req.getParameter("salary");
        String department  = req.getParameter("department");
        String password =  req.getParameter("password");

        Emp emp = new Emp(name,department,salary,email,password);
//        System.out.println(name +  email + salary + department + password );
//        System.out.println(emp);
        EmpDao doa = new EmpDao(HibernateUtil.getSessionFactory());
        boolean f =  doa.saveEmp(emp);
        HttpSession session = req.getSession();
        if(f){
            session.setAttribute("msg","Emp registerd successfuly");
            System.out.println("Emp registerd successfuly");
        }
        else{
            session.setAttribute("msg","Not registered");
            System.out.println("Something went wrong");
        }
        resp.sendRedirect("index.jsp");
    }
}
