package application;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MainController implements Initializable {
	@FXML private MediaView mv;
	 private MediaPlayer mp;
	 private Media me;
	 @FXML Slider volumeslider,videoslider;
	 @FXML HBox hbox;
	 @FXML Label label;
	 String Path;
	 public void OpenPath() {
		   FileChooser fc=new FileChooser();
		   FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select file format","*.mp4","*.mp3");
		   fc.getExtensionFilters().add(filter);
		   File file=fc.showOpenDialog(null);
		   Path=file.toURI().toString();
		   if(Path!=null) {
			   me=new Media(Path);
				mp=new MediaPlayer(me);
				mv.setMediaPlayer(mp);
				DoubleProperty width=mv.fitWidthProperty();
				DoubleProperty height=mv.fitHeightProperty();
				width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
				height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
				DoubleProperty w=mv.fitWidthProperty();
				DoubleProperty h=mv.fitHeightProperty();
				w.bind(Bindings.selectDouble(hbox.sceneProperty(), "width"));
				h.bind(Bindings.selectDouble(hbox.sceneProperty(), "height"));
				volumeslider.setValue(mp.getVolume()*100);
				volumeslider.valueProperty().addListener(new InvalidationListener() {
					public void invalidated(Observable observable)
					{
						mp.setVolume(volumeslider.getValue()/100);
				}
				});
				mp.currentTimeProperty().addListener(new ChangeListener<Duration>()
						{
					public void changed(ObservableValue<? extends Duration> observable,Duration oldvalue,Duration newValue) {
						videoslider.setValue(newValue.toSeconds());
					}
					
						}
						);
			videoslider.setOnMouseClicked(new EventHandler<MouseEvent>() {

					

					@Override
					public void handle(MouseEvent event) {
						// TODO Auto-generated method stub
						mp.seek(Duration.seconds(videoslider.getValue()));
						
					}
					
				});
			 
		   }
		 
		 
	 }	
	public void play(ActionEvent event) {
		mp.play();
		
	}

	public void pause(ActionEvent event) {
		mp.pause();
	}

	public void fast(ActionEvent event) {
		mp.setRate(2);
	}

	public void slow(ActionEvent event) {
		mp.setRate(0.5);
	}
	public void reload(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.play();
	}
	public void start(ActionEvent event) {
		mp.seek(mp.getStartTime());
		mp.stop();
	}
	public void last(ActionEvent event) {
		mp.seek(mp.getTotalDuration());
		mp.stop();
	}
	
	public void close(ActionEvent event) {
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}




}
