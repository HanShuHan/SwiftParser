package block.structures;

import java.util.List;

public class UserHeader {

    private List<String> content;

    // Block Identifier
    public static final String BLOCK_IDENTIFIER = "3";

    // Identifier And Subjects' Length
    public static final int BLOCK_IDENTIFIER_LENGTH = BLOCK_IDENTIFIER.length();

    public UserHeader() { }

    public List<String> getContent() {
        return content;
    }
    public void setContent(List<String> content) {
        this.content = content;
    }

    public String findTag(String tag) {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        String fields = this.content.get(0);
        int i;
        if((i = fields.indexOf("{" + tag + ":")) != -1) {
            int end = fields.indexOf("}", i);
            return fields.substring(i + tag.length() + 2, end);
        }
        else
            return null;
    }

}
