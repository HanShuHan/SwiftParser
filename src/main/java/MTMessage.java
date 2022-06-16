import block.structures.*;
import java.util.LinkedHashMap;
import java.util.List;

public class MTMessage extends LinkedHashMap<String, List<String>> {

    public BasicHeader getBlock1() {
        return getBasicHeader();
    }

    public BasicHeader getBasicHeader() {

        List<String> content = get(BasicHeader.BLOCK_IDENTIFIER);
        BasicHeader bh = new BasicHeader();
        bh.setContent(content);
        return bh;
    }

    public ApplicationHeader getBlock2() {
        return getApplicationHeader();
    }

    public ApplicationHeader getApplicationHeader() {

        List<String> content = get(ApplicationHeader.BLOCK_IDENTIFIER);
        ApplicationHeader ah = new ApplicationHeader();
        ah.setContent(content);
        return ah;
    }

    public UserHeader getBlock3() {
        return getUserHeader();
    }

    public UserHeader getUserHeader() {

        List<String> content = get(UserHeader.BLOCK_IDENTIFIER);
        UserHeader uh = new UserHeader();
        uh.setContent(content);
        return uh;
    }

    public TextHeader getBlock4() {
        return getTextHeader();
    }

    public TextHeader getTextHeader() {

        List<String> content = get(TextHeader.BLOCK_IDENTIFIER);
        TextHeader th = new TextHeader();
        th.setContent(content);
        return th;
    }

    public TrailersHeader getBlock5() {
        return getTrailersHeader();
    }

    public TrailersHeader getTrailersHeader() {

        List<String> content = get(TrailersHeader.BLOCK_IDENTIFIER);
        TrailersHeader th = new TrailersHeader();
        th.setContent(content);
        return th;
    }

    public AnonymousHeader getBlockS() {
        return getAnonymousBlock();
    }

    public AnonymousHeader getAnonymousBlock() {

        List<String> content = get(AnonymousHeader.BLOCK_IDENTIFIER);
        AnonymousHeader ah = new AnonymousHeader();
        ah.setContent(content);
        return ah;
    }

}
