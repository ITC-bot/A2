/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.awt.Font;
import java.util.HashMap;
import java.util.regex.*;

import javax.swing.table.*;

public final class ParserDML extends javax.swing.JFrame {

    DefaultTableModel lexica = new DefaultTableModel(), constante = new DefaultTableModel(), identificador = new DefaultTableModel();

    HashMap<Integer, String[][]> datos = new HashMap<>();
    HashMap<String, String> sintactica = new HashMap<>();
    HashMap<String, String> primero = new HashMap<>();
    int valorCons = 600, valorIden = 401;
    
    public ParserDML() {
        initComponents();
        
        this.setLocationRelativeTo(null);

        lexica = (DefaultTableModel) jTable1.getModel();
        constante = (DefaultTableModel) jTable3.getModel();
        identificador = (DefaultTableModel) jTable2.getModel();
        
        JTableHeader th = jTable1.getTableHeader();
        th.setFont(new Font("Arial", Font.PLAIN, 17));

        cargarDatos();
    }
    
    public void cargarDatos() {
        String[][] reservadas = {{"Palabra Reservada", "1", ""},
        {"SELECT", "10", "s"},
        {"FROM", "11", "f"},
        {"WHERE", "12", "w"},
        {"IN", "13", "n"},
        {"AND", "14", "y"},
        {"OR", "15", "o"},
        {"CREATE", "16", "c"},
        {"TABLE", "17", "t"},
        {"CHAR", "18", "h"},
        {"NUMERIC", "19", "u"},
        {"NOT", "20", "e"},
        {"NULL", "21", "g"},
        {"CONSTRAINT", "22", "b"},
        {"KEY", "23", "k"},
        {"PRIMARY", "24", "p"},
        {"FOREIGN", "25", "j"},
        {"REFERENCES", "26", "l"},
        {"INSERT", "27", "m"},
        {"INTO", "28", "q"},
        {"VALUES", "29", "v"}};

        String[][] delimitadores = {{"Delimitador", "5"},
        {",", "50"},
        {"\\.", "51"},
        {"\\(", "52"},
        {"\\)", "53"},
        {"'", "54"},
        {";", "55"}};

        String[][] operadores = {{"Operador", "7"},
        {"\\+", "70"},
        {"-", "71"},
        {"\\*", "72"},
        {"/", "73"}};

        String[][] constantes = {{"Constante", "6"},
        {"\\d+", "61"},//dígitos del 0-9
        {"^'[\\w ]+'?$", "62"}};//Inicia y termina con comillas

        String[][] relacionales = {{"Operador Relacional", "8"},
        {">", "81"},
        {"<", "82"},
        {"=", "83"},
        {">=", "84"},
        {"<=", "85"}};

        String[][] identificadores = {{"Identificador", "4"},
        {"^\\w+#?$", "400"}};//Puede terminar con #

        datos.put(0, reservadas);
        datos.put(1, delimitadores);
        datos.put(2, operadores);
        datos.put(3, constantes);
        datos.put(4, relacionales);
        datos.put(5, identificadores);
    }
    
    public void primerosySiguientesDML() {//Datos de la tabla primeros y siguientes del DML
        primero.put("300", "10");
        primero.put("301", "4 72");
        primero.put("302", "4");
        primero.put("303", "50 99");
        primero.put("304", "4");
        primero.put("305", "51 99");
        primero.put("306", "4");
        primero.put("307", "50 99");
        primero.put("308", "4");
        primero.put("309", "4 99");
        primero.put("310", "12 99");
        primero.put("311", "4");
        primero.put("312", "14 15 99");
        primero.put("313", "4");
        primero.put("314", "8 13");
        primero.put("315", "8");
        primero.put("316", "4 54 61");
        primero.put("317", "14 15");
        primero.put("318", "62");
        primero.put("319", "61");
    }
   
