package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagensDTO {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    public void setImagens(@Size(min = 1) List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
