import block.structures.*;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    // 24093940 15114340
    private static final String filePath = "C:\\Users\\luisi\\Downloads\\電文s\\24093940.SWI";

    public static void main(String[] args) {

//        SwiftCode sc2;

//        Path path = Path.of(filePath);
//        sc2 = Swift.parse(path);

//        File f = new File(filePath);
//        sc2 = Swift.parse(f);

//        sc2 = Swift.parse(filePath);sc2.f();

//        sc2.print();

        //
        SwiftCode sc = new SwiftCode(filePath);
        List<MTMessage> messages = sc.getMessages();

        for(int nth = 1; nth <= messages.size(); nth ++) {
            MTMessage message = messages.get(nth - 1);
            System.out.println(message);

            BasicHeader block1 = message.getBlock1();
            ApplicationHeader block2 = message.getBlock2();
            UserHeader block3 = message.getBlock3();
            TextHeader block4 = message.getBlock4();
            TrailersHeader block5 = message.getBlock5();
            AnonymousHeader blockS = message.getBlockS();

            // Block 1
            System.out.println("1: " + block1.getContent());
            System.out.println("AppID: " + block1.getAppID());
            System.out.println("ServiceID: " + block1.getServiceID());
            System.out.println("LTAddress: " + block1.getLTAddress());
            System.out.println("SessionNumber: " + block1.getSessionNumber());
            System.out.println("SequenceNumber: " + block1.getSequenceNumber());
            // Block 2
            System.out.println("2: " + block2.getContent());
            System.out.println("InOutID: " + block2.getInOutID());
            System.out.println("SwiftMessageType: " + block2.getSwiftMessageType());
            System.out.println("DestAddress: " + block2.getDestAddress());
            System.out.println("Priority: " + block2.getPriority());
            System.out.println("DeliveryMonitoring: " + block2.getDeliveryMonitor());
            System.out.println("ObsolescencePeriod: " + block2.getObsolescencePeriod());
            System.out.println("InputTime: " + block2.getInputTime());
            System.out.println("MIR(Message Input Reference): " + block2.getMIR());
            System.out.println("InputDate: " + block2.getInputDate());
            System.out.println("SenderLTAddress: " + block2.getSenderLTAddress());
            System.out.println("SessionNumber: " + block2.getSessionNumber());
            System.out.println("SequenceNumber: " + block2.getSequenceNumber());
            System.out.println("OutputDate: " + block2.getOutputDate());
            System.out.println("OutputTime: " + block2.getOutputTime());
            // Block 3
            System.out.println("3: " + block3.getContent());
            String tag1 = "103";
            String tag2 = "108";
            String tag3 = "119";
            System.out.println(tag1 + ":" + block3.findTag(tag1));
            System.out.println(tag2 + ":" + block3.findTag(tag2));
            System.out.println(tag3 + ":" + block3.findTag(tag3));
            // Block 4
            tag1 = "70";
            tag2 = "59";
            tag3 = "ABC";
            int n = 2;
            System.out.println("4: " + block4.getContent());
            System.out.println(tag1 + ":" + block4.findTag(tag1));
            System.out.println(tag1 + "-" + n + ":" + block4.findTag(tag1, n));
            System.out.println(tag2 + ":" + block4.findTag(tag2));
            System.out.println(tag2 + "-" + n + ":" + block4.findTag(tag2, n));
            System.out.println("===================");
            block4.printTag(tag2, n);
            System.out.println("===================");
            System.out.println(tag2 + "-10:" + block4.findTag(tag2, 10));
            System.out.println(tag3 + ":" + block4.findTag(tag3));
            System.out.println(tag3 + "-" + n + ":" + block4.findTag(tag3, n));
            // Block 5
            tag1 = "MAC";
            tag2 = "TNG";
            tag3 = "ABC";
            System.out.println("5: " + block5.getContent());
            System.out.println(tag1 + ":" + block5.findTag(tag1));
            System.out.println(tag2 + ":" + block5.findTag(tag2));
            System.out.println(tag3 + ":" + block5.findTag(tag3));
            // Block S
            tag1 = "SAC";
            tag2 = "MAN";
            tag3 = "ABC";
            System.out.println("S: " + message.getBlockS().getContent());
            System.out.println(tag1 + ":" + blockS.findTag(tag1));
            System.out.println(tag2 + ":" + blockS.findTag(tag2));
            System.out.println(tag3 + ":" + blockS.findTag(tag3));
        }

        System.out.println(messages.size());
    }

}
