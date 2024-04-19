import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


//Se declara la clase EditorTexto como una subclase de JFrame

public class EditorTexto extends JFrame {

    private JTextPane PanelTexto;
    private JComboBox<String> fontComboBox;
    private JComboBox<Integer> sizeComboBox;
    private JComboBox<String> colorComboBox;

    //En el constructor de la clase, se establecen las propiedades de la ventana,
    //como el título, el tamaño y la ubicación en la pantalla.
    public EditorTexto() {
        setTitle("Editor de Texto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        //Se crea un panel (toolbarPanel) que contendrá los botones y las listas desplegables. Se establece su diseño de disposición como un
        //FlowLayout para que los componentes se alineen horizontalmente y se ajusten automáticamente.

        JPanel toolbarPanel = new JPanel();
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));


        //Se crea un botón llamado "Negrita" (boldButton) y se agrega un ActionListener para manejar los eventos de clic.
        //Cuando se hace clic en este botón, se aplica negrita al texto seleccionado en el JTextPane.

        JButton boldButton = new JButton("Negrita");
        boldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledEditorKit.BoldAction boldAction = new StyledEditorKit.BoldAction();
                boldAction.actionPerformed(e);
            }
        });

        //Se crea un botón llamado "Cursiva" (italicButton) y se agrega un ActionListener similar al botón de negrita.
        //Cuando se hace clic en este botón, se aplica cursiva al texto seleccionado en el JTextPane.

        JButton italicButton = new JButton("Cursiva");
        italicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledEditorKit.ItalicAction italicAction = new StyledEditorKit.ItalicAction();
                italicAction.actionPerformed(e);
            }
        });

        //Se crea un botón llamado "Subrayado" (underlineButton) y se agrega un ActionListener similar a los botones de negrita y cursiva.
        //Cuando se hace clic en este botón, se subraya el texto seleccionado en el JTextPane.

        JButton underlineButton = new JButton("Subrayado");
        underlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StyledEditorKit.UnderlineAction underlineAction = new StyledEditorKit.UnderlineAction();
                underlineAction.actionPerformed(e);
            }
        });



        //Se obtiene la lista de nombres de fuentes disponibles en el sistema y se utiliza para inicializar el JComboBox fontComboBox.
        //Cuando se selecciona una fuente de la lista, se cambia la fuente del texto en el JTextPane.

        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontComboBox = new JComboBox<>(fonts);
        fontComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFont = (String) fontComboBox.getSelectedItem();
                MutableAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attrs, selectedFont);
                PanelTexto.setCharacterAttributes(attrs, false);
            }
        });

        //Se define una lista de tamaños de letra y se utiliza para inicializar el JComboBox sizeComboBox.
        //Cuando se selecciona un tamaño de la lista, se cambia el tamaño de la letra del texto en el JTextPane

        Integer[] sizes = {8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72};
        sizeComboBox = new JComboBox<>(sizes);
        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedSize = (int) sizeComboBox.getSelectedItem();
                MutableAttributeSet attrs = new SimpleAttributeSet();
                StyleConstants.setFontSize(attrs, selectedSize);
                PanelTexto.setCharacterAttributes(attrs, false);
            }
        });

        //Se define una lista de colores y se utiliza para inicializar el JComboBox colorComboBox.
        //Cuando se selecciona un color de la lista, se cambia el color del texto en el JTextPane.

        String[] colors = {"Negro", "Rojo", "Azul", "Verde", "Amarillo"};
        colorComboBox = new JComboBox<>(colors);
        colorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedColor = (String) colorComboBox.getSelectedItem();
                    Color color = Color.BLACK;
                    if (selectedColor.equals("Rojo")) {
                        color = Color.RED;
                    } else if (selectedColor.equals("Azul")) {
                        color = Color.BLUE;
                    } else if (selectedColor.equals("Verde")) {
                        color = Color.GREEN;
                    } else if (selectedColor.equals("Amarillo"))
                        color = Color.YELLOW;
                    MutableAttributeSet attrs = new SimpleAttributeSet();
                    StyleConstants.setForeground(attrs, color);
                    PanelTexto.setCharacterAttributes(attrs, false);
                }
            }
        });
        //Se agregan los botones y las listas desplegables al panel toolbarPanel.

        toolbarPanel.add(boldButton);
        toolbarPanel.add(italicButton);
        toolbarPanel.add(underlineButton);
        toolbarPanel.add(new JLabel("Fuente:"));
        toolbarPanel.add(fontComboBox);
        toolbarPanel.add(new JLabel("Tamaño:"));
        toolbarPanel.add(sizeComboBox);
        toolbarPanel.add(new JLabel("Color:"));
        toolbarPanel.add(colorComboBox);

        //Se crea el JTextPane PanelTexto y se coloca dentro de un JScrollPane para permitir el desplazamiento si el texto es demasiado grande.
        //Luego, se agregan el panel de la barra de herramientas (toolbarPanel) y el JScrollPane al contenido principal del JFrame.

        PanelTexto = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(PanelTexto);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(toolbarPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    //Se define el método main que crea una instancia de la clase EditorTexto y la hace visible en la pantalla.
    //Este método se ejecuta en el hilo de despacho de eventos de Swing para garantizar la seguridad de la interfaz gráfica.

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EditorTexto().setVisible(true);
            }
        });
    }
}
