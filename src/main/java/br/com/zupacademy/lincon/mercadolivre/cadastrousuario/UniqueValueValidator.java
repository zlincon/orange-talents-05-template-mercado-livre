package br.com.zupacademy.lincon.mercadolivre.cadastrousuario;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UnicValue, Object> {

    private String domainAtribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UnicValue params) {
        domainAtribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        List resultList = manager.createQuery("select 1 from " + klass.getName()
                + " where " + domainAtribute + " = :Pvalue")
        .setParameter(
                        "Pvalue", value).getResultList();

        Assert.state(resultList.size() <= 1,
                "Foi encontrado mais de um " + klass + " com o atributo "
                        + domainAtribute + " = " + value);

        return resultList.isEmpty();
    }
}
