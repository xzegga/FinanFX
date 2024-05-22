package finanfx.models;

/**
 *
 * @author Raul Escamilla
 */
public class Cuenta {

    private int idCuenta;
    private Integer idUsuario;
    private String tipoCuenta;
    private String numeroCuenta;
    private String nombreBanco;
    private Double saldoInicial;

    // Constructor
    public Cuenta(int idCuenta, byte[] numeroCuenta) {
        this.idCuenta = idCuenta;
    }

    public Cuenta(String tipoCuenta, String numeroCuenta, String nombreBanco, Double saldoInicial) {
        this.tipoCuenta = tipoCuenta;
        this.numeroCuenta = numeroCuenta != null ? numeroCuenta : null;
        this.nombreBanco = nombreBanco;
        this.saldoInicial = saldoInicial;
    }

    // Getters and Setters
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

}
