package school.sptech.libertfy.service.usuario.dto;
import school.sptech.libertfy.dominio.Arquivo;

import java.util.ArrayList;
import java.util.List;

public class ArquivoMapper {

    public static ArquivoDto of(Arquivo arquivo){
        ArquivoDto arquivoDto = new ArquivoDto();

        arquivoDto.setIdArquivo(arquivo.getIdArquivo());
        arquivoDto.setNomeArquivoSalvo(arquivo.getNomeArquivoSalvo());
        arquivoDto.setNomeArquivoOriginal(arquivo.getNomeArquivoOriginal());
        arquivoDto.setDataUpload(arquivo.getDataUpload());

        return arquivoDto;
    }


    public static Arquivo of(ArquivoDto arquivoDto) {
        Arquivo arquivo = new Arquivo();

        arquivo.setIdArquivo(arquivoDto.getIdArquivo());
        arquivo.setNomeArquivoSalvo(arquivoDto.getNomeArquivoSalvo());
        arquivo.setNomeArquivoOriginal(arquivoDto.getNomeArquivoOriginal());
        arquivo.setDataUpload(arquivoDto.getDataUpload());

        return arquivo;
    }

    public static List<ArquivoDto> of(List<Arquivo> arquivos) {
        List<ArquivoDto> arquivoDtos = new ArrayList<>();
        for (Arquivo arquivo : arquivos) {
            ArquivoDto arquivoDto = new ArquivoDto();

            arquivoDto.setIdArquivo(arquivo.getIdArquivo());
            arquivoDto.setNomeArquivoSalvo(arquivo.getNomeArquivoSalvo());
            arquivoDto.setNomeArquivoOriginal(arquivo.getNomeArquivoOriginal());
            arquivoDto.setDataUpload(arquivo.getDataUpload());
            arquivoDtos.add(arquivoDto);
        }
        return arquivoDtos;

    }
}