package database;

import dao.DAO;
import model.*;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataBase implements DAO {

    Connection con;
    Statement stm;

    public DataBase() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicgarage1","root","");
        stm = con.createStatement();
    }

    public OwnerEntity getOwnerById(int id) throws SQLException {

        String query = "select * from owner where OwnerId = "+id+" ;";

        ResultSet result = stm.executeQuery(query);

        OwnerEntity u = new OwnerEntity();
        while(result.next())
        {
            u.setOwnerId(result.getInt("OwnerId"));
            u.setUsername(result.getString("Username"));
            u.setPassword(result.getString("Password"));
            u.setEnabled(result.getBoolean("Enabled"));
            u.setFirstName(result.getString("FirstName"));
            u.setLastName(result.getString("LastName"));
            u.setPhoneNr(result.getString("PhoneNr"));
            u.setUserRole(result.getString("Role"));

        }
        return u;

    }

    public void updateOwner(OwnerEntity ownerEntity) throws SQLException {
        String query = "Update owner set  FirstName ='"+ownerEntity.getFirstName()+"' , LastName = '"+ownerEntity.getLastName()+"',PhoneNr = '"+ownerEntity.getPhoneNr()+"'  where OwnerId = "+ownerEntity.getOwnerId()+";";
        stm.executeUpdate(query);
    }

    public Boolean checkOwnerExist(String mail) throws SQLException {
        String query = "select * from owner where Username = '"+mail+"' ;";

        ResultSet result = stm.executeQuery(query);
        return result.next();
    }



    public OwnerEntity getUserByMailAndPass(String username, String password) throws SQLException {
        String query = "select * from owner where Password = '"+password+"' and Username = '"+username+"' ;";

        ResultSet result = stm.executeQuery(query);

        OwnerEntity u = new OwnerEntity();
        while(result.next())
        {
            u.setOwnerId(result.getInt("OwnerId"));
            u.setUsername(result.getString("Username"));
            u.setPassword(result.getString("Password"));
            u.setEnabled(result.getBoolean("Enabled"));
            u.setFirstName(result.getString("FirstName"));
            u.setLastName(result.getString("LastName"));
            u.setPhoneNr(result.getString("PhoneNr"));
            u.setUserRole(result.getString("Role"));

        }
        return u;
    }

    public void insertUser(OwnerEntity ownerEntity) throws SQLException {

        String query = "INSERT into owner(Username,Password,Enabled,FirstName,LastName,PhoneNr,Role) " +
                "VALUES ('"+ownerEntity.getUsername()+"','"+ownerEntity.getPassword()+"',"+ownerEntity.isEnabled()+",'"+ownerEntity.getFirstName()+"','"+ownerEntity.getLastName()+"','"+ownerEntity.getPhoneNr()+"','"+ownerEntity.getUserRole()+"');";
        stm.executeUpdate(query);

    }
    
    public void insertCar(CarEntity carEntity) throws SQLException {

        String query = "INSERT into car (Model,Year_,Name,DateOfBought,OwnerId) \n" +
                "values ('"+carEntity.getModel()+"','"+carEntity.getYear_()+"','"+carEntity.getName()+"','"+carEntity.getDateOfBought()+"',"+carEntity.getOwnerId()+");";
        stm.executeUpdate(query);

    }
    
    public ArrayList<CarEntity> getAllCarOfOwnerById(int ownerId) throws SQLException {
        ArrayList <CarEntity> carLists = new ArrayList<CarEntity>();

        String query = " SELECT * FROM car where ownerId = "+ownerId+"";

        ResultSet result = stm.executeQuery(query);
        
        while(result.next())
        {
            CarEntity car = new CarEntity();

            car.setCarId(result.getInt("CarId"));
            car.setModel(result.getString("Model"));
            car.setYear_(result.getInt("Year_"));
            car.setName(result.getString("Name"));
            car.setDateOfBought(result.getString("DateOfBought"));
            car.setOwnerId(result.getInt("OwnerId"));

            carLists.add(car);
        }
        return carLists;
    }

    public CarEntity getCarById(int carId) throws SQLException {

        String query = " SELECT * FROM car where carId = "+carId+";";

        ResultSet result = stm.executeQuery(query);

        CarEntity car = new CarEntity();

        while(result.next())
        {
            car.setCarId(result.getInt("CarId"));
            car.setModel(result.getString("Model"));
            car.setYear_(result.getInt("Year_"));
            car.setName(result.getString("Name"));
            car.setDateOfBought(result.getString("DateOfBought"));
            car.setOwnerId(result.getInt("OwnerId"));
        }
        return car;
    }

    public void updateCar(CarEntity carEntity) throws SQLException {
        String query = "Update car set  Model='"+carEntity.getModel()+"',Year_= '"+carEntity.getYear_()+"',Name = '"+carEntity.getName()+"',DateOfBought = '"+carEntity.getDateOfBought()+"' where CarId = "+carEntity.getCarId()+";";
        stm.executeUpdate(query);
    }

    public void deleteCar(CarEntity carEntity) throws SQLException {
        String query = "Delete cp from carpart cp inner join repair r on cp.RepairId=r.RepairId WHERE r.CarId= "+carEntity.getCarId()+";";
        String query2 = "Delete from repair  WHERE CarId="+carEntity.getCarId()+";";
        String query3 = "Delete from car where CarId = "+carEntity.getCarId()+";";
        
        stm.executeUpdate(query);
        stm.executeUpdate(query2);
        stm.executeUpdate(query3);
    }

    public ArrayList<RepairEntity> getAllOwnerRepair(int ownerId) throws SQLException {

        ArrayList <RepairEntity> repairList = new ArrayList<RepairEntity>();

        String query = "SELECT r.* from repair r inner join car c on r.CarId=c.CarId WHERE c.OwnerId="+ownerId+";";

        ResultSet result = stm.executeQuery(query);

        while(result.next())
        {
            RepairEntity repair = new RepairEntity();

            repair.setRepairId(result.getInt("RepairId"));
            repair.setCost(result.getDouble("Cost"));
            repair.setRepairDesc(result.getString("RepairDesc"));
            repair.setCarId(result.getInt("CarId"));

            repairList.add(repair);
        }
        return repairList;
    }

    public void insertRepair(RepairEntity repairEntity) throws SQLException {

        String query = "INSERT into repair (RepairDesc,Cost,CarId) " +
                "values ('"+repairEntity.getRepairDesc()+"','"+repairEntity.getCost()+"',"+repairEntity.getCarId()+");" ;

        stm.executeUpdate(query);
    }

    public RepairEntity getRepairById(int repairId) throws SQLException {
        String query = " SELECT * FROM repair where RepairId = "+repairId+";";

        ResultSet result = stm.executeQuery(query);

        RepairEntity repair = new RepairEntity();

        while(result.next())
        {
            repair.setRepairId(result.getInt("RepairId"));
            repair.setCarId(result.getInt("CarId"));
            repair.setCost(result.getDouble("Cost"));
            repair.setRepairDesc(result.getString("RepairDesc"));


        }
        return repair;
    }

    public void updateRepair(RepairEntity repairEntity) throws SQLException {

        String query = "Update repair set   RepairDesc = '"+repairEntity.getRepairDesc()+"' where RepairId = "+repairEntity.getRepairId()+";";
        stm.executeUpdate(query);
    }

    public ArrayList<PartEntity> getAllPart() throws SQLException {

        ArrayList <PartEntity> partList = new ArrayList<PartEntity>();

        String query = "select * from part ;";

        ResultSet result = stm.executeQuery(query);

        while(result.next())
        {
            PartEntity partEntity = new PartEntity();

            partEntity.setPartId(result.getInt("PartId"));
            partEntity.setName(result.getString("Name"));
            partEntity.setDescription(result.getString("Description"));
            partEntity.setCost(result.getDouble("Cost"));

            partList.add(partEntity);
        }
        return partList;
    }

    public void insertCarPart(CarPartEntity carPartEntity) throws SQLException {
        String query = "INSERT into carpart(RepairId, PartId) " +
                "values ("+carPartEntity.getRepairId()+","+carPartEntity.getPartId()+");" ;

        stm.executeUpdate(query);
    }

    public PartEntity getPartById(int partId) throws SQLException {
        String query = "select * from part where PartId = "+partId+";";

        ResultSet result = stm.executeQuery(query);
        PartEntity partEntity = new PartEntity();

        while(result.next())
        {
            partEntity.setPartId(result.getInt("PartId"));
            partEntity.setName(result.getString("Name"));
            partEntity.setDescription(result.getString("Description"));
            partEntity.setCost(result.getDouble("Cost"));

        }
        return partEntity;
    }

    public void updateRepairCost(CarPartEntity carPartEntity) throws SQLException {

       PartEntity partEntity = this.getPartById(carPartEntity.getPartId());
       RepairEntity repairEntity = this.getRepairById(carPartEntity.getRepairId());

        double cost = repairEntity.getCost()+partEntity.getCost();
        String query = "Update repair set Cost ="+cost+"  where RepairId = "+repairEntity.getRepairId()+";";

        stm.executeUpdate(query);
    }
    
    public ArrayList<RepairEntity> getCarRepair(int carId) throws SQLException {

        ArrayList <RepairEntity> repairList = new ArrayList<RepairEntity>();

        String query = "SELECT r.* from repair r WHERE  CarId="+carId+";";

        ResultSet result = stm.executeQuery(query);

        while(result.next())
        {
            RepairEntity repair = new RepairEntity();

            repair.setRepairId(result.getInt("RepairId"));
            repair.setCost(result.getDouble("Cost"));
            repair.setRepairDesc(result.getString("RepairDesc"));
            repair.setCarId(result.getInt("CarId"));

            repairList.add(repair);
        }
        return repairList;

    }

    public ArrayList<PartEntity> getPartFromRepair(int repairId) throws SQLException {

        ArrayList <PartEntity> partList = new ArrayList<PartEntity>();

        String query = "select p.* from part p inner join carpart cp on p.PartId=cp.PartId where cp.RepairId ="+repairId+" ; ";

        ResultSet result = stm.executeQuery(query);

        while(result.next())
        {
            PartEntity part = new PartEntity();

            part.setPartId(result.getInt("PartId"));
            part.setDescription(result.getString("Description"));
            part.setCost(result.getDouble("Cost"));
            part.setName(result.getString("Name"));

            partList.add(part);
        }
        return partList;
    }

    public CarPartEntity getCarPartById(int carPartId) throws SQLException {

        String query = "select * from carpart WHERE CarPartId = "+carPartId+";";

        ResultSet result = stm.executeQuery(query);

        CarPartEntity carPart = new CarPartEntity();

        while(result.next())
        {
            carPart.setCarPartId(result.getInt("CarPartId"));
            carPart.setRepairId(result.getInt("RepairId"));
            carPart.setPartId(result.getInt("PartId"));
        }

        return carPart;
    }

    public void updateRepairCost(RepairEntity repairEntity,PartEntity partEntity) throws SQLException {

        double cost = repairEntity.getCost()-partEntity.getCost();
        String query = "Update repair set Cost ="+cost+"  where RepairId = "+repairEntity.getRepairId()+";";

        stm.executeUpdate(query);
    }

    public void deleteCarPartFromRepair(CarPartEntity carPartEntity) throws SQLException {

        String query = "Delete FROM carpart WHERE CarPartId =  "+carPartEntity.getCarPartId()+";";

        stm.executeUpdate(query);

    }

    public CarPartEntity getCarPartByRepairIdAndPartId(CarPartEntity carPartEntity) throws SQLException {

            String query = "select * from carpart WHERE PartId = "+carPartEntity.getPartId()+" and RepairId = "+carPartEntity.getRepairId()+" ;";

            ResultSet result = stm.executeQuery(query);

            CarPartEntity carPart = new CarPartEntity();

            while(result.next())
            {
                carPart.setCarPartId(result.getInt("CarPartId"));
                carPart.setRepairId(result.getInt("RepairId"));
                carPart.setPartId(result.getInt("PartId"));
            }

            return carPart;
            }

}
