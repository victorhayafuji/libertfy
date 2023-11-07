package school.sptech.libertfy.dominio;

import jakarta.persistence.*;
import jakarta.persistence.ManyToOne;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer idArquivo;
    private String nomeArquivoOriginal;
    private String nomeArquivoSalvo;
    private LocalDate dataUpload;
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public Integer getIdArquivo() {
        return idArquivo;
    }

    public void setIdArquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }

    public String getNomeArquivoOriginal() {
        return nomeArquivoOriginal;
    }

    public void setNomeArquivoOriginal(String nomeArquivoOriginal) {
        this.nomeArquivoOriginal = nomeArquivoOriginal;
    }

    public String getNomeArquivoSalvo() {
        return nomeArquivoSalvo;
    }

    public void setNomeArquivoSalvo(String nomeArquivoSalvo) {
        this.nomeArquivoSalvo = nomeArquivoSalvo;
    }

    public LocalDate getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDate dataUpload) {
        this.dataUpload = dataUpload;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static void gravaRegistro (String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Bloco try-catch para abrir o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        }
        catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar e fechar o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        }
        catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo");
        }
    }
    public static void gravaArquivoTxt(List<Usuario> lista, String nomeArq) {
        int contaRegDadosGravados = 0;

        // Monta o registro de header
        String header = "00USUARIO";
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Monta e grava os registros de dados ou registros de corpo
        String corpo;
        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            corpo = "02";
            corpo += String.format("%-50.50s", u.getNome());
            corpo += String.format("%9.9s", u.getGenero());
            corpo += String.format("%-60.60s", u.getEmail());
            corpo += String.format("%-10.10s", u.getDataNascimento());
           gravaRegistro(corpo, nomeArq);
            contaRegDadosGravados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d",contaRegDadosGravados);
        gravaRegistro(trailer, nomeArq);

    }
}