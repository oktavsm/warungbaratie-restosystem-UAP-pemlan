package service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import actor.*;

public class ManajemenAntrian implements AntrianService {
    private Queue<Pelanggan> antrian;
    private FileHandlerService fileHandlerService; 

    public ManajemenAntrian(FileHandlerService fileHandlerService) {
        this.antrian = new LinkedList<>();
        this.fileHandlerService = fileHandlerService;
    }

    @Override
    public void tambahAntrian(Pelanggan pelanggan) {
        antrian.offer(pelanggan);
        saveAntrianToFile(ManajemenFile.ANTRIAN_FILE);
    }

    @Override
    public void tambahPesananKeAntrian(String idPesanan, String namaPelangganJikaBaru, String pesananItem) {
        
        Optional<Pelanggan> pelangganOpt = antrian.stream()
                .filter(p -> p.getIdPesanan().equals(idPesanan))
                .findFirst();

        if (pelangganOpt.isPresent()) {
            pelangganOpt.get().tambahPesanan(pesananItem);
        } else {
            Pelanggan pelangganBaru = new Pelanggan(idPesanan, namaPelangganJikaBaru, LocalDateTime.now());
            pelangganBaru.tambahPesanan(pesananItem);
            antrian.offer(pelangganBaru);
        }
        saveAntrianToFile(ManajemenFile.ANTRIAN_FILE);
    }


    @Override
    public Pelanggan layaniPelanggan() throws AntrianKosongException {
        if (antrian.isEmpty()) {
            throw new AntrianKosongException("Antrian kosong! Tidak ada pelanggan untuk dilayani.");
        }
        Pelanggan pelangganDilayani = antrian.poll();
        saveAntrianToFile(ManajemenFile.ANTRIAN_FILE);
        return pelangganDilayani;
    }

    @Override
    public Pelanggan lihatBerikutnya() {
        return antrian.peek();
    }

    @Override
    public Queue<Pelanggan> getSemuaAntrian() {
        
        return new LinkedList<>(antrian);
    }


    @Override
    public boolean isAntrianKosong() {
        return antrian.isEmpty();
    }

    @Override
    public void loadAntrianFromFile(String filename) {
        this.antrian = fileHandlerService.bacaDataAntrian(filename);
    }

    @Override
    public void saveAntrianToFile(String filename) {
        fileHandlerService.tulisDataAntrian(this.antrian, filename);
    }
}