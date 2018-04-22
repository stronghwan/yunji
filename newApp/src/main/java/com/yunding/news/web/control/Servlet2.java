package com.yunding.news.web.control;

import com.yunding.news.model.service.ServiceFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet2")
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory.getService("ViewAll").findByPerUserID();
        {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