    public void datosTablaSintacticaDML() {//Datos de la tabla sintáctica del DML
        sintactica.put("300 10", "10 301 11 306 310");
        sintactica.put("301 4", "302");
        sintactica.put("301 72", "72");
        sintactica.put("302 4", "304 303");
        sintactica.put("303 11", "99");
        sintactica.put("303 50", "50 302");
        sintactica.put("303 199", "99");
        sintactica.put("304 4", "4 305");
        sintactica.put("305 8", "99");
        sintactica.put("305 11", "99");
        sintactica.put("305 13", "99");
        sintactica.put("305 14", "99");
        sintactica.put("305 15", "99");
        sintactica.put("305 50", "99");
        sintactica.put("305 51", "51 4");
        sintactica.put("305 53", "99");
        sintactica.put("305 199", "99");
        sintactica.put("306 4", "308 307");
        sintactica.put("307 12", "99");
        sintactica.put("307 50", "50 306");
        sintactica.put("307 53", "99");
        sintactica.put("307 199", "99");
        sintactica.put("308 4", "4 309");
        sintactica.put("309 4", "4");
        sintactica.put("309 12", "99");
        sintactica.put("309 50", "99");
        sintactica.put("309 53", "99");
        sintactica.put("309 199", "99");
        sintactica.put("310 12", "12 311");
        sintactica.put("310 53", "99");
        sintactica.put("310 199", "99");
        sintactica.put("311 4", "313 312");
        sintactica.put("312 14", "317 311");
        sintactica.put("312 15", "317 311");
        sintactica.put("312 53", "99");
        sintactica.put("312 199", "99");
        sintactica.put("313 4", "304 314");
        sintactica.put("314 8", "315 316");
        sintactica.put("314 13", "13 52 300 53");
        sintactica.put("315 8", "8");
        sintactica.put("316 4", "304");
        sintactica.put("316 54", "54 318 54");
        sintactica.put("316 61", "319");
        sintactica.put("317 14", "14");
        sintactica.put("317 15", "15");
        sintactica.put("318 62", "62");
        sintactica.put("319 61", "61");
    }
 
    public void validarSintaxis() {
        HashMap<Integer, String> pila = new HashMap<>();
        String x, k = "";
        int apuntador = 0, contadorPila = 2;

        pila.put(0, "199");
        
        if(jTable1.getValueAt(apuntador, 2).toString().equals("SELECT")){//Se pregunta si empieza con el SELECT para usar la tabla sintáctica y de primeros y siguientes del DML
            pila.put(1, "300");
            datosTablaSintacticaDML();
            primerosySiguientesDML();
        }
        
        lexica.addRow(new Object[]{"", "", "$", "9", "199"});
        do {
            x = pila.get(pila.size() - 1);
            pila.remove(pila.size() - 1);
            contadorPila--;
            //Se almacena en k el valor de las palabras pertenecientes a la sentencia SQL
            if (Integer.parseInt(jTable1.getValueAt(apuntador, 4).toString()) > 80 && Integer.parseInt(jTable1.getValueAt(apuntador, 4).toString()) < 86) {
                k = "8";
            } else if (Integer.parseInt(jTable1.getValueAt(apuntador, 4).toString()) > 400 && Integer.parseInt(jTable1.getValueAt(apuntador, 4).toString()) < 600) {
                k = "4";
            } else if (Integer.parseInt(jTable1.getValueAt(apuntador, 3).toString()) == 6) {
                for (int i = 0; i < jTable3.getRowCount(); i++) {
                    if (Integer.parseInt(jTable1.getValueAt(apuntador, 4).toString()) == Integer.parseInt(jTable3.getValueAt(i, 3).toString())) {//Si se encuentra se retorna el valor que tiene
                        k = jTable3.getValueAt(i, 2).toString();
                        break;
                    }
                }
            } else {
                k = jTable1.getValueAt(apuntador, 4).toString();
            }
            if (Integer.parseInt(x) < 100 || x.equals("199")) {//Si el valor que esta en x es terminal o igual a $
                if (x.equals(k)) {//Si x y k son iguales
                    apuntador++;
                } else {//En caso de no ser iguales muestra el error
                    jTextArea1.setText(error(jTable1.getValueAt(apuntador - 1, 1).toString(),x));
                    break;
                }
            } else {
                if (sintactica.containsKey(x + " " + k)) {//Se pregunta si existe la intersección de x y k en la tabla sintáctica
                    if (!sintactica.get(x + " " + k).equals("99")) {//En caso de existir intersección se pregunta si es diferente de cadena vacia
                        String produccion[] = sintactica.get(x + " " + k).split(" ");
                        for (int i = produccion.length - 1; i >= 0; i--) {//En caso de ser diferente de cadena vacia se guarda el producto en la pila de forma inversa
                            pila.put(contadorPila++, produccion[i]);
                        }
                    }
                } else {//En caso de no existir la interacción muestra el error
                    jTextArea1.setText(error(jTable1.getValueAt(apuntador - 1, 1).toString(),x));
                    break;
                }
            }
        } while (!x.equals("199"));
        lexica.removeRow(lexica.getRowCount() - 1);
    }

