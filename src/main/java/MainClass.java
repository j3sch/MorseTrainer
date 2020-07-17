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
import javax.sound.sampled.LineUnavailableException;

/**
 * Main class. Handles the UI and communicates with the backend to get contents and translate Userinput
 * @author Max Herkenhoff
 */
public class MainClass extends Application {
    //not entirely sure why I have to declare the scene outside everything else...but if I don´t java complains
    private final VBox main_UI_grid = new VBox();
    private final Scene scene = new Scene(main_UI_grid, 640, 480);

    /**
     * Essentially this is the main method as public void main just calls launch
     * which ends up here again.
     *
     * This method displays the Main menu.
     *
     * @param stage the stage is the container for everything that will be displayed
     *
     */
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
    /**
     * main method
     * calls launch but essentially ends up calling start
     *
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Overwrites the stop function for the JavaFX stage.
     * Without this the sound playback would not stop when the UI is closed.
     */

    @Override
    public void stop(){
        System.exit(0);
    }


    /**
     * Displays the game for the default/translate gamemode. Based on the game_mode the way the user can
     * interact with the software changes slightly.
     * This function gets its contents from the Morsequiz class.
     * If user answers the question correctly this method will call itself recursively until user returns to the Main Menu
     * If the user fails to translate a word correctly, the popup_wrong_answer method will be called.
     *
     * @param game_mode  Integer that determines the "direction" of the translation and therefore also the way the user interacts with the UI
     * @param stage      class container for everything visual...is passed on to (almost every funtion)
     */
    public void default_game_screen(Integer game_mode, Stage stage){

        //init Items in UI
        final String[] data = MorseQuiz.defaultGameWord();
        final String usr_txt_inpt;
        final HBox tmp = new HBox();
        final Label given_value = new Label(data[game_mode]);
        final HBox user_input = new HBox();
        final Button play_sound = new Button("play sound");
        final Button return_to_MM_button = new Button("Main Menu");

        //not actually useful or needed but this makes testing easier...well if you don´t want to actually translate it, that is
        System.out.println(data[0]+":"+data[1]);

        //set styling
        tmp.setAlignment(Pos.CENTER);
        HBox.setMargin(tmp,new Insets(20,20,20,20));
        tmp.setSpacing(10);
        tmp.getChildren().addAll(given_value,return_to_MM_button);
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        //main_UI_grid.getChildren().add(given_value);
        main_UI_grid.getChildren().add(tmp);

        // Morse -> Alpha
        if (game_mode==1){
            final TextField alpha_input = new TextField();
            final Button user_done = new Button("Done");
            user_done.setOnAction(e->{
                    String alpha_from_user = alpha_input.getCharacters().toString();
                    if (alpha_from_user.equals(data[0])){
                        main_UI_grid.getChildren().clear();
                        default_game_screen(game_mode,stage);
                    }else{
                        popup_wrong_answer();
                    }
            });
            play_sound.setOnAction(e->{start_sound_playback(data[0]);});
            user_input.setAlignment(Pos.CENTER);
            user_input.getChildren().addAll(alpha_input,user_done);
            main_UI_grid.getChildren().add(user_input);
        }
        // Alpha -> Morse
        else if (game_mode == 0){
            final Label display_user_input = new Label();
            final Button short_morse = new Button(".");
            final Button long_morse = new Button("-");
            final Button done_button = new Button("done");

            display_user_input.setTextFill(Color.web("#0099ff"));

            short_morse.setOnAction(value -> { alter_label(".",display_user_input); });
            long_morse.setOnAction(value -> { alter_label("-",display_user_input); });
            done_button.setOnAction(e-> {
                if(display_user_input.getText().equals(data[1])){
                    main_UI_grid.getChildren().clear();
                    default_game_screen(game_mode,stage);
                } else {
                    display_user_input.setText("");
                    popup_wrong_answer();
                }});
            play_sound.setOnAction(e->{start_sound_playback(display_user_input.getText());});

            user_input.setAlignment(Pos.CENTER);
            main_UI_grid.getChildren().add(display_user_input);
            user_input.getChildren().addAll(short_morse,long_morse,done_button);
            main_UI_grid.getChildren().add(user_input);
        }
    }

    /**
     * Displays the 4 choices gamemode. Again gets its contents from the Morsequiz class
     *
     * @param game_mode     determines which way the game will be played
     * @param stage         class container for everything visual
     */

