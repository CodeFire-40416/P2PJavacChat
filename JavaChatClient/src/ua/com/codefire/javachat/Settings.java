/*
 * Copyright (C) 2016 CodeFireUA <edu@codefire.com.ua>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ua.com.codefire.javachat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import ua.com.codefire.javachat.ui.ContactsFrame;

/**
 *
 * @author CodeFireUA <edu@codefire.com.ua>
 */
public final class Settings {

    // SINGLETON
    private static final Settings instance = new Settings();

    static {
        instance.loadSettings();
    }

    public static Settings getInstance() {
        return instance;
    }

    // DATA
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public Properties loadSettings() {
        try (FileInputStream fis = new FileInputStream("settings.properties")) {
            properties.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        return properties;
    }

    public Properties storeSettings() {
        try (FileOutputStream fos = new FileOutputStream("settings.properties")) {
            properties.store(fos, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }

        return properties;
    }
}
