package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Mbr;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.server.ResponseStatusException;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class JdbcMbrDao implements MbrDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMbrDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Mbr> getMbrs() {
        List<Mbr> mbrList = null;
        String sql = "SELECT * FROM mbr";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Mbr mbr = mapRowToMbr(results);
                mbrList.add(mbr);
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return mbrList;
    }

    @Override
    public Mbr getMbrById(int id) {
        Mbr mbr = null;
        String sql = "SELECT * FROM mbr WHERE mbr_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                mbr = mapRowToMbr(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return mbr;
    }

    @Override
    public Mbr createMbrs(String excelFilePath) {
        Mbr mbr = null;
        String sql = "INSERT INTO mbr (product_name, total_quantity_issued, review_status,date_created)" +
                "VALUES (?,?,1,?)";
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            // Get the first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Loop through rows, assuming the first row is the header
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                // Read product name (assuming it's in the first column)
                String productName = row.getCell(0).getStringCellValue();

                // Read total quantity issued (assuming it's in the second column)
                int totalQuantityIssued = (int) row.getCell(1).getNumericCellValue();

                // Assuming date_created is in the third column as a date value
                LocalDate dateCreated = row.getCell(2).getLocalDateTimeCellValue().toLocalDate();

                //System.out.println("Product: " + productName + ", Quantity: " + totalQuantityIssued + ", Date: " + dateCreated);
                // Insert data using JdbcTemplate
                jdbcTemplate.update(sql, productName, totalQuantityIssued, Timestamp.valueOf(dateCreated.atStartOfDay()));
            }

        } catch (DaoException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,e.getMessage()
            );
        } catch (FileNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, ex.getMessage()
            );
        } catch (IOException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage()
            );
        }

        return null;
    }

    @Override
    public boolean updateMbr(Mbr mbr){
        String sql = "UPDATE mbr "+
                "SET product_name = ?, total_quantity_issued = ?, total_quantity_made = ?, date_created = ? "+
                "WHERE mbr_id = ?;";

        try{
            int rowsAffected = jdbcTemplate.update(sql,
                    mbr.getProductName(),
                    mbr.getTotalQuantityIssued(),
                    mbr.getTotalQuantityMade(),
                    mbr.getDateCreated(),
                    mbr.getMbrId()
            );
            return rowsAffected == 1;
        }  catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Unable to connect to server or database",e);
        }
    }

    @Override
    public boolean deleteMbr(int mbrId){
        String sql = "DELETE FROM mbr "+
                "WHERE mbr_id = ?";
        try{
             int rowsAffected = jdbcTemplate.update(sql,mbrId);
             return rowsAffected == 1;

        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("unable to connect to server or database",e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation",e);
        }
    }

    private Mbr mapRowToMbr(SqlRowSet rs) {
        Mbr mbr = new Mbr();

        mbr.setMbrId(rs.getInt("mbr_id"));
        mbr.setProductName(rs.getString("product_name"));
        mbr.setTotalQuantityIssued(rs.getInt("total_quantity_issued"));
        return mbr;
    }
}




