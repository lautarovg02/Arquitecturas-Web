package Dao;

import javax.sql.RowSet;
// Interface that all CustomerDAOs must support

import java.sql.*;
import java.util.Collection;

public interface PeopleDao {
    public int insertPeople();
    public boolean deletePeople();
    public PeopleDao findPeople();
    public boolean updatePeople();
    public RowSet selectPeopleRS();
    public Collection selectPeople();
}
