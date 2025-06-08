package actor;
public class Produk {
    private String namaProduk;
    

    public Produk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    
    @Override
    public String toString() {
        return namaProduk;
    }

    public static Produk fromString(String line) {
        return new Produk(line);
    }
}