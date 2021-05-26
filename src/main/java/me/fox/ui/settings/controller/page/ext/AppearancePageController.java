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

package me.fox.ui.settings.controller.page.ext;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.config.Config;
import me.fox.config.ImagePinConfig;
import me.fox.config.ScreenshotConfig;
import me.fox.config.SettingsConfig;
import me.fox.services.JsonService;
import me.fox.ui.settings.components.ext.ColorPickerComponent;
import me.fox.ui.settings.components.ext.DoubleSpinnerComponent;
import me.fox.ui.settings.components.ext.ToggleSwitchComponent;
import me.fox.ui.settings.controller.page.PageController;
import me.fox.ui.settings.events.ColorChangeEvent;
import me.fox.ui.settings.events.SpinnerValueChangeEvent;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author (Ausgefuchster)
 * @version (~ 06.05.2021)
 */

@Getter
public class AppearancePageController extends PageController {

    @FXML
    private VBox container;
    @FXML
    private ColorPickerComponent zoomMiddleRectColor, zoomBorderColor, zoomCrossColor, zoomGridColor, dimColor;
    @FXML
    private ToggleSwitchComponent zoomMiddleRect, zoomCross, zoomGrid, zoom;
    @FXML
    private DoubleSpinnerComponent dropShadow;
    @FXML
    private ColorPickerComponent dropShadowColor;

    @FXML
    private void zoomRectangleColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomMiddleRectColor(event.colorAsHex());
    }

    @FXML
    private void zoomBorderColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomBorderColor(event.colorAsHex());
    }

    @FXML
    private void zoomCrossColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomCrossColor(event.colorAsHex());
    }

    @FXML
    private void zoomGridColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomGridColor(event.colorAsHex());
    }

    @FXML
    private void dimColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setDimColor(event.colorAsHex());
    }

    @FXML
    private void showZoomChanged(ActionEvent event) {
        boolean selected = ((ToggleSwitchComponent) event.getSource()).isSelected();
        this.zoomMiddleRect.setDisable(!selected);
        this.zoomCross.setDisable(!selected);
        this.zoomGrid.setDisable(!selected);
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoom(selected);
    }

    @FXML
    private void zoomMiddleRectChanged(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomMiddleRect(this.zoomMiddleRect.isSelected());
    }

    @FXML
    private void zoomCrossChanged(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomCross(this.zoomCross.isSelected());
    }

    @FXML
    private void zoomGridChanged(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getScreenshotConfig().setZoomGrid(this.zoomGrid.isSelected());
    }

    @FXML
    private void dropShadowRadiusChanged(SpinnerValueChangeEvent<Double> event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getImagePinConfig().setShadowRadius(event.getNewValue());
    }

    @FXML
    private void dropShadowColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getImagePinConfig().setColor(event.colorAsHex());
    }

    @Override
    public void applyConfig(Config config) {
        ScreenshotConfig screenshotConfig = config.getScreenshotConfig();
        this.dimColor.setColor(screenshotConfig.getDimColor());

        boolean selected = screenshotConfig.isZoom();
        this.zoom.setSelected(selected);
        this.zoomGrid.setSelected(screenshotConfig.isZoomGrid());
        this.zoomCross.setSelected(screenshotConfig.isZoomCross());
        this.zoomMiddleRect.setSelected(screenshotConfig.isZoomMiddleRect());

        this.zoomGridColor.setColor(screenshotConfig.getZoomGridColor());
        this.zoomCrossColor.setColor(screenshotConfig.getZoomCrossColor());
        this.zoomBorderColor.setColor(screenshotConfig.getZoomBorderColor());
        this.zoomMiddleRectColor.setColor(screenshotConfig.getZoomMiddleRectColor());

        ImagePinConfig imagePinConfig = config.getImagePinConfig();
        this.dropShadowColor.setColor(imagePinConfig.getColor());
        this.dropShadow.setValue(imagePinConfig.getShadowRadius());

        SettingsConfig settingsConfig = config.getSettingsConfig();
        List<Color> list = settingsConfig.getCustomColors().stream().map(Color::web).collect(Collectors.toList());
        this.dimColor.setCustomColors(list);
        this.zoomGridColor.setCustomColors(list);
        this.zoomCrossColor.setCustomColors(list);
        this.zoomBorderColor.setCustomColors(list);
        this.zoomMiddleRectColor.setCustomColors(list);
        this.dropShadowColor.setCustomColors(list);

        this.zoomMiddleRect.setDisable(!selected);
        this.zoomCross.setDisable(!selected);
        this.zoomGrid.setDisable(!selected);
    }
}
