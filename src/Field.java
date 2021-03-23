import javafx.application.Application;
import javafx.application.Platform;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum States {
    S,
    A1,
    A2,
    A3,
    A4,
    A5,
    A6,
    F
}

public class Field extends Application {
    private ArrayList<Image> listImages = new ArrayList<>();
    private Circle sPoint;
    private Circle a1Point;
    private Circle a2Point;
    private Circle a3Point;
    private Circle a4Point;
    private Circle a5Point;
    private Circle a6Point;
    private Circle fPoint;
    private Label setFatigue;
    private Label setStress;
    private Label setEndurance;
    private Label setBrave;
    private Label timeInHour;
    private TextArea messageArea = new TextArea();
    private ImageView imageScene;
    private Task<Void> task;
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public void start(Stage stage) throws Exception {

        sPoint = new Circle(525, 45, 10);
        sPoint.setFill(Color.GREY);
        sPoint.setStroke(Color.BLACK);

        Line diagonalS_A1 = new Line(520, 50, 400, 180);
        diagonalS_A1.setStroke(Color.GREY);

        Line diagonalS_A2 = new Line(525, 45, 650, 180);
        diagonalS_A2.setStroke(Color.GREY);

        Polyline polylineS_F = new Polyline();
        polylineS_F.getPoints().addAll(525.0, 45.0,
                350.0, 150.0,
                350.0, 600.0,
                590.0, 720.0);
        polylineS_F.setStroke(Color.GREY);


        a1Point = new Circle(400, 180, 10);
        a1Point.setFill(Color.GREY);
        a1Point.setStroke(Color.BLACK);

        Line diagonalA1_A4 = new Line(400, 180, 525, 360);
        diagonalA1_A4.setStroke(Color.GREY);

        Polyline polylineA1_F = new Polyline();
        polylineA1_F.getPoints().addAll(400.0, 180.0,
                410.0, 600.0,
                590.0, 720.0);
        polylineA1_F.setStroke(Color.GREY);


        a2Point = new Circle(650, 180, 10);
        a2Point.setFill(Color.GREY);
        a2Point.setStroke(Color.BLACK);

        Line diagonalA2_A4 = new Line(650, 180, 525, 360);
        diagonalA2_A4.setStroke(Color.GREY);

        Line diagonalA2_A3 = new Line(650, 180, 775, 180);
        diagonalA2_A3.setStroke(Color.GREY);


        a3Point = new Circle(775, 180, 10);
        a3Point.setFill(Color.GREY);
        a3Point.setStroke(Color.BLACK);

        Line diagonalA3_S = new Line(775, 180, 525, 45);
        diagonalA3_S.setStroke(Color.GREY);

        Line diagonalA3_A6 = new Line(775, 180, 725, 360);
        diagonalA3_A6.setStroke(Color.GREY);


        a4Point = new Circle(525, 360, 10);
        a4Point.setFill(Color.GREY);
        a4Point.setStroke(Color.BLACK);

        Line diagonalA4_A5 = new Line(525, 360, 525, 540);
        diagonalA4_A5.setStroke(Color.GREY);


        a5Point = new Circle(725, 360, 10);
        a5Point.setFill(Color.GREY);
        a5Point.setStroke(Color.BLACK);

        Line diagonalA5_S = new Line(525, 540, 525, 45);
        diagonalA5_S.setStroke(Color.GREY);

        Line diagonalA5_F = new Line(525, 540, 590, 720);
        diagonalA5_F.setStroke(Color.GREY);

        Polyline polylineA5_S = new Polyline();
        polylineA5_S.getPoints().addAll(525.0, 540.0,
                950.0, 300.0,
                950.0, 180.0,
                525.0, 45.0);
        polylineA5_S.setStroke(Color.GREY);


        a6Point = new Circle(525, 540, 10);
        a6Point.setFill(Color.GREY);
        a6Point.setStroke(Color.BLACK);

        Polyline polylineA6_S = new Polyline();
        polylineA6_S.getPoints().addAll(725.0, 360.0,
                850.0, 300.0,
                850.0, 180.0,
                525.0, 45.0);
        polylineA6_S.setStroke(Color.GREY);

        Line diagonalA6_F = new Line(725, 360, 590, 720);
        diagonalA6_F.setStroke(Color.GREY);

        fPoint = new Circle(590, 720, 10);
        fPoint.setFill(Color.GREY);
        fPoint.setStroke(Color.BLACK);

        final double rem = new Text("").getLayoutBounds().getHeight();

        Button getTime = new Button("Выполним наш квест");
        getTime.snapPositionX(10);
        getTime.snapPositionY(10);
        getTime.setMinSize(125, 35);
        getTime.setOnAction(event -> {
            write(stage);
        });

        initionalizeImage();
        imageScene = new ImageView(listImages.get(6));
        imageScene.setFitHeight(170);
        imageScene.setFitWidth(300);

        GridPane labelPane = new GridPane();
        labelPane.setHgap(0.8 * rem);
        labelPane.setVgap(0.8 * rem);
        labelPane.setPadding(new Insets(0.8 * rem));

        Font font = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 18.0);
        Label fatigue = new Label("Усталость:");
        fatigue.setFont(font);
        Label stress = new Label("Стресс:");
        stress.setFont(font);
        Label endurance = new Label("Выносливость:");
        endurance.setFont(font);
        Label brave = new Label("Смелость:");
        brave.setFont(font);
        setFatigue = new Label();
        setFatigue.setFont(font);
        setStress = new Label();
        setStress.setFont(font);
        setEndurance = new Label();
        setEndurance.setFont(font);
        setBrave = new Label();
        setBrave.setFont(font);
        labelPane.add(fatigue, 0, 0);
        labelPane.add(setFatigue, 1, 0);
        labelPane.add(stress, 0, 1);
        labelPane.add(setStress, 1, 1);
        labelPane.add(endurance, 0, 2);
        labelPane.add(setEndurance, 1, 2);
        labelPane.add(brave, 0, 3);
        labelPane.add(setBrave, 1, 3);

