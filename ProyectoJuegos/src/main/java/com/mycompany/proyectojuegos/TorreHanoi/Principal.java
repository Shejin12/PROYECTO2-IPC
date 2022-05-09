package com.mycompany.proyectojuegos.TorreHanoi;

import com.mycompany.proyectojuegos.TorreHanoi.Pila.Nodos;
import com.mycompany.proyectojuegos.TorreHanoi.Pila.Pila;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;


public class Principal extends javax.swing.JFrame {
    int ContadorMovimientos = 0, NumDiscos = 0;
    boolean FinJuego = false;
    Pila Torre_1;
    Pila Torre_2;
    Pila Torre_3;
    
    DefaultTableModel Tabla_1, Tabla_2, Tabla_3;
    
    private Timer Crono;
    private ActionListener Acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Seg++;
            if(Seg == 60){
                Seg = 0;
                Min++;
            }
            MostrarTiempo();
        }
    };
    int Min = 0, Seg = 0;
    
    public Principal() {
        initComponents();
        
        Tabla_1 = (DefaultTableModel) Torre1.getModel();
        Tabla_1.setRowCount(10);
        
        Tabla_2 = (DefaultTableModel) Torre2.getModel();
        Tabla_2.setRowCount(10);
        
        Tabla_3 = (DefaultTableModel) Torre3.getModel();
        Tabla_3.setRowCount(10);
        
        DefaultTableCellRenderer Reenderizado_1 = new DefaultTableCellRenderer();
        Reenderizado_1.setHorizontalAlignment(SwingConstants.CENTER);
        Torre1.getColumnModel().getColumn(0).setCellRenderer(Reenderizado_1);
        
        DefaultTableCellRenderer Reenderizado_2 = new DefaultTableCellRenderer();
        Reenderizado_2.setHorizontalAlignment(SwingConstants.CENTER);
        Torre2.getColumnModel().getColumn(0).setCellRenderer(Reenderizado_2);
        
        DefaultTableCellRenderer Reenderizado_3 = new DefaultTableCellRenderer();
        Reenderizado_3.setHorizontalAlignment(SwingConstants.CENTER);
        Torre3.getColumnModel().getColumn(0).setCellRenderer(Reenderizado_3);
    }
    
    private void LabelMovimientos(){
        ContadorMovimientos++;
        LblContMoves.setText(String.valueOf(ContadorMovimientos));
    }
    
    private void Limpiar(){
        ContadorMovimientos = 0;
        ComboDiscos.setSelectedItem(String.valueOf(NumDiscos));
    }
    
    private void Iniciar(){
        OneTwo.setEnabled(true);
        OneThree.setEnabled(true);
        TwoOne.setEnabled(true);
        TwoThree.setEnabled(true);
        ThreeOne.setEnabled(true);
        ThreeTwo.setEnabled(true);
        Crono = new Timer(1000, Acciones);
        Cronometro();
        ContadorMovimientos=0;
        try {
            Torre_1 = new Pila();
            Torre_2 = new Pila();
            Torre_3 = new Pila();
            LblContMoves.setText(String.valueOf(ContadorMovimientos));
    
            NumDiscos = Integer.parseInt(ComboDiscos.getSelectedItem().toString());
    
                for (int i = NumDiscos; i >= 1; i--) {
                 Nodos Nivel = new Nodos();
                    String Disco = "";
            
                    for (int j = i; j > 0; j--) {
                        Disco= Disco + "#";
                    }
                    Nivel.setDato(Disco);
                    Torre_1.Colocar(Nivel);
                }
            DibujarTorre1();
            DibujarTorre2();
            DibujarTorre3();
        } catch (Exception e) {
        }
    }

    private void DibujarTorre1(){
        ((DefaultTableModel)Torre1.getModel()).setRowCount(0);
        Tabla_1.setRowCount(9);
        
        Nodos tmp;
        int NivelDisco = (10 - Torre_1.getContadorNodos());
        
        if(Torre_1.getContadorNodos() > 0){
            for(tmp = Torre_1.getCabeza(); tmp.getAbajo() != null; tmp = tmp.getAbajo()){
                String[] Vector = {tmp.getDato()};
                Tabla_1.insertRow(NivelDisco, Vector);
                NivelDisco++;
            }
            if(tmp.getAbajo() == null){
                String[] Vector = {tmp.getDato()};
                Tabla_1.insertRow(NivelDisco, Vector);
            }
        }
        
        Torre1.setModel(Tabla_1);
        Tabla_1.setRowCount(10);
    }
    
    private void DibujarTorre2(){
        ((DefaultTableModel)Torre2.getModel()).setRowCount(0);
        Tabla_2.setRowCount(9);
        
        Nodos tmp;
        int NivelDisco = (10 - Torre_2.getContadorNodos());
        
        if(Torre_2.getContadorNodos() > 0){
            for(tmp = Torre_2.getCabeza(); tmp.getAbajo() != null; tmp = tmp.getAbajo()){
                String[] Vector = {tmp.getDato()};
                Tabla_2.insertRow(NivelDisco, Vector);
                NivelDisco++;
            }
            if(tmp.getAbajo() == null){
                String[] Vector = {tmp.getDato()};
                Tabla_2.insertRow(NivelDisco, Vector);
            }
        }
        
        Torre2.setModel(Tabla_2);
        Tabla_2.setRowCount(10);
    }
    
    private void DibujarTorre3(){
        ((DefaultTableModel)Torre3.getModel()).setRowCount(0);
        Tabla_3.setRowCount(9);
        
        Nodos tmp;
        int NivelDisco = (10 - Torre_3.getContadorNodos());
        
        if(Torre_3.getContadorNodos() > 0){
            for(tmp = Torre_3.getCabeza(); tmp.getAbajo() != null; tmp = tmp.getAbajo()){
                String[] Vector = {tmp.getDato()};
                Tabla_3.insertRow(NivelDisco, Vector);
                NivelDisco++;
            }
            if(tmp.getAbajo() == null){
                String[] Vector = {tmp.getDato()};
                Tabla_3.insertRow(NivelDisco, Vector);
            }
        }
        
        Torre3.setModel(Tabla_3);
        Tabla_3.setRowCount(10);
    }
    
    private void Mover_1_2(){
        try {
            if(Torre_1.getContadorNodos() > 0){
                Nodos Plataforma = new Nodos();
                Plataforma.setDato(Torre_1.regreso());
                if(Torre_2.getContadorNodos() > 0){
                    if(Plataforma.getDato().compareTo(Torre_2.regreso()) > 0){
                        return;
                    }
                }
                Torre_1.Quitar();
                Torre_2.Colocar(Plataforma);
                DibujarTorre1();
                DibujarTorre2();
                LabelMovimientos();
                
                if(Torre_2.getContadorNodos() == NumDiscos){
                    Crono.stop();
                    setVictoria();
                    JOptionPane.showMessageDialog(null, "Felicidades " + getJugador() + ", por Completar el Juego :D", "Excelente!", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Se han jugado " + getPartida() + "Partidas \nSe han ganado: " + getVictoria() + "\nSe han perdido: " + getDerrota(), "Estadisticas", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
        }
    }
    
    private void Mover_1_3(){
         try {
            if(Torre_1.getContadorNodos() > 0){
                Nodos Plataforma = new Nodos();
                Plataforma.setDato(Torre_1.regreso());
                if(Torre_3.getContadorNodos() > 0){
                    if(Plataforma.getDato().compareTo(Torre_3.regreso()) > 0){
                        return;
                    }
                }
                Torre_1.Quitar();
                Torre_3.Colocar(Plataforma);
                DibujarTorre1();
                DibujarTorre3();
                LabelMovimientos();
                
                if(Torre_3.getContadorNodos() == NumDiscos){
                    Crono.stop();
                    setVictoria();
                    JOptionPane.showMessageDialog(null, "Felicidades " + getJugador() + ", por Completar el Juego :D", "Excelente!", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Se han jugado " + getPartida() + "Partidas \nSe han ganado: " + getVictoria() + "\nSe han perdido: " + getDerrota(), "Estadisticas", JOptionPane.INFORMATION_MESSAGE);                }
            }
        } catch (Exception e) {
        }
    }
    
    private void Mover_2_1(){
        try {
            if(Torre_2.getContadorNodos() > 0){
                Nodos Plataforma = new Nodos();
                Plataforma.setDato(Torre_2.regreso());
                if(Torre_1.getContadorNodos() > 0){
                    if(Plataforma.getDato().compareTo(Torre_1.regreso()) > 0){
                        return;
                    }
                }
                Torre_2.Quitar();
                Torre_1.Colocar(Plataforma);
                DibujarTorre2();
                DibujarTorre1();
                LabelMovimientos();
            }
        } catch (Exception e) {
        }
    }
    
    private void Mover_2_3(){
        try {
            if(Torre_2.getContadorNodos() > 0){
                Nodos Plataforma = new Nodos();
                Plataforma.setDato(Torre_2.regreso());
                if(Torre_3.getContadorNodos() > 0){
                    if(Plataforma.getDato().compareTo(Torre_3.regreso()) > 0){
                        return;
                    }
                }
                Torre_2.Quitar();
                Torre_3.Colocar(Plataforma);
                DibujarTorre2();
                DibujarTorre3();
                LabelMovimientos();
                
                if(Torre_3.getContadorNodos() == NumDiscos){
                    Crono.stop();
                    setVictoria();
                    JOptionPane.showMessageDialog(null, "Felicidades " + getJugador() + ", por Completar el Juego :D", "Excelente!", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Se han jugado " + getPartida() + "Partidas \nSe han ganado: " + getVictoria() + "\nSe han perdido: " + getDerrota(), "Estadisticas", JOptionPane.INFORMATION_MESSAGE);                }
            }
        } catch (Exception e) {
        }
    }
    
    private void Mover_3_1(){
        try {
            if(Torre_3.getContadorNodos() > 0){
                Nodos Plataforma = new Nodos();
                Plataforma.setDato(Torre_3.regreso());
                if(Torre_1.getContadorNodos() > 0){
                    if(Plataforma.getDato().compareTo(Torre_1.regreso()) > 0){
                        return;
                    }
                }
                Torre_3.Quitar();
                Torre_1.Colocar(Plataforma);
                DibujarTorre3();
                DibujarTorre1();
                LabelMovimientos();
            }
        } catch (Exception e) {
        }
    }
    
    private void Mover_3_2(){
        try {
            if(Torre_3.getContadorNodos() > 0){
                Nodos Plataforma = new Nodos();
                Plataforma.setDato(Torre_3.regreso());
                if(Torre_2.getContadorNodos() > 0){
                    if(Plataforma.getDato().compareTo(Torre_2.regreso()) > 0){
                        return;
                    }
                }
                Torre_3.Quitar();
                Torre_2.Colocar(Plataforma);
                DibujarTorre3();
                DibujarTorre2();
                LabelMovimientos();
                
                if(Torre_2.getContadorNodos() == NumDiscos){
                    Crono.stop();
                    setVictoria();
                    JOptionPane.showMessageDialog(null, "Felicidades " + getJugador() + ", por Completar el Juego :D", "Excelente!", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Se han jugado " + getPartida() + "Partidas \nSe han ganado: " + getVictoria() + "\nSe han perdido: " + getDerrota(), "Estadisticas", JOptionPane.INFORMATION_MESSAGE);                }
            }
        } catch (Exception e) {
        }
    }
    
    private void Cronometro(){
        Crono.start();
    }
    
    private void MostrarTiempo(){
        String Tiempo = (Min<=9?"0":"")+Min+":"+(Seg<=9?"0":"")+Seg;
        LblTimeCount.setText(Tiempo);
    }
    
    boolean FinRes = false;
    private void Resolucion(Pila Origen, Pila Fin){
        Nodos Plataforma = new Nodos();
        if(FinRes == false){
            Plataforma.setDato(Origen.regreso());
            Origen.Quitar();
            Fin.Colocar(Plataforma);
            
            DibujarTorre1();
            DibujarTorre2();
            DibujarTorre3();
            LabelMovimientos();
            
            JOptionPane Letrero = new JOptionPane("Paso No. " + LblContMoves.getText()+"\n¿Continuar?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
            
            JDialog Mensaje = Letrero.createDialog("Pasos");
            
            Mensaje.setLocation(0, 200);
            Mensaje.setVisible(true);
            
            int tmp = (int) Letrero.getValue();
            
            if(tmp == JOptionPane.NO_OPTION){
                FinRes = true;
            }
        }
    }
    
    private void Resolver(int tamano, Pila Origen, Pila Extra, Pila Fin){
        if(tamano == 1){
            Resolucion(Origen, Fin);
        } else {
            Resolver(tamano-1, Origen, Fin, Extra);
            Resolucion(Origen, Fin);
            Resolver(tamano-1, Extra, Origen, Fin);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Torre2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Torre3 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Torre1 = new javax.swing.JTable();
        Tittle = new javax.swing.JLabel();
        OneTwo = new javax.swing.JButton();
        OneThree = new javax.swing.JButton();
        TwoOne = new javax.swing.JButton();
        TwoThree = new javax.swing.JButton();
        ThreeOne = new javax.swing.JButton();
        ThreeTwo = new javax.swing.JButton();
        ComboDiscos = new javax.swing.JComboBox<>();
        LblDiscos = new javax.swing.JLabel();
        LblMoves = new javax.swing.JLabel();
        LblContMoves = new javax.swing.JLabel();
        BtnInicio = new javax.swing.JButton();
        BtnPista = new javax.swing.JButton();
        BtnJugador = new javax.swing.JButton();
        BtnSalida = new javax.swing.JButton();
        LblTime = new javax.swing.JLabel();
        LblTimeCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Torres De Hanoi");
        setBounds(new java.awt.Rectangle(200, 100, 450, 500));

        Torre2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre2.setAutoscrolls(false);
        Torre2.setFocusable(false);
        Torre2.setRowSelectionAllowed(false);
        jScrollPane1.setViewportView(Torre2);
        if (Torre2.getColumnModel().getColumnCount() > 0) {
            Torre2.getColumnModel().getColumn(0).setResizable(false);
        }

        Torre3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre3.setAutoscrolls(false);
        Torre3.setFocusable(false);
        Torre3.setRowSelectionAllowed(false);
        jScrollPane2.setViewportView(Torre3);
        if (Torre3.getColumnModel().getColumnCount() > 0) {
            Torre3.getColumnModel().getColumn(0).setResizable(false);
        }

        Torre1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Torre 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Torre1.setAutoscrolls(false);
        Torre1.setFocusable(false);
        Torre1.setRowSelectionAllowed(false);
        jScrollPane3.setViewportView(Torre1);
        if (Torre1.getColumnModel().getColumnCount() > 0) {
            Torre1.getColumnModel().getColumn(0).setResizable(false);
        }

        Tittle.setText("TORRES DE HANOI");

        OneTwo.setText("2");
        OneTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneTwoActionPerformed(evt);
            }
        });

        OneThree.setText("3");
        OneThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OneThreeActionPerformed(evt);
            }
        });

        TwoOne.setText("1");
        TwoOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwoOneActionPerformed(evt);
            }
        });

        TwoThree.setText("3");
        TwoThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TwoThreeActionPerformed(evt);
            }
        });

        ThreeOne.setText("1");
        ThreeOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThreeOneActionPerformed(evt);
            }
        });

        ThreeTwo.setText("2");
        ThreeTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThreeTwoActionPerformed(evt);
            }
        });

        ComboDiscos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "3", "4", "5", "6", "7", "8" }));

        LblDiscos.setText("Cantidad de Discos:");

        LblMoves.setText("Movimientos Realizados");

        LblContMoves.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblContMoves.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblContMoves.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BtnInicio.setText("Inicio");
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        BtnPista.setText("Resolver");
        BtnPista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPistaActionPerformed(evt);
            }
        });

        BtnJugador.setText("Jugador");
        BtnJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJugadorActionPerformed(evt);
            }
        });

        BtnSalida.setText("Salir");
        BtnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalidaActionPerformed(evt);
            }
        });

        LblTime.setText("Tiempo:");

        LblTimeCount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblTimeCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LblTimeCount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LblDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblMoves, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LblContMoves, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(LblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(OneTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(OneThree, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Tittle)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(TwoOne, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(TwoThree, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(ThreeOne, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(ThreeTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(LblTimeCount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnPista, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(BtnJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Tittle)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OneTwo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TwoOne)
                        .addComponent(TwoThree)
                        .addComponent(ThreeOne)
                        .addComponent(ThreeTwo)
                        .addComponent(OneThree)))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboDiscos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LblDiscos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblMoves)
                    .addComponent(LblContMoves, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblTimeCount, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnInicio)
                    .addComponent(BtnPista)
                    .addComponent(BtnJugador)
                    .addComponent(BtnSalida))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
        Iniciar();
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void OneTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneTwoActionPerformed
        Mover_1_2();
    }//GEN-LAST:event_OneTwoActionPerformed

    private void OneThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OneThreeActionPerformed
        Mover_1_3();
    }//GEN-LAST:event_OneThreeActionPerformed

    private void TwoOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TwoOneActionPerformed
        Mover_2_1();
    }//GEN-LAST:event_TwoOneActionPerformed

    private void TwoThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TwoThreeActionPerformed
        Mover_2_3();
    }//GEN-LAST:event_TwoThreeActionPerformed

    private void ThreeOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThreeOneActionPerformed
        Mover_3_1();
    }//GEN-LAST:event_ThreeOneActionPerformed

    private void ThreeTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThreeTwoActionPerformed
        Mover_3_2();
    }//GEN-LAST:event_ThreeTwoActionPerformed

    private void BtnPistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPistaActionPerformed
        setDerrota();
        if (Torre_3.getContadorNodos() != NumDiscos) {
            Iniciar();
            FinRes = false;
            
            Resolver(NumDiscos, Torre_1, Torre_2, Torre_3);
        }
        if (Torre_3.getContadorNodos()== NumDiscos){
            Crono.stop();
        }
    }//GEN-LAST:event_BtnPistaActionPerformed

    private void BtnJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJugadorActionPerformed
        LeerJugador();
    }//GEN-LAST:event_BtnJugadorActionPerformed

    private void BtnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalidaActionPerformed
        Finalizar();
    }//GEN-LAST:event_BtnSalidaActionPerformed
    
    private void Finalizar(){
        setDerrota();
        Crono.stop();
        OneTwo.setEnabled(false);
        OneThree.setEnabled(false);
        TwoOne.setEnabled(false);
        TwoThree.setEnabled(false);
        ThreeOne.setEnabled(false);
        ThreeTwo.setEnabled(false);
        JOptionPane.showMessageDialog(null, "Se ha rendido :c", "Abandonado", JOptionPane.WARNING_MESSAGE);
    }
    
    private void LeerJugador(){
        setJugador(JOptionPane.showInputDialog("Ingrese su nombre", JOptionPane.QUESTION_MESSAGE));
    }
    
    String Jugador;
    int Partida = 0, Victoria = 0, Derrota = 0;

    public String getJugador() {
        return Jugador;
    }

    public void setJugador(String Jugador) {
        this.Jugador = Jugador;
    }

    public int getPartida() {
        return Partida;
    }

    public void setPartida() {
        this.Partida++;
    }
    
    public int getVictoria() {
        return Victoria;
    }

    public void setVictoria() {
        this.Victoria++;
    }

    public int getDerrota() {
        return Derrota;
    }

    public void setDerrota() {
        this.Derrota++;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnJugador;
    private javax.swing.JButton BtnPista;
    private javax.swing.JButton BtnSalida;
    private javax.swing.JComboBox<String> ComboDiscos;
    private javax.swing.JLabel LblContMoves;
    private javax.swing.JLabel LblDiscos;
    private javax.swing.JLabel LblMoves;
    private javax.swing.JLabel LblTime;
    private javax.swing.JLabel LblTimeCount;
    private javax.swing.JButton OneThree;
    private javax.swing.JButton OneTwo;
    private javax.swing.JButton ThreeOne;
    private javax.swing.JButton ThreeTwo;
    private javax.swing.JLabel Tittle;
    private javax.swing.JTable Torre1;
    private javax.swing.JTable Torre2;
    private javax.swing.JTable Torre3;
    private javax.swing.JButton TwoOne;
    private javax.swing.JButton TwoThree;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
