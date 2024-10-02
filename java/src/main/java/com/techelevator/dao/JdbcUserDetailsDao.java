//package com.techelevator.dao;
//
//import com.techelevator.model.UserDetails;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.rowset.SqlRowSet;
//
//import java.sql.Array;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JdbcUserDetailsDao implements UserDetailsDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    public JdbcUserDetailsDao(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}
//    @Override
//    public List<UserDetails> getUserDetails(){
//        List <UserDetails> details = new ArrayList<>();
//        String sql = "SELECT * FROM user_details;";
//
//        try{
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//            while (results.next()){
//                UserDetails userDetails = mapRowToUserDetails(results);
//                details.add(userDetails);
//            }
//        }catch()
//    }
//
//    @Override
//    public UserDetails createUserDetails(int userId){
//
//    }
//
//
//    public int updateUserDetails(UserDetails userDetails){
//
//    }
//
//    public UserDetails getUserDetailsByUsername(String username){
//
//    }
//
//    public UserDetails getUserDetailsByDetailId(int Id){
//
//    }
//
//    public UserDetails getUserDetailsByUserId(int id){
//
//    }
//
//    private UserDetails mapRowToUserDetails(SqlRowSet rs){
//        UserDetails userDetails = new UserDetails();
//
//        userDetails.setUserId(rs.getInt("user_id"));
//        userDetails.setEmailAddress(rs.getString("email_address"));
//        userDetails.setIsSupervisor(rs.getBoolean("is_supervisor"));
//        userDetails.setDepartmentId(rs.getInt("department_id"));
//        return userDetails;
//    }
//}
