package controllers;

import database.DataBase;
import model.CarEntity;
import model.RepairEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class CarController {

    private DataBase dataBase;

    {
        try {
            dataBase = new DataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/CarIndex")
    public ModelAndView indexCar(HttpServletRequest request) throws SQLException {

        ModelAndView modelAndView = new ModelAndView();

        int ownerID =  Integer.parseInt( request.getSession().getAttribute("OwnerId").toString());
        ArrayList<CarEntity> carsList =  dataBase.getAllCarOfOwnerById(ownerID);
        modelAndView.addObject("carsList",carsList);

        return modelAndView;
    }

    @RequestMapping("/CarCreate")
    public ModelAndView CarCreate(HttpServletRequest request)  {
        return new ModelAndView();
    }


    @RequestMapping(value = "/createCarPost", method= RequestMethod.POST)
    public ModelAndView createCarPost(CarEntity carEntity, HttpServletRequest request) throws  SQLException {

        dataBase.insertCar(carEntity);
        return new ModelAndView("redirect:" + "CarIndex");
    }

    @RequestMapping(value = "/CarEdit", method= RequestMethod.POST)
    public ModelAndView carEdit(CarEntity carEntity, HttpServletRequest request) throws  SQLException {

        ModelAndView modelAndView = new ModelAndView();
        CarEntity car = dataBase.getCarById(carEntity.getCarId());
        modelAndView.addObject("car",car);

        return modelAndView;
    }

    @RequestMapping(value = "/CarEditPost", method= RequestMethod.POST)
    public ModelAndView CarEditPost(CarEntity carEntity, HttpServletRequest request) throws  SQLException {

        dataBase.updateCar(carEntity);

        return new ModelAndView("redirect:" + "CarIndex");
    }


    @RequestMapping(value = "/CarDetails", method= RequestMethod.POST)
    public ModelAndView carDetails(CarEntity carEntity, HttpServletRequest request) throws  SQLException {

        ModelAndView modelAndView = new ModelAndView();
        CarEntity car = dataBase.getCarById(carEntity.getCarId());
        modelAndView.addObject("car",car);

        ArrayList<RepairEntity> repairList = dataBase.getCarRepair(carEntity.getCarId());
        modelAndView.addObject("repairList",repairList);

        return modelAndView;
    }

    @RequestMapping(value = "/CarDelete", method= RequestMethod.POST)
    public ModelAndView carDelete(CarEntity carEntity, HttpServletRequest request) throws  SQLException {

        ModelAndView modelAndView = new ModelAndView();
        CarEntity car = dataBase.getCarById(carEntity.getCarId());
        modelAndView.addObject("car",car);

        return modelAndView;
    }

    @RequestMapping(value = "/CarDeletePost", method= RequestMethod.POST)
    public ModelAndView CarDeletePost( CarEntity carEntity, HttpServletRequest request) throws  SQLException {

        dataBase.deleteCar(carEntity);

        return new ModelAndView("redirect:" + "CarIndex");
    }

}
