package br.com.zupacademy.lincon.mercadolivre.cadastroproduto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class ProibeNomesDuplicadosCaracteristicaValidador implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoDTO request = (ProdutoDTO) o;
        Set<String> nomesIguais = request.buscaCaracteristicasIguais();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Você tem " +
                    "caracteristicas iguais " + nomesIguais);
        }
    }
}