    public void choices_gamemode(Integer game_mode, Stage stage){
        String[] game_contents;
        if (game_mode == 0) {
            game_contents = MorseQuiz.getWordToMorseQuiz();
        } else{
            game_contents = MorseQuiz.getMorseToWordQuiz();
        }
        //init
        final HBox tmp = new HBox();
        final Label given_word = new Label(game_contents[0]);
        final HBox button_row = new HBox();
        final Button return_to_MM_button = new Button("Main Menu");

        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});

        //styling
        tmp.setAlignment(Pos.CENTER);
        HBox.setMargin(tmp,new Insets(20,20,20,20));
        tmp.setSpacing(10);
        tmp.getChildren().addAll(given_word,return_to_MM_button);

        int amount_of_created_buttons = 0;
        //randomize the order that the 4 choices will be displayed
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

    /**
     * Displays popup if user fails to translate the word in default_game_screen.
     *
     */
    public void popup_wrong_answer(){
        //init
        Stage popup_window = new Stage();
        final VBox popup_layout = new VBox(10);
        final Scene popup_scene = new Scene(popup_layout,250,100);
        final Label fail_text = new Label("Your answer was wrong...please try again");
        final Button exit_button = new Button("Okay");

        //style
        popup_window.setTitle("Wrong Answer");
        popup_window.initModality(Modality.APPLICATION_MODAL);
        exit_button.setOnAction(e->popup_window.close());
        popup_layout.getChildren().addAll(fail_text,exit_button);
        popup_layout.setAlignment(Pos.CENTER);

        //display
        popup_window.setScene(popup_scene);
        popup_window.showAndWait();
    }

    /**
     * Screen for the Translator. Based on the mode the way the user can interact with the UI changes (similar to the game_screens)
     * Directly interacts with the translator class to translate user input.
     *
     * @param mode
     * @param stage
     */
    public void translator(Integer mode,Stage stage){
        //init
        final HBox head_box = new HBox();
        final Label head_label = new Label("Enter text here");
        final Label translated_text = new Label("");
        final Button switch_button = new Button("Switch mode");
        final Button return_to_MM_button = new Button("Main Menu");
        final Button Translate_button = new Button("Translate");
        final Button play_sound = new Button("play sound");
        final HBox user_input = new HBox();

        //style and configuration
        head_box.setAlignment(Pos.CENTER);
        return_to_MM_button.setOnAction(e->{return_to_Main_Menu(stage);});
        head_box.getChildren().addAll(head_label,switch_button,return_to_MM_button);
        user_input.setPadding(new Insets(10,10,10,10));
        main_UI_grid.getChildren().add(head_box);


        if (mode==0){
            //init
            final Button short_morse = new Button(".");
            final Button long_morse = new Button("-");
            final Button finish_cha = new Button("next char");
            final Button add_space = new Button("*space*");
            final Button clear_all_input = new Button("clear");
            final Label display_user_input = new Label();
            LinkedList<String> to_translate = new LinkedList<>();

            //style
            user_input.setAlignment(Pos.CENTER);
            display_user_input.setTextFill(Color.web("#0099ff"));


            //button functionality
            clear_all_input.setOnAction(e -> {
                to_translate.clear();
                display_user_input.setText("");
            });
            add_space.setOnAction(value -> {to_translate.add(" "); alter_label(" ",display_user_input);});
            finish_cha.setOnAction(value -> {
                to_translate.add(StringUtils.difference(String.join("",to_translate),display_user_input.getText()));
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
            play_sound.setOnAction(e->{
                start_sound_playback(to_translate);
            });

            user_input.getChildren().addAll(short_morse,long_morse,finish_cha,add_space,clear_all_input,play_sound);
            main_UI_grid.getChildren().add(display_user_input);
        }
        else{
            //init
            final TextField alpha_input = new TextField();

            //style
            user_input.setAlignment(Pos.CENTER);


            //button func
            switch_button.setOnAction(value -> {
                main_UI_grid.getChildren().clear();
                translator(0,stage);
            });
            Translate_button.setOnAction(value -> {
                char[] to_translate = alpha_input.getCharacters().toString().toCharArray();
                translated_text.setText(Translator.abcToMorse(to_translate));
            });
            play_sound.setOnAction(e->{
                start_sound_playback(translated_text.getText());
            });


            user_input.getChildren().addAll(alpha_input,play_sound);
        }
        main_UI_grid.getChildren().addAll(user_input,Translate_button,translated_text);
    }

    /**
     * Is called by the return_to_MM button and clears the screen before returning to the start() method
     *
     * @param stage class container for everything visual
     */
    public void return_to_Main_Menu(Stage stage){
        main_UI_grid.getChildren().clear();
        start(stage);
    }

    /**
     * Used to display the morse input of the user in default_game_screen and translator
     *
     * @param character - character that was entered by the user. Stored as String because Label.setText only takes String input
     * @param x         - Label to add character to
     */
    public void alter_label(String character,Label x){

        x.setText(x.getText()+character);
    }

    /**
     * This starts the sound playback. Has to be in another Thread as otherwise the program would freeze until sound playback is finished.
     * Calls Translator.morseToSound
     *
     * @param input - input to be translated
     */
    public void start_sound_playback(String input){
        new Thread(() -> {
            try {
                Translator.morseToSound(input);
            } catch (LineUnavailableException e){
                System.out.println("oops...something went wrong");
            }
        }).start();
    }

    /**
     * This starts the sound playback. Has to be in another Thread as otherwise the program would freeze until sound playback is finished.
     * Calls Translator.morseToSound
     *
     * @param input - input to be translated
     */
    public void start_sound_playback(LinkedList<String> input){
        new Thread(() -> {
            try {
                Translator.morseToSound(input);
            } catch (LineUnavailableException e){
                System.out.println("oops...something went wrong");
            }
        }).start();
    }
}
