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

import lombok.Getter;
import me.fox.Fireshotapp;
import me.fox.components.ConfigManager;
import me.fox.config.Config;
import me.fox.services.JsonService;
import me.fox.ui.panels.PanelManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author (Ausgefuchster)
 * @version (~ 11.11.2020)
 */

@Getter
public class SettingsFrame extends JFrame implements ConfigManager, WindowListener {

    private final PanelManager panelManager = new PanelManager(this);
    private final JButton okButton, cancelButton, applyButton;

    public SettingsFrame() {
        super("Fireshot - Settings");

        this.setContentPane(this.panelManager);
        this.okButton = new JButton("Ok");
        this.cancelButton = new JButton("Cancel");
        this.applyButton = new JButton("Apply");
        this.setupButtons();
        this.setup();
    }

    private void setup() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setFocusable(true);
        this.setVisible(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.addWindowListener(this);
    }

    private void setupButtons() {
        this.okButton.setLocation(490, 515);
        this.okButton.setSize(80, 35);

        this.cancelButton.setLocation(590, 515);
        this.cancelButton.setSize(80, 35);
        this.applyButton.setLocation(690, 515);
        this.applyButton.setSize(80, 35);

        this.add(this.okButton);
        this.add(this.cancelButton);
        this.add(this.applyButton);

        this.okButton.addActionListener(this::okPerformed);
        this.cancelButton.addActionListener(this::cancelPerformed);
        this.applyButton.addActionListener(this::applyPerformed);
    }

    private void okPerformed(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.saveAndApply();
        this.setVisible(false);
    }

    private void cancelPerformed(ActionEvent event) {
        this.setVisible(false);
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.read(null);
    }

    private void applyPerformed(ActionEvent event) {
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.saveAndApply();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (!b) return;
        this.applyConfig(Fireshotapp.getInstance().use(JsonService.class).getConfig());
    }

    @Override
    public void applyConfig(Config config) {
        this.panelManager.applyConfig(config);
    }

    @Override
    public void windowClosed(WindowEvent event) {
        this.setVisible(false);
        JsonService jsonService = Fireshotapp.getInstance().use(JsonService.class);
        jsonService.read(null);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
