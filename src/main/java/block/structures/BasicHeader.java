package block.structures;

import java.util.List;

public class BasicHeader extends CommonSubjects {

    private List<String> content;

    // Block Identifier
    public static final String BLOCK_IDENTIFIER = "1";

    // Identifier And Subjects' Length
    public static final int BLOCK_IDENTIFIER_LENGTH = BLOCK_IDENTIFIER.length();
    public static final int APP_ID_LENGTH = 1;
    public static final int SERVICE_ID_LENGTH = 2;
    /*
        LT_ADDRESS_LENGTH
        SESSION_NUMBER_LENGTH
        SEQUENCE_NUMBER_LENGTH
    */

    public BasicHeader() { }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public String getAppID() {
        if(this.content == null || this.content.get(0).equals(""))
            return null;
        return this.content.get(0).substring(0, APP_ID_LENGTH);
    }

    public String getServiceID() {
        if(this.content == null || this.content.get(0).equals(""))
            return null;
        return this.content.get(0).substring(APP_ID_LENGTH, APP_ID_LENGTH + SERVICE_ID_LENGTH);
    }

    public String getLTAddress() {
        if(this.content == null || this.content.get(0).equals(""))
            return null;
        return this.content.get(0).substring(APP_ID_LENGTH + SERVICE_ID_LENGTH,
                APP_ID_LENGTH + SERVICE_ID_LENGTH + LT_ADDRESS_LENGTH);
    }

    public String getSessionNumber() {
        if(this.content == null || this.content.get(0).equals(""))
            return null;
        return this.content.get(0).substring(APP_ID_LENGTH + SERVICE_ID_LENGTH + LT_ADDRESS_LENGTH,
                APP_ID_LENGTH + SERVICE_ID_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH);
    }

    public String getSequenceNumber() {
        if(this.content == null || this.content.get(0).equals(""))
            return null;
        return this.content.get(0).substring(APP_ID_LENGTH + SERVICE_ID_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH,
                APP_ID_LENGTH + SERVICE_ID_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH +SEQUENCE_NUMBER_LENGTH);
    }

}
