package controllers;

import database.DataBase;
import model.OwnerEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


@Controller
public class OwnerController {

    private DataBase dataBase;

    {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/OwnerDetails")
    public ModelAndView OwnerDetails( HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();
        int ownerID =  Integer.parseInt( request.getSession().getAttribute("OwnerId").toString());
        OwnerEntity owner = dataBase.getOwnerById(ownerID);
        modelAndView.addObject("owner",owner);

        return modelAndView;
    }

    @RequestMapping("/OwnerEdit")
    public ModelAndView OwnerEdit(OwnerEntity ownerEntity, HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();
        int ownerID =  Integer.parseInt( request.getSession().getAttribute("OwnerId").toString());
        OwnerEntity owner = dataBase.getOwnerById(ownerID);
        modelAndView.addObject("owner",owner);

        return modelAndView;
    }

    @RequestMapping("/OwnerEditPost")
    public ModelAndView OwnerEditPost(OwnerEntity ownerEntity, HttpServletRequest request) throws SQLException {

        dataBase.updateOwner(ownerEntity);

        return  new ModelAndView("redirect:" + "OwnerDetails");
    }

}
