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

public final class ScannerDML extends javax.swing.JFrame {

    DefaultTableModel tablaLexica = new DefaultTableModel(), tablaConstantes = new DefaultTableModel(), tablaIdentificadores = new DefaultTableModel();

    HashMap<Integer, String[][]> tablaDatos = new HashMap<>();
    HashMap<String, String> tablaSintactica = new HashMap<>();
    HashMap<String, String> tablaPrimero = new HashMap<>();
    int valorCons = 600, valorIden = 401;
    
    public ScannerDML() {
        initComponents();
        
        this.setLocationRelativeTo(null);

        tablaLexica = (DefaultTableModel) jTableLex.getModel();
        tablaConstantes = (DefaultTableModel) jTableConst.getModel();
        tablaIdentificadores = (DefaultTableModel) jTableIdent.getModel();
        
        JTableHeader headerLex = jTableLex.getTableHeader();
        headerLex.setFont(new Font("Arial", Font.PLAIN, 17));
        
        JTableHeader headerConstantes = jTableConst.getTableHeader();
        headerConstantes.setFont(new Font("Arial", Font.PLAIN, 17));
        
        JTableHeader headerIdentificadores = jTableIdent.getTableHeader();
        headerIdentificadores.setFont(new Font("Arial", Font.PLAIN, 17));
        
        llenarLexer();
    }
    
    public void llenarLexer() {
        
        String[][] delimitadores = {{"Delimitador", "5"},
        {",", "50"},
        {"\\.", "51"},
        {"\\(", "52"},
        {"\\)", "53"},
        {"'", "54"},
        {";", "55"}};
        
        String[][] relacionales = {{"Operador Relacional", "8"},
        {">", "81"},
        {"<", "82"},
        {"=", "83"},
        {">=", "84"},
        {"<=", "85"}};
       

        String[][] operadores = {{"Operador", "7"},
        {"\\+", "70"},
        {"-", "71"},
        {"\\*", "72"},
        {"/", "73"}};

        String[][] constantes = {{"Constante", "6"},
        {"\\d+", "61"},//dígitos del 0-9
        {"^'[\\w ]+'?$", "62"}};//Inicia y termina con comillas
        
        String[][] identificadores = {{"Identificador", "4"},
        {"^\\w+#?$", "400"}};//Puede terminar con #

      
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

        tablaDatos.put(0, reservadas);
        tablaDatos.put(1, delimitadores);
        tablaDatos.put(2, operadores);
        tablaDatos.put(3, constantes);
        tablaDatos.put(4, relacionales);
        tablaDatos.put(5, identificadores);
    }
    
    public void primerosySiguientesDML() {//Datos de la tabla primeros y siguientes del DML
        tablaPrimero.put("300", "10");
        tablaPrimero.put("301", "4 72");
        tablaPrimero.put("302", "4");
        tablaPrimero.put("303", "50 99");
        tablaPrimero.put("304", "4");
        tablaPrimero.put("305", "51 99");
        tablaPrimero.put("306", "4");
        tablaPrimero.put("307", "50 99");
        tablaPrimero.put("308", "4");
        tablaPrimero.put("309", "4 99");
        tablaPrimero.put("310", "12 99");
        tablaPrimero.put("311", "4");
        tablaPrimero.put("312", "14 15 99");
        tablaPrimero.put("313", "4");
        tablaPrimero.put("314", "8 13");
        tablaPrimero.put("315", "8");
        tablaPrimero.put("316", "4 54 61");
        tablaPrimero.put("317", "14 15");
        tablaPrimero.put("318", "62");
        tablaPrimero.put("319", "61");
    }
   
