package school.sptech.libertfy.api.controller.usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.libertfy.dominio.Arquivo;
import school.sptech.libertfy.dominio.FilaObj;
import school.sptech.libertfy.dominio.PilhaObj;
import school.sptech.libertfy.dominio.Usuario;
import school.sptech.libertfy.repository.arquivo.ArquivoRepository;
import school.sptech.libertfy.repository.usuario.UsuarioRepository;

import jakarta.validation.Valid;
import school.sptech.libertfy.service.usuario.UsuarioService;
import school.sptech.libertfy.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.libertfy.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.libertfy.service.usuario.dto.ArquivoDto;
import school.sptech.libertfy.service.usuario.dto.ArquivoMapper;
import school.sptech.libertfy.service.usuario.dto.UsuarioCriacaoDto;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private ArquivoRepository arquivoRepository;
    @Autowired
    private FilaObj<Integer> upload = new FilaObj<>();
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/Downloads");

    @SecurityRequirement(name = "Bearer")
    @PostMapping("/criar")
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }
    @SecurityRequirement(name = "Bearer")
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        UsuarioTokenDto usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);
        return ResponseEntity.status(200).body(usuarioTokenDto);

    }
    @SecurityRequirement(name = "Bearer")
    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody @Valid Usuario novoUsuario) {

        Usuario usuarioBanco = usuarioRepository.save(novoUsuario);
        return ResponseEntity.status(201).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> listaUsuario = usuarioRepository.findAll();

        if (listaUsuario.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(listaUsuario);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUsuarioIndice(@PathVariable Integer id) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            return ResponseEntity.status(200).body(usuarioOpt.get());
        }

        return ResponseEntity.status(404).build();
    }
    @SecurityRequirement(name = "Bearer")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id,
                                             @RequestBody Usuario usuarioAtualizado) {
        if (usuarioRepository.existsById(id)) {
            usuarioAtualizado.setId(id);
            usuarioRepository.save(usuarioAtualizado);
            return ResponseEntity.status(200).body(usuarioAtualizado);
        }
        return ResponseEntity.status(404).build();
    }
    @SecurityRequirement(name = "Bearer")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerUsuario(@PathVariable Integer id) {

        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/download-csv")
    public void gerarCSV(HttpServletResponse response) throws IOException, SQLException {
        List<Usuario> usuarios = usuarioRepository.listagemEmOrdemAlfabetica();

        StringBuilder csv = new StringBuilder();
        csv.append("ID;Nome;Genero;E-mail;Data Nascimento\n");

        for (Usuario usuario : usuarios) {
            csv.append(usuario.getId());
            csv.append(";");
            csv.append(usuario.getNome());
            csv.append(";");
            csv.append(usuario.getGenero());
            csv.append(";");
            csv.append(usuario.getEmail());
            csv.append(";");
            csv.append(usuario.getDataNascimento());
            csv.append("\n");
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=usuarios.csv");

        byte[] bytes = csv.toString().getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(bytes);
    }
    @SecurityRequirement(name = "Bearer")
    @GetMapping("/download-txt")
    public ResponseEntity<ByteArrayResource> downloadUsers() {
        List<Usuario> users = usuarioRepository.listagemEmOrdemAlfabetica();
        PilhaObj<String> pilha = new PilhaObj<>(1000);

        for (Usuario u : users) {
            pilha.push(String.format("%-10s%-10s%-30s%-20s",
                    u.getNome(), u.getGenero(), u.getEmail(), u.getDataNascimento().toString()));
        }

        StringBuilder content = new StringBuilder();
        while (!pilha.isEmpty()) {
            content.append(pilha.pop()).append("\n");
        }

        try {
            File file = new File("users.txt");
            FileWriter writer = new FileWriter(file);
            writer.write(content.toString());
            writer.close();

            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=users.txt")
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            // Tratar o erro adequadamente de acordo com suas necessidades
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    private String formatarNomeArquivo(String nomeOriginal) {
        return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
    }

    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Uploads de arquivos")
    @PostMapping(value = "/upload/{id}", consumes = "multipart/form-data")
    public ResponseEntity<ArquivoDto> upload(@RequestParam("arquivo") MultipartFile file, @PathVariable int id) {
        if (file.isEmpty()) {
            return ResponseEntity.status(400).build();
        }
        if (!this.diretorioBase.toFile().exists()) {
            this.diretorioBase.toFile().mkdir();
        }
        if (!this.usuarioRepository.existsById(id)) {
            return ResponseEntity.status(404).build();
        }

        String nomeArquivoFormatado = formatarNomeArquivo(file.getOriginalFilename());

        String filePath = this.diretorioBase + "/" + nomeArquivoFormatado;

        File dest = new File(filePath);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
        }

        Arquivo arquivo = new Arquivo();
        arquivo.setUsuario(this.usuarioRepository.findById(id).get());
        arquivo.setDataUpload(LocalDate.now());
        arquivo.setNomeArquivoOriginal(file.getOriginalFilename());
        arquivo.setNomeArquivoSalvo(nomeArquivoFormatado);
        Arquivo arquivoBanco = arquivoRepository.save(arquivo);

        // Adicionar o ID do arquivo à fila de uploads
        upload.insert(arquivoBanco.getIdArquivo());

        ArquivoDto arquivoDto = ArquivoMapper.of(arquivoBanco);
        return ResponseEntity.status(200).body(arquivoDto);
    }

    @GetMapping("/media-idade")
    public ResponseEntity<Double> obterMediaIdadeUsuarios() {
        double mediaIdade = usuarioService.calcularMediaIdadeUsuarios();
        return new ResponseEntity<>(mediaIdade, HttpStatus.OK);
    }

    @GetMapping("/percentual-masculinos")
    public ResponseEntity<Double> obterPercentualUsuariosMasculinos() {
        Double percentualMasculinos = usuarioRepository.calcularPercentualUsuariosMasculinos();
        return new ResponseEntity<>(percentualMasculinos, HttpStatus.OK);
    }

    @GetMapping("/percentual-femininos")
    public ResponseEntity<Double> obterPercentualUsuariosFemininos() {
        Double percentualFemininos = usuarioRepository.calcularPercentualUsuariosFemininos();
        return new ResponseEntity<>(percentualFemininos, HttpStatus.OK);
    }
}

