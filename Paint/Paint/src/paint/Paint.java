
package paint;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class Paint extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("view/styles.css").toExternalForm());
        
        
        stage.setScene(scene);
        stage.setMaxHeight(528);
        stage.setMaxWidth(1000);
        stage.show();
    }

   
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        launch(args);
        
    }
}
