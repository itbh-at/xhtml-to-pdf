package at.itbh.xhtmltopdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.stream.Collectors;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.openhtmltopdf.util.XRLog;
import io.quarkus.runtime.Quarkus;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "convert", mixinStandardHelpOptions = true)
public class ConvertCommand implements Runnable {

    @Parameters(paramLabel = "<XHTML file>", description = "The XHTML file to be converted to PDF.")
    File htmlFile;

    @Override
    public void run() {
        PdfRendererBuilder pdfBuilder;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            XRLog.setLoggingEnabled(false);
            pdfBuilder = new PdfRendererBuilder();
            pdfBuilder.useFastMode();
            System.out.println("Rendering " + htmlFile.getName() + " to PDF ...");
            pdfBuilder.withHtmlContent(
                    Files.readAllLines(htmlFile.toPath()).stream().collect(Collectors.joining(" ")),
                    null);
            pdfBuilder.toStream(bos);
            System.out.println("Rendering finished");
            pdfBuilder.run();
            File output = new File(htmlFile.getAbsolutePath().replace(".html", ".pdf"));
            System.out.println("Writing PDF to " + output.getAbsolutePath());
            try (FileOutputStream fos = new FileOutputStream(output)) {
                bos.writeTo(fos);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            Quarkus.asyncExit(1);
        }
    }

}
