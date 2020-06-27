import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;


public class MainClass extends Application {
    final VBox main_UI_grid = new VBox();
    Scene scene = new Scene(main_UI_grid, 640, 480);
    @Override
    public void start(Stage stage) {
        main_UI_grid.setSpacing(8);
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label head_label = new Label("Select prefered gamemode");
        Button M2L_Button = new Button();
        Button L2M_Button = new Button();
        main_UI_grid.getChildren().addAll(head_label,M2L_Button,L2M_Button);
        L2M_Button.setText("Letters -> Morse");
        M2L_Button.setText("Morse -> letters");
        M2L_Button.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                main_UI_grid.getChildren().clear();
                game_screen(0);
            }
        });
        L2M_Button.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                main_UI_grid.getChildren().clear();
                game_screen(1);
            }
        });
        stage.setTitle("Morsetrainer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void game_screen(Integer game_mode){
        final String[] data = {"test","_..-.-."};
        final String usr_txt_inpt;
        //get data from backend logic
        System.out.println(game_mode);
        Label given_value = new Label(data[0]);
        main_UI_grid.getChildren().add(given_value);
        if (game_mode==0){
            final HBox user_input = new HBox();
            final TextField alpha_input = new TextField();
            Button user_done = new Button();
            user_done.setText("Done");
            user_done.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent event) {
                    String alpha_from_user = alpha_input.getCharacters().toString();
                    if (alpha_from_user==data[1]){
                        main_UI_grid.getChildren().clear();
                        launch();
                    }
                    else{
                        System.out.println("Nope");
                        //game_fail(data,alpha_from_user);
                    }
                }
            });
            user_input.getChildren().addAll(alpha_input,user_done);
            main_UI_grid.getChildren().add(user_input);
        }
        else if (game_mode == 1){
            HBox user_input = new HBox();
            final Label display_user_input = new Label();
            Button short_morse = new Button();
            short_morse.setText(".");
            // what?!
            short_morse.setOnAction(value -> { });
            Button long_morse = new Button();
            long_morse.setText("_");
            Button exit = new Button();
            exit.setText("cancel");
            user_input.getChildren().addAll(short_morse,long_morse,exit);
            main_UI_grid.getChildren().add(user_input);
        }
    }
    public void alter_label(String character;Label x){
        x.setText(x.getText()+character);
    }
}
