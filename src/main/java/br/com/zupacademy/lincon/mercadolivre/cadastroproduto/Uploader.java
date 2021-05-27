package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {
    /**
     *
     * @param imagens
     * @return links para imagens que foram upadas
     */
    Set<String> envia(List<MultipartFile> imagens);
}