    public void datosTablaSintacticaDML() {//Datos de la tabla sintáctica del DML
        tablaSintactica.put("300 10", "10 301 11 306 310");
        tablaSintactica.put("301 4", "302");
        tablaSintactica.put("301 72", "72");
        tablaSintactica.put("302 4", "304 303");
        tablaSintactica.put("303 11", "99");
        tablaSintactica.put("303 50", "50 302");
        tablaSintactica.put("303 199", "99");
        tablaSintactica.put("304 4", "4 305");
        tablaSintactica.put("305 8", "99");
        tablaSintactica.put("305 11", "99");
        tablaSintactica.put("305 13", "99");
        tablaSintactica.put("305 14", "99");
        tablaSintactica.put("305 15", "99");
        tablaSintactica.put("305 50", "99");
        tablaSintactica.put("305 51", "51 4");
        tablaSintactica.put("305 53", "99");
        tablaSintactica.put("305 199", "99");
        tablaSintactica.put("306 4", "308 307");
        tablaSintactica.put("307 12", "99");
        tablaSintactica.put("307 50", "50 306");
        tablaSintactica.put("307 53", "99");
        tablaSintactica.put("307 199", "99");
        tablaSintactica.put("308 4", "4 309");
        tablaSintactica.put("309 4", "4");
        tablaSintactica.put("309 12", "99");
        tablaSintactica.put("309 50", "99");
        tablaSintactica.put("309 53", "99");
        tablaSintactica.put("309 199", "99");
        tablaSintactica.put("310 12", "12 311");
        tablaSintactica.put("310 53", "99");
        tablaSintactica.put("310 199", "99");
        tablaSintactica.put("311 4", "313 312");
        tablaSintactica.put("312 14", "317 311");
        tablaSintactica.put("312 15", "317 311");
        tablaSintactica.put("312 53", "99");
        tablaSintactica.put("312 199", "99");
        tablaSintactica.put("313 4", "304 314");
        tablaSintactica.put("314 8", "315 316");
        tablaSintactica.put("314 13", "13 52 300 53");
        tablaSintactica.put("315 8", "8");
        tablaSintactica.put("316 4", "304");
        tablaSintactica.put("316 54", "54 318 54");
        tablaSintactica.put("316 61", "319");
        tablaSintactica.put("317 14", "14");
        tablaSintactica.put("317 15", "15");
        tablaSintactica.put("318 62", "62");
        tablaSintactica.put("319 61", "61");
    }
 
    public void validacionSintactica() {
        HashMap<Integer, String> pila = new HashMap<>();
        String x, k = "";
        int apuntador = 0, contadorPila = 2;

        pila.put(0, "199");
        
        if(jTableLex.getValueAt(apuntador, 2).toString().equals("SELECT")){//Se pregunta si empieza con el SELECT para usar la tabla sintáctica y de primeros y siguientes del DML
            pila.put(1, "300");
            datosTablaSintacticaDML();
            primerosySiguientesDML();
        }
        
        tablaLexica.addRow(new Object[]{"", "", "$", "9", "199"});
        x = pila.get(pila.size() - 1);
        while (!x.equals("199")) {
            pila.remove(pila.size() - 1);
            contadorPila--;
            //Se almacena en k el valor de las palabras pertenecientes a la sentencia SQL
            if (Integer.parseInt(jTableLex.getValueAt(apuntador, 4).toString()) > 80 && Integer.parseInt(jTableLex.getValueAt(apuntador, 4).toString()) < 86) {
                k = "8";
            } else if (Integer.parseInt(jTableLex.getValueAt(apuntador, 4).toString()) > 400 && Integer.parseInt(jTableLex.getValueAt(apuntador, 4).toString()) < 600) {
                k = "4";
            } else if (Integer.parseInt(jTableLex.getValueAt(apuntador, 3).toString()) == 6) {
                for (int i = 0; i < jTableConst.getRowCount(); i++) {
                    if (Integer.parseInt(jTableLex.getValueAt(apuntador, 4).toString()) == Integer.parseInt(jTableConst.getValueAt(i, 3).toString())) {//Si se encuentra se retorna el valor que tiene
                        k = jTableConst.getValueAt(i, 2).toString();
                        break;
                    }
                }
            } else {
                k = jTableLex.getValueAt(apuntador, 4).toString();
            }
            if (Integer.parseInt(x) < 100 || x.equals("199")) {//Si el valor que esta en x es terminal o igual a $
                if (x.equals(k)) {//Si x y k son iguales
                    apuntador++;
                } else {//En caso de no ser iguales muestra el error
                    JtaSalidaErrores.setText(error(jTableLex.getValueAt(apuntador - 1, 1).toString(),x));
                    break;
                }
            } else {
                if (tablaSintactica.containsKey(x + " " + k)) {//Se pregunta si existe la intersección de x y k en la tabla sintáctica
                    if (!tablaSintactica.get(x + " " + k).equals("99")) {//En caso de existir intersección se pregunta si es diferente de cadena vacia
                        String produccion[] = tablaSintactica.get(x + " " + k).split(" ");
                        for (int i = produccion.length - 1; i >= 0; i--) {//En caso de ser diferente de cadena vacia se guarda el producto en la pila de forma inversa
                            pila.put(contadorPila++, produccion[i]);
                        }
                    }
                } else {//En caso de no existir la interacción muestra el error
                    JtaSalidaErrores.setText(error(jTableLex.getValueAt(apuntador - 1, 1).toString(),x));
                    break;
                }
            }
        x = pila.get(pila.size() - 1);
        } 
        tablaLexica.removeRow(tablaLexica.getRowCount() - 1);
    }

