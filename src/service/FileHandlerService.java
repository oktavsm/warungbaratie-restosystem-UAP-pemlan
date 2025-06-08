package service;
import java.util.List;
import java.util.Queue;


import actor.*;

public interface FileHandlerService {
    void tulisDataAntrian(Queue<Pelanggan> antrian, String filename);
    Queue<Pelanggan> bacaDataAntrian(String filename);
    void tulisDataProduk(List<Produk> produkList, String filename);
    List<Produk> bacaDataProduk(String filename);
}