    public String error(String linea,String x) {
        String tipo;
        if (Integer.parseInt(x) > 100) {//Si x es una regla se extraen los valores primeros de la tabla primeros y siguientes          
            tipo = tipoDeError(primero.get(x));
        } else {
            tipo = tipoDeError(x);
        }
        //Se clasifica el error que se mostrará deacuerdo al tipo
        switch (tipo) {
            case "Se esperaba Palabra Reservada":
                tipo = "Error| 2:201 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Identificador":
                tipo = "Error| 2:204 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Delimitador":
                tipo = "Error| 2:205 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Constante":
                tipo = "Error| 2:206 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Operador":
                tipo = "Error| 2:207 Línea " + linea + " " + tipo;
                break;
            case "Se esperaba Operador Relacional":
                tipo = "Error| 2:208 Línea " + linea + " " + tipo;
                break;
            default:
                break;
        }
        
        return tipo;
    }

    public String tipoDeError(String palabra) {//Se busca que tipo de error fue el que se encontró
        String primeros[] = palabra.split(" ");
        for (String primero1 : primeros) {
            for (int j = 0; j < datos.size(); j++) {
                for (String[] get : datos.get(j)) {
                    if (get[1].equals(primero1)) {
                        return "Se esperaba " + datos.get(j)[0][0];
                    }
                }
            }
        }
        return null;
    }

    public void tablaLexica() {
        Pattern patron = Pattern.compile("'[\\w ]+'?|\\w+#?|,|\\.|\\(|\\)|[+]|-|[*]|/|\\d+|=|[>][=]?|[<][=]?|\\W|\\n");
        Matcher matcher = patron.matcher(txtACode.getText());
        Pattern clasificacion;
        Matcher matcher2;
        int contador = 1, linea = 1;
        boolean ban = false, error = false;

        while (matcher.find()) {
            if (matcher.group().matches("\\n")) {
                linea++;
            } else if (!matcher.group().matches("'[\\w+ ]+'?|\\w+#?|,|\\.|\\(|\\)|[+]|-|[*]|/|\\d+|=|[>][=]?|[<][=]?| |;|\\n")) {
                error = true;
                break;
            } else {
                for (int i = 0; i < datos.size(); i++) {
                    for (int y = 1; y < datos.get(i).length; y++) {
                        clasificacion = Pattern.compile(datos.get(i)[y][0]);
                        matcher2 = clasificacion.matcher(matcher.group().replace(" ", ""));
                        if (matcher2.matches()) {
                            switch (datos.get(i)[0][0]) {
                                case "Constante":
                                    if (matcher.group().matches("^\\d+$")) {
                                        lexica.addRow(new Object[]{contador++, linea, matcher.group(), datos.get(i)[0][1], tablaConstante(contador - 1, matcher.group().replace("'", ""), Integer.parseInt(datos.get(i)[y][1]))});
                                    } else {
                                        lexica.addRow(new Object[]{contador++, linea, "'", 5, 54});
                                        lexica.addRow(new Object[]{contador++, linea, "CONSTANTE", datos.get(i)[0][1], tablaConstante(contador - 1, matcher.group().replace("'", ""), Integer.parseInt(datos.get(i)[y][1]))});
                                        if ("'".equals(String.valueOf(matcher.group().charAt(matcher.group().length() - 1)))) {
                                            lexica.addRow(new Object[]{contador++, linea, "'", 5, 54});
                                        }
                                    }   break;
                                case "Identificador":
                                    lexica.addRow(new Object[]{contador++, linea, matcher.group(), datos.get(i)[0][1], tablaIdentificador(matcher.group(), linea)});
                                    break;
                                default:
                                    lexica.addRow(new Object[]{contador++, linea, matcher.group(), datos.get(i)[0][1], datos.get(i)[y][1]});
                                    break;
                            }
                            ban = true;
                            break;
                        }
                    }
                    if (ban) {
                        ban = false;
                        break;
                    }

                }
            }

        }

        if (error) {
            jTextArea1.setText("Error| 1:101 Línea " + linea + " simbolo " + matcher.group() + " desconocido.");
            reiniciar();
        } else {
            jTextArea1.setText("--Analisis Léxico Finalizado--\n");
            validarSintaxis();//Se llama a la función que que verifica la sintaxis
        }

    }

