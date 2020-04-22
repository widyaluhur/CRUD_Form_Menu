
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class HapusData extends JFrame {
    String [][] data = new String [480][3];
    String[] kolom = {"NIM","Nama","Alamat"};
    JTable tabel;
    JButton kembali, btnDelete;
    JLabel ljudul;
    JScrollPane scrollpane;
    Statement statement;
    ResultSet resultSet;
    public static String dataterpilih;
    
    public void HapusData(){
        setTitle("Data Mahasiswa");
        
        ljudul = new JLabel("Data Mahasiswa");
        kembali = new JButton("Kembali");
        btnDelete = new JButton("Hapus");
        tabel = new JTable(data, kolom);
        JScrollPane scrollPane = new JScrollPane(tabel);
        
        setSize(500, 390);
        ljudul.setHorizontalAlignment(SwingConstants.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(scrollPane);
        
        pack();
        setLocationRelativeTo(null);
        setLayout(null);
        add(kembali);
        add(btnDelete);
        add(ljudul);
        add(scrollPane);
        
        ljudul.setBounds(0,25,500,20);
        scrollPane.setBounds(50,90,380,300);
        kembali.setBounds(150,60,90,20);
        btnDelete.setBounds(260,60,90,20);
        
         kembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                setVisible(false);
                Menu m = new Menu();
                m.Menu();            
            }
        });
        
        tabel.addMouseListener(new MouseAdapter(){
            @Override
           public void mouseClicked(MouseEvent e){ 
               int baris = tabel.getSelectedRow();
               int kolom = tabel.getSelectedColumn();
               dataterpilih = tabel.getValueAt(baris, 0).toString();
                
                
               System.out.println(dataterpilih);
               btnDelete.addActionListener((java.awt.event.ActionEvent f) -> {
                  btnBuatactionListener();
                }); 
           }

            private void btnBuatactionListener() {
               KoneksiDB koneksi = new KoneksiDB();
                 try{
            
                statement = koneksi.getKoneksi().createStatement();
                statement.executeUpdate("DELETE FROM `data_mhs` WHERE nim='" + dataterpilih + "'");
                JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
                
            } catch (SQLException sqle){
            JOptionPane.showMessageDialog(rootPane, "data gagal dihapus" + sqle);
        } catch (ClassNotFoundException classe){
            JOptionPane.showMessageDialog(rootPane, "data tidak ditemukan" + classe);
        }
            }
            
        });
        
        
        
        
        KoneksiDB koneksi = new KoneksiDB();
        try{
            statement = koneksi.getKoneksi().createStatement();
            String sql = "select *from data_mhs";
            resultSet = statement.executeQuery(sql);
            int p = 0;
            while(resultSet.next()){
                data[p][0] = resultSet.getString("nim");
                data[p][1] = resultSet.getString("nama");
                data[p][2] = resultSet.getString("alamat");
                p++;
            }
            statement.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Driver Tidak Ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
        
    }

   

    
    
}
