/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymediaplayer;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 *
 * @author Rui
 */
public class FXMLDocumentController implements Initializable {
    
    private String path;
    private MediaPlayer mediaPlayer;
    private double rate;
    
    @FXML
    private MediaView mediaView;
    
    public void chooseFileMethod(ActionEvent event){
        FileChooser fc = new FileChooser();
        File file = fc.showOpenDialog(null);      
        path = file.toURI().toString();
        
        if(path != null){
            Media media = new Media(path);           
            mediaPlayer = new MediaPlayer(media);
            
            mediaView.setMediaPlayer(mediaPlayer);
            
            DoubleProperty widthProp = mediaView.fitWidthProperty();
            DoubleProperty heigthProp = mediaView.fitHeightProperty();
            widthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            heigthProp.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
            
            mediaPlayer.play();
        }
    }
    
    public void play(ActionEvent event){
        mediaPlayer.play();      
    }
    
    public void pause(ActionEvent event){
        mediaPlayer.pause();
    }
    
    public void stop(ActionEvent event){
        mediaPlayer.setRate(1);
        mediaPlayer.stop();
    }
    
    public void slowRate(ActionEvent event){
        rate = mediaPlayer.getRate();
        if(rate > 0.25){
            mediaPlayer.setRate(rate - 0.25);
        }
            
    }
    
    public void fastForward(ActionEvent event){
        rate = mediaPlayer.getRate();
        mediaPlayer.setRate(rate + 0.25);
    }
    
    public void back10(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(javafx.util.Duration.seconds(10)));
    }
    
    public void skip10(ActionEvent event){
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(10)));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
