import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;


public class EditData extends JFrame{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/mahasiswa";
    static final String USER = "root";
    static final String PASS = "";
    
    Connection koneksi;
    Statement statement;
    
    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"NIM", "Nama", "Alamat"};
    
    JLabel ljudul = new JLabel("Edit Data Mahasiswa");
    JLabel lnim = new JLabel("NIM ");
    JTextField tnim = new JTextField();
    JLabel lnama = new JLabel("Nama ");
    JTextField tnama = new JTextField(); 
    JLabel lalamat = new JLabel("Alamat ");
    JTextField talamat = new JTextField();
    JButton edit = new JButton("Edit");
    JButton kembali = new JButton("Kembali"); 
    
    
    public void EditData(){
        try{
            Class.forName(JDBC_DRIVER);
            koneksi = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Koneksi Berhasil");
        }   catch(ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        setSize(550, 600);
        
        tableModel = new DefaultTableModel(namaKolom,0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);        
        add(scrollPane);
        scrollPane.setBounds(20, 270, 480, 250);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(ljudul);
        ljudul.setBounds(230, 10, 300, 60);
        add(lnama);
        add(lnim);
        add(lalamat);
        add(tnama);
        add(tnim);
        add(talamat);
        
        lnim.setBounds(40, 80, 80, 20);
        tnim.setBounds(140, 80, 220, 20);        
        lnama.setBounds(40, 110, 80, 20);        
        tnama.setBounds(140, 110, 220, 20);        
        lalamat.setBounds(40, 140, 80, 20);        
        talamat.setBounds(140, 140, 220, 20);        
        
        add(edit);
        add(kembali);
        edit.setBounds(150, 220, 80, 30);        
        kembali.setBounds(350, 220, 80, 30);        
        
        kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible (false);
                Menu m = new Menu();
                m.Menu();
            }
        });
        
        edit.addActionListener((ActionEvent e) -> {
            if (tnim.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong!");
            } else {
                String nim = tnim.getText();
                String nama = tnama.getText();
                String alamat = talamat.getText();
                             
                this.simpanMahasiswa(nim, nama, alamat);
  
                String dataMahasiswa[][] = this.readMahasiswa();
                table.setModel(new JTable(dataMahasiswa,namaKolom).getModel());
            }
        });
        
        if (this.getBanyakData() != 0) {  
            String dataMahasiswa[][] = this.readMahasiswa();  
            table.setModel((new JTable(dataMahasiswa, namaKolom)).getModel());
             
        } else {
            JOptionPane.showMessageDialog(null, "Data Tidak Ada!");
        }
        
        table.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e){ 
               int baris = table.getSelectedRow();
               int kolom = table.getSelectedColumn();  
               String dataterpilih = table.getValueAt(baris, 0).toString();
           }
        });

}
    
    public void simpanMahasiswa(String nim, String nama, String alamat) {
        try{
            String query ="UPDATE data_mhs SET nama='" + tnama.getText() + "',"+ "alamat='" +
                        talamat.getText() + "' WHERE nim='" + tnim.getText() + "'";
            statement = (Statement) koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil diedit");
            JOptionPane.showMessageDialog(null,"Berhasil diedit "+nim+"");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }        
    }
    String[][] readMahasiswa() {
        try{
            int jmlData = 0;
            String data[][]=new String[getBanyakData()][3];
            String query = "Select * from `data_mhs`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("nim");
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("alamat");
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }

    int getBanyakData() {
        int jmlData = 0;
        try{
            statement = koneksi.createStatement();
            String query = "SELECT * from `data_mhs`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return 0;
        }
    }

 
    

}
