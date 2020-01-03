package com.example.photo.controller;

import android.transition.Scene;
import android.widget.Button;

import com.example.photo.models.User;
import com.example.photo.models.UserList;

import java.io.IOException;
import java.util.Comparator;

/**
 * CS 213 Photo Project
 *
 * User_Page Controller
 *
 * @author Xiao Yan & Chunjie Yang
 */

//import java.io.IOException;
//import java.util.Comparator;
//import java.util.Optional;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//import photo_models.Albumpage;
//import photo_models.User;
//import photo_models.UserList;
//import java.text.SimpleDateFormat;


public class User_page {
//
//    /**
//     * the main stage
//     */
//
//    public Stage window;
//    /**
//     * the user list
//     */
//    public UserList users=new UserList();
//
    /**
     * temp user
     */
    public User user=new User();
//
//    /**
//     * current album
//     */
//    public Albumpage tmp;
//
//    /**
//     * content of view list
//     */
//    public ObservableList<Albumpage> obs_al;
//
//    /**
//     * login menu
//     */
//    Login login;
//
//    /**
//     * search controller
//     */
//    Search Search;
//
//    // fxml part
//    /**
//     * Anchor Pane
//     */
//    @FXML
//    public AnchorPane AP_User;
//
//    /**
//     * Delete button
//     */
//    @FXML
//    public Button delete;
//
////	/**
////	 * logout button
////	 */
////	@FXML
////	public Button logout;
//
//    /**
//     * Open button
//     */
//    @FXML
//    public Button open;
//
//    /**
//     * Rename button
//     */
//    @FXML
//    public Button rename;
//
//    /**
//     * Search button
//     */
//    @FXML
//    public Button search;
//
//    /**
//     * Create a new Albumpage
//     */
//    @FXML
//    public Button create;
//
//
//    /**
//     * input the Name of an Albumpage
//     */
//    @FXML
//    public TextField Album_Name;
//
////	/**
////	 * input the date of earliest photo
////	 */
////	@FXML
////	public TextField Startdate;
//
////	/**
////	 * input the date of latest photo
////	 */
////	@FXML
////	public TextField Enddate;
//
//    /**
//     * input the new name of an Albumpage
//     */
//    @FXML
//    public TextField New_Album_Name;
//
//
//    /**
//     * list view to display the albums
//     */
//    @FXML
//    public ListView<Albumpage> table;
//
//
//
//    // Button method
//
//    /**
//     * alert helper
//     * @param header the header of the alert
//     * @param content the content of the alert
//     */
//    public void alerthelper(String header,String content) {
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Error");
//        alert.setHeaderText(header);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//
//    /**
//     * Delete an Albumpage
//     * @param event Click the delete button
//     */
//    public void delete(MouseEvent event) {
//        if(obs_al.isEmpty()) {
//            alerthelper("Not Valid: ", "the Albumpage is empty, then you are not able to do deletion.");
//        }else {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Alert");
//            alert.setHeaderText("Are you sure: ");
//            alert.setContentText("Continue Deleting?");
//            Optional<ButtonType>result =alert.showAndWait();
//
//            if(result.isPresent()) {
//                if(!obs_al.isEmpty()) {
//                    tmp=table.getSelectionModel().getSelectedItem();
//                }
//                user.deleteAlbum(tmp);
//
//                // load data
//                obs_al.clear();
//                for(int i=0;i<user.getAlbums().size();i++) {
//                    obs_al.add(user.getAlbums().get(i));
//                }
//
//                FXCollections.sort(obs_al, new Comparator<Albumpage>() {
//                    @Override
//                    public int compare(Albumpage o1, Albumpage o2) {
//                        return o1.getAlbumName().compareTo(o2.getAlbumName());
//                    }
//                });
//                //end
//
//                if(!obs_al.isEmpty()) {
//                    table.getSelectionModel().select(0);
//                }
//            }
//        }
//    }
//
//    /**
//     * logout the page
//     * @param event Click the logout button
//     * @throws IOException
//     */
//    @SuppressWarnings("static-access")
//    public void logout(MouseEvent event) throws IOException {
//        return;
//    }
//
//    /**
//     * rename an Albumpage
//     * @param event Click the rename button
//     */
//    public void rename(MouseEvent event) {
//        if(obs_al.isEmpty()) {
//            alerthelper("Not Valid: ", "the Albumpage is empty, then you are not able to rename any album.");
//        }else {
//            // pop up a dialog to get the name of the new
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Alert");
//            alert.setHeaderText("Are you sure: ");
//            alert.setContentText("Continue rename the album?");
//            Optional<ButtonType>result =alert.showAndWait();
//
//
//            if(result.isPresent()) {
//                tmp=table.getSelectionModel().getSelectedItem();
//                String new_name=Album_Name.getText();
//                if(new_name.isEmpty()){
//                    alerthelper("Not Valid", "Please enter a not null string");
//                }else if(user.renameAlbum(tmp.getAlbumName(), new_name)){
//
//                    // load data
//                    obs_al.clear();
//                    for(int i=0;i<user.getAlbums().size();i++) {
//                        obs_al.add(user.getAlbums().get(i));
//                    }
//
//                    FXCollections.sort(obs_al, new Comparator<Albumpage>() {
//                        @Override
//                        public int compare(Albumpage o1, Albumpage o2) {
//                            return o1.getAlbumName().compareTo(o2.getAlbumName());
//                        }
//                    });
//                    // end
//
//                    table.getSelectionModel().select(tmp);
//
//                }else{
//                    alerthelper("Not valid", "There is already an album with same name which you want to use.");
//                }
//            }
//        }
//    }
//
//    /**
//     * create an Albumpage
//     * @param event Click the create button
//     */
//    public void create(MouseEvent event) {
//        // pop up a dialog to get the name of the new
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Alert");
//        alert.setHeaderText("Are you sure: ");
//        alert.setContentText("Continue create the album?");
//        Optional<ButtonType>result =alert.showAndWait();
//
//        if(result.isPresent()) {
//            String new_name1=New_Album_Name.getText();
//            if(new_name1.isEmpty()){
//                alerthelper("Not Valid", "Please don't enter an empty string");
//            }
//            else if(user.createAlbum(new_name1)){
//                obs_al.add(user.getAlbum(user.getAlbums().size()-1));
//                // load data
//                obs_al.clear();
//                for(int i=0;i<user.getAlbums().size();i++) {
//                    obs_al.add(user.getAlbums().get(i));
//                }
//
//                FXCollections.sort(obs_al, new Comparator<Albumpage>() {
//                    @Override
//                    public int compare(Albumpage o1, Albumpage o2) {
//                        return o1.getAlbumName().compareTo(o2.getAlbumName());
//                    }
//                });
//                // end
//
//                table.getSelectionModel().select(user.getAlbum(user.getAlbums().size()-1));
//                New_Album_Name.clear();
//            }else{
//                alerthelper("Not valid", "There is already an album with same name which you want to use.");
//            }
//        }
//    }
//
//    /**
//     * open an Albumpage
//     * @param event Click the delete button
//     * @throws IOException
//     */
//    public void open(MouseEvent event) throws IOException {
//        if(table.getSelectionModel().isEmpty()) {
//            return;
//        }else {
//            tmp = table.getSelectionModel().getSelectedItem();
//
//            FXMLLoader loader=new FXMLLoader();
//            loader.setImage(getClass().getResource("/photo_view/Album_page.fxml"));
//            AnchorPane initial=loader.load();
//            Album_ops controller=loader.getController();
//            controller.setAlbum(tmp);
//            //controller.setName(tmp.getAlbumName());
//            controller.setUser(user);
//            controller.start(window);
//            controller.setUsers(users);
//            Scene scene=new Scene(initial);
//            window.setScene(scene);
//            window.setTitle("Albumpage Page");
//            window.setResizable(false);
//        }
//    }
//
//    /**
//     * search an Albumpage
//     * @param event Click the search button
//     * @throws IOException
//     */
//    public void search(MouseEvent event) throws IOException {
//        if(table.getSelectionModel().isEmpty()) {
//            return;
//        }else {
//            tmp = table.getSelectionModel().getSelectedItem();
//            FXMLLoader loader=new FXMLLoader();
//            loader.setImage(getClass().getResource("/photo_view/Search_page.fxml"));
//            AnchorPane initial=loader.load();
//            Search controller=loader.getController();
//            controller.setAlbum(tmp);
//            controller.setAlbumtemp(tmp);
//            controller.setUser(user);
//            controller.start(window);
//            controller.setUsers(users);
//            Scene scene=new Scene(initial);
//            window.setScene(scene);
//            window.setTitle("Search");
//            window.setResizable(false);
//        }
//    }
//
//    /**
//     * change temp user with user
//     * @param users2 user
//     */
//    public void setUsers(UserList users2) {
//        users=users2;
//    }
//
//    /**
//     * start the window
//     * @param window1 the window which shows the start page
//     */
//    public void start(Stage window1) {
//        window=window1;
//        obs_al = FXCollections.observableArrayList();
//
//        //load date
//        obs_al.clear();
//        for(int i=0;i<user.getAlbums().size();i++) {
//            obs_al.add(user.getAlbums().get(i));
//        }
//
//        FXCollections.sort(obs_al, new Comparator<Albumpage>() {
//            @Override
//            public int compare(Albumpage o1, Albumpage o2) {
//                return o1.getAlbumName().compareTo(o2.getAlbumName());
//            }
//        });
//        table.setItems(obs_al);
//
//        //display listview with the name of album
//        table.setCellFactory(lv -> new ListCell<Albumpage>() {
//            @Override
//            public void updateItem(Albumpage item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setText(null);
//                } else {
//                    String text =(item.getAlbumName()); // get text from item
//                    setText(text);
//                }
//            }
//        });
//
//        if(!obs_al.isEmpty()) {
//            table.getSelectionModel().select(0);
//            presentAlbum();
//            if(table.getItems()==null ) {
//                Album_Name.setText("");
//                //NumberofPhotos.setText("");
//                //Startdate.setText("");
//                //Enddate.setText("");
//                New_Album_Name.setText("");
//            }else{
//                table.getSelectionModel().selectedIndexProperty().addListener(
//                        (obs, oldVal, newVal) -> presentAlbum());
//            }
//        }
//    }
//
//    /**Present Albums on the left side of the window*/
//    public void presentAlbum() {
//        if(table.getSelectionModel().getSelectedIndex()<0) return;
//
//        New_Album_Name.setText("");
//    }
//
//    /**
//     * change current user's index
//     * @param i the index of user
//     */
//    public void setIndex(int i) {
//        user=users.getUsers().get(i);
//    }
//
//    /**
//     * set users given
//     * @param user3 the user assigned
//     */
//    public void setUser(User user3) {
//        // TODO Auto-generated method stub
//        user=user3;
//    }

}
