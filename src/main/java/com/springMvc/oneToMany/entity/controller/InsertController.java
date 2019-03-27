package com.springMvc.oneToMany.entity.controller;

import com.springMvc.oneToMany.entity.Document;
import com.springMvc.oneToMany.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.print.Doc;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class InsertController {

    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").
            addAnnotatedClass(Employee.class).addAnnotatedClass(Document.class).buildSessionFactory();


    @RequestMapping("/insert")
    public String insert(Model model){
        Session session = sessionFactory.getCurrentSession();


        session.beginTransaction();
        Employee employee=new Employee("Suju","Java");
        Document document=new Document("Java");
        document.setEmployee(employee);
        session.save(document);

        Document document1=new Document("C++");
        document1.setEmployee(employee);
        session.save(document1);
        session.save(employee);


        session.getTransaction().commit();


        session.close();


        model.addAttribute("msg","inserted sucessfully");
        return "sucess";
    }
}
