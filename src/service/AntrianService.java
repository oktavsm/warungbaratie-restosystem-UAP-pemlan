package service;
import java.util.List;
import java.util.Queue;

import actor.*;
import service.*;

public interface AntrianService {
    void tambahAntrian(Pelanggan pelanggan);
    void tambahPesananKeAntrian(String idPesanan, String namaPelangganJikaBaru, String pesanan);
    Pelanggan layaniPelanggan() throws AntrianKosongException;
    Pelanggan lihatBerikutnya();
    Queue<Pelanggan> getSemuaAntrian(); 
    boolean isAntrianKosong();
    void loadAntrianFromFile(String filename);
    void saveAntrianToFile(String filename);
}