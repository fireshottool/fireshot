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

package me.fox.ui.settings.components.ext;

import com.sun.javafx.application.PlatformImpl;
import javafx.stage.DirectoryChooser;
import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.services.JsonService;
import me.fox.ui.components.settings.SettingsComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * @author (Ausgefuchster)
 * @version (~ 01.12.2020)
 */

@Getter
public class LocationChooserComponent extends SettingsComponent {

    private final JButton button;
    private final JLabel label;
    private final String labelText;

    private final DirectoryChooser fileChooser = new DirectoryChooser();

    public LocationChooserComponent(Point location, String label) {
        super(location);

        this.labelText = label;
        this.button = new JButton("Choose Location");
        this.button.setSize(120, 30);
        this.button.setLocation(20, 40);
        this.label = new JLabel(label);
        this.label.setLocation(10, 0);
        this.label.setSize(350, 60);

        this.add(this.button);
        this.add(this.label);
        this.button.addActionListener(this::actionPerformed);
    }

    public void setLocationText(String string) {
        this.label.setText(labelText + string);
    }

    private void actionPerformed(ActionEvent event) {
        PlatformImpl.startup(() -> {
            File file = this.fileChooser.showDialog(null);
            if (file == null) return;
            JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
            String filePath = file.toString();
            jsonService.getConfig().getFileConfig().setImageLocation(filePath);
            this.label.setText(labelText + filePath);
        });
    }
}
