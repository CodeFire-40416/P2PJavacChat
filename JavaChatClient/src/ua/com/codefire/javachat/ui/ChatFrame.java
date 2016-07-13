/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.codefire.javachat.ui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ua.com.codefire.javachat.model.Contact;
import ua.com.codefire.javachat.model.Message;
import ua.com.codefire.javachat.net.MessageReceiverListener;
import ua.com.codefire.javachat.net.MessageSender;

/**
 *
 * @author homefulloflove
 */
public class ChatFrame extends javax.swing.JFrame implements MessageReceiverListener {

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    /**
     * Describes the info of interlocutor.
     */
    private Contact contact;
    private int serverPort;
    private MessageSender sender;

    /**
     * Creates new form ChatFrame
     *
     * @param ipAddress
     * @param serverPort
     * @throws java.io.IOException
     */
    public ChatFrame(Contact contact, int serverPort) throws IOException {
        this.contact = contact;
        this.serverPort = serverPort;

        initNetwork();

        initComponents();

        loadHistory();

        setTitle(contact.toString());

        jtaMessage.requestFocus();
    }

    private void initNetwork() throws IOException {
        sender = new MessageSender(serverPort);
    }

    public Contact getContact() {
        return contact;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaHistory = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaMessage = new javax.swing.JTextArea();
        jbSend = new javax.swing.JButton();
        jlStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jSplitPane1.setDividerLocation(250);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jScrollPane1.setAutoscrolls(true);

        jtaHistory.setEditable(false);
        jtaHistory.setColumns(20);
        jtaHistory.setRows(5);
        jScrollPane1.setViewportView(jtaHistory);

        jSplitPane1.setTopComponent(jScrollPane1);

        jtaMessage.setColumns(20);
        jtaMessage.setRows(5);
        jtaMessage.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtaMessageKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtaMessage);

        jSplitPane1.setRightComponent(jScrollPane2);

        jbSend.setText("Send");
        jbSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSendActionPerformed(evt);
            }
        });

        jlStatus.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSend)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbSend)
                    .addComponent(jlStatus))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSendActionPerformed

        sendMessage();

    }//GEN-LAST:event_jbSendActionPerformed

    private void jtaMessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtaMessageKeyPressed
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume();

            sendMessage();
        }
    }//GEN-LAST:event_jtaMessageKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

//        saveHistory();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        contact.setUnread(0);
        setTitle(contact.toString());
//        getParent().invalidate();
//        getParent().repaint();
    }//GEN-LAST:event_formWindowActivated

    private void sendMessage() {
//        String address = jtfAddress.getText();
        String message = jtaMessage.getText();
        jlStatus.setText(" ");

        // TODO: Validate address and message
        if (sender.sendMessage(contact.getIpAddress(), message)) {
            Message msg = new Message(new Date(), message);
            addHistory(msg.getTimestamp(), "me", msg.getText());
            jtaMessage.setText("");

        } else {
            jlStatus.setText("Message was not sent");
        }
        jtaHistory.setCaretPosition(jtaHistory.getDocument().getLength());
        jtaMessage.requestFocus();
    }

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    new ChatFrame().setVisible(true);
//                } catch (IOException ex) {
//                    Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jbSend;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JTextArea jtaHistory;
    private javax.swing.JTextArea jtaMessage;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(String address, String message) {
        if (contact.getIpAddress().equals(address)) {
            Message msg = new Message(new Date(), message, isActive());
            contact.getMessages().add(msg);
            addHistory(msg.getTimestamp(), address, msg.getText());
        }
    }

    private void addHistory(Date when, String address, String message) {
        String history = String.format("[%s] %s:\n    %s\n", timeFormat.format(when), address, message);
        jtaHistory.append(history);
    }

//    private void saveHistory() {
//        try (FileOutputStream fos = new FileOutputStream(new File("history", contact.getIpAddress() + ".history"))) {
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(jtaHistory.getText());
//        } catch (IOException ex) {
//            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    private void loadHistory() {
        for (Message message : contact.getMessages()) {
            if (message.isIncome()) {
                addHistory(message.getTimestamp(), contact.getIpAddress(), message.getText());
            } else {
                addHistory(message.getTimestamp(), "me", message.getText());
            }
        }

//        try (FileInputStream fis = new FileInputStream(new File("history", contact.getIpAddress() + ".history"))) {
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            jtaHistory.setText((String) ois.readObject());
//        } catch (IOException ex) {
//            jlStatus.setText("You have no message history with this contact yet");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(ChatFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
