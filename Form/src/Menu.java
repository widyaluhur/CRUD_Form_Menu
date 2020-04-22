
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class Menu extends JFrame {
    JLabel judul;
    JButton input,tampil,hapus,edit,exit;
    
    public void Menu(){
        
        setTitle("GUI MAHASISWA");
        
        judul = new JLabel("MENU");
        input = new JButton("1. Input Data Mahasiswa");
        tampil = new JButton("2. Tampilkan Semua Data");
        hapus = new JButton("3. Hapus Data Mahasiswa");
        edit = new JButton("4. Edit Data Mahasiswa");
        exit = new JButton("0. Exit");
        
        setLayout(null);
        add(judul);
        add(input);
        add(hapus);
        add(tampil);
        add(edit);
        add(exit);
        
        judul.setBounds(150, 20, 180, 20);
        input.setBounds(75, 50, 180, 20);
        tampil.setBounds(75, 70, 180, 20);
        hapus.setBounds(75, 90, 180, 20);
        edit.setBounds(75, 110, 180, 20);
        exit.setBounds(75, 130, 180, 20);
        
        setSize(350,250); //untuk luas jendela
        
        input.setHorizontalAlignment(SwingConstants.LEFT);
        tampil.setHorizontalAlignment(SwingConstants.LEFT);
        hapus.setHorizontalAlignment(SwingConstants.LEFT);
        edit.setHorizontalAlignment(SwingConstants.LEFT);
        exit.setHorizontalAlignment(SwingConstants.LEFT);
        
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        hapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HapusData dt = new HapusData();
                dt.HapusData();
            }
        });
        
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                EditData ed = new EditData();
                ed.EditData();
            }
        });
        
        tampil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                LihatData dt = new LihatData();
                dt.Lihatdata();
            }
        });
        
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                formmhs fm = new formmhs();
                fm.tesformmhs();
            }
        });
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}
