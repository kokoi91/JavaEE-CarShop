package dao;

import model.*;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO {

    OwnerEntity getOwnerById(int id) throws SQLException;
    void updateOwner(OwnerEntity ownerEntity)throws SQLException;
    Boolean checkOwnerExist(String mail) throws SQLException;
    OwnerEntity getUserByMailAndPass(String mail,String password) throws SQLException;
    void insertUser(OwnerEntity ownerEntity) throws SQLException;
    void insertCar(CarEntity carEntity) throws SQLException;
    ArrayList<CarEntity> getAllCarOfOwnerById(int ownerId) throws SQLException;
    CarEntity getCarById(int carId) throws SQLException;
    void updateCar(CarEntity car) throws SQLException;
    void deleteCar(CarEntity car) throws SQLException;
    ArrayList<RepairEntity> getAllOwnerRepair(int ownerId) throws SQLException;
    void insertRepair(RepairEntity repairEntity) throws SQLException;
    RepairEntity getRepairById(int repairId) throws SQLException;
    void updateRepair(RepairEntity repairEntity) throws SQLException;
    ArrayList<PartEntity> getAllPart() throws SQLException;
    PartEntity getPartById(int partId) throws SQLException;
    void insertCarPart(CarPartEntity carPartEntity) throws SQLException;
    void updateRepairCost(CarPartEntity carPartEntity)throws SQLException;
    void updateRepairCost(RepairEntity repairEntity,PartEntity partEntity)throws SQLException;
    ArrayList<RepairEntity> getCarRepair(int carId)throws SQLException;
    ArrayList<PartEntity> getPartFromRepair(int repairId)throws SQLException;
    CarPartEntity getCarPartById(int carPartId)throws SQLException;
    CarPartEntity getCarPartByRepairIdAndPartId(CarPartEntity carPartEntity)throws SQLException;
    void deleteCarPartFromRepair(CarPartEntity carPartEntity)throws SQLException;

}
