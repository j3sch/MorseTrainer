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
        main_UI_grid.setAlignment(Pos.CENTER);
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label head_label = new Label("Select prefered gamemode");
        Button M2L_Button = new Button();
        Button L2M_Button = new Button();
        L2M_Button.setText("Letters -> Morse");
        M2L_Button.setText("Morse -> letters");
        M2L_Button.setOnAction(e->{
                main_UI_grid.getChildren().clear();
                default_game_screen(1, stage);
        });
        L2M_Button.setOnAction(e->{
                main_UI_grid.getChildren().clear();
                default_game_screen(0, stage);
        });
        main_UI_grid.getChildren().addAll(head_label,M2L_Button,L2M_Button);
        stage.setTitle("Morsetrainer");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void default_game_screen(Integer game_mode, Stage stage){
        final String[] data = MorseQuiz.defaultGameWord();
        final String usr_txt_inpt;
        //get data from backend logic
        final HBox tmp = new HBox();
        final Label given_value = new Label(data[game_mode]);
        Button return_to_MM_button = new Button("Main Menu");
        tmp.setAlignment(Pos.CENTER);
        HBox.setMargin(tmp,new Insets(20,20,20,20));
        tmp.setSpacing(10);
        tmp.getChildren().addAll(given_value,return_to_MM_button);
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        //main_UI_grid.getChildren().add(given_value);
        main_UI_grid.getChildren().add(tmp);
        // Morse -> Alpha
        if (game_mode==1){
            final HBox user_input = new HBox();
            final TextField alpha_input = new TextField();
            final Button user_done = new Button();
            user_done.setText("Done");
            user_done.setOnAction(e->{
                    String alpha_from_user = alpha_input.getCharacters().toString();
                    if (alpha_from_user.equals(data[0])){
                        main_UI_grid.getChildren().clear();
                        default_game_screen(game_mode,stage);
                    }else{
                        popup_wrong_answer();
                    }
            });
            user_input.setAlignment(Pos.CENTER);
            user_input.getChildren().addAll(alpha_input,user_done);
            main_UI_grid.getChildren().add(user_input);
        }
        // Alpha -> Morse
        else if (game_mode == 0){
            HBox user_input = new HBox();
            final Label display_user_input = new Label();
            display_user_input.setTextFill(Color.web("#0099ff"));
            final Button short_morse = new Button();
            short_morse.setText(".");
            short_morse.setOnAction(value -> { alter_label(".",display_user_input); });
            Button long_morse = new Button();
            long_morse.setText("-");
            long_morse.setOnAction(value -> { alter_label("-",display_user_input); });
            Button done_button = new Button();
            done_button.setText("done");
            done_button.setOnAction(e-> {
                if(display_user_input.getText().equals(data[1])){
                    main_UI_grid.getChildren().clear();
                    default_game_screen(game_mode,stage);
                } else {
                    display_user_input.setText("");
                    popup_wrong_answer();
                }});
            user_input.setAlignment(Pos.CENTER);
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

    public void choices_gamemode(Integer gamemode){
        if (gamemode == 0) {
            String[] game_contents = MorseQuiz.getWordToMorseQuiz();
        }
        else{
            String[] game_contents = MorseQuiz.getMorseToWordQuiz();
        }
        final HBox tmp = new HBox();
        final Label given_word = new Label(game_contents[0]);
        Button return_to_MM_button = new Button("Main Menu");
        tmp.setAlignment(Pos.CENTER);
        HBox.setMargin(tmp,new Insets(20,20,20,20));
        tmp.setSpacing(10);
        tmp.getChildren().addAll(given_word,return_to_MM_button);
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        Integer amount_of_created_buttons = 0;
        HBox button_row = new HBox();
        while (amount_of_created_buttons<4){
            int random_index = (int)(Math.random() * (4 - 1 + 1) + 1);
            if (game_contents[random_index] != "in use"){
                Button choice = new Button(game_contents[random_index]);
                if (random_index==1){
                    choice.setOnAction(e-> {
                        main_UI_grid.getChildren().clear();
                        choices_gamemode(gamemode);
                        });
                    }
                else{
                    choice.setOnAction(e->{
                        choice.setEnabled(false);
                    });
                }
                choice.getChildren().add(done_button);
                amount_of_created_buttons+=1;
            }
        }
    }


    public void return_to_Main_Menu(Stage stage){
        main_UI_grid.getChildren().clear();
        start(stage);
    }

    public void alter_label(String character,Label x){

        x.setText(x.getText()+character);
    }
}
