package ca.ualberta.cmput301w16t18.gamexchange;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Adam on 2016-03-14.
 */
public class UserTest extends TestCase {

    public void testGetId() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Returned User ID did not return the expected value", "TEST_ID", user.getId());
    }

    public void testSetId() throws Exception {
        // Generated by Elastic Search, do not need to test for invalid ID's
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setId("NEW_TEST_ID");
        assertEquals("Returned User ID did not return the expected value", "NEW_TEST_ID", user.getId());
    }

    public void testGetEmail() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Returned User ID did not return the expected value", "Email", user.getEmail());
    }

    public void testSetEmail() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setEmail("New Email");
        assertEquals("Returned User ID did not return the expected value", "New Email", user.getEmail());
        //test empty string
        user.setEmail("");
        assertEquals("Empty string did not match","",user.getEmail());

    }

    public void testGetName() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Returned Name did not return the expected value", "Name", user.getName());

    }

    public void testSetName() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setName("New Name");
        assertEquals("Returned Name did not return the expected value", "New Name", user.getName());
        //test empty string
        user.setName("");
        assertEquals("Empty String did not match","",user.getName());

    }

    public void testGetPasshash() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Returned Passhash did not return the expected value", "Passhash", user.getPasshash());
    }

    public void testSetPasshash() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setPasshash("New Passhash");
        assertEquals("Returned Passhash did not return the expected value", "New Passhash", user.getPasshash());
        //test empty string
        user.setPasshash("");
        assertEquals("Empty String did not match","",user.getPasshash());
    }

    public void testGetAddress1() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Addresses did not match", "Address1", user.getAddress1());
    }

    public void testSetAddress1() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setAddress1("New address");
        assertEquals("Returned Name did not return the expected value", "New address", user.getAddress1());
        //test empty string
        user.setAddress1("");
        assertEquals("Empty String did not match","",user.getAddress1());
    }

    public void testGetAddress2() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Address 2 was not equal", "Address2", user.getAddress2());
    }

    public void testSetAddress2() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setAddress2("New address");
        assertEquals("Returned Name did not return the expected value", "New address", user.getAddress2());
        //test empty string
        user.setAddress2("");
        assertEquals("Empty String did not match","",user.getAddress2());
    }

    public void testGetCity() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("City did not match", "City", user.getCity());
    }

    public void testSetCity() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setCity("New City");
        assertEquals("Returned Name did not return the expected value", "New City", user.getCity());
        //test empty string
        user.setCity("");
        assertEquals("Empty String did not match","",user.getCity());
    }

    public void testGetPhone() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Phone was not equal", "Phone", user.getPhone());
    }

    public void testSetPhone() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setPhone("New Phone");
        assertEquals("Returned Name did not return the expected value", "New Phone", user.getPhone());
        //test empty string
        user.setPhone("");
        assertEquals("Empty String did not match","",user.getPhone());
    }

    public void testGetPostal() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("Postal did not match", "postal", user.getPostal());
    }

    public void testSetPostal() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        user.setPostal("New Postal Code");
        assertEquals("Returned Postal code did not return the expected value", "New Postal Code", user.getPostal());
        //test empty string
        user.setPostal("");
        assertEquals("Empty String did not match","",user.getPostal());
    }

    public void testGetGames() throws Exception {
        ArrayList<String> games = new ArrayList<String>();
        games.add("New Game");
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",games,new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("GetGames did not match", games, user.getGames());
    }

    public void testSetGames() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        ArrayList<String> newGames = new ArrayList<>();
        newGames.add("non empty list");
        user.setGames(newGames);
        assertEquals("New gameslist was not added",newGames,user.getGames());
    }

    public void testGetWatchlist() throws Exception {
        ArrayList<String> games = new ArrayList<String>();
        games.add("New Game");
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),games,
                new ArrayList<String>(),new ArrayList<Review>());
        assertEquals("GetGames did not match", games, user.getWatchlist());
    }

    public void testSetWatchlist() throws Exception {
        User user = new User("TEST_ID","Email","Name","Passhash","Address1","Address2","City",
                "Phone","postal",new ArrayList<String>(),new ArrayList<String>(),
                new ArrayList<String>(),new ArrayList<Review>());
        ArrayList<String> newGames = new ArrayList<>();
        newGames.add("non empty list");
        user.setWatchlist(newGames);
        assertEquals("New watchlist was not added",newGames,user.getWatchlist());
    }
}