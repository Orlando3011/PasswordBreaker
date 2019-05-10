package PatternsGenerator.model.classes.singleton;

import PatternsGenerator.model.classes.common.Pattern;
import PatternsGenerator.model.interfaces.singleton.SingletonGeneratorInterface;
import PatternsGenerator.services.FileIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SingletonGenerator implements SingletonGeneratorInterface {

    private Pattern pattern;
    private FileIOService fileIOService;


    @Autowired
    public SingletonGenerator( FileIOService fileIOService) {
        this.fileIOService = fileIOService;
    }

    @Override
    public String ToString() throws IOException {
        String singletonString = this.MakeSingletonTemplate();
        singletonString = HandleComments(singletonString);
        singletonString = singletonString.replaceAll("patternName", pattern.getName());
        return singletonString;
    }

    private String MakeSingletonTemplate() throws IOException {
        String code = "";
        switch(pattern.getVersion()) {
            case 1: {
                code = fileIOService.ReadFromFile("patterns/singleton/Singleton1.txt");
                break;
            }
            case 2: {
                code = fileIOService.ReadFromFile("patterns/singleton/Singleton2.txt");
                break;
            }
            case 3:{
                code = fileIOService.ReadFromFile("patterns/singleton/Singleton3.txt");
                break;
            }
        }
        return code;
    }

    private String HandleComments(String code) throws IOException {
        if(!pattern.getAreCommentsIncluded()) {
            code = code.replaceAll("creationInfo", "");
            code = code.replaceAll("privateConstructorComment", "");
        }
        else {
            code = code.replaceAll("creationInfo", fileIOService.ReadFromFile("patterns/common/comments/creationDetails.txt"));
            code = code.replaceAll("privateConstructorComment", fileIOService.ReadFromFile("patterns/singleton/comments/privateConstructorComment.txt"));
        }
        return code;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
