package PatternsGenerator.model.classes;

import PatternsGenerator.model.interfaces.SingletonGeneratorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


public class SingletonGenerator implements SingletonGeneratorInterface {

    @Autowired
    private Pattern pattern;
    private List<Attribute> attributes;



    @Override
    public String ToString() {
        return null;
    }
}
