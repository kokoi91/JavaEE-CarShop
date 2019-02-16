package controllers;

import database.DataBase;
import model.RepairEntity;
import model.CarEntity;
import model.CarPartEntity;
import model.PartEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class CarPartController {

    private DataBase dataBase;
    {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/AddPartToRepair")
    public ModelAndView AddPartToRepair(CarPartEntity carPartEntity, HttpServletRequest request) throws SQLException {


        ModelAndView modelAndView = new ModelAndView();
        RepairEntity repair = dataBase.getRepairById(carPartEntity.getRepairId());
        ArrayList<PartEntity> partList = dataBase.getAllPart();

        modelAndView.addObject("partList",partList);
        modelAndView.addObject("repair",repair);


        return modelAndView;
    }

    @RequestMapping("/AddPartToRepairPost")
    public ModelAndView AddPartToRepairPost(CarPartEntity carPartEntity, HttpServletRequest request) throws SQLException {

        dataBase.insertCarPart(carPartEntity);
        dataBase.updateRepairCost(carPartEntity);

        return new ModelAndView("redirect:" + "RepairIndex");
    }
}
