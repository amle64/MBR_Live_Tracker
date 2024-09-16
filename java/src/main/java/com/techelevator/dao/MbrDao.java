package com.techelevator.dao;
import com.techelevator.model.Mbr;

import java.util.List;

public interface MbrDao {

    List<Mbr> getMbrs();

    Mbr getMbrById(int id);

    Mbr createMbrs(String excelFilePath);

    boolean updateMbr(Mbr mbr);

    boolean deleteMbr(int mbrId);
}