    public String error(String linea,String x) {
        String tipo;
        if (Integer.parseInt(x) > 100) {//Si x es una regla se extraen los valores primeros de la tabla primeros y siguientes          
            tipo = tipoDeError(tablaPrimero.get(x));
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
            for (int j = 0; j < tablaDatos.size(); j++) {
                for (String[] get : tablaDatos.get(j)) {
                    if (get[1].equals(primero1)) {
                        return "Se esperaba " + tablaDatos.get(j)[0][0];
                    }
                }
            }
        }
        return null;
    }

    public void tablaLexica() {
        Pattern reconocimiento = Pattern.compile("'[\\w ]+'?|\\w+#?|,|\\.|\\(|\\)|[+]|-|[*]|/|\\d+|=|[>][=]?|[<][=]?|\\W|\\n");
        Matcher sentenciaReconocer = reconocimiento.matcher(txtACode.getText());
        Pattern sentenciaClasificacion;
        Matcher clasificarLex;
        int contador = 1, linea = 1;
        boolean clasificado = false, error = false;

        while (sentenciaReconocer.find()) {
            if (sentenciaReconocer.group().matches("\\n")) {
                linea++;
            } else if (!sentenciaReconocer.group().matches("'[\\w+ ]+'?|\\w+#?|,|\\.|\\(|\\)|[+]|-|[*]|/|\\d+|=|[>][=]?|[<][=]?| |;|\\n")) {
                error = true;
                break;
            } else {
                for (int i = 0; i < tablaDatos.size(); i++) {
                    for (int y = 1; y < tablaDatos.get(i).length; y++) {
                        sentenciaClasificacion = Pattern.compile(tablaDatos.get(i)[y][0]);
                        clasificarLex = sentenciaClasificacion.matcher(sentenciaReconocer.group().replace(" ", ""));
                        if (clasificarLex.matches()) {
                            switch (tablaDatos.get(i)[0][0]) {
                                case "Identificador":
                                    tablaLexica.addRow(new Object[]{contador++, linea, sentenciaReconocer.group(), tablaDatos.get(i)[0][1], tablaIdentificador(sentenciaReconocer.group(), linea)});
                                    break;
                                case "Constante":
                                    if (sentenciaReconocer.group().matches("^\\d+$")) {
                                        tablaLexica.addRow(new Object[]{contador++, linea, sentenciaReconocer.group(), tablaDatos.get(i)[0][1], tablaConstante(contador - 1, sentenciaReconocer.group().replace("'", ""), Integer.parseInt(tablaDatos.get(i)[y][1]))});
                                    } else {
                                        tablaLexica.addRow(new Object[]{contador++, linea, "'", 5, 54});
                                        tablaLexica.addRow(new Object[]{contador++, linea, "CONSTANTE", tablaDatos.get(i)[0][1], tablaConstante(contador - 1, sentenciaReconocer.group().replace("'", ""), Integer.parseInt(tablaDatos.get(i)[y][1]))});
                                        if ("'".equals(String.valueOf(sentenciaReconocer.group().charAt(sentenciaReconocer.group().length() - 1)))) {
                                            tablaLexica.addRow(new Object[]{contador++, linea, "'", 5, 54});
                                        }
                                    }   break;
                                default:
                                    tablaLexica.addRow(new Object[]{contador++, linea, sentenciaReconocer.group(), tablaDatos.get(i)[0][1], tablaDatos.get(i)[y][1]});
                                    break;
                            }
                            clasificado = true;
                            break;
                        }
                    }
                    if (clasificado) {
                        clasificado = false;
                        break;
                    }

                }
            }

        }

        if (error) {
            JtaSalidaErrores.setText("Error| 1:101 Línea " + linea + " simbolo " + sentenciaReconocer.group() + " desconocido.");
            reiniciar();
        } else {
            JtaSalidaErrores.setText("1:100 Sin error.");
            validacionSintactica();//Se llama a la función que que verifica la sintaxis
        }

    }
    
