package controllers;

import database.DataBase;
import model.CarEntity;
import model.PartEntity;
import model.RepairEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class RepairController {
    private DataBase dataBase;
    {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/RepairIndex")
    public ModelAndView RepairIndex(HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();

        int ownerId =  Integer.parseInt( request.getSession().getAttribute("OwnerId").toString());
        ArrayList<RepairEntity> repairList =  dataBase.getAllOwnerRepair(ownerId);
        modelAndView.addObject("repairList",repairList);

        return modelAndView;
    }

    @RequestMapping("/RepairCreate")
    public ModelAndView RepairCreate(HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();
        int ownerId =  Integer.parseInt( request.getSession().getAttribute("OwnerId").toString());
        ArrayList<CarEntity> carList =  dataBase.getAllCarOfOwnerById(ownerId);
        modelAndView.addObject("carList",carList);

        return modelAndView;
    }

    @RequestMapping("/RepairCreatePost")
    public ModelAndView RepairCreatePost(RepairEntity repairEntity, HttpServletRequest request) throws SQLException {

        //repairEntity.setCost(0);
        dataBase.insertRepair(repairEntity);
        return new ModelAndView("redirect:" + "RepairIndex");
    }

    @RequestMapping("/RepairEdit")
    public ModelAndView RepairEdit(RepairEntity repairEntity , HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();
        RepairEntity repair = dataBase.getRepairById(repairEntity.getRepairId());
        CarEntity car = dataBase.getCarById(repair.getCarId());
        modelAndView.addObject("repair",repair);
        modelAndView.addObject("car",car);

        return modelAndView;
    }

    @RequestMapping("/RepairEditPost")
    public ModelAndView RepairEditPost(RepairEntity repairEntity, HttpServletRequest request) throws SQLException {

        dataBase.updateRepair(repairEntity);

        return  new ModelAndView("redirect:" + "RepairIndex");
    }

    @RequestMapping("/RepairDetails")
    public ModelAndView RepairDetails(RepairEntity repairEntity , HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();
        RepairEntity repair = dataBase.getRepairById(repairEntity.getRepairId());
        CarEntity car = dataBase.getCarById(repair.getCarId());
        modelAndView.addObject("repair",repair);
        modelAndView.addObject("car",car);

        ArrayList<PartEntity> partList =  dataBase.getPartFromRepair(repair.getRepairId());
        modelAndView.addObject("partList",partList);

        return modelAndView;
    }

}
