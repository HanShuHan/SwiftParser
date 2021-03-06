import block.structures.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwiftCode {

    private Path path;
    private String filePath;
    private final List<String> originalLines;
    private final String reg = "^(#|$)";
    private List<MTMessage> messages = new ArrayList<MTMessage>();
    private MTMessage message = new MTMessage();
    private List<String> contents = null;

    public SwiftCode(Path path)
    {
        this.path = path;
        this.originalLines = copyOriginalLines();
        setFilePath(path.toString());
        generate();
    }

    public SwiftCode(File file)
    {
        this.path = file.toPath();
        this.originalLines = copyOriginalLines();
        setFilePath(file.getAbsolutePath());
        generate();
    }

    public SwiftCode(String filepath)
    {
        this.path = Path.of(filepath);
        this.originalLines = copyOriginalLines();
        setFilePath(filepath);
        generate();
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public List<String> getOriginalLines()
    {
        return originalLines;
    }

    public static SwiftCode of(Path path)
    {
        return new SwiftCode(path);
    }

    public static SwiftCode of(File file)
    {
        return new SwiftCode(file);
    }

    public static SwiftCode of(String filepath)
    {
        return new SwiftCode(filepath);
    }

    private List<String> copyOriginalLines()
    {
        try
        {
            return Files.readAllLines(this.path, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void f()
    {
        List<String> l;
        String s = String.join("\n", this.originalLines);
        if(Pattern.matches("\u0001.*\u0003", s))
            s = s.substring(1, s.length() - 1);
        System.out.println(s);
//        if(Pattern.matches("^\u0001+\u0003$", s))
//            s = s.substring(1, s.length() - 1);
//        Pattern pattern = Pattern.compile("\u0001\u0003", Pattern.DOTALL);
//        l = Arrays.asList(pattern.split(s));
//        System.out.println(l.get(0));
    }

    /**
     * ?????? filePath ?????????????????????
     */
    public void print()
    {
        try
        {
            List<String> lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
            for(String line : lines)
                System.out.println(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void removeIf(Predicate<? super String> filter)
    {
        try {
            List<String> lines = Files.readAllLines(this.path, StandardCharsets.UTF_8);
            lines.removeIf(filter);
            for(String line : lines)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??? filePath ??????????????????????????? Blocks ????????????
     */
    public void printBlocks()
    {
        try
        {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filePath), StandardCharsets.UTF_8));
            // ??????????????????
            String line;
            // ?????????????????? ETXSOH
            while((line = br.readLine()).contains("\u0003\u0001"))
                System.out.println(line);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void generate() {

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filePath), StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                // ???????????????????????????????????????
//                if(!Pattern.matches(reg, line))
                    containsSOHETX(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ?????? SOHETX
    private void containsSOHETX(String line) {
        int i;
        if((i = line.indexOf("\u0003\u0001")) != -1) {
            // ???????????? SOH ??????
            ifStartsWithSOH(line.substring(0, i));
            // ??????  => ????????????????????? swift code ??? this.list
            this.messages.add(this.message);
            this.message = new MTMessage();
            //
            containsSOHETX(line.substring(i + 1));
        }
        else {
            if(line.startsWith("\u0001") && line.endsWith("\u0003"))
                ifEndsWithETX(line.substring(1));
            else if(line.endsWith("\u0003"))
                ifEndsWithETX(line);
            else if(line.startsWith("\u0001"))
                ifStartsWithSOH(line);
            else
                putBlocks(line);
        }
    }

    // ?????? 
    private void ifStartsWithSOH(String line) {
        if(line.startsWith("\u0001"))
            // ???????????????????????????????????????????????????List
            putBlocks(line.substring(1));
        else
            putBlocks(line);
    }

    // ?????? 
    private void ifEndsWithETX(String line) {
        if(line.endsWith("\u0003")) {
            // ???????????????????????????????????????????????????List
            putBlocks(line.substring(0, line.length() - 1));
            // ??????  => ????????????????????? swift code ??? this.list
            this.messages.add(this.message);
            this.message = new MTMessage();
        }
        else
            putBlocks(line);
    }

    // ???????????????????????????????????????????????????List
    private void putBlocks(String line) {

        int i;
        // Check IF the Line Contains BasicHeader
        if((i = line.indexOf("{" + BasicHeader.BLOCK_IDENTIFIER + ":")) != -1) {
            this.contents = new ArrayList<String>();
            int end = line.indexOf("}", i);
            String content = line.substring(i + BasicHeader.BLOCK_IDENTIFIER.length() + 2, end);
            this.contents.add(content);
            this.message.put(BasicHeader.BLOCK_IDENTIFIER, this.contents);
        }

        // Check IF the Line Contains ApplicationHeader
        if((i = line.indexOf("{" + ApplicationHeader.BLOCK_IDENTIFIER + ":")) != -1) {
            this.contents = new ArrayList<String>();
            int end = line.indexOf("}", i);
            String content = line.substring(i + ApplicationHeader.BLOCK_IDENTIFIER.length() + 2, end);
            this.contents.add(content);
            this.message.put(ApplicationHeader.BLOCK_IDENTIFIER, this.contents);
        }

        if((i = line.indexOf("{" + UserHeader.BLOCK_IDENTIFIER + ":")) != -1) {
            this.contents = new ArrayList<String>();
            String content;

            if(String.valueOf(line.charAt(i + UserHeader.BLOCK_IDENTIFIER.length() + 2)).equals("}") )
                content = "";
            else {
                int end = line.indexOf("}}", i);
                content = line.substring(i + UserHeader.BLOCK_IDENTIFIER.length() + 2, end + 1);
            }
            this.contents.add(content);
            this.message.put(UserHeader.BLOCK_IDENTIFIER, this.contents);
        }

        if((i = line.indexOf("{" + TextHeader.BLOCK_IDENTIFIER + ":")) != -1)
            this.contents = new ArrayList<String>();

        if(!(line.startsWith("{") || line.startsWith("-}")))
            this.contents.add(line);

        if(line.startsWith("-}"))
            this.message.put(TextHeader.BLOCK_IDENTIFIER, this.contents);

        if((i = line.indexOf("{" + TrailersHeader.BLOCK_IDENTIFIER + ":")) != -1) {
            this.contents = new ArrayList<String>();
            String content;

            if(String.valueOf(line.charAt(i + TrailersHeader.BLOCK_IDENTIFIER.length() + 2)).equals("}") )
                content = "";
            else {
                int end = line.indexOf("}}", i);
                content = line.substring(i + TrailersHeader.BLOCK_IDENTIFIER.length() + 2, end + 1);
            }
            this.contents.add(content);
            this.message.put(TrailersHeader.BLOCK_IDENTIFIER, this.contents);
        }

        if((i = line.indexOf("{" + AnonymousHeader.BLOCK_IDENTIFIER + ":")) != -1) {
            this.contents = new ArrayList<String>();
            String content;

            if(String.valueOf(line.charAt(i + AnonymousHeader.BLOCK_IDENTIFIER.length() + 2)).equals("}") )
                content = "";
            else {
                int end = line.indexOf("}}", i);
                content = line.substring(i + AnonymousHeader.BLOCK_IDENTIFIER.length() + 2, end + 1);
            }this.contents.add(content);
            this.message.put(AnonymousHeader.BLOCK_IDENTIFIER, this.contents);
        }
    }

    public List<MTMessage> getMessages() {
        return messages;
    }

}
