import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;



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
        M2L_Button.setOnAction(e->{
                main_UI_grid.getChildren().clear();
                game_screen(0, stage);
        });
        L2M_Button.setOnAction(e->{
                main_UI_grid.getChildren().clear();
                game_screen(1, stage);
        });
        stage.setTitle("Morsetrainer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }

    public void game_screen(Integer game_mode, Stage stage){
        final String[] data = {"THIS IS A HUGE PLACEHOLDER!!!!!!","_..-.-."};
        final String usr_txt_inpt;
        //get data from backend logic
        System.out.println(game_mode);
        HBox tmp = new HBox();
        Label given_value = new Label(data[0]);
        main_UI_grid.getChildren().add(given_value);
        Button return_to_MM_button = new Button("Main Menu");
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        tmp.getChildren().addAll(given_value,return_to_MM_button);
        tmp.setSpacing(10);
        HBox.setMargin(tmp,new Insets(20,20,20,20));
        main_UI_grid.getChildren().add(tmp);
        if (game_mode==0){
            final HBox user_input = new HBox();
            final TextField alpha_input = new TextField();
            Button user_done = new Button();
            user_done.setText("Done");
            user_done.setOnAction(e->{
                    String alpha_from_user = alpha_input.getCharacters().toString();
                    if (alpha_from_user==data[1]){
                        game_screen(game_mode,stage);
                    }else{
                        popup_wrong_answer();
                    }
            });
            user_input.getChildren().addAll(alpha_input,user_done);
            main_UI_grid.getChildren().add(user_input);
        }
        else if (game_mode == 1){
            HBox user_input = new HBox();
            final Label display_user_input = new Label();
            display_user_input.setTextFill(Color.web("#0099ff"));
            Button short_morse = new Button();
            short_morse.setText(".");
            short_morse.setOnAction(value -> { alter_label(".",display_user_input); });
            Button long_morse = new Button();
            long_morse.setText("_");
            long_morse.setOnAction(value -> { alter_label("_",display_user_input); });
            Button done_button = new Button();
            done_button.setText("done");
            done_button.setOnAction(e-> {
                if(display_user_input.getText()==data[0]){
                    game_screen(game_mode,stage);
                } else {
                    display_user_input.setText("");
                    popup_wrong_answer();
                }});
            main_UI_grid.getChildren().add(display_user_input);
            user_input.getChildren().addAll(short_morse,long_morse,done_button);
            main_UI_grid.getChildren().add(user_input);
        }
    }

    public void popup_wrong_answer(){
        Stage popup_window = new Stage();
        popup_window.setTitle("Wrong Answer");
        popup_window.initModality(Modality.APPLICATION_MODAL);
        Label fail_text = new Label("Your answer was wrong...please try again");
        Button exit_button = new Button("Okay");
        exit_button.setOnAction(e->popup_window.close());
        VBox popup_layout = new VBox(10);
        popup_layout.getChildren().addAll(fail_text,exit_button);
        popup_layout.setAlignment(Pos.CENTER);
        Scene popup_scene = new Scene(popup_layout,250,100);
        popup_window.setScene(popup_scene);
        popup_window.showAndWait();
    }

    public void return_to_Main_Menu(Stage stage){
        main_UI_grid.getChildren().clear();
        start(stage);
    }

    public void alter_label(String character,Label x){

        x.setText(x.getText()+character);
    }
}
