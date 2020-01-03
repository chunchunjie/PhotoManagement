package com.example.photo.models;

import java.util.ArrayList;
import java.io.*;

@SuppressWarnings("serial")
public class UserList implements Serializable{
    /**
     * user list's name
     */
    private String name;
    /**
     * users in the list
     */
    private ArrayList<User> users=new ArrayList<User>();

    /**
     * get users in the list
     * @return user list in use
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * get the user list's name
     * @return user list's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * change the user list's name
     * @param name the user list's new name
     */
    public void setName(String name) {
        this.name=name;
    }

    /**
     * add the user to the list
     * @param u the user to add
     * @return add the user if true, else do nothing
     */
    public boolean addone(User u) {
        //verify if exist
        for(int j=0;j<this.getUsers().size();j++) {
            if(this.getUsers().get(j).getName().equals(u.getName())) {
                return false;
            }
        }
        this.users.add(u);
        return true;
    }

    /**
     * get data
     * @return get user list from the file
     * @throws FileNotFoundException read
     * @throws IOException read
     * @throws ClassNotFoundException read
     */

    @SuppressWarnings("resource")
    public static UserList read() throws FileNotFoundException, IOException, ClassNotFoundException {
        //System.out.println("read");
        File dir = new File("store.bin");
        UserList users = new UserList();
        if(dir.exists()){
            ObjectInputStream OIS = new ObjectInputStream (new FileInputStream("store.bin"));
            users = (UserList) OIS.readObject();
        }else {
            ObjectOutputStream OOS = new ObjectOutputStream (new FileOutputStream("store.bin"));
            OOS.writeObject(users);
        }
        return users;


    }

    /**
     * delete the user from the list
     * @param u the user to delete
     * @return delete the user if true, else do nothing
     */
    public boolean deleteUser(User u) {//e
        for (int j= 0; j < this.getUsers().size(); j++) {
            if(this.getUsers().get(j).getName().equals(u.getName())) {
                users.remove(j);
                return true;
            }
        }
        return false;
    }


    /**
     * save date to the file
     * @param users the user list to write
     * @throws IOException write
     */
    @SuppressWarnings("resource")
    public static void write(UserList users) throws IOException {
        //System.out.println("test if we can successfully write");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream (new FileOutputStream("store.bin"));
        objectOutputStream.writeObject(users);
    }





}

