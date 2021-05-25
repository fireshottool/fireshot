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

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.config.Config;
import me.fox.config.DrawConfig;
import me.fox.config.SettingsConfig;
import me.fox.services.JsonService;
import me.fox.ui.settings.components.ext.ColorPickerComponent;
import me.fox.ui.settings.components.ext.IntegerSpinnerComponent;
import me.fox.ui.settings.controller.page.PageController;
import me.fox.ui.settings.events.ColorChangeEvent;
import me.fox.ui.settings.events.SpinnerValueChangeEvent;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author (Ausgefuchster)
 * @version (~ 10.04.2021)
 */

@Getter
public class DrawPageController extends PageController {

    @FXML
    private IntegerSpinnerComponent defaultThickness, increaseThickness, decreaseThickness;

    @FXML
    private ColorPickerComponent drawColor;


    @FXML
    private void defaultThicknessChanged(SpinnerValueChangeEvent<Integer> event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        int value = event.getNewValue();
        jsonService.getConfig().getDrawConfig().setDefaultThickness(value);
    }

    @FXML
    private void decreaseThicknessChanged(SpinnerValueChangeEvent<Integer> event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        int value = event.getNewValue();
        jsonService.getConfig().getDrawConfig().setThicknessDecrease(value);
    }

    @FXML
    private void increaseThicknessChanged(SpinnerValueChangeEvent<Integer> event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        int value = event.getNewValue();
        jsonService.getConfig().getDrawConfig().setThicknessDecrease(value);
    }

    @FXML
    private void drawColorChanged(ColorChangeEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.getConfig().getDrawConfig().setColor(event.colorAsHex());
    }

    @Override
    public void applyConfig(Config config) {
        DrawConfig drawConfig = config.getDrawConfig();
        this.defaultThickness.setValue(drawConfig.getDefaultThickness());
        this.increaseThickness.setValue(drawConfig.getThicknessIncrease());
        this.decreaseThickness.setValue(drawConfig.getThicknessDecrease());
        this.drawColor.setColor(drawConfig.getColor());

        SettingsConfig settingsConfig = config.getSettingsConfig();
        List<Color> list = settingsConfig.getCustomColors().stream().map(Color::web).collect(Collectors.toList());
        this.drawColor.setCustomColors(list);
    }
}
