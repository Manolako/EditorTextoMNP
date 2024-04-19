package EDITOR_TEXTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

    public class EditorTexto extends JFrame {
        private JTextArea areaDeTexto;

        public EditorTexto() {
            super("Editor de Texto");
            areaDeTexto = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(areaDeTexto);
            getContentPane().add(scrollPane, BorderLayout.CENTER);

            JMenuBar menuBar = new JMenuBar();
            JMenu archivoMenu = new JMenu("Archivo");
            JMenuItem nuevoItem = new JMenuItem("Nuevo");
            JMenuItem abrirItem = new JMenuItem("Abrir");
            JMenuItem guardarItem = new JMenuItem("Guardar");
            JMenuItem salirItem = new JMenuItem("Salir");

            archivoMenu.add(nuevoItem);
            archivoMenu.add(abrirItem);
            archivoMenu.add(guardarItem);
            archivoMenu.addSeparator();
            archivoMenu.add(salirItem);
            menuBar.add(archivoMenu);

            setJMenuBar(menuBar);

            nuevoItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    areaDeTexto.setText("");
                }
            });

            abrirItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int seleccion = fileChooser.showOpenDialog(EditorTexto.this);
                    if (seleccion == JFileChooser.APPROVE_OPTION) {
                        File archivo = fileChooser.getSelectedFile();
                        try {
                            FileReader fr = new FileReader(archivo);
                            BufferedReader br = new BufferedReader(fr);
                            String linea;
                            while ((linea = br.readLine()) != null) {
                                areaDeTexto.append(linea + "\n");
                            }
                            br.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            guardarItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int seleccion = fileChooser.showSaveDialog(EditorTexto.this);
                    if (seleccion == JFileChooser.APPROVE_OPTION) {
                        File archivo = fileChooser.getSelectedFile();
                        try {
                            FileWriter fw = new FileWriter(archivo);
                            BufferedWriter bw = new BufferedWriter(fw);
                            bw.write(areaDeTexto.getText());
                            bw.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            salirItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            setSize(600, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);
        }

        public static void main(String[] args) {
            new EditorTexto();
        }
    }