    public int tablaConstante(int num, String palabra, int tipo) {
        for (int i = 0; i < jTable3.getRowCount(); i++) {
            if (palabra.equals(jTable3.getValueAt(i, 1).toString())) {
                return Integer.parseInt(jTable3.getValueAt(i, 3).toString());
            }
        }
        constante.addRow(new Object[]{num, palabra, tipo, valorCons++});
        return valorCons - 1;
    }

    public int tablaIdentificador(String palabra, int linea) {
        String[] parts;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            if (palabra.equals(jTable2.getValueAt(i, 0).toString())) {
                parts = jTable2.getValueAt(i, 2).toString().split(", ");
                if (Integer.parseInt(parts[parts.length - 1]) != linea) {
                    jTable2.setValueAt(jTable2.getValueAt(i, 2).toString() + ", " + linea, i, 2);
                }
                return Integer.parseInt(jTable2.getValueAt(i, 1).toString());
            }
        }

        identificador.addRow(new Object[]{palabra, valorIden++, linea});
        return valorIden - 1;
    }
    
    public void reiniciar(){
        lexica.setRowCount(0);
        constante.setRowCount(0);
        identificador.setRowCount(0);
        valorCons = 600;
        valorIden = 401;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtACode = new javax.swing.JTextArea();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtACode.setColumns(20);
        txtACode.setRows(5);
        jScrollPane1.setViewportView(txtACode);

        lbl1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl1.setText("Escriba el código en el recuadro presentado a continuación.");
        lbl1.setToolTipText("");
        lbl1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        lbl2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl2.setText("Posteriormente, presione el botón analizar.");
        lbl2.setToolTipText("");
        lbl2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        btnAnalizar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAnalizar.setText("Analizar");
        btnAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarActionPerformed(evt);
            }
        });

        btnReiniciar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnReiniciar.setText("Reiniciar");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "LÍNEA", "TOKEN", "TIPO", "CÓDIGO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Table Léxica", jPanel1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDENTIFICADOR", "VALOR", "LÍNEA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setResizingAllowed(false);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NO.", "CONSTANTE", "TIPO", "VALOR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.getTableHeader().setResizingAllowed(false);
        jTable3.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tabla Dinamica", jPanel2);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl2)
                            .addComponent(lbl1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbl1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jTabbedPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarActionPerformed
        if(!txtACode.getText().equals("")){
            reiniciar();
            tablaLexica();
        }
            
    }//GEN-LAST:event_btnAnalizarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        reiniciar();
        txtACode.setText("");
        jTextArea1.setText("");
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParserDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParserDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParserDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParserDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParserDML().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JTextArea txtACode;
    // End of variables declaration//GEN-END:variables
}
