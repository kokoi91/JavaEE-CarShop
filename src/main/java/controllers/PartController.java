package controllers;

import database.DataBase;
import model.RepairEntity;
import model.CarPartEntity;
import model.PartEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class PartController {

    private DataBase dataBase;

    {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/PartIndex")
    public ModelAndView PartIndex(HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();

        ArrayList<PartEntity> partList = dataBase.getAllPart();
        modelAndView.addObject("partList",partList);

        return modelAndView;
    }

    @RequestMapping("/PartDetails")
    public ModelAndView PartDetails(PartEntity partEntity,HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();

        PartEntity part= dataBase.getPartById(partEntity.getPartId());

        modelAndView.addObject("part",part);

        return modelAndView;
    }

    @RequestMapping("/PartDeleteFromRepair")
    public ModelAndView PartDeleteFromRepair(CarPartEntity carPartEntity, HttpServletRequest request) throws SQLException {

        CarPartEntity carPart = dataBase.getCarPartByRepairIdAndPartId(carPartEntity);

        RepairEntity repair = dataBase.getRepairById(carPart.getRepairId());
        PartEntity part = dataBase.getPartById(carPart.getPartId());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("repair",repair);
        modelAndView.addObject("part",part);
        modelAndView.addObject("carPart",carPart);

        return modelAndView;
    }

    @RequestMapping("/PartDeleteFromRepairPost")
    public ModelAndView PartDeleteFromOrderPost(CarPartEntity petPartEntity, HttpServletRequest request) throws SQLException {

        CarPartEntity petPart = dataBase.getCarPartById(petPartEntity.getCarPartId());

        RepairEntity repair = dataBase.getRepairById(petPart.getRepairId());
        PartEntity part = dataBase.getPartById(petPart.getPartId());

        dataBase.updateRepairCost(repair,part);
        dataBase.deleteCarPartFromRepair(petPart);


        return  new ModelAndView("redirect:" + "RepairIndex");
    }
}
