package controllers;

import database.DataBase;
import model.OwnerEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.SQLException;

@Controller
public class MainController {

    private  DataBase dataBase;

    {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/home")
    public ModelAndView home()
    {
        ModelAndView modelAndView = new ModelAndView();

             return modelAndView;

    }

    @RequestMapping(value = "/Registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;

    }

    @RequestMapping(value = "/RegistrationPOST", method= RequestMethod.POST)
    public ModelAndView registrationPOST(OwnerEntity owner, HttpServletRequest request, HttpSession session) throws SQLException {

        if(dataBase.checkOwnerExist(owner.getUsername()))
        {
            return  new ModelAndView("redirect:" + "Registration?eType=1");
        }

        owner.setUserRole("ROLE_USER");
        owner.setEnabled(true);
        dataBase.insertUser(owner);

        return new ModelAndView("redirect:" + "index.jsp");

    }

    @RequestMapping(value = "/Login")
    public ModelAndView login()
        {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;

    }

    @RequestMapping(value = "/LoginPOST", method= RequestMethod.POST)
    public ModelAndView loginPOST(OwnerEntity owner, HttpServletRequest request, HttpSession session) throws  SQLException {


       OwnerEntity ownerEntity = dataBase.getUserByMailAndPass(owner.getUsername(),owner.getPassword());


       if( ownerEntity.isEnabled() == true)
       {
           if (!session.isNew()) {
               session.invalidate();
           }

        HttpSession newSession = request.getSession();
        newSession.setAttribute("OwnerId",ownerEntity.getOwnerId());
        newSession.setAttribute("Username",ownerEntity.getUsername());
            }

        return new ModelAndView("redirect:" + "index.jsp");
    }


    @RequestMapping("/Logout")
    public ModelAndView logout(HttpServletRequest request, HttpSession session)
    {
      session.invalidate();

      return new ModelAndView("redirect:" + "index.jsp");
    }

}
