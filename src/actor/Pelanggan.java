package actor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Pelanggan {
    private String idPesanan;
    private String namaPelanggan;
    private List<String> pesananList;
    private LocalDateTime waktuDaftar;

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Pelanggan(String idPesanan, String namaPelanggan, LocalDateTime waktuDaftar) {
        this.idPesanan = idPesanan;
        this.namaPelanggan = namaPelanggan;
        this.pesananList = new ArrayList<>();
        this.waktuDaftar = waktuDaftar;
    }

    public Pelanggan(String idPesanan, String namaPelanggan, List<String> pesananList, LocalDateTime waktuDaftar) {
        this.idPesanan = idPesanan;
        this.namaPelanggan = namaPelanggan;
        this.pesananList = new ArrayList<>(pesananList); 
        this.waktuDaftar = waktuDaftar;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public List<String> getPesananList() {
        return pesananList;
    }

    public void tambahPesanan(String pesanan) {
        this.pesananList.add(pesanan);
    }

    public void setPesananList(List<String> pesananList) {
        this.pesananList = pesananList;
    }

    public LocalDateTime getWaktuDaftar() {
        return waktuDaftar;
    }

    public String getWaktuDaftarFormatted() {
        return waktuDaftar.format(TIME_FORMATTER);
    }

    public void setWaktuDaftar(LocalDateTime waktuDaftar) {
        this.waktuDaftar = waktuDaftar;
    }

    
    @Override
    public String toString() {
        StringJoiner pesananJoiner = new StringJoiner(";");
        for (String item : pesananList) {
            pesananJoiner.add(item);
        }
        return idPesanan + "|" + namaPelanggan + "|" + pesananJoiner.toString() + "|" + getWaktuDaftarFormatted();
    }

    public static Pelanggan fromString(String line) {
        String[] parts = line.split("\\|", 4); 
        if (parts.length < 4) {
            System.err.println("Invalid line format for Pelanggan: " + line);
            return null; 
        }
        String id = parts[0];
        String nama = parts[1];
        String[] pesananItems = parts[2].isEmpty() ? new String[0] : parts[2].split(";");
        
        
        
        
        
        
        
        
        
        
        
        

        Pelanggan p = new Pelanggan(id, nama, LocalDateTime.now()); 
        for (String item : pesananItems) {
            p.tambahPesanan(item);
        }
        
        
        
        
        
        
        

        
        
        
        

        
        
        
        
        return p;
    }

     public String getPesananDisplay() {
        if (pesananList == null || pesananList.isEmpty()) {
            return "";
        }
        StringJoiner sj = new StringJoiner(", ");
        for (String pesanan : pesananList) {
            sj.add(pesanan);
        }
        return sj.toString();
    }
}