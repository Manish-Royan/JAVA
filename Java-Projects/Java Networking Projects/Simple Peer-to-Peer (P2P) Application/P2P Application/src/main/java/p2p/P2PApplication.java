package p2p;

import p2p.ui.ChatUI;

import javax.swing.*;

public class P2PApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set system look and feel
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ChatUI ui = new ChatUI();
                ui.show();
            }
        });
    }
}

/*
- cd P2P-Chat-App
RUN: java -cp . p2p.P2PApplication
 */