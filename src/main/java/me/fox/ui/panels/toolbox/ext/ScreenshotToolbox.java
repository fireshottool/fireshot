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

package me.fox.ui.panels.toolbox.ext;

import javafx.application.Platform;
import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.enums.ToolboxType;
import me.fox.services.DrawService;
import me.fox.services.ScreenService;
import me.fox.ui.components.toolbox.ToolboxComponent;
import me.fox.ui.components.toolbox.ext.DefaultToolboxComponent;
import me.fox.ui.panels.toolbox.Toolbox;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author (Ausgefuchster)
 * @version (~ 24.10.2020)
 */

@Getter
public class ScreenshotToolbox extends Toolbox {

    private ToolboxComponent drawComponent,
            confirmComponent,
            googleSearchComponent,
            textRecognitionComponent,
            pinScreenshotComponent,
            cancelComponent;

    public ScreenshotToolbox() {
        super(ToolboxType.HORIZONTAL);
    }


    private void confirmScreenshot(ActionEvent event) {
        Fireshotapp.getInstance().use(ScreenService.class).hideAndConfirm(false, false);
    }

    private void switchDraw(ActionEvent event) {
        DrawService drawService = Fireshotapp.getInstance().use(DrawService.class);

        drawService.setDraw(!drawService.isDraw());
        ScreenService screenService = Fireshotapp.getInstance().use(ScreenService.class);
        if (screenService.getDrawToolbox().isVisible()) {
            screenService.getDrawToolbox().hideSelf();
        } else {
            screenService.getDrawToolbox().showSelf();
        }
    }

    private void cancel(ActionEvent event) {
        Fireshotapp.getInstance().use(ScreenService.class).resetAndHide();
    }

    private void googleSearch(ActionEvent event) {
        Fireshotapp.getInstance().use(ScreenService.class).hideAndConfirm(false, true);
    }

    private void textRecognition(ActionEvent event) {
        Fireshotapp.getInstance().use(ScreenService.class).hideAndConfirm(true, false);
    }

    private void pinScreenshot() {
        Fireshotapp.getInstance().use(ScreenService.class).pinScreenshot();
    }

    @Override
    public void loadToolboxComponents() {
        this.googleSearchComponent = new DefaultToolboxComponent(null, this::googleSearch);
        this.googleSearchComponent.setToolTipText("Search the image on google");

        this.pinScreenshotComponent = new DefaultToolboxComponent(null, event -> Platform.runLater(this::pinScreenshot));
        this.pinScreenshotComponent.setToolTipText("Pin screenshot");

        this.textRecognitionComponent = new DefaultToolboxComponent(null, this::textRecognition);
        this.textRecognitionComponent.setToolTipText("Get the text on the image (OCR)");

        this.cancelComponent = new DefaultToolboxComponent(null, this::cancel);
        this.cancelComponent.setToolTipText("Cancel the screenshot");

        this.drawComponent = new DefaultToolboxComponent(null, this::switchDraw, true, false);
        this.drawComponent.setToolTipText("Switch to draw");

        this.confirmComponent = new DefaultToolboxComponent(null, this::confirmScreenshot);
        this.confirmComponent.setToolTipText("Confirm Screenshot");

        this.addComponent(this.cancelComponent);
        this.addComponent(this.pinScreenshotComponent);
        this.addComponent(this.googleSearchComponent);
        this.addComponent(this.textRecognitionComponent);
        this.addComponent(this.drawComponent);
        this.addComponent(this.confirmComponent);
    }

    private Image readAndScaleImage(File file) throws IOException {
        return ImageIO.read(file).getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    }

    @Override
    public void resetResources() {
        this.drawComponent.setIcon(null);
        this.cancelComponent.setIcon(null);
        this.confirmComponent.setIcon(null);
        this.googleSearchComponent.setIcon(null);
        this.textRecognitionComponent.setIcon(null);
    }

    @Override
    public void reset() {
        DrawService drawService = Fireshotapp.getInstance().use(DrawService.class);
        if (drawService.isDraw()) {
            this.drawComponent.unselect();
        }
    }

    @Override
    public void applyResources(List<File> files) {
        super.applyResources(files);
        this.resetResources();

        files.stream().filter(var -> !var.getName().equals("toolboxbg.png")).forEach(var -> {
            try {
                if (var.getName().equals("save.png")) {
                    this.confirmComponent.setIcon(new ImageIcon(
                            this.readAndScaleImage(var)
                    ));
                } else if (var.getName().equals("pencil.png")) {
                    this.drawComponent.setIcon(new ImageIcon(
                            this.readAndScaleImage(var)
                    ));
                } else if (var.getName().equals("ocr.png")) {
                    this.textRecognitionComponent.setIcon(new ImageIcon(
                            this.readAndScaleImage(var)
                    ));
                } else if (var.getName().equals("googlesearch.png")) {
                    this.googleSearchComponent.setIcon(new ImageIcon(
                            this.readAndScaleImage(var)
                    ));
                } else if (var.getName().equals("cross.png")) {
                    this.cancelComponent.setIcon(new ImageIcon(
                            this.readAndScaleImage(var)
                    ));
                } else if (var.getName().equals("pin.png")) {
                    this.pinScreenshotComponent.setIcon(new ImageIcon(
                            this.readAndScaleImage(var)
                    ));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
