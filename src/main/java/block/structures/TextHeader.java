package block.structures;

import java.util.ArrayList;
import java.util.List;

public class TextHeader {

    private List<String> content;

    // Block Identifier
    public static final String BLOCK_IDENTIFIER = "4";

    public TextHeader() { }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public List<List<String>> findTag(String tag) {
        //
        if(this.content == null || this.content.isEmpty())
            return null;

        List<List<String>> list = new ArrayList<List<String>>();
        List<String> l = new ArrayList<String>();
        String lastLineTag = null;
        int round = 0;
        for(String line : this.content) {
            // 找Tag
            int end = line.indexOf(":", 1);
            if(line.startsWith(":") && end != -1) {
                String lineTag = line.substring(1, end);
                if(lineTag.equals(tag)) {
                    // Tag 出現次數
                    round ++;
                    if(round >= 2) {
                        list.add(l);
                        l = new ArrayList<String>();
                    }
                    //
                    l.add(line);
                }
                lastLineTag = lineTag;
            }
            else {
                // 無 Tag 行
                if(lastLineTag != null && lastLineTag.equals(tag))
                    l.add(line);
            }
        }
        //
        if(round >= 1)
            list.add(l);

        if (list.isEmpty())
            return null;
        return list;
    }

    // Find Tag by Order
    public List<String> findTag(String tag, int nth) {
        if(findTag(tag) == null)
            return null;
        if(nth <= findTag(tag).size() && nth > 0)
            return findTag(tag).get(nth - 1);
        // n < 0
        return null;
    }

    // Print Tag by Order
    public void printTag(String tag, int nth) {
        List<String> content = findTag(tag, nth);
        if(content != null)
            for(String line : content)
                System.out.println(line);;
    }

}
