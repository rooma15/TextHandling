import Service.LoggerService;
import composite.TextHandler;
import org.junit.Test;

import java.io.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void mainTest(){

        String inputSrc = "./src/main/input.txt";
        String outputSrc = "./src/main/output.txt";

        TextHandler textHandler;


        try(BufferedReader reader = new BufferedReader(new FileReader(inputSrc))){
            String text = reader.lines().collect(Collectors.joining(""));
            textHandler = new TextHandler(text);
            textHandler.disassemble();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputSrc))){
                String assembledText = textHandler.assemble();
                LoggerService.LOGGER.info("Output wil be written into file {}", outputSrc);
                writer.write(assembledText);
                LoggerService.LOGGER.info("File was written successfully");
                System.out.println(assembledText);
            }catch (IOException e){
                LoggerService.LOGGER.error("Something went wrong with creation of output file");
            }
        }catch (IOException e){
            LoggerService.LOGGER.error("File not found");
        }
    }
}
