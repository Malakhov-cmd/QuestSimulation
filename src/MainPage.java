import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage extends Application {
    @Override
    public void start(Stage stage) {
        Menu fileMenu = new Menu("File");

        MenuItem startItem = new MenuItem("Start");
        startItem.setOnAction( event -> {
            Field field = new Field();
            myLaunch(field);
        });

        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> Platform.exit());

        fileMenu.getItems().addAll(startItem, new SeparatorMenuItem(), exitItem);

        MenuItem aboutProgramItem = new MenuItem("_About this program");
        aboutProgramItem.setOnAction(event ->
        {
            TextArea areaInfo = new TextArea("The program calculates the Poisson distribution " + "\n" +
                    "Divides into intervals and groups them together" + "\n" +
                    "Builds histograms and makes a decision about the" + "\n" +
                    "permissibility using the Pearson Chi square criterion");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Work information");
            alert.getDialogPane().setExpandableContent(areaInfo);
            alert.showAndWait();
        });

        MenuItem aboutProgramer = new MenuItem("About _programer");
        aboutProgramer.setOnAction(event ->
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Malakhov Georgey, 6302");
            alert.showAndWait();
        });

        Menu helpMenu = new Menu("_Help", null, aboutProgramItem, new SeparatorMenuItem(), aboutProgramer);

        MenuBar bar = new MenuBar(fileMenu, helpMenu);
        VBox root = new VBox(bar);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");
        stage.setScene(scene);
        stage.setWidth(750);
        stage.setHeight(450);
        stage.setTitle("Main Page");
        stage.show();

        TextArea areaInfo = new TextArea("Перед нами таинственный город будущего " + "\n" +
                "Люди в нем живут разные: богатые, бедные, заблудшие" + "\n" +
                "Всех людей в городе объединяет один очень странный факт" + "\n" +
                "Город - непрерывная сеть быстрорастущих кварталов и дорог" + "\n"+
                "имеющая собственный интеллект. " + "\n"+
                "По непонятным алгоритмам он выбирает рандомного человека" + "\n"+
                "и начинает испытывать его на прочность и сообразительность"+ "\n"+
                "Давайте выясним чтоже будет с нами в этом городе"+ "\n"+
                "Для этого нажмите в меню управления кнопку \"" + "start" + "\"");
        Alert introduce = new Alert(Alert.AlertType.INFORMATION);
        introduce.setHeaderText("Вводная информация");
        introduce.getDialogPane().setExpandableContent(areaInfo);
        introduce.showAndWait();
    }

    public static void myLaunch(Application applicationClass) {
        Platform.runLater(() -> {
            try {
                Application application = applicationClass;
                Stage primaryStage = new Stage();
                application.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
