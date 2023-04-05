package org.atom;

import org.atom.panels.LoginPanel;

public class Main {
    public static void main(String[] args) {
        Database.InitBaseData();
        new LoginPanel();
    }
}