    public int tablaConstante(int num, String palabra, int tipo) {
        for (int i = 0; i < jTableConst.getRowCount(); i++) {
            if (palabra.equals(jTableConst.getValueAt(i, 1).toString())) {
                return Integer.parseInt(jTableConst.getValueAt(i, 3).toString());
            }
        }
        tablaConstantes.addRow(new Object[]{num, palabra, tipo, valorCons++});
        return valorCons - 1;
    }

    public int tablaIdentificador(String palabra, int linea) {
        String[] parts;
        for (int i = 0; i < jTableIdent.getRowCount(); i++) {
            if (palabra.equals(jTableIdent.getValueAt(i, 0).toString())) {
                parts = jTableIdent.getValueAt(i, 2).toString().split(", ");
                if (Integer.parseInt(parts[parts.length - 1]) != linea) {
                    jTableIdent.setValueAt(jTableIdent.getValueAt(i, 2).toString() + ", " + linea, i, 2);
                }
                return Integer.parseInt(jTableIdent.getValueAt(i, 1).toString());
            }
        }

        tablaIdentificadores.addRow(new Object[]{palabra, valorIden++, linea});
        return valorIden - 1;
    }
    
    public void reiniciar(){
        tablaLexica.setRowCount(0);
        tablaConstantes.setRowCount(0);
        tablaIdentificadores.setRowCount(0);
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

        jspTextEntrada = new javax.swing.JScrollPane();
        txtACode = new javax.swing.JTextArea();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        btnAnalizar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jspConst = new javax.swing.JScrollPane();
        jTableConst = new javax.swing.JTable();
        jspIdents = new javax.swing.JScrollPane();
        jTableIdent = new javax.swing.JTable();
        jspLex = new javax.swing.JScrollPane();
        jTableLex = new javax.swing.JTable();
        jspSalida = new javax.swing.JScrollPane();
        JtaSalidaErrores = new javax.swing.JTextArea();
        jlbErrores = new javax.swing.JLabel();
        lblTablaLexica = new javax.swing.JLabel();
        lblTablaIdents = new javax.swing.JLabel();
        lblTablaConsts = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtACode.setBackground(new java.awt.Color(255, 255, 204));
        txtACode.setColumns(20);
        txtACode.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        txtACode.setRows(5);
        jspTextEntrada.setViewportView(txtACode);

        lbl1.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lbl1.setText(" Por favor, ingrese su consulta SQL en el campo designado a continuación.");
        lbl1.setToolTipText("");
        lbl1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        lbl2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lbl2.setText("Después, por favor proceda a presionar el botón \"Realizar Analisis\".");
        lbl2.setToolTipText("");
        lbl2.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        btnAnalizar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnAnalizar.setText("Realizar Analisis");
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

        jTableConst.setBackground(new java.awt.Color(255, 255, 204));
        jTableConst.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTableConst.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableConst.setShowGrid(true);
        jTableConst.getTableHeader().setResizingAllowed(false);
        jTableConst.getTableHeader().setReorderingAllowed(false);
        jspConst.setViewportView(jTableConst);

        jTableIdent.setBackground(new java.awt.Color(255, 255, 204));
        jTableIdent.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTableIdent.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableIdent.setSelectionBackground(new java.awt.Color(204, 204, 255));
        jTableIdent.setShowGrid(true);
        jTableIdent.getTableHeader().setResizingAllowed(false);
        jTableIdent.getTableHeader().setReorderingAllowed(false);
        jspIdents.setViewportView(jTableIdent);

        jTableLex.setBackground(new java.awt.Color(255, 255, 204));
        jTableLex.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTableLex.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableLex.setInheritsPopupMenu(true);
        jTableLex.setShowGrid(true);
        jTableLex.getTableHeader().setResizingAllowed(false);
        jTableLex.getTableHeader().setReorderingAllowed(false);
        jspLex.setViewportView(jTableLex);
        if (jTableLex.getColumnModel().getColumnCount() > 0) {
            jTableLex.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableLex.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableLex.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTableLex.getColumnModel().getColumn(1).setMaxWidth(60);
            jTableLex.getColumnModel().getColumn(3).setPreferredWidth(60);
            jTableLex.getColumnModel().getColumn(3).setMaxWidth(60);
            jTableLex.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableLex.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        JtaSalidaErrores.setEditable(false);
        JtaSalidaErrores.setColumns(20);
        JtaSalidaErrores.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        JtaSalidaErrores.setForeground(new java.awt.Color(51, 51, 51));
        JtaSalidaErrores.setRows(5);
        jspSalida.setViewportView(JtaSalidaErrores);

        jlbErrores.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jlbErrores.setText("Modulo de errores");

        lblTablaLexica.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTablaLexica.setText("Tabla lexica");

        lblTablaIdents.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTablaIdents.setText("Identificadores");

        lblTablaConsts.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        lblTablaConsts.setText("Constantes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspTextEntrada)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlbErrores, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl1)
                            .addComponent(lbl2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jspLex, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspIdents, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jspConst)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(lblTablaLexica)
                .addGap(327, 327, 327)
                .addComponent(lblTablaIdents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTablaConsts)
                .addGap(241, 241, 241))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspTextEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTablaIdents)
                    .addComponent(lblTablaConsts)
                    .addComponent(lblTablaLexica))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspIdents, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addComponent(jspConst, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jspLex, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jlbErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        JtaSalidaErrores.setText("");
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
            java.util.logging.Logger.getLogger(ScannerDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ScannerDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ScannerDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScannerDML.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ScannerDML().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JtaSalidaErrores;
    private javax.swing.JButton btnAnalizar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JTable jTableConst;
    private javax.swing.JTable jTableIdent;
    private javax.swing.JTable jTableLex;
    private javax.swing.JLabel jlbErrores;
    private javax.swing.JScrollPane jspConst;
    private javax.swing.JScrollPane jspIdents;
    private javax.swing.JScrollPane jspLex;
    private javax.swing.JScrollPane jspSalida;
    private javax.swing.JScrollPane jspTextEntrada;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lblTablaConsts;
    private javax.swing.JLabel lblTablaIdents;
    private javax.swing.JLabel lblTablaLexica;
    private javax.swing.JTextArea txtACode;
    // End of variables declaration//GEN-END:variables
}
