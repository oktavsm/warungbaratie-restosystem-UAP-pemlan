package service;
import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;

import actor.*;

public class ManajemenFile implements FileHandlerService {

    public static final String ANTRIAN_FILE = "antrian_kasir.txt";
    public static final String PRODUK_FILE = "produk.txt";
    
    private static final DateTimeFormatter TIME_FORMATTER = Pelanggan.TIME_FORMATTER;


    @Override
    public void tulisDataAntrian(Queue<Pelanggan> antrian, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Pelanggan p : antrian) {
                StringJoiner pesananJoiner = new StringJoiner(";");
                for (String item : p.getPesananList()) {
                    pesananJoiner.add(item);
                }
                
                writer.write(p.getIdPesanan() + "|" +
                             p.getNamaPelanggan() + "|" +
                             pesananJoiner.toString() + "|" +
                             p.getWaktuDaftar().format(TIME_FORMATTER));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing antrian to file: " + e.getMessage());
            
        }
    }

    @Override
    public Queue<Pelanggan> bacaDataAntrian(String filename) {
        Queue<Pelanggan> antrian = new LinkedList<>();
        File file = new File(filename);
        if (!file.exists()) {
            return antrian; 
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 4); 
                if (parts.length == 4) {
                    String id = parts[0];
                    String nama = parts[1];
                    String pesananStr = parts[2];
                    String waktuStr = parts[3];

                    
                    
                    LocalTime timeFromFile = LocalTime.parse(waktuStr, TIME_FORMATTER);
                    LocalDateTime waktuDaftar = LocalDateTime.now().withHour(timeFromFile.getHour()).withMinute(timeFromFile.getMinute()).withSecond(timeFromFile.getSecond());
                    
                    Pelanggan p = new Pelanggan(id, nama, waktuDaftar);
                    if (!pesananStr.isEmpty()) {
                        String[] pesananItems = pesananStr.split(";");
                        for (String item : pesananItems) {
                            p.tambahPesanan(item.trim());
                        }
                    }
                    antrian.offer(p);
                } else {
                     System.err.println("Skipping malformed line in " + filename + ": " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading antrian from file: " + e.getMessage());
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Error parsing time from file: " + e.getMessage());
        }
        return antrian;
    }

    @Override
    public void tulisDataProduk(List<Produk> produkList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Produk p : produkList) {
                writer.write(p.getNamaProduk());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing produk to file: " + e.getMessage());
        }
    }

    @Override
    public List<Produk> bacaDataProduk(String filename) {
        List<Produk> produkList = new ArrayList<>();
         File file = new File(filename);
        if (!file.exists()) {
            
            produkList.add(new Produk("Nasi Goreng"));
            produkList.add(new Produk("Mie Ayam"));
            produkList.add(new Produk("Es Teh"));
            produkList.add(new Produk("Kopi Hitam"));
            tulisDataProduk(produkList, filename); 
            return produkList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    produkList.add(new Produk(line.trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading produk from file: " + e.getMessage());
        }
        return produkList;
    }
}