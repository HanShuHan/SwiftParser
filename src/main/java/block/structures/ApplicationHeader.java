package block.structures;

import java.util.List;

public class ApplicationHeader extends CommonSubjects {

    private List<String> content;
    private String ioID;

    // Block Identifier
    public static final String BLOCK_IDENTIFIER = "2";
    // Priority
    public static final List<String> PRIORITY = List.of("S", "U", "N");

    // Identifier's Length
    public static final int BLOCK_IDENTIFIER_LENGTH = BLOCK_IDENTIFIER.length();

    // Subjects' Length

    // Input
    public static final int IO_ID_LENGTH = 1;
    public static final int SWIFT_MESSAGE_TYPE_LENGTH = 3;
    /*
        LT_ADDRESS_LENGTH
     */
    public static final int PRIORITY_LENGTH = PRIORITY.get(0).length();
    public static final int DELIVERY_MONITORING_LENGTH = 1;
    public static final int OBSOLESCENCE_PERIOD_LENGTH = 3;

    // Output
    public static final int INPUT_TIME_LENGTH = 4;
    public static final int INPUT_DATE_LENGTH = 6;
    /*
        LT_ADDRESS_LENGTH
        SESSION_NUMBER_LENGTH
        SEQUENCE_NUMBER_LENGTH
    */
    // Message Input Reference Length
    public static final int MIR_LENGTH =
            INPUT_DATE_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH + SEQUENCE_NUMBER_LENGTH;
    public static final int OUTPUT_DATE_LENGTH = 6;
    public static final int OUTPUT_TIME_LENGTH = 4;


    public ApplicationHeader() {
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
        this.ioID = getInOutID();
    }

    public String getInOutID() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        return this.content.get(0).substring(0, IO_ID_LENGTH);
    }

    // Input

    public String getSwiftMessageType() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        return this.content.get(0).substring(IO_ID_LENGTH, IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH);
    }

    public String getDestAddress() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("I"))
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH);
        return null;
    }

    public String getPriority() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("I") && this.content.get(0).length() > IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH)
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH);

        else if(this.ioID.equals("O") && this.content.get(0).length() > IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH +
                INPUT_TIME_LENGTH + MIR_LENGTH + OUTPUT_DATE_LENGTH + OUTPUT_TIME_LENGTH)
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH + MIR_LENGTH + OUTPUT_DATE_LENGTH + OUTPUT_TIME_LENGTH);

        return null;
    }

    public String getDeliveryMonitor() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("I") && this.content.get(0).length() > IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH)
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH + DELIVERY_MONITORING_LENGTH);
        return null;
    }

    public String getObsolescencePeriod() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("I") && this.content.get(0).length() > IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH + DELIVERY_MONITORING_LENGTH)
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH + DELIVERY_MONITORING_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + LT_ADDRESS_LENGTH + PRIORITY_LENGTH + DELIVERY_MONITORING_LENGTH + OBSOLESCENCE_PERIOD_LENGTH);
        return null;
    }

    // Output

    public String getInputTime() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH);
        return null;
    }

    public String getMIR() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH + MIR_LENGTH);
        return null;
    }

    public String getInputDate() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return getMIR().substring(0, INPUT_DATE_LENGTH);
        return null;
    }

    public String getSenderLTAddress() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return getMIR().substring(INPUT_DATE_LENGTH, INPUT_DATE_LENGTH + LT_ADDRESS_LENGTH);
        return null;
    }

    public String getSessionNumber() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return getMIR().substring(INPUT_DATE_LENGTH + LT_ADDRESS_LENGTH,
                    INPUT_DATE_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH);
        return null;
    }

    public String getSequenceNumber() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return getMIR().substring(INPUT_DATE_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH,
                    INPUT_DATE_LENGTH + LT_ADDRESS_LENGTH + SESSION_NUMBER_LENGTH + SEQUENCE_NUMBER_LENGTH);
        return null;
    }

    public String getOutputDate() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH + MIR_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH + MIR_LENGTH + OUTPUT_DATE_LENGTH);
        return null;
    }

    public String getOutputTime() {
        //
        if(this.content == null || this.content.get(0).equals(""))
            return null;

        if(this.ioID.equals("O"))
            return this.content.get(0).substring(IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH + MIR_LENGTH + OUTPUT_DATE_LENGTH,
                    IO_ID_LENGTH + SWIFT_MESSAGE_TYPE_LENGTH + INPUT_TIME_LENGTH + MIR_LENGTH + OUTPUT_DATE_LENGTH + OUTPUT_TIME_LENGTH);
        return null;
    }

}
