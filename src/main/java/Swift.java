import java.io.File;
import java.nio.file.Path;

public class Swift {

    public static SwiftCode parse(Path path)
    {
        return SwiftCode.of(path);
    }

    public static SwiftCode parse(File file)
    {
        return SwiftCode.of(file);
    }

    public static SwiftCode parse(String filepath)
    {
        return SwiftCode.of(filepath);
    }

}
