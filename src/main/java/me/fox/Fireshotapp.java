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

package me.fox;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.Getter;
import me.fox.components.Version;
import me.fox.services.*;
import me.fox.ui.components.TrayIcon;
import me.fox.ui.frames.ScreenshotFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @author (Ausgefuchster)
 * @version (2.0 ~ 21.10.2020)
 */

@Getter
public class Fireshotapp extends Application {

    @Getter
    private static Fireshotapp instance;

    public static final Version VERSION = new Version("0.4.1");
    private final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(2));

    private final Map<Class<? extends Service>, Service> services = new HashMap<>();

    private final TrayIcon systemTray = new TrayIcon("Fireshot");
    private final ScreenshotFrame screenshotFrame = new ScreenshotFrame("Fireshotapp");

    @SuppressWarnings("unchecked")
    public <T extends Service> T use(Class<? super T> type) {
        return (T) this.services.get(type);
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void registerServices(Service... services) {
        Arrays.stream(services).forEach((service) -> this.services.put(service.getClass(), service));
    }

    private void registerServices() {
        DrawService drawService = new DrawService();

        this.screenshotFrame.setContentPane(drawService);
        JsonService jsonService = new JsonService();
        RequestService requestService = new RequestService();
        ScreenshotService screenshotService = new ScreenshotService(this.screenshotFrame, drawService, requestService);
        ScreenService screenService = new ScreenService(this.screenshotFrame, screenshotService);
        HotkeyService hotkeyService = new HotkeyService(screenshotService, drawService, screenService);
        FileService fileService = new FileService(screenService, this.systemTray, drawService);
        UpdateService updateService = new UpdateService(requestService, jsonService);

        this.registerServices(
                drawService,
                jsonService,
                requestService,
                screenshotService,
                screenService,
                hotkeyService,
                fileService,
                updateService
        );

    }

    private void load() {
        this.registerServices();
        this.readJson();
        this.use(FileService.class).loadResources();
        this.use(UpdateService.class).checkForUpdate(false);
        this.screenshotFrame.registerMouseListener(this.use(DrawService.class).getDrawListener());
    }

    private void readJson() {
        this.use(JsonService.class).read(this.services.values());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        instance = this;
        instance.load();
        Platform.setImplicitExit(false);
    }
}
