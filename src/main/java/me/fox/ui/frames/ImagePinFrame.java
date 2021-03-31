/*
 * Fireshotapp is an easy to use screenshot program
 *     Copyright (C) 2021  Ausgefuchster
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.fox.ui.frames;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import me.fox.components.ConfigManager;
import me.fox.config.Config;
import me.fox.config.ImagePinConfig;

import java.awt.image.BufferedImage;

/**
 * @author (Ausgefuchster)
 * @version (~ 13.03.2021)
 */

public class ImagePinFrame implements ConfigManager {

    private final Stage stage = new Stage();
    private final VBox vBox = new VBox();
    private final Scene scene = new Scene(vBox);
    private final DropShadow dropShadow = new DropShadow(10.0, Color.WHITE);

    private int distanceX;
    private int distanceY;

    public ImagePinFrame() {
        this.stage.setTitle("Fireshotapp - Screenshot pin");
        this.stage.initStyle(StageStyle.TRANSPARENT);
        this.stage.setAlwaysOnTop(true);
        this.stage.setScene(this.scene);
        this.scene.setFill(Color.TRANSPARENT);

        this.vBox.setEffect(dropShadow);

        this.addListener();
    }


    public void showImage(BufferedImage bufferedImage) {
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        this.vBox.setBackground(new Background(backgroundImage));
        this.vBox.setMinWidth(image.getWidth() + 20);
        this.vBox.setMinHeight(image.getHeight() + 20);

        this.stage.show();
    }

    private void addListener() {
        this.scene.setOnMousePressed(this::onMousePressed);
        this.scene.setOnMouseDragged(this::onMouseDragged);
        this.scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onMousePressed(MouseEvent event) {
        this.distanceX = (int) (event.getX() - this.scene.getX());
        this.distanceY = (int) (event.getY() - this.scene.getY());
    }

    private void onMouseDragged(MouseEvent event) {
        this.stage.setX(event.getScreenX() - this.distanceX);
        this.stage.setY(event.getScreenY() - this.distanceY);
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ESCAPE))
            this.stage.hide();
    }

    @Override
    public void applyConfig(Config config) {
        ImagePinConfig imagePinConfig = config.getImagePinConfig();
        java.awt.Color color = java.awt.Color.decode(imagePinConfig.getColor());
        Color shadowColor = Color.rgb(color.getRed(), color.getGreen(), color.getBlue());
        double shadowRadius = imagePinConfig.getShadowRadius();
        this.dropShadow.setColor(shadowColor);
        this.dropShadow.setRadius(shadowRadius);
    }
}