        Font fontTimes = Font.font("System", FontWeight.BOLD, FontPosture.REGULAR, 20.0);
        Label duration = new Label("Длительность эксперемента:");
        duration.setFont(fontTimes);
        timeInHour = new Label();
        timeInHour.setFont(fontTimes);
        Label hours = new Label("Часов");
        hours.setFont(fontTimes);

        VBox timeBox = new VBox(0.8 * rem, duration, timeInHour, hours);

        Pane root = new Pane(sPoint, a1Point, a2Point, a3Point, a4Point, a5Point, a6Point, fPoint, diagonalS_A1, diagonalS_A2, polylineS_F, diagonalA1_A4, polylineA1_F, diagonalA2_A4, diagonalA2_A3
                , diagonalA3_A6, polylineA6_S, diagonalA6_F, diagonalA4_A5, diagonalA5_F, polylineA5_S, diagonalA3_S);

        messageArea.setMinSize(300, 245);
        messageArea.setWrapText(true);
        VBox vBox = new VBox(0.8 * rem, getTime, messageArea, imageScene, labelPane, timeBox);

        vBox.setMaxSize(300, 450);
        HBox hBox = new HBox(vBox, root);
        hBox.setPadding(new Insets(0.8 * rem));

        Scene scene = new Scene(hBox);
        scene.getStylesheets().add("styleField.css");
        stage.setScene(scene);
        stage.setWidth(1350);
        stage.setHeight(900);
        stage.setTitle("Main Page");
        stage.show();
    }

    private void initionalizeImage() {
        File dir = new File("E:\\ModelingGraphSolution\\src\\IMG");
        List<File> fileList = new ArrayList<>();
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                fileList.add(file);
            }
        }

        for (File file : fileList) {
            String placeURL = null;
            try {
                placeURL = file.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Image image = new Image(placeURL);
            listImages.add(image);
        }
    }

    private void graphVisualization(States state) {
        switch (state) {
            case S:
                sPoint.setFill(Color.RED);
                imageScene.setImage(listImages.get(6));
                a1Point.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case A1:
                a1Point.setFill(Color.RED);
                imageScene.setImage(listImages.get(3));
                sPoint.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case A2:
                a2Point.setFill(Color.RED);
                imageScene.setImage(listImages.get(4));
                a1Point.setFill(Color.GREY);
                sPoint.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case A3:
                a3Point.setFill(Color.RED);
                imageScene.setImage(listImages.get(2));
                a1Point.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                sPoint.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case A4:
                a4Point.setFill(Color.RED);
                imageScene.setImage(listImages.get(7));
                a1Point.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                sPoint.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case A5:
                a5Point.setFill(Color.RED);
                imageScene.setImage(listImages.get(5));
                a1Point.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                sPoint.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case A6:
                a6Point.setFill(Color.RED);
                imageScene.setImage(listImages.get(1));
                a1Point.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                sPoint.setFill(Color.GREY);
                fPoint.setFill(Color.GREY);
                break;
            case F:
                fPoint.setFill(Color.RED);
                imageScene.setImage(listImages.get(0));
                a1Point.setFill(Color.GREY);
                a2Point.setFill(Color.GREY);
                a3Point.setFill(Color.GREY);
                a4Point.setFill(Color.GREY);
                a5Point.setFill(Color.GREY);
                a6Point.setFill(Color.GREY);
                sPoint.setFill(Color.GREY);
                break;
        }
    }

    private int poissonRandomNumber() {
        double L = Math.exp(-5);
        int k = 0;
        double p = 1;
        do {
            k = k + 1;
            double u = (Math.random());
            p = p * u;
        } while (p > L);
        return k - 1;
    }

    private void write(Stage stage) {
        task = new Task<Void>() {
            @Override
            public Void call() {
                States states = States.S;
                graphVisualization(states);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PersonStats person = new PersonStats(5, 5, 5, 5);

                while (states != States.F && person.getHour() < 150) {
                    switch (states) {
                        case S:
                            messageArea.appendText("\n" + " Вы вышли на улицу города будущего"+ "\n");
                            if (person.getFatigue() > 6) {
                                states = States.F;
                                person.setHourPast(person.getHour() + 23);
                                messageArea.appendText("\n" + "Вы настолько хотели спать, что приняли решиние воздержать от выхода на улицу сегодня"+ "\n");
                            } else {
                                if (person.getEndurance() < 5) {
                                    states = States.A1;
                                    person.setHourPast(person.getHour() + 2);
                                    messageArea.appendText("\n" + "Вы настолько голодны, что были вынуждены отправиться в магазин за едой"+ "\n");
                                } else {
                                    states = States.A2;
                                    person.setHourPast(person.getHour() + 2);
                                    messageArea.appendText("\n" + "Вы отправились на работу"+ "\n");
                                }
                            }
                            break;
                        case A1:
                            messageArea.appendText("\n" + "Вы купили ароматный кофе и круасан" + "\n" +
                                    "но впереди показался интересный молодой человек"+ "\n");
                            if (person.getBrave() > 5) {
                                states = States.A4;
                                person.setHourPast(person.getHour() + 5);
                                messageArea.appendText("\n" + "Вы разговорились с человеком, но с каждой секундой вы теряете сознание"+ "\n");
                            } else {
                                states = States.F;
                                person.setHourPast(person.getHour() + 9);
                                messageArea.appendText("\n" + "Разговоры с незнакомцами не в ваших привычках, да и дела на работе сами себя не решат" + "\n" +
                                        "Вы приходите на работу и успешно справляетесь с поставленными задачами"+ "\n");
                            }
                            break;
                        case A2:
                            messageArea.appendText("\n" + "На работе как всегда очередь в раздевалку" + "\n" +
                                    "новенький сотрудник предлагает обойти толку и зайти в раздевалку со служебного входа"+ "\n");
                            if (person.getStress() > 5) {
                                states = States.A4;
                                person.setHourPast(person.getHour() + 5);
                                messageArea.appendText("\n" + "Парень дотронулся до вашего виска и улыбается, вы теряете сознание"+ "\n");
                            } else {
                                states = States.A3;
                                person.setHourPast(person.getHour() + 1);
                                messageArea.appendText("\n" + "Что этот парень о себе возомнил, поеду на лифте, одежду в гардероб отдам позже"+ "\n");
                            }
                            break;
                        case A3:
                            messageArea.appendText("\n" + "Ваш лифт застревает, необходимо выбираться" + "\n" + "так как он вот-вот сорвется"+ "\n");
                            person.setStress(poissonRandomNumber());
                            person.setBrave(poissonRandomNumber());
                            if (person.getStress() < 5 && person.getBrave() > 7) {
                                states = States.A5;
                                person.setHourPast(person.getHour() + 1);
                                messageArea.appendText("\n" + "Вам хватило духу и смекалки, чтобы вылезти из лифта через люк"+ "\n");
                            } else {
                                states = States.S;
                                person.setBrave(poissonRandomNumber());
                                person.setStress(poissonRandomNumber());
                                person.setEndurance(poissonRandomNumber());
                                person.setFatigue(poissonRandomNumber());
                                person.setHourPast(person.getHour() + 20);
                                messageArea.appendText("\n" + "Вы слышате как троссы лопнули, и видите яркую вспушку"+ "\n");
                            }
                            break;
                        case A4:
                            messageArea.appendText("\n" + "Вы приходите нендолго в себя и осознаете, что вас куда-то везут"+ "\n");
                            person.setBrave(poissonRandomNumber());
                            person.setStress(poissonRandomNumber());
                            person.setEndurance(poissonRandomNumber());
                            person.setFatigue(poissonRandomNumber());
                            states = States.A6;
                            person.setHourPast(person.getHour() + 10);
                            break;
                        case A5:
                            messageArea.appendText("\n" + "После невероятного спасения вы оказываетесь в корридоре полном людей"+ "\n");
                            if (person.getEndurance() < 3) {
                                person.setBrave(poissonRandomNumber());
                                person.setStress(poissonRandomNumber());
                                person.setEndurance(poissonRandomNumber());
                                person.setFatigue(poissonRandomNumber());
                                states = States.S;
                                person.setHourPast(person.getHour() + 20);
                                messageArea.appendText("\n" + "Из-за сильного волнения вы теряете сознание, перед этим увидев лишь яркую вспушку"+ "\n");
                            } else {
                                states = States.F;
                                person.setHourPast(person.getHour() + 1);
                                messageArea.appendText("\n" + "Вы отряхиваетесь под аплодисменты, все вами гордятся и восхищаются"+ "\n");
                            }
                            break;
                        case A6:
                            messageArea.appendText("\n" + "Очнувшись в каком-то саду вы слышате лай собак и хохот"+ "\n");
                            if (person.getEndurance() > 6 && person.getFatigue() < 5) {
                                states = States.F;
                                person.setHourPast(person.getHour() + 2);
                                messageArea.appendText("\n" + "Через несколько секунд лай собак приблизился и стали слышну пролетающие рядом пули," + "\n" +
                                        "выпущенные из старого мушкета. Вы никогда не бежали с такой скоростью. Это спасло вам жизнь"+ "\n");
                            } else {
                                if (person.getStress() > 5) {
                                    person.setBrave(poissonRandomNumber());
                                    person.setStress(poissonRandomNumber());
                                    person.setEndurance(poissonRandomNumber());
                                    person.setFatigue(poissonRandomNumber());
                                    states = States.S;
                                    person.setHourPast(person.getHour() + 10);
                                    messageArea.appendText("\n" + "Через несколько секунд лай собак приблизился и стали слышну пролетающие рядом пули," + "\n" +
                                            "выпущенные из старого мушкета. Перенервничав вы постоянно оглядываетесь." + "\n" +
                                            "Из-за этого вы спотыкаетесь, что позволяет охотничим псам догнать вас. Вы видите вспышку" + "\n");
                                } else {
                                    person.setBrave(poissonRandomNumber());
                                    person.setStress(poissonRandomNumber());
                                    person.setEndurance(poissonRandomNumber());
                                    person.setFatigue(poissonRandomNumber());
                                    states = States.S;
                                    person.setHourPast(person.getHour() + 10);
                                    messageArea.appendText("\n" + "Через несколько секунд лай собак приблизился и стали слышну пролетающие рядом пули," + "\n" +
                                            "выпущенные из старого мушкета. Вы истощены" + "\n" +
                                            " Вы ловите пулю под громкий смех и псы настигают вас. Вы видите вспышку"+ "\n");
                                }
                            }
                            break;
                        case F:
                            messageArea.appendText("\n" + "День завршается и вы возращаетесь домой, чувствуя себя уставшим"+ "\n");
                            break;
                    }
                    Platform.runLater(() -> {
                        setFatigue.setText(String.valueOf(person.getFatigue()));
                        setStress.setText(String.valueOf(person.getStress()));
                        setEndurance.setText(String.valueOf(person.getEndurance()));
                        setBrave.setText(String.valueOf(person.getBrave()));
                        timeInHour.setText(String.valueOf(person.getHour()));
                    });
                    graphVisualization(states);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        executor.execute(task);
    }
}
