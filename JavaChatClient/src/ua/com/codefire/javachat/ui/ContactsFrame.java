/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.ui;

import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import ua.com.codefire.javachat.Settings;
import ua.com.codefire.javachat.model.Contact;
import ua.com.codefire.javachat.model.Message;
import ua.com.codefire.javachat.net.MessageReceiver;
import ua.com.codefire.javachat.net.MessageReceiverListener;

/**
 *
 * @author homefulloflove
 */
public class ContactsFrame extends javax.swing.JFrame implements MessageReceiverListener {

    private static final int SERVER_PORT = 5890;

    private MessageReceiver receiver;

    private List<Contact> contactList = new ArrayList<>();

    /**
     * Creates new form ContactsFrame
     */
    public ContactsFrame() {
        initComponents();

        loadAction();

        try {
            initNetwork();
        } catch (IOException ex) {
            Logger.getLogger(ContactsFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        loadContactList();

    }

    private void loadContactList() {
        DefaultListModel<Contact> dlm = new DefaultListModel<>();

        for (Contact contact : contactList) {
            dlm.addElement(contact);
        }

        jlContacts.setModel(dlm);
    }

    private void initNetwork() throws IOException {
        receiver = new MessageReceiver(SERVER_PORT);
        receiver.addListener(this);
        new Thread(receiver).start();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jlContacts = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jlStatus = new javax.swing.JLabel();
        jmbMain = new javax.swing.JMenuBar();
        jmFile = new javax.swing.JMenu();
        jmiExit = new javax.swing.JMenuItem();
        jmList = new javax.swing.JMenu();
        jmiAdd = new javax.swing.JMenuItem();
        jmiRemove = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(230, 350));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jlContacts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlContactsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jlContacts);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jlStatus.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlStatus)
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlStatus)
                .addContainerGap())
        );

        jmFile.setText("File");

        jmiExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jmiExit.setText("Exit");
        jmiExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExitActionPerformed(evt);
            }
        });
        jmFile.add(jmiExit);

        jmbMain.add(jmFile);

        jmList.setText("List");

        jmiAdd.setText("Add...");
        jmiAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddActionPerformed(evt);
            }
        });
        jmList.add(jmiAdd);

        jmiRemove.setText("Delate");
        jmiRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoveActionPerformed(evt);
            }
        });
        jmList.add(jmiRemove);

        jmbMain.add(jmList);

        setJMenuBar(jmbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jlContactsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlContactsMouseClicked

        if (evt.getClickCount() == 2 && jlContacts.getSelectedIndex() >= 0) {
            evt.consume();

            Contact selectedContact = jlContacts.getSelectedValue();

            try {
                ChatFrame chat = new ChatFrame(selectedContact, SERVER_PORT);
                receiver.addListener(chat);
                chat.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        receiver.removeListener(chat);
                    }
                });
                chat.setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(ContactsFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

            jlContacts.setSelectedIndex(-1);
        }

    }//GEN-LAST:event_jlContactsMouseClicked

    private void jmiAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddActionPerformed

        DefaultListModel<Contact> dlm = (DefaultListModel<Contact>) jlContacts.getModel();
        String input = JOptionPane.showInputDialog("type ip address");
        if (input.matches("^\\d{1,3}(\\.\\d{1,3}){3}$")) {
            Contact contact = new Contact(input);
            dlm.addElement(contact);
            contactList.add(contact);
        }

//       

    }//GEN-LAST:event_jmiAddActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveAction();
    }//GEN-LAST:event_formWindowClosing

    private void jmiRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoveActionPerformed
        DefaultListModel<Contact> dlm = (DefaultListModel<Contact>) jlContacts.getModel();
        if (jlContacts.getSelectedIndex() >= 0) {
            Contact toBeRemoved = jlContacts.getSelectedValue();
            dlm.removeElement(toBeRemoved);
            contactList.remove(toBeRemoved);
        } else {
            jlStatus.setText("Choose contact to be deleted first");
        }
    }//GEN-LAST:event_jmiRemoveActionPerformed

    private void jmiExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExitActionPerformed
        receiver.stop();
        saveAction();
        dispose();
//        System.exit(0);
    }//GEN-LAST:event_jmiExitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ContactsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactsFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<Contact> jlContacts;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JMenu jmFile;
    private javax.swing.JMenu jmList;
    private javax.swing.JMenuBar jmbMain;
    private javax.swing.JMenuItem jmiAdd;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiRemove;
    // End of variables declaration//GEN-END:variables

    private void saveAction() {
        try (FileOutputStream fos = new FileOutputStream("contacts.list")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contactList);
        } catch (IOException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Rectangle bounds = getBounds();
        
        Properties properties = Settings.getInstance().getProperties();
        
        properties.setProperty("frame.contacts.x", Integer.toString(bounds.x));
        properties.setProperty("frame.contacts.y", Integer.toString(bounds.y));
        properties.setProperty("frame.contacts.w", Integer.toString(bounds.width));
        properties.setProperty("frame.contacts.h", Integer.toString(bounds.height));
        
        Settings.getInstance().storeSettings();
    }

    private void loadAction() {
        File history = new File("history");
        
        if (!history.exists()) {
            history.mkdir();
        }
        
        try (FileInputStream fis = new FileInputStream("contacts.list")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            contactList = (List<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        Properties properties = Settings.getInstance().getProperties();
        
        int x = Integer.parseInt(properties.getProperty("frame.contacts.x", "0"));
        int y = Integer.parseInt(properties.getProperty("frame.contacts.y", "0"));
        int w = Integer.parseInt(properties.getProperty("frame.contacts.w", "230"));
        int h = Integer.parseInt(properties.getProperty("frame.contacts.h", "360"));
        
        setBounds(x, y, w, h);
    }

    @Override
    public void messageReceived(String address, String message) {
        Message msg = new Message(new Date(), message, false);

        Contact foundContact = null;

        for (Contact contact : contactList) {
            if (contact.getIpAddress().equals(address)) {
                foundContact = contact;
                break;
            }
        }

        if (foundContact == null) {
            foundContact = new Contact(address);
            contactList.add(foundContact);

            loadContactList();
        }

        ChatFrame foundChat = null;
        Window[] windows = getWindows();
        for (Window window : windows) {
            if (window instanceof ChatFrame) {
                ChatFrame chat = (ChatFrame) window;
                
                if (chat.getContact().equals(foundContact) && chat.isDisplayable()) {
                    foundChat = chat;
                }
            }
        }

        if (foundChat != null) {
            if (foundChat.getContact().getIpAddress().equals(address) && !foundChat.isActive()) {
                foundChat.requestFocus();
            }
        } else {
            foundContact.increase(1);
            foundContact.getMessages().add(msg);
        }

        jlContacts.repaint();
    }
}
