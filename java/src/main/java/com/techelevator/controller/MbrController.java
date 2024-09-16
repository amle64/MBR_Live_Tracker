package com.techelevator.controller;

import com.techelevator.dao.JdbcMbrDao;
import com.techelevator.dao.MbrDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Mbr;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/mbrs")
public class MbrController {
    private final MbrDao mbrDao;

    public MbrController(JdbcTemplate jdbcTemplate){
        this.mbrDao = new JdbcMbrDao(jdbcTemplate);
    }
    @RequestMapping(path="/{mbrId}", method = RequestMethod.GET)
    public Mbr getMbr(@PathVariable int mbrId){
        Mbr mbr;
        try{
            mbr = mbrDao.getMbrById(mbrId);
            if(mbr== null) throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Mbr not found in system"
            );

        }catch(DaoException e){
            throw new ResponseStatusException(
                    HttpStatus.REQUEST_TIMEOUT, e.getMessage()
            );
        }
        return mbr;
    }

    //POST endpoint to upload an Excel file and create MBRs
    @RequestMapping(path="/upload", method= RequestMethod.POST)
    public ResponseEntity<String> uploadMbrsFile(@RequestPart("file") MultipartFile file){
        //Step 1: Save the uploaded file to a temporary location
        String filePath = "temp/" +file.getOriginalFilename();
        File tempFile = new File(filePath);

        try{
            file.transferTo(tempFile);

            //Step 2: Pass the file path to the DAO to process
            mbrDao.createMbrs(filePath);

            //Step 3: Return a success response
            return ResponseEntity.ok("MBR data uploaded and processed successfully");
        } catch(IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file");
        } catch(DaoException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file"+e.getMessage());
        } finally {
            tempFile.delete();
        }

    }

}
