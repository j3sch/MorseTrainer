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
import java.io.IOException;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;


public class MainClass extends Application {
    final VBox main_UI_grid = new VBox();
    Scene scene = new Scene(main_UI_grid, 640, 480);
    @Override
    public void start(Stage stage) {
        main_UI_grid.setSpacing(8);
        main_UI_grid.setAlignment(Pos.CENTER);
        final String javaVersion = System.getProperty("java.version");
        final String javafxVersion = System.getProperty("javafx.version");

        //creating Main menu items
        final Label head_label = new Label("Select prefered gamemode");
        final Label def_Label = new Label("Translation game");
        final Label diff_label = new Label("4 choices gamemode");
        final Label trans_label = new Label("Translator");
        final Button M2L_Button = new Button("Morse -> letters");
        final Button L2M_Button = new Button("Letters -> Morse");
        final Button Morse_4C_Button = new Button("Morse -> letters");
        final Button Letters_4C_Button = new Button("Letters -> Morse");
        final Button Translator = new Button("Translator");

        //adding button functionality
        Morse_4C_Button.setOnAction(e->{
            main_UI_grid.getChildren().clear();
            choices_gamemode(0, stage);
        });
        Letters_4C_Button.setOnAction(e->{
            main_UI_grid.getChildren().clear();
            choices_gamemode(1, stage);
        });
        M2L_Button.setOnAction(e->{
                main_UI_grid.getChildren().clear();
                default_game_screen(1, stage);
        });
        L2M_Button.setOnAction(e->{
                main_UI_grid.getChildren().clear();
                default_game_screen(0, stage);
        });
        Translator.setOnAction(e -> {
            main_UI_grid.getChildren().clear();
            translator(0, stage);
        });

        //last step before displaying it
        main_UI_grid.getChildren().addAll(head_label,def_Label,M2L_Button,L2M_Button,diff_label,Morse_4C_Button,Letters_4C_Button,trans_label,Translator);
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
        System.out.println(data[0]+":"+data[1]);
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

    public void choices_gamemode(Integer game_mode, Stage stage){
        String[] game_contents;
        try {
            if (game_mode == 0) {
                game_contents = MorseQuiz.getWordToMorseQuiz();
            }
            else{
                game_contents = MorseQuiz.getMorseToWordQuiz();
            }
        }catch (IOException e) {
            e.printStackTrace();
            return;
        }
        final HBox tmp = new HBox();
        final Label given_word = new Label(game_contents[0]);
        Button return_to_MM_button = new Button("Main Menu");
        tmp.setAlignment(Pos.CENTER);
        HBox.setMargin(tmp,new Insets(20,20,20,20));
        tmp.setSpacing(10);
        tmp.getChildren().addAll(given_word,return_to_MM_button);
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        int amount_of_created_buttons = 0;
        HBox button_row = new HBox();
        while (amount_of_created_buttons<4){
            int random_index = (int)(Math.random() * (4 - 1 + 1) + 1);
            if (!game_contents[random_index].equals("in use")){
                Button choice = new Button(game_contents[random_index]);
                if (random_index==1){
                    System.out.println("right_choice:"+game_contents[random_index]);
                    choice.setOnAction(e-> {
                        main_UI_grid.getChildren().clear();
                        choices_gamemode(game_mode,stage);
                    });
                }
                else{
                    choice.setOnAction(e->{
                        choice.setDisable(true);
                    });
                }
                game_contents[random_index] = "in use";
                button_row.getChildren().add(choice);
                amount_of_created_buttons+=1;
            }
        }
        button_row.setAlignment(Pos.CENTER);
        main_UI_grid.getChildren().addAll(tmp,button_row);
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

    public void translator(Integer mode,Stage stage){
        HBox head_box = new HBox();
        head_box.setAlignment(Pos.CENTER);
        Label head_label = new Label("Enter text here");
        Label translated_text = new Label("");
        Button switch_button = new Button("Switch mode");
        Button return_to_MM_button = new Button("Main Menu");
        Button Translate_button = new Button("Translate");
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        head_box.getChildren().addAll(head_label,switch_button,return_to_MM_button);
        HBox user_input = new HBox();
        user_input.setSpacing(10);
        if (mode==0){
            LinkedList<String> to_translate = new LinkedList<>();
            user_input.setAlignment(Pos.CENTER);
            final Label display_user_input = new Label();
            display_user_input.setTextFill(Color.web("#0099ff"));
            final Button short_morse = new Button(".");
            final Button long_morse = new Button("-");
            final Button finish_cha = new Button("next cha");
            final Button add_space = new Button("*space*");
            final Button clear_all_input = new Button("clear");
            clear_all_input.setOnAction(e -> {
                to_translate.clear();
                display_user_input.setText("");
            });
            add_space.setOnAction(value -> {to_translate.add(" "); alter_label(" ",display_user_input);});
            finish_cha.setOnAction(value -> {
                to_translate.add(StringUtils.difference(StringUtils.join(to_translate),display_user_input.getText()));
            });
            short_morse.setOnAction(value -> { alter_label(".",display_user_input);
            });
            long_morse.setOnAction(value -> { alter_label("-",display_user_input); });
            switch_button.setOnAction(value -> {
                main_UI_grid.getChildren().clear();
                translator(1,stage);
            });
            Translate_button.setOnAction(value -> {
                translated_text.setText(Translator.morseToAbc(to_translate));
            });
            user_input.getChildren().addAll(short_morse,long_morse,finish_cha,add_space);
            main_UI_grid.getChildren().add(display_user_input);
        }
        else{
            final TextField alpha_input = new TextField();
            user_input.setAlignment(Pos.CENTER);
            user_input.getChildren().add(alpha_input);
            switch_button.setOnAction(value -> {
                main_UI_grid.getChildren().clear();
                translator(0,stage);
            });
            Translate_button.setOnAction(value -> {
                char[] to_translate = alpha_input.getCharacters().toString().toCharArray();
                translated_text.setText(Translator.abcToMorse(to_translate));
            });
        }
        main_UI_grid.getChildren().addAll(head_box,user_input,Translate_button,translated_text);
    }

    public void return_to_Main_Menu(Stage stage){
        main_UI_grid.getChildren().clear();
        start(stage);
    }

    public void alter_label(String character,Label x){

        x.setText(x.getText()+character);
    }
